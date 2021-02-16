package com.yamada.liveviewer.service;

import com.yamada.liveviewer.vo.GameDetailVO;
import com.yamada.liveviewer.vo.GameVO;

import java.util.List;

public interface GameService {

    List<GameVO> getRank(int page);

    GameDetailVO getDetailByGid(Integer gid);

    void deleteGameRank();
}
