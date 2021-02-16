package com.yamada.liveviewer.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yamada.liveviewer.service.GameService;
import com.yamada.liveviewer.utils.ResultUtil;
import com.yamada.liveviewer.vo.GameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "http://111.230.220.64")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("")
    public Object rank(@RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "size", defaultValue = "30") int size) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(page, size);
        List<GameVO> gameVOList = gameService.getRank(page);
        PageInfo<GameVO> pageInfo = new PageInfo<>(gameVOList);

        map.put("gameVOList", gameVOList);
        map.put("page", page);
        map.put("size", size);
        map.put("totalPage", pageInfo.getPages());
        return ResultUtil.success(map);
    }

    @GetMapping("/{gid}")
    public Object detail(@PathVariable("gid") Integer gid) {
        return ResultUtil.success(gameService.getDetailByGid(gid));
    }
}
