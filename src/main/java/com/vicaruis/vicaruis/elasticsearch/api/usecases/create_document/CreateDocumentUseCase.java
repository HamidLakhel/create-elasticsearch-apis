package com.vicaruis.vicaruis.elasticsearch.api.usecases.create_document;

import com.vicaruis.vicaruis.core.models.UseCase;
import com.vicaruis.vicaruis.elasticsearch.business.ElasticSearchService;
import org.springframework.stereotype.Service;
@Service
public class CreateDocumentUseCase implements UseCase<CreateDocumentRequestDto, CreateDocumentResponseDto> {

    ElasticSearchService elasticSearchService;

    public CreateDocumentUseCase(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }
    @Override
    public CreateDocumentResponseDto execute(CreateDocumentRequestDto requestDto) {
        return elasticSearchService.createDocument(requestDto);
    }
}
