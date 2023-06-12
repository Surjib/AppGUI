package com.example.appgui;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


public class TableContent {
    private Integer id;

    private Integer smpCnt;

    private String timestamp;

    public TableContent(Integer id, Integer smpCnt, String timestamp) {
        this.id = id;
        this.smpCnt = smpCnt;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSmpCnt() {
        return smpCnt;
    }

    public void setSmpCnt(Integer smpCnt) {
        this.smpCnt = smpCnt;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
