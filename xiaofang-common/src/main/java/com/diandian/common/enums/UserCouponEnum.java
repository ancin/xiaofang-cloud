package com.diandian.common.enums;

/**
 * @author shengxiaohua
 * @Description: 用户优惠券枚举
 * @create 2020-03-23 13:48
 * @last modify by [shengxiaohua 2020-03-23 13:48]
 **/
public class UserCouponEnum {
    public enum UseScope{
        USE_SCOPE_COLUMN("1","专栏"),
        USE_SCOPE_ALL("ALL","全场通用");
        private String value;
        private String desc;
        UseScope(String value,String desc){
           this.value = value;
           this.desc = desc;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum Status {
        STATUS_UNUSE(0,"未使用"),
        STATUS_USE(1,"已使用");
        private Integer value;
        private String desc;

        Status(int value,String desc){
            this.value = value;
            this.desc = desc;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
