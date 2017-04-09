package com.huachuang.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Asuka on 2017/4/6.
 */

@Controller
public class RootController {

    @RequestMapping(value = {"index.html", "index", "/"}, method = RequestMethod.GET)
    public ModelAndView renderIndexPage() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = "register_step_one.html", method = RequestMethod.GET)
    public ModelAndView renderRegisterStepOnePage() {
        ModelAndView mv = new ModelAndView("register_step_one");
        return mv;
    }

    @RequestMapping(value = "register_step_two.html", method = RequestMethod.GET)
    public ModelAndView renderRegisterStepTwoPage() {
        ModelAndView mv = new ModelAndView("register_step_two");
        return mv;
    }

    @RequestMapping(value = "success.html", method = RequestMethod.GET)
    public ModelAndView renderSuccessPage() {
        ModelAndView mv = new ModelAndView("success");
        return mv;
    }

    @RequestMapping(value = "tables.html", method = RequestMethod.GET)
    public ModelAndView renderTablesPage() {
        ModelAndView mv = new ModelAndView("tables");
        return mv;
    }
}
