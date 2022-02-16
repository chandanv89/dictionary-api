package com.github.chandanv89.dictionary.api.filters;

import com.github.chandanv89.dictionary.api.model.Filter;

public interface WordFilter<R> {

    R applyFilter(R source, Filter predicate);
}
