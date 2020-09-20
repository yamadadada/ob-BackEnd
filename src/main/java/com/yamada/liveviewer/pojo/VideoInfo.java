package com.yamada.liveviewer.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class VideoInfo implements Serializable {

    private String bvid;

    private Integer aid;

    private String title;

    private String pic;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pubdate;

    private String desc;

    private String owner;
}
