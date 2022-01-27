package com.github.chandanv89.dictionaryservice.filters;

import com.github.chandanv89.dictionaryservice.model.Filter;

public interface WordFilter<R> {

    R applyFilter(R source, Filter predicate);
}
