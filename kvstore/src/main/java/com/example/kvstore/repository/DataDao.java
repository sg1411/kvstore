package com.example.kvstore.repository;

import com.example.kvstore.service.KeyValueData;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class DataDao {

    private final String expiryFilePath;
    private final Long dataTimeOutMs;
    private final KeyValueData keyValueData;

    public DataDao(String expiryFilePath, String dataTimeOutMs, KeyValueData keyValueData) {
        this.expiryFilePath = expiryFilePath;
        this.dataTimeOutMs = Long.valueOf(dataTimeOutMs);
        this.keyValueData = keyValueData;
    }

    public void removeExpired() {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        Long currentMs = current.getTime();

        Map<String, Pair<Integer, Timestamp>> toRemove = new HashMap<>();
        for (Map.Entry<String, Pair<Integer, Timestamp>> entry: keyValueData.getData().entrySet()) {
            Timestamp keyTimeStamp = entry.getValue().getRight();
            Long keyMs = keyTimeStamp.getTime();
            if (currentMs - dataTimeOutMs >= keyMs) {
                toRemove.put(entry.getKey(), entry.getValue());
            }
        }

        removeFromDataStore(toRemove);
        writeToFile(toRemove);
    }

    private void removeFromDataStore(Map<String, Pair<Integer, Timestamp>> toRemove) {
        for (Map.Entry<String, Pair<Integer, Timestamp>> entry: toRemove.entrySet()) {
            keyValueData.removeKey(entry.getKey());
        }
    }

    // not writing in file currently
    private void writeToFile(Map<String, Pair<Integer, Timestamp>> toRemove) {
        for (Map.Entry<String, Pair<Integer, Timestamp>> entry: toRemove.entrySet()) {
            keyValueData.saveExpiredEntry(entry.getKey(), entry.getValue().getLeft());
        }
    }
}
