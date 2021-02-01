package com.example.kvstore.service;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
public class KeyValueData {

    private final ConcurrentHashMap<String, Pair<Integer, Timestamp>> data = new ConcurrentHashMap<>();
    private Integer size = 0;
    private Integer sum = 0;

    private final List<Pair<String, Integer>> expiredEntries = new ArrayList<>();

    public Integer getValue(String key) {
        if (data.containsKey(key)) {
            return data.get(key).getLeft();
        }
        return null;
    }

    public Double getAverage() {
        if (size == 0) return 0.0;
        return Double.valueOf(sum) / Double.valueOf(size);
    }

    public Timestamp putValue(String key, Integer value) {
        removeKey(key);
        size++;
        sum += value;

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        data.put(key, Pair.of(value, timestamp));
        return timestamp;
    }

    public Boolean removeKey(String key) {
        if (data.containsKey(key)) {
            size--;
            sum -= data.get(key).getLeft();
            data.remove(key);
            return true;
        }
        return false;
    }

    public void saveExpiredEntry(String key, Integer value) {
        expiredEntries.add(Pair.of(key, value));
    }
}
