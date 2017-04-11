package com.huachuang.server.controller;

import com.huachuang.server.dao.UserCertificationInfoDao;
import com.huachuang.server.dao.UserManagerDao;
import com.huachuang.server.entity.User;
import com.huachuang.server.service.UserManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Asuka on 2017/4/6.
 */

@Controller
public class RootController {

    @Resource
    private UserManagerDao userManagerDao;

    @Resource
    private UserCertificationInfoDao userCertificationInfoDao;

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
    public ModelAndView renderTablesPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("tables");
        List<User> agents = userManagerDao.findAllAgents();
        request.setAttribute("agents", agents);
        return mv;
    }
}
