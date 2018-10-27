package com.pingye.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 * @author huping
 * @create 2018-08-06 14:33
 **/
@Data
@RefreshScope
@Component
@ConfigurationProperties(prefix = "ueditor.upload")

public class UploadConfig {

    @Value("${ueditor.upload.filepath}")
    private String filepath;


    @Value("${ueditor.upload.staticFilePath}")
    private String staticFilePath;

}
