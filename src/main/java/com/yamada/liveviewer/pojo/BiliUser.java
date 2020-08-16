package com.yamada.liveviewer.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BiliUser implements Serializable {

    private Integer id;

    private Integer uid;

    private String name;
}
