package com.yamada.liveviewer.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BiliChangeVO implements Serializable {

    private List<String> timeList;

    private List<Integer> valueList;
}
