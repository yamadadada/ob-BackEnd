package com.yamada.liveviewer.service.impl;

import com.yamada.liveviewer.dao.liveviewer.GameDao;
import com.yamada.liveviewer.dao.liveviewer.ResultDao;
import com.yamada.liveviewer.pojo.Result;
import com.yamada.liveviewer.service.GameService;
import com.yamada.liveviewer.vo.GameDetailVO;
import com.yamada.liveviewer.vo.GameVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Resource
    private GameDao gameDao;

    @Resource
    private ResultDao resultDao;

    @Override
    @Cacheable(value = "gameRank", key = "#page")
    public List<GameVO> getRank(int page) {
        List<GameVO> gameVOList = gameDao.getGameRank();
        for (GameVO gameVO : gameVOList) {
            String huya = gameVO.getHuya();
            if (!StringUtils.isEmpty(huya)) {
                gameVO.setHuya("https://images.weserv.nl/?url=" + huya);
            }
        }
        return gameVOList;
    }

    @Override
    public GameDetailVO getDetailByGid(Integer gid) {
        List<Result> resultList = resultDao.getAllByGid(gid);

        SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-M-d HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM-dd");
        List<String> allX = new ArrayList<>();
        List<Integer> allY = new ArrayList<>();
        List<String> dayX = new ArrayList<>();
        List<Integer> dayY = new ArrayList<>();

        String temp = "";
        double total = 0.0;
        int count = 0;
        for (Result result: resultList) {
            String time1 = sdf1.format(result.getTime());
            String time2 = sdf2.format(result.getTime());
            allX.add(time1);
            allY.add(new BigDecimal(result.getTotal()).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
            if (!temp.equals(time2)) {
                if (!temp.equals("")) {
                    dayX.add(temp);
                    dayY.add(new BigDecimal(total / count).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
                }
                temp = time2;
                total = 0.0;
                count = 0;
            }
            total += result.getTotal();
            count++;
        }
        dayX.add(temp);
        dayY.add(new BigDecimal(total / count).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());

        GameDetailVO gameDetailVO = new GameDetailVO();
        gameDetailVO.setAllX(allX);
        gameDetailVO.setAllY(allY);
        gameDetailVO.setDayX(dayX);
        gameDetailVO.setDayY(dayY);
        return gameDetailVO;
    }

    @Override
    @CacheEvict(value = "gameRank", allEntries = true)
    public void deleteGameRank() {
        System.out.println("删除所有游戏排名");
    }
}
