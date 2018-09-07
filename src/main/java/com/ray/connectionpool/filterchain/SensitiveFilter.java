package com.ray.connectionpool.filterchain;

public class SensitiveFilter implements Filter {
    @Override
    public String doFilter(String content) {
        return content
                .replace("法伦功", "flg")
                .replace("政府", "zf");
    }
}
