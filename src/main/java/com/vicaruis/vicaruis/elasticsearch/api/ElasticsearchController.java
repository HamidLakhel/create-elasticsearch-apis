package com.vicaruis.vicaruis.elasticsearch.api;


import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document.CreateDocumentRequestDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document.CreateDocumentResponseDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document.CreateDocumentUseCase;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index.CreateIndexRequestDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index.CreateIndexResponseDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index.CreateIndexUseCase;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.get_document_by_id.GetDocumentByIdRequestDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.get_document_by_id.GetDocumentByIdResponseDto;
import com.vicaruis.vicaruis.elasticsearch.api.usecases.get_document_by_id.GetDocumentByIdUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elasticsearch")
public class ElasticsearchController {

    CreateDocumentUseCase createDocumentUseCase;
    CreateIndexUseCase createIndexUseCase;
    GetDocumentByIdUseCase getDocumentByIdUseCase;

    public ElasticsearchController(CreateDocumentUseCase createDocumentUseCase, CreateIndexUseCase createIndexUseCase, GetDocumentByIdUseCase getDocumentByIdUseCase) {
        this.createDocumentUseCase = createDocumentUseCase;
        this.createIndexUseCase = createIndexUseCase;
        this.getDocumentByIdUseCase = getDocumentByIdUseCase;
    }

    @PostMapping("/index")
    public CreateIndexResponseDto addIndex(@Valid @RequestBody CreateIndexRequestDto properties) {
        return this.createIndexUseCase.execute(properties);
    }

    @PostMapping("/document")
    public CreateDocumentResponseDto addDocument(@Valid @RequestBody CreateDocumentRequestDto document) {
         return createDocumentUseCase.execute(document);
    }

    @GetMapping("/document/{id}")
    public GetDocumentByIdResponseDto getDocumentById(@RequestParam String indexName, @PathVariable String id) {
        return this.getDocumentByIdUseCase.execute(new GetDocumentByIdRequestDto(indexName,id));
    }
}
