package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;

/**
 * Created by zl
 * 2020/05/14
 * 用于阿里云上传信息返回
 */
public class AliOssBean implements Serializable {
    /**
     * 端点
     */
    private String endpoint;
    /**
     * id
     */
    private String accessKeyId;
    /**
     * 密钥
     */
    private String accessKeySecret;
    /**
     * 仓库名
     */
    private String bucketName;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
