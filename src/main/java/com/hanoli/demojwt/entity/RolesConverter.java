package com.hanoli.demojwt.entity;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter(autoApply = false)
public class RolesConverter implements AttributeConverter<Set<String>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "[]";
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            return "[]";
        }
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty() || dbData.equals("[]")) {
            return Set.of();
        }
        try {
            String[] roles = objectMapper.readValue(dbData, String[].class);
            return Arrays.stream(roles).collect(Collectors.toSet());
        } catch (Exception e) {
            return Set.of();
        }
    }
}

