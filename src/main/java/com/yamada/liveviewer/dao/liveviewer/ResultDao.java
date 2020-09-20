package com.yamada.liveviewer.dao.liveviewer;

import com.yamada.liveviewer.pojo.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResultDao {

    @Select("SELECT total, time FROM `result` where gid=#{gid}")
    List<Result> getAllByGid(@Param("gid") Integer gid);
}
