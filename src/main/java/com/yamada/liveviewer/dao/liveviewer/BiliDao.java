package com.yamada.liveviewer.dao.liveviewer;

import com.yamada.liveviewer.pojo.BiliData;
import com.yamada.liveviewer.pojo.BiliUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BiliDao {

    @Select("select uid, `name`, time date, fans from bili_data where uid=#{uid} order by time")
    List<BiliData> getByUid(Integer uid);

    @Select("select uid, time date, fans from bili_data_short " +
            "where uid=#{uid} and time >= #{start} and time <= #{end} " +
            "order by time")
    List<BiliData> getShortByUid(@Param("uid") Integer uid, @Param("start") String start, @Param("end") String end);

    @Select("SELECT * from bili_user")
    List<BiliUser> getUserList();
}
