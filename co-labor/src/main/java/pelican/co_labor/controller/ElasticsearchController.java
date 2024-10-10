package pelican.co_labor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pelican.co_labor.service.ElasticsearchService;
import pelican.co_labor.service.JsonLoader;
import pelican.co_labor.dto.CaseDocument;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/elasticsearch")
public class ElasticsearchController {

    private final ElasticsearchService elasticsearchService;

    @Autowired
    public ElasticsearchController(ElasticsearchService elasticsearchService) {
        this.elasticsearchService = elasticsearchService;
    }

    @PostMapping("/bulk-index-directory")
    public ResponseEntity<String> bulkIndexFromDirectory(
            @RequestParam String directoryPath) {
        try {
            // 디렉토리에서 모든 JSON 읽기
            List<CaseDocument> documents = JsonLoader.loadJsonFromDirectory(directoryPath);
            String indexName = "your_index_name"; // 적절한 인덱스 이름으로 변경
            elasticsearchService.bulkIndexDocuments(indexName, documents);
            return ResponseEntity.ok("Batch indexing from directory completed.");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error during bulk indexing: " + e.getMessage());
        }
    }
}
