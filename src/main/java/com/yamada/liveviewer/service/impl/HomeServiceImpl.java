package com.yamada.liveviewer.service.impl;

import com.yamada.liveviewer.dao.SummaryDao;
import com.yamada.liveviewer.pojo.Summary;
import com.yamada.liveviewer.service.HomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private SummaryDao summaryDao;

    @Override
    public Object overview() {
        List<Summary> summaryList = summaryDao.getReal24HoursTotal();
        Map<String, Object> map = new HashMap<>();
        List<String> xList = new ArrayList<>();
        List<String> yList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("M-d HH:mm");
        long dayMax = 0L;
        for (Summary summary : summaryList) {
            xList.add(sdf.format(summary.getTime()));
            long total = Math.round(summary.getTotal());
            yList.add(String.valueOf(total));
            dayMax = Math.max(dayMax, total);
        }
        long now = Long.parseLong(yList.get(yList.size() - 1));

        long monthMax = Math.round(summaryDao.getMonthMax());

        long before24h = Math.round(summaryDao.get24HoursBefore());
        float dayRate = (now - before24h) / (float)before24h;

        long beforeWeek = Math.round(summaryDao.getWeekBefore());
        float weekRate = (now - beforeWeek) / (float)beforeWeek;

        map.put("xList", xList);
        map.put("yList", yList);

        map.put("now", now);
        map.put("dayMax", dayMax);
        map.put("monthMax", monthMax);
        map.put("dayRate", dayRate);
        map.put("weekRate", weekRate);
        return map;
    }
}
