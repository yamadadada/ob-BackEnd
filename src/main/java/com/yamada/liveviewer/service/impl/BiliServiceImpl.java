package com.yamada.liveviewer.service.impl;

import com.yamada.liveviewer.dao.liveviewer.BiliDao;
import com.yamada.liveviewer.dao.liveviewer.LogDao;
import com.yamada.liveviewer.pojo.BiliData;
import com.yamada.liveviewer.pojo.BiliUser;
import com.yamada.liveviewer.pojo.Log;
import com.yamada.liveviewer.service.BiliService;
import com.yamada.liveviewer.vo.BiliChangeVO;
import com.yamada.liveviewer.vo.BiliFanVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BiliServiceImpl implements BiliService {

    @Resource
    private BiliDao biliDao;

    @Resource
    private LogDao logDao;

    /**
     * 获取每日0点数据
     * @param uid
     * @return
     */
    @Override
    @Cacheable(value = "dayFans", key = "#uid")
    public BiliFanVO getByUid(Integer uid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        List<BiliData> biliDataList = biliDao.getByUid(uid);
        return getFanVO(biliDataList, sdf, 86400000, new Date().getTime());
    }

    /**
     * 获取每十分钟数据
     * @param uid
     * @param start
     * @param end
     * @return
     */
    @Override
    public BiliFanVO getShortByUid(Integer uid, Long start, Long end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d H:mm");
        List<BiliData> biliDataList = biliDao.getShortByUid(uid, sdf.format(start), sdf.format(end));
        return getFanVO(biliDataList, sdf, 600000, end);
    }

    /**
     * 获取每日的粉丝变化数据
     * @param uid
     * @return
     */
    @Override
    @Cacheable(value = "changeList", key = "#uid")
    public BiliChangeVO getChangeListByUid(Integer uid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        List<BiliData> biliDataList = biliDao.getByUid(uid);
        List<String> timeList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        for (int i = 1; i < biliDataList.size(); ++i) {
            long time1 = firstTimeInDay(biliDataList.get(i - 1).getDate());
            long time2 = firstTimeInDay(biliDataList.get(i).getDate());
            long count = (time2 - time1) / (24 * 60 * 60 * 1000);
            int change = biliDataList.get(i).getFans() - biliDataList.get(i - 1).getFans();
            for (int j = 0; j < count; ++j) {
                timeList.add(sdf.format(new Date(time1)));
                valueList.add(change / (int)count);
                time1 += 24 * 60 * 60 * 1000;
            }
        }
        BiliChangeVO biliChangeVO = new BiliChangeVO();
        biliChangeVO.setTimeList(timeList);
        biliChangeVO.setValueList(valueList);
        return biliChangeVO;
    }

    @Override
    @Cacheable(value = "userList")
    public List<BiliUser> getUserList() {
        return biliDao.getUserList();
    }

    @Override
    @CacheEvict(value = "userList", allEntries = true)
    public void deleteUserList() {
        System.out.println("删除userList");
    }

    @Override
    @CacheEvict(value = "dayFans", allEntries = true)
    public void deleteDayFans() {
        System.out.println("删除所有的dayFans");
    }

    @Override
    @CacheEvict(value = "changeList", allEntries = true)
    public void deleteChangeList() {
        System.out.println("删除所有的changeList");
    }

    @Override
    public List<Log> getLog() {
        return logDao.getLog();
    }

    private BiliFanVO getFanVO(List<BiliData> biliDataList, SimpleDateFormat sdf, int interval, long endTime) {
        BiliFanVO biliFanVO = new BiliFanVO();
        if (biliDataList.size() == 0) {
            return biliFanVO;
        }
        List<String> timeList = new ArrayList<>();
        List<List<String>> valueList = new ArrayList<>();
        long time;
        if (interval == 86400000) {
            time = firstTimeInDay(biliDataList.get(0).getDate());
        } else {
            time = firstTimeIn10Min(biliDataList.get(0).getDate());
        }
        biliFanVO.setMinTime(time);
        int i = 0;
        while (i < biliDataList.size()) {
            String s = sdf.format(new Date(time));
            timeList.add(s);
            long tempTime;
            if (interval == 86400000) {
                tempTime = firstTimeInDay(biliDataList.get(i).getDate());
            } else {
                tempTime = firstTimeIn10Min(biliDataList.get(i).getDate());
            }
            if (tempTime == time) {
                List<String> item = new ArrayList<>();
                item.add(s);
                item.add(String.valueOf(biliDataList.get(i).getFans()));
                valueList.add(item);
                ++i;
            }
            time += interval;
        }
        biliFanVO.setTimeList(timeList);
        biliFanVO.setValueList(valueList);
        return biliFanVO;
    }

    /**
     * 转换为当天的0时0分0秒
     * @param date
     * @return
     */
    private long firstTimeInDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 转换为当天的23时59分59秒999毫秒
     * @param date
     * @return
     */
    private long lastTimeInDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

    /**
     * 转换为整的10分钟
     * @param date
     * @return
     */
    private long firstTimeIn10Min(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) / 10 * 10);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
