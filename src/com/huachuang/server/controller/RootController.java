package com.huachuang.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Asuka on 2017/4/6.
 */

@Controller
public class RootController {

    @RequestMapping(value = {"index.html", "index"}, method = RequestMethod.GET)
    public ModelAndView renderIndexPage() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

}
