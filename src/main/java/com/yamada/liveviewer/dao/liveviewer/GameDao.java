package com.yamada.liveviewer.dao.liveviewer;

import com.yamada.liveviewer.vo.GameVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GameDao {

    /**
     * 获得游戏观看人数排行榜
     * @return
     */
    @Select("select init.gid, init.game, result.total, init.huya " +
            "from result left join init " +
            "on result.gid = init.gid " +
            "where DATE_FORMAT(result.time, '%Y-%m-%d %H') = DATE_FORMAT(NOW() - interval 5 minute, '%Y-%m-%d %H') " +
            "order by total desc")
    List<GameVO> getGameRank();
}
