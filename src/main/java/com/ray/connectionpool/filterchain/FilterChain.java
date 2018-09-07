package com.ray.connectionpool.filterchain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 过滤器链
 */
public class FilterChain implements Filter {

    private List<Filter> filters= Lists.newArrayList();

    public FilterChain addFilter(Filter filter){
        filters.add(filter);
        return this;
    }

    @Override
    public String doFilter(String content) {
        for(Filter filter : filters){
            content = filter.doFilter(content);
        }
        return content;
    }
}
