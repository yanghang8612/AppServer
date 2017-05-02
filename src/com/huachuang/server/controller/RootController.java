package com.huachuang.server.controller;

import com.huachuang.server.dao.ApplyLoanDao;
import com.huachuang.server.dao.UserCertificationInfoDao;
import com.huachuang.server.dao.UserManagerDao;
import com.huachuang.server.entity.User;
import com.huachuang.server.entity.UserCertificationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Asuka on 2017/4/6.
 */

@Controller
public class RootController {

    @Resource
    private UserManagerDao userManagerDao;

    @Resource
    private UserCertificationInfoDao certificationInfoDao;

    @Resource
    private ApplyLoanDao applyLoanDao;

    @RequestMapping(value = {"index.html", "index", "/", "login.html"}, method = RequestMethod.GET)
    public ModelAndView renderIndexPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map<String, String> login(HttpServletRequest request,
                     @RequestParam String username,
                     @RequestParam String password) {

        Map<String, String> result = new HashMap<>();
        if (username.equals("admin") && password.equals("admin")) {
            result.put("Status", "true");
            result.put("Info", "登录成功");
        }
        else {
            result.put("Status", "false");
            result.put("Info", "用户名或密码错误");
        }
        return result;
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

    @RequestMapping(value = "main.html", method = RequestMethod.GET)
    public ModelAndView renderMainPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("main");
        List<User> agents = userManagerDao.findAllAgents();
        request.setAttribute("agents", agents);
        return mv;
    }

    @RequestMapping(value = "loan.html", method = RequestMethod.GET)
    public ModelAndView renderLoanPage() {
        ModelAndView mv = new ModelAndView("loan");
        return mv;
    }

    @RequestMapping(value = "agent_info.html", method = RequestMethod.GET)
    public ModelAndView renderAgentInfoPage(HttpServletRequest request, @RequestParam String phoneNumber) {
        ModelAndView mv = new ModelAndView("agent_info");
        User agent = userManagerDao.findUserByPhoneNumber(phoneNumber);
        request.setAttribute("agent", agent);
        List<User> users = userManagerDao.findSubCommonUsers(agent.getUserId());
        request.setAttribute("users", users);
        if (agent.isCertificationState()) {
            UserCertificationInfo certificationInfo = certificationInfoDao.findCertificationInfoByUserID(agent.getUserId());
            request.setAttribute("CertificationInfo", certificationInfo);
        }
        return mv;
    }
}
