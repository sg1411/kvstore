package com.example.kvstore.service;

import com.example.kvstore.config.DataExpiryProperties;
import com.example.kvstore.repository.DataDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataExpiryService {

    private final DataExpiryProperties dataExpiryProperties;
    private final KeyValueData keyValueData;

    @Autowired
    public DataExpiryService(DataExpiryProperties dataExpiryProperties, KeyValueData keyValueData) {
        this.dataExpiryProperties = dataExpiryProperties;
        this.keyValueData = keyValueData;
    }

    @Scheduled(fixedDelayString = "#{dataExpiryProperties.getTimeoutMs()}")
    private void run() {
        log.info("Running expiry service");
        DataDao dataDao = new DataDao(dataExpiryProperties.getFilePath(),
                dataExpiryProperties.getTimeoutMs(),
                keyValueData);
        dataDao.removeExpired();
    }
}
