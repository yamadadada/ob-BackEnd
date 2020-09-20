package com.yamada.liveviewer.controller;

import com.yamada.liveviewer.form.FDGForm;
import com.yamada.liveviewer.pojo.VideoInfo;
import com.yamada.liveviewer.service.FDGService;
import com.yamada.liveviewer.utils.ResultUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fdg")
@CrossOrigin(origins = "http://111.230.220.64")
public class FDGController {

    private final FDGService fdgService;

    public FDGController(FDGService fdgService) {
        this.fdgService = fdgService;
    }

    @PostMapping("/addFDGVideo")
    public Object addFDGVideo(@RequestBody FDGForm form) {
        if (StringUtils.isEmpty(form.getBv()) || !form.getBv().startsWith("BV")) {
            return ResultUtil.error(500, "BV号格式不正确");
        }
        VideoInfo videoInfo = fdgService.getVideoInfo(form.getBv());
        if (videoInfo == null) {
            return ResultUtil.error(500, "找不到相关视频");
        }
        int result = fdgService.addVideo(form, videoInfo);
        if (result == 0) {
            return ResultUtil.error(500, "插入视频信息失败，请稍后再试");
        }
        return ResultUtil.success(200, videoInfo);
    }
}
