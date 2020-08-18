/**
 * ant.com Inc.
 * Copyright (c) 2013-2019 All Rights Reserved.
 */
package com.diandian.common.dto;

import java.io.Serializable;

/**
 * @author ancin
 * @create 2019-05-13 16:26
 **/
public class UploadFileResponse implements Serializable {
    private String id;
    private String url;
    private String store;
    private String objType;
    private String originalName;
    private long byteLength;
    private String byteToStr;
    private String pdfUrl;

    public String getByteToStr() {
        return byteToStr;
    }

    public void setByteToStr(String byteToStr) {
        this.byteToStr = byteToStr;
    }

    public long getByteLength() {
        return byteLength;
    }

    public void setByteLength(long byteLength) {
        this.byteLength = byteLength;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
    public String getPdfUrl() {
        return this.pdfUrl;
    }

    @Override
    public String toString(){
        return "{id:"+id+",store:"+store+",url:"+url+",objType:"+objType+",originalName:"+originalName+",byteLength:"+byteLength+",byteToStr:"+byteToStr+",pdfUrl:"+pdfUrl+"}";
    }


}
