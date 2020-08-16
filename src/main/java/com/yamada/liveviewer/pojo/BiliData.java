package com.yamada.liveviewer.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class BiliData {

    private Integer uid;

    private String name;

    private Date date;

    private Integer fans;
}
