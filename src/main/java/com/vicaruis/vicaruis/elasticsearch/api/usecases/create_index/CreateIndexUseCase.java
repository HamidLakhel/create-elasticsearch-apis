package com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index;

import com.vicaruis.vicaruis.core.models.UseCase;
import com.vicaruis.vicaruis.elasticsearch.business.ElasticSearchService;
import org.springframework.stereotype.Service;

@Service
public class CreateIndexUseCase implements UseCase<CreateIndexRequestDto, CreateIndexResponseDto> {

    ElasticSearchService elasticSearchService;

    public CreateIndexUseCase(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

        @Override
        public CreateIndexResponseDto execute(CreateIndexRequestDto requestDto) {
            return elasticSearchService.createIndex(requestDto);
        }
}
