package com.example.kvstore.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "data")
public class DataExpiryProperties {

    private String timeoutMs;
    private String filePath;
}
