package com.ray.connectionpool.filterchain;

/**
 *
 * 过滤接口
 */
public interface Filter {
    String doFilter(String content);
}
