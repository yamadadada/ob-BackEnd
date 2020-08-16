package com.yamada.liveviewer.dao;

import com.yamada.liveviewer.pojo.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogDao {

    @Select("select * from log order by `time` desc")
    List<Log> getLog();
}
