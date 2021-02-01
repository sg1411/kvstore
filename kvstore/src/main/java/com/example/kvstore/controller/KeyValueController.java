package com.example.kvstore.controller;

import com.example.kvstore.service.KeyValueData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;


@RestController
@Slf4j
public class KeyValueController {

    private final KeyValueData keyValueData;

    @Autowired
    public KeyValueController(KeyValueData keyValueData) {
        this.keyValueData = keyValueData;
    }

    /**
     *  Api to get value
     *  e.g. /get/{abc}
     */
    @GetMapping(value = "/get/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> get(@PathVariable(name = "key") String key) {
        try {
            Integer value = keyValueData.getValue(key);
            return ResponseEntity.ok(value);
        } catch (Exception ex) {
            log.error("Error while reading key - {}", key, ex);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Api to get average
     *  e.g. /average
     */
    @GetMapping(value = "/average", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Double> average() {
        try {
            Double average = keyValueData.getAverage();
            return ResponseEntity.ok(average);
        } catch (Exception ex) {
            log.error("Error while reading average", ex);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Api to put value
     *  e.g. /put/{abc}/{1}
     */
    @GetMapping(value = "/put/{key}/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Timestamp> put(@PathVariable(name = "key") String key,
                                  @PathVariable(name = "value") Integer value) {
        try {
            Timestamp timestamp = keyValueData.putValue(key, value);
            return ResponseEntity.ok(timestamp);
        } catch (Exception ex) {
            log.error("Error while putting key - {}, value - {}", key, value, ex);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Api to get expired file
     *  e.g. /expired
     */
    @GetMapping(value = "/expired", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Pair<String, Integer>>> expired() {
        try {
            List<Pair<String, Integer>> expiredEntries = keyValueData.getExpiredEntries();
            return ResponseEntity.ok(expiredEntries);
        } catch (Exception ex) {
            log.error("Error while reading expired entries", ex);
            return ResponseEntity.notFound().build();
        }
    }
}
