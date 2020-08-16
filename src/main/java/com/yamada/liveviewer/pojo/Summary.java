package com.yamada.liveviewer.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Summary {

    private Long id;

    private Date time;

    private Double total;

    private Double biliTotal;

    private Double douyuTotal;

    private Double huyaTotal;

    private Integer gameCount;

    private Double travelTime;
}
