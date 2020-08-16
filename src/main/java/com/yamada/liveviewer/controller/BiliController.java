package com.yamada.liveviewer.controller;

import com.yamada.liveviewer.service.BiliService;
import com.yamada.liveviewer.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bili")
@CrossOrigin(origins = "http://127.0.0.1")
public class BiliController {

    private final BiliService biliService;

    @Autowired
    public BiliController (BiliService biliService) {
        this.biliService = biliService;
    }

    /**
     * 获取每日数据
     * @param uid
     * @return
     */
    @GetMapping("/all/{uid}")
    public Object getByUid(@PathVariable("uid") Integer uid) {
        return ResultUtil.success(biliService.getByUid(uid));
    }

    /**
     * 获取每10分钟数据
     * @param uid
     * @return
     */
    @GetMapping("/short/{uid}")
    public Object getShortByUid(@PathVariable("uid") Integer uid, @RequestParam(value = "start") Long start,
                                @RequestParam(value = "end") Long end) {
        return ResultUtil.success(biliService.getShortByUid(uid, start, end));
    }

    @GetMapping("/change/{uid}")
    public Object getChangeList(@PathVariable("uid") Integer uid) {
        return ResultUtil.success(biliService.getChangeListByUid(uid));
    }

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/userList")
    public Object getUserList() {
        return ResultUtil.success(biliService.getUserList());
    }

    @GetMapping("/log")
    public Object getLog() {
        return ResultUtil.success(biliService.getLog());
    }
}
