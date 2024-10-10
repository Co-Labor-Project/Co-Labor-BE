package pelican.co_labor.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pelican.co_labor.dto.CaseDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticsearchService {

    private final ElasticsearchClient esClient;
    private static final int BATCH_SIZE = 32;

    @Autowired
    public ElasticsearchService(ElasticsearchClient esClient) {
        this.esClient = esClient;
    }

    public void bulkIndexDocuments(String indexName, List<CaseDocument> documents) throws IOException {
        List<CaseDocument> filteredDocuments = filterAndPrepareDocuments(documents);
        int totalDocuments = filteredDocuments.size();
        int batches = (int) Math.ceil((double) totalDocuments / BATCH_SIZE);

        for (int i = 0; i < batches; i++) {
            int fromIndex = i * BATCH_SIZE;
            int toIndex = Math.min(fromIndex + BATCH_SIZE, totalDocuments);
            List<CaseDocument> batch = filteredDocuments.subList(fromIndex, toIndex);

            bulkIndexBatch(indexName, batch);
        }
    }

    private List<CaseDocument> filterAndPrepareDocuments(List<CaseDocument> documents) {
        List<CaseDocument> filteredDocuments = new ArrayList<>();

        for (CaseDocument document : documents) {
            CaseDocument filteredDocument = new CaseDocument();
            filteredDocument.set사건명(document.get사건명() != null ? document.get사건명() : "기본 사건명");
            filteredDocument.set사건종류명(document.get사건종류명() != null ? document.get사건종류명() : "기본 사건종류명");
            filteredDocument.set판시사항(document.get판시사항() != null ? document.get판시사항() : "기본 판시사항");
            filteredDocument.set판결요지(document.get판결요지() != null ? document.get판결요지() : "기본 판결요지");
            filteredDocument.set참조조문(document.get참조조문() != null ? document.get참조조문() : "기본 참조조문");
            filteredDocument.set사건번호(document.get사건번호() != null ? document.get사건번호() : "기본 사건번호");

            filteredDocuments.add(filteredDocument);
        }

        return filteredDocuments;
    }

    private void bulkIndexBatch(String indexName, List<CaseDocument> batch) throws IOException {
        BulkRequest.Builder br = new BulkRequest.Builder();

        for (CaseDocument document : batch) {
            String documentId = document.generateCaseDocumentId(); // 사건번호를 사용하여 ID 생성
            br.operations(op -> op.index(idx -> idx.index(indexName).id(documentId).document(document)));
        }

        BulkResponse result = esClient.bulk(br.build());

        if (result.errors()) {
            List<String> errorMessages = new ArrayList<>();
            for (BulkResponseItem item : result.items()) {
                if (item.error() != null) {
                    errorMessages.add(item.error().reason());
                }
            }
            throw new RuntimeException("Bulk indexing failed: " + String.join(", ", errorMessages));
        }
    }
}
