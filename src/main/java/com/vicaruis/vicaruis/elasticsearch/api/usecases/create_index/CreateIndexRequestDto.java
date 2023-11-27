package com.vicaruis.vicaruis.elasticsearch.api.usecases.create_index;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class CreateIndexRequestDto {
    @NotEmpty(message = "Index name cannot be empty")
    @Pattern(regexp = "^(?![-_+\\\\.]{1})[a-z0-9]+$", message = "Index name is invalid")
    String name;

    Map<String, Object> mappings;

    Map<String, Object> settings;

    Map<String, Object> aliases;
}
