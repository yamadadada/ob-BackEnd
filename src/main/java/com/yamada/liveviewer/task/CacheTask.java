package com.yamada.liveviewer.task;

import com.yamada.liveviewer.service.BiliService;
import com.yamada.liveviewer.service.GameService;
import com.yamada.liveviewer.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class CacheTask {

    private final BiliService biliService;

    private final GameService gameService;

    private final HomeService homeService;

    @Scheduled(cron = "15 0 0 * * *")
    public void deleteTask() {
        biliService.deleteUserList();
        biliService.deleteDayFans();
        biliService.deleteChangeList();
    }

    @Scheduled(cron = "0 5 * * * *")
    public void deleteGameTask() {
        gameService.deleteGameRank();
        homeService.deleteOverview();
    }
}
