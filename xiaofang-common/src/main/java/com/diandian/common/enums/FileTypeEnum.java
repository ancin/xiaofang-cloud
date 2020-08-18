package com.diandian.common.enums;

import com.diandian.common.exception.BusinessException;

/**
 * @author shengxiaohua
 * @Description: 文件类型枚举
 * @create 2019-12-22 13:54
 * @last modify by [shengxiaohua 2019-12-22 13:54]
 **/
public enum FileTypeEnum {
    FILE_TYPE_PIC(1,"图片","pic"),
    FILE_TYPE_MUSIC(2,"音乐","music"),
    FILE_TYPE_VIDEO(3,"视频","video"),
    FILE_TYPE_DOC(4,"文档","doc");

    private int value;
    private String desc;
    private String name;

    FileTypeEnum(int value,String desc,String name){
       this.value = value;
       this.desc = desc;
       this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static FileTypeEnum getFileTypeEnumBySuffix(String suffix){
        if("bmp".equals(suffix) || "jpg".equals(suffix) || "jpeg".equals(suffix)
                ||  "png".equals(suffix)){
            return FILE_TYPE_PIC;
        }else if("mp3".equals(suffix) || "wma".equals(suffix) || "wav".equals(suffix)){
            return FILE_TYPE_MUSIC;
        }else if("rmvb".equals(suffix) || "avi".equals(suffix) | "mp4".equals(suffix)){
            return FILE_TYPE_VIDEO;
        }else if("doc".equals(suffix) || "docx".equals(suffix) ||
                "xlsx".equals(suffix) || "xls".equals(suffix)
                || "pptx".equals(suffix) || "txt".equals(suffix) || "ppt".equals(suffix)
                || "pdf".equals(suffix)){
            return FILE_TYPE_DOC;
        }else{
            throw new BusinessException("上传文件类型不支持","");
        }
    }
}
