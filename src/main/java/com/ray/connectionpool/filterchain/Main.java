package com.ray.connectionpool.filterchain;

public class Main {
    public static void main(String[] args) {
        String content = "<scrpit> 法伦功一定要灭掉，尼玛的，你妈的。中国政府真的太好了，呵呵，呵呵";
        //新建一个『过滤链条』
        FilterChain filterChain = new FilterChain();
        // 在过滤链条中添加过滤规则
        filterChain.addFilter(new FuckFilter()).addFilter(new SensitiveFilter());
        String filterContent=filterChain.doFilter(content);
        //输出内容
        System.out.print(filterContent);
    }
}
