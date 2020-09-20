package com.yamada.liveviewer.dao.fdgirls;

import com.yamada.liveviewer.pojo.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VideoDao {

    @Insert("insert into video values(#{video.avId}, #{video.title}, #{video.time}, #{video.view}, #{video.liudao}, " +
            "#{video.pipi}, #{video.tuotuo}, #{video.momo}, #{video.guonong}, #{video.hanxiaomu}, #{video.cucu}, " +
            "#{video.yaya}, #{video.total}, #{video.status}, #{video.bv})")
    int insert(@Param("video") Video video);
}
