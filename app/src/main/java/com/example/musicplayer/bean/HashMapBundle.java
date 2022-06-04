package com.example.musicplayer.bean;

import java.io.Serializable;
import java.util.Map;

public class HashMapBundle implements Serializable {
    private Map<String, Object> hashMap;

    public HashMapBundle(Map<String, Object> hashMap) {
        this.hashMap = hashMap;
    }

    public void setHashMap(Map<String, Object> hashMap) {
        this.hashMap = hashMap;
    }

    public Map<String, Object> getHashMap() {
        return hashMap;
    }
}
