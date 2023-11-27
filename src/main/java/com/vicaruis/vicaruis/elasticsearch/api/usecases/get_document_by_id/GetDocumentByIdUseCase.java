package com.vicaruis.vicaruis.elasticsearch.api.usecases.get_document_by_id;

import com.vicaruis.vicaruis.core.models.UseCase;
import com.vicaruis.vicaruis.elasticsearch.business.ElasticSearchService;
import org.springframework.stereotype.Service;

@Service
public class GetDocumentByIdUseCase implements UseCase<GetDocumentByIdRequestDto, GetDocumentByIdResponseDto> {

    ElasticSearchService elasticSearchService;

    public GetDocumentByIdUseCase(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @Override
    public GetDocumentByIdResponseDto execute(GetDocumentByIdRequestDto request) {
        return this.elasticSearchService.getDocumentById(request.getIndexName(), request.getId());
    }
}
