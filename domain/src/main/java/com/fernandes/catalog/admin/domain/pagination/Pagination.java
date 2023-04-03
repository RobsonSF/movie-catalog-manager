package com.fernandes.catalog.admin.domain.pagination;

import java.util.List;

public record Pagination<T>(
        int currentPage,
        int perPage,
        long Total,
        List<T> items
) {}
