package com.ray.project.kit;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Project: o2o-orderdiscount
 * Package: com.jd.o2o.orderdiscount.domain.enums
 * User: @CZN 2018/2/7 14:55
 * Use: 促销活动内外部状态
 */
public enum InfoStateEnum {
    /*内部活动状态*/
    NEW(1, "新建"),
    PASS(2, "审核通过"),
    REJECT(3, "驳回"),
    RUNNING(4, "进行中"),
    GAME_OVER(5, "已结束"),
    CANCELED(6, "已取消"),
    PENDING(7, "待审核"),

    NULL(-1, "空");

    /*返回外部活动状态*/
    public static InfoStateWebVoEnum getWebVoInfoState(Integer num) {
        InfoStateEnum infoStateEnum = null;
        for (InfoStateEnum stateEnum : InfoStateEnum.values()) {
            if (stateEnum.getNum().intValue() == num.intValue()) {
                infoStateEnum = stateEnum;
                break;
            }
        }

        if (null != infoStateEnum) {
            switch (infoStateEnum) {
                case NEW:
                case PASS:
                case REJECT:
                case PENDING:
                    return InfoStateWebVoEnum.WAITING_START;
                case RUNNING:
                    return InfoStateWebVoEnum.RUNNING;
                case GAME_OVER:
                    return InfoStateWebVoEnum.GAME_OVER;
                case CANCELED:
                    return InfoStateWebVoEnum.CANCELED;
            }
        }
        return null;
    }

    public static String getInfoStateEnumDesc(Integer num) {
        String desc = "不详";
        for (InfoStateEnum stateEnum : InfoStateEnum.values()) {
            if (stateEnum.getNum().intValue() == num.intValue()) {
                desc = stateEnum.getMsg();
                break;
            }
        }
        return desc;
    }

    /*返回活动内部活动状态*/
    public static List<Integer> getInnerInfoStateByWebVoState(Integer code) {
        InfoStateWebVoEnum infoStateEnum = null;
        for (InfoStateWebVoEnum webVoEnum : InfoStateWebVoEnum.values()) {
            if (webVoEnum.getCode().intValue() == code.intValue()) {
                infoStateEnum = webVoEnum;
                break;
            }
        }

        if (null != infoStateEnum) {
            switch (infoStateEnum) {
                case WAITING_START:
                    return Arrays.asList(1, 2, 3, 7);
                case RUNNING:
                    return Arrays.asList(4);
                case GAME_OVER:
                    return Arrays.asList(5);
                case CANCELED:
                    return Arrays.asList(6);
                case ALL:
                    return Arrays.asList(1, 2, 3, 7, 4, 5, 6);
            }
        }
        return null;
    }

    /*外部活动状态codes*/
    public static List<Integer> getInfoStateWebVos() {
        List<Integer> lists = Lists.newArrayList();
        for (InfoStateWebVoEnum webVoEnum : InfoStateWebVoEnum.values()) {
            lists.add(webVoEnum.getCode());
        }
        return lists;
    }

    /*外部活动状态*/
    public enum InfoStateWebVoEnum {
        WAITING_START(101, "待开始"),
        RUNNING(102, "进行中"),
        GAME_OVER(103, "已结束"),
        CANCELED(104, "已取消"),
        ALL(-1, "全部");

        private Integer code;
        private String voMsg;

        InfoStateWebVoEnum(Integer code, String voMsg) {
            this.code = code;
            this.voMsg = voMsg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getVoMsg() {
            return voMsg;
        }

        public void setVoMsg(String voMsg) {
            this.voMsg = voMsg;
        }
    }

    private Integer num;
    private String msg;

    InfoStateEnum(Integer num, String msg) {
        this.num = num;
        this.msg = msg;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
