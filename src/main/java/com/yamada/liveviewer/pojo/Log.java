package com.yamada.liveviewer.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Log {

    private Integer id;

    private String comment;

    private String version;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone="GMT+8")
    private Date time;
}
