package pelican.co_labor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pelican.co_labor.dto.CaseDocument; // CaseDocument는 필드에 맞춰 정의되어야 합니다.

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<CaseDocument> loadJsonFromDirectory(String directoryPath) throws IOException {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".json"));

        List<CaseDocument> documents = new ArrayList<>();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                CaseDocument document = loadJsonAsEntity(file);
                documents.add(document);
            }
        }

        return documents;
    }

    private static CaseDocument loadJsonAsEntity(File file) throws IOException {
        return objectMapper.readValue(file, CaseDocument.class);
    }
}
