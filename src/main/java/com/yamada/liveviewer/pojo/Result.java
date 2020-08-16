package com.yamada.liveviewer.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Result {

    private Integer id;

    private Integer gid;

    private String gameName;

    private Double total;

    private Double bilibili;

    private Double douyu;

    private Double huya;

    private Date time;
}
