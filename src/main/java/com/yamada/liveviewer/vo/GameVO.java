package com.yamada.liveviewer.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameVO implements Serializable {

    private Integer gid;

    private String game;

    private Double total;

    private String huya;
}
