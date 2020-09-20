package com.yamada.liveviewer.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yamada.liveviewer.dao.fdgirls.VideoDao;
import com.yamada.liveviewer.form.FDGForm;
import com.yamada.liveviewer.pojo.Video;
import com.yamada.liveviewer.pojo.VideoInfo;
import com.yamada.liveviewer.service.FDGService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class FDGServiceImpl implements FDGService {

    @Resource
    private VideoDao videoDao;

    @Override
    public VideoInfo getVideoInfo(String bv) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("https://api.bilibili.com/x/web-interface/view?bvid=" + bv.substring(2), String.class);
        if (response == null) {
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(response);
        Integer code = jsonObject.getInteger("code");
        if (code == null || code != 0) {
            return null;
        }
        JSONObject data = jsonObject.getJSONObject("data");
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setBvid(data.getString("bvid"));
        videoInfo.setAid(data.getInteger("aid"));
        videoInfo.setTitle(data.getString("title"));
        videoInfo.setPic("https://images.weserv.nl/?url=" + data.getString("pic"));
        videoInfo.setDesc(data.getString("desc"));
        videoInfo.setOwner(data.getJSONObject("owner").getString("name"));
        videoInfo.setPubdate(new Date(data.getLong("pubdate") * 1000));
        return videoInfo;
    }

    @Override
    public int addVideo(FDGForm form, VideoInfo videoInfo) {
        Video video = new Video();
        video.setAvId(videoInfo.getAid());
        video.setTitle(videoInfo.getTitle());
        video.setTime(videoInfo.getPubdate());
        video.setView(0);
        if (form.getLiudao()) {
            video.setLiudao(0);
        }
        if (form.getPipi()) {
            video.setPipi(0);
        }
        if (form.getTuotuo()) {
            video.setTuotuo(0);
        }
        if (form.getMomo()) {
            video.setMomo(0);
        }
        if (form.getGuonong()) {
            video.setGuonong(0);
        }
        if (form.getHanxiaomu()) {
            video.setHanxiaomu(0);
        }
        if (form.getCucu()) {
            video.setCucu(0);
        }
        if (form.getYaya()) {
            video.setYaya(0);
        }
        video.setTotal(0);
        video.setStatus(0);
        video.setBv(videoInfo.getBvid().substring(2));
        return videoDao.insert(video);
    }
}
