package com.yamada.liveviewer.controller;

import com.yamada.liveviewer.service.HomeService;
import com.yamada.liveviewer.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://111.230.220.64")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/overview")
    public Object overview() {
        return ResultUtil.success(homeService.overview());
    }
}
