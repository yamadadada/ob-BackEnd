package com.yamada.liveviewer.service;

import com.yamada.liveviewer.pojo.BiliUser;
import com.yamada.liveviewer.pojo.Log;
import com.yamada.liveviewer.vo.BiliChangeVO;
import com.yamada.liveviewer.vo.BiliFanVO;

import java.util.List;

public interface BiliService {

    BiliFanVO getByUid(Integer uid);

    BiliFanVO getShortByUid(Integer uid, Long start, Long end);

    BiliChangeVO getChangeListByUid(Integer uid);

    List<BiliUser> getUserList();

    List<Log> getLog();

    void deleteUserList();

    void deleteDayFans();

    void deleteChangeList();
}
