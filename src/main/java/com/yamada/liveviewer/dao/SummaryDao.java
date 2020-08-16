package com.yamada.liveviewer.dao;

import com.yamada.liveviewer.pojo.Summary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SummaryDao {

    /**
     * 获取最近24小时数据
     * @return
     */
    @Select("SELECT id,total,time FROM `summary` where time >= DATE_FORMAT(NOW(), '%Y-%m-%d %H') - interval 23 hour " +
            "order by id")
    List<Summary> getReal24HoursTotal();

    /**
     * 获取24小时前的数据
     * @return
     */
    @Select("SELECT total FROM `summary` where time > DATE_FORMAT(NOW(), '%Y-%m-%d %H') - interval 24 hour and " +
            "time < DATE_FORMAT(NOW(), '%Y-%m-%d %H') - interval 23 hour")
    Double get24HoursBefore();

    /**
     * 获取最近一个月的最大值（30天）
     * @return
     */
    @Select("SELECT MAX(total) FROM `summary` where time >= DATE_FORMAT(NOW(), '%Y-%m-%d %H') - interval 30 day")
    Double getMonthMax();

    /**
     * 获取上周同一时间的数据
     * @return
     */
    @Select("SELECT total FROM `summary` where time > DATE_FORMAT(NOW(), '%Y-%m-%d %H') - interval 168 hour and " +
            "time < DATE_FORMAT(NOW(), '%Y-%m-%d %H') - interval 167 hour")
    Double getWeekBefore();
}
