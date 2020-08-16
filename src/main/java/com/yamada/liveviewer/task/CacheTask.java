package com.yamada.liveviewer.task;

import com.yamada.liveviewer.service.BiliService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class CacheTask {

    private final BiliService biliService;

    @Scheduled(cron = "15 0 0 * * *")
    public void deleteTask() {
        biliService.deleteUserList();
        biliService.deleteDayFans();
        biliService.deleteChangeList();
    }
}
