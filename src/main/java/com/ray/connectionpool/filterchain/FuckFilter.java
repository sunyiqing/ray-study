package com.ray.connectionpool.filterchain;

/**
 * 具体过滤实现
 */
public class FuckFilter implements Filter {
    @Override
    public String doFilter(String content) {
        return content.replace("尼玛", "xx")
                .replace("你妈", "xx");
    }
}
