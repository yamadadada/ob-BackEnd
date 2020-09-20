package com.yamada.liveviewer.service;

import com.yamada.liveviewer.form.FDGForm;
import com.yamada.liveviewer.pojo.VideoInfo;

public interface FDGService {

    VideoInfo getVideoInfo(String bv);

    int addVideo(FDGForm form, VideoInfo videoInfo);
}
