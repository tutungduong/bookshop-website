package com.bookshop.utils;

import io.github.perplexhub.rsql.RSQLJPASupport;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class SearchUtils {

    public static <T> Specification<T> parse(String search, List<String> searchFields) {
        if (search == null || search.isBlank() || searchFields == null || searchFields.isEmpty()) {
            return Specification.where(null);
        }

        String rsqlQuery = searchFields.stream()
                .map(field -> field + "=like='" + search.trim() + "'")
                .collect(Collectors.joining(","));

        return RSQLJPASupport.toSpecification(rsqlQuery);
    }

}