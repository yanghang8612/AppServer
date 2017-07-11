package com.huachuang.server.controller;

import com.huachuang.server.CommonUtils;
import com.huachuang.server.dao.*;
import com.huachuang.server.entity.*;
import com.huachuang.server.service.FinancialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
    private UserDebitCardDao debitCardDao;

    @Resource
    private ApplyLoanDao applyLoanDao;

    @Resource
    private ApplyCreditCardDao applyCreditCardDao;

    @Resource
    private UserFeedbackDao userFeedbackDao;

    @Resource
    private UserWithdrawDao userWithdrawDao;

    @RequestMapping(value = {"index.html", "index", "/", "login.html"}, method = RequestMethod.GET)
    public ModelAndView renderIndexPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("login");
        //testDao.createRows();
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map<String, String> login(HttpServletRequest request,
                                     HttpServletResponse response,
                     @RequestParam String username,
                     @RequestParam String password) throws IOException {



        Map<String, String> result = new HashMap<>();
        if (username.equals("admin") && password.equals("admin")) {
            result.put("Status", "true");
            result.put("Info", "登录成功");
        }
        else {
            result.put("Status", "false");
            result.put("Info", "用户名或密码错误");
        }
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) {
//            Cookie cookie = new Cookie("yang", "8612");
//            cookie.setMaxAge(10);
//            cookie.setPath("/");
//            response.addCookie(cookie);
//            return new ModelAndView("forward:/main.html");
//        }
//        else {
//            for (Cookie cookie : cookies) {
//                System.out.println(cookies[0].getName() + ":" + cookies[0].getValue());
//            }
//            return new ModelAndView("redirect:https://www.baidu.com");
//        }
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

    @RequestMapping(value = "mainother.html", method = RequestMethod.GET)
    public ModelAndView renderOtherPage() {
        ModelAndView mv = new ModelAndView("main");
        return mv;
    }

    @RequestMapping(value = "main.html", method = RequestMethod.GET)
    public ModelAndView renderMainPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("main");
        List<User> agents = userManagerDao.findAllAgents();
        Map<Long, UserCertificationInfo> certifications = new HashMap<>();
        if (agents != null) {
            for (User agent : agents) {
                if (agent.isCertificationState()) {
                    certifications.put(agent.getUserId(), certificationInfoDao.findCertificationInfoByUserID(agent.getUserId()));
                }
            }
        }
        request.setAttribute("agents", agents);
        request.setAttribute("certifications", certifications);
        return mv;
    }

    @RequestMapping(value = "agent_info.html", method = RequestMethod.GET)
    public ModelAndView renderAgentInfoPage(HttpServletRequest request, @RequestParam String phoneNumber) {
        ModelAndView mv = new ModelAndView("agent_info");
        User agent = userManagerDao.findUserByPhoneNumber(phoneNumber);
        request.setAttribute("agent", agent);
        if (agent.isCertificationState()) {
            UserCertificationInfo certificationInfo = certificationInfoDao.findCertificationInfoByUserID(agent.getUserId());
            request.setAttribute("certificationInfo", certificationInfo);
        }
        List<User> users = userManagerDao.findSubUsers(agent.getUserId());
        request.setAttribute("users", users);
        Map<Long, UserCertificationInfo> certifications = new HashMap<>();
        if (users != null) {
            for (User user : users) {
                if (user.isCertificationState()) {
                    certifications.put(user.getUserId(), certificationInfoDao.findCertificationInfoByUserID(user.getUserId()));
                }
            }
        }
        request.setAttribute("certifications", certifications);
        return mv;
    }

    @RequestMapping(value = "loan.html", method = RequestMethod.GET)
    public ModelAndView renderLoanPage() {
        ModelAndView mv = new ModelAndView("loan");
        return mv;
    }

    @RequestMapping(value = "loan_records.html", method = RequestMethod.GET)
    public ModelAndView renderLoanRecordsPage(HttpServletRequest request, @RequestParam byte state) {
        ModelAndView mv = new ModelAndView("loan_records");
        List<ApplyLoan> records = new ArrayList<>();
        switch (state) {
            case -1:
                records.addAll(applyLoanDao.findAllApplyRecords());
                break;
            case 0:
                records.addAll(applyLoanDao.findApplyRecordsByState((byte) 0));
                break;
            case 1:
                records.addAll(applyLoanDao.findApplyRecordsByState((byte) 1));
                records.addAll(applyLoanDao.findApplyRecordsByState((byte) 2));
                break;
            case 2:
                records.addAll(applyLoanDao.findApplyRecordsByState((byte) 3));
                records.addAll(applyLoanDao.findApplyRecordsByState((byte) 4));
                break;
        }
        Map<Long, User> users = new HashMap<>();
        for (ApplyLoan record : records) {
            long userID = record.getUserId();
            if (!users.containsKey(userID)) {
                users.put(userID, userManagerDao.findUserByUserID(userID));
            }
        }
        Map<Long, User> superUsers = new HashMap<>();
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            long superUserID = entry.getValue().getSuperiorUserId();
            if (!superUsers.containsKey(superUserID)) {
                superUsers.put(superUserID, userManagerDao.findUserByUserID(superUserID));
            }
        }
        users.putAll(superUsers);
        request.setAttribute("records", records);
        request.setAttribute("users", users);
        return mv;
    }

    @RequestMapping(value = "loan_record_info.html", method = RequestMethod.GET)
    public ModelAndView renderLoanRecordInfoPage(HttpServletRequest request, @RequestParam long id) {
        ModelAndView mv = new ModelAndView("loan_record_info");
        ApplyLoan record = applyLoanDao.findApplyRecordByID(id);
        request.setAttribute("record", record);
        return mv;
    }

    @RequestMapping(value = "credit_card.html", method = RequestMethod.GET)
    public ModelAndView renderCreditCardPage() {
        ModelAndView mv = new ModelAndView("credit_card");
        return mv;
    }

    @RequestMapping(value = "credit_card_records.html", method = RequestMethod.GET)
    private ModelAndView renderCreditCardRecordPage(HttpServletRequest request, @RequestParam int interval) {
        ModelAndView mv = new ModelAndView("credit_card_records");
        List<ApplyCreditCard> records = applyCreditCardDao.findApplyRecordsByInterval(interval);
        Map<Long, User> users = new HashMap<>();
        for (ApplyCreditCard record : records) {
            long userID = record.getUserId();
            if (!users.containsKey(userID)) {
                users.put(userID, userManagerDao.findUserByUserID(userID));
            }
        }
        Map<Long, UserCertificationInfo> certifications = new HashMap<>();
        Map<Long, User> superUsers = new HashMap<>();
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            if (!certifications.containsKey(entry.getKey())) {
                certifications.put(entry.getKey(), certificationInfoDao.findCertificationInfoByUserID(entry.getKey()));
            }
            long superUserID = entry.getValue().getSuperiorUserId();
            if (!superUsers.containsKey(superUserID)) {
                superUsers.put(superUserID, userManagerDao.findUserByUserID(superUserID));
            }
        }
        users.putAll(superUsers);
        request.setAttribute("records", records);
        request.setAttribute("users", users);
        request.setAttribute("certifications", certifications);
        return mv;
    }

    @RequestMapping(value = "feedback.html", method = RequestMethod.GET)
    public ModelAndView renderFeedbackPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("feedback");
        List<UserFeedback> feedbacks = userFeedbackDao.findAllFeedback();
        Map<Long, User> users = new HashMap<>();
        for (UserFeedback userFeedback : feedbacks) {
            long userID = userFeedback.getUserId();
            if (!users.containsKey(userID)) {
                users.put(userID, userManagerDao.findUserByUserID(userID));
            }
        }
        Map<Long, UserCertificationInfo> certifications = new HashMap<>();
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            if (entry.getValue().isCertificationState()) {
                certifications.put(entry.getKey(), certificationInfoDao.findCertificationInfoByUserID(entry.getKey()));
            }
        }
        request.setAttribute("feedback", feedbacks);
        request.setAttribute("users", users);
        request.setAttribute("certifications", certifications);
        return mv;
    }

    @RequestMapping(value = "into.html", method = RequestMethod.GET)
    public ModelAndView renderIntoPage() {
        ModelAndView mv = new ModelAndView("into");
        return mv;
    }

    @RequestMapping(value = "into_records.html", method = RequestMethod.GET)
    public ModelAndView renderIntoRecordsPage(HttpServletRequest request, @RequestParam byte state) {
        ModelAndView mv = new ModelAndView("into_records");
        List<User> allUsers = userManagerDao.findAllUsers();
        List<User> users = new ArrayList<>();
        Map<Long, UserCertificationInfo> certifications = new HashMap<>();
        Map<Long, UserDebitCard> cards = new HashMap<>();
        for (User user : allUsers) {
            if (user.isCertificationState() && user.isDebitCardState()) {
                if (state == -1 ||
                        (state == 0 && user.getMobilePayState() == 0) ||
                        (state == 1 && user.getMobilePayState() == 1) ||
                        (state == 2 && user.getMobilePayState() >= 2)) {
                    users.add(user);
                    long userID = user.getUserId();
                    certifications.put(userID, certificationInfoDao.findCertificationInfoByUserID(userID));
                    cards.put(userID, debitCardDao.findDebitCardByUserID(userID));
                }
            }
        }
        request.setAttribute("users", users);
        request.setAttribute("certifications", certifications);
        request.setAttribute("cards", cards);
        return mv;
    }

    @RequestMapping(value = "into_record_info.html", method = RequestMethod.GET)
    public ModelAndView renderIntoRecordInfoPage(HttpServletRequest request, @RequestParam long userID) {
        ModelAndView mv = new ModelAndView("into_record_info");
        request.setAttribute("user", userManagerDao.findUserByUserID(userID));
        request.setAttribute("certification", certificationInfoDao.findCertificationInfoByUserID(userID));
        request.setAttribute("card", debitCardDao.findDebitCardByUserID(userID));
        return mv;
    }

    @RequestMapping(value = "setting.html", method = RequestMethod.GET)
    public ModelAndView renderSettingPage() {
        ModelAndView mv = new ModelAndView("setting");
        return mv;
    }

    @RequestMapping(value = "withdraw.html", method = RequestMethod.GET)
    public ModelAndView renderWithdrawPage() {
        ModelAndView mv = new ModelAndView("withdraw");
        return mv;
    }

    @RequestMapping(value = "withdraw_records.html", method = RequestMethod.GET)
    public ModelAndView renderWithdrawRecordsPage(HttpServletRequest request, @RequestParam byte state) {
        ModelAndView mv = new ModelAndView("withdraw_records");
        List<UserWithdraw> records = new ArrayList<>();
        switch (state) {
            case -1:
                records.addAll(userWithdrawDao.findAllWithdraw());
                break;
            case 0:
                records.addAll(userWithdrawDao.findWithdrawByState((byte) 0));
                break;
            case 1:
                records.addAll(userWithdrawDao.findWithdrawByState((byte) 1));
                break;
            case 2:
                records.addAll(userWithdrawDao.findWithdrawByState((byte) 2));
                break;
        }
        Map<Long, User> users = new HashMap<>();
        Map<Long, UserCertificationInfo> certifications = new HashMap<>();
        for (UserWithdraw record : records) {
            long userID = record.getUserId();
            if (!users.containsKey(userID)) {
                User user = userManagerDao.findUserByUserID(userID);
                users.put(userID, user);
                if (user.isCertificationState()) {
                    certifications.put(userID, certificationInfoDao.findCertificationInfoByUserID(userID));
                }
            }
        }
        request.setAttribute("records", records);
        request.setAttribute("users", users);
        request.setAttribute("certifications", certifications);
        return mv;
    }

    @RequestMapping(value = "withdraw_record_info.html", method = RequestMethod.GET)
    public ModelAndView renderWithdrawRecordInfoPage(HttpServletRequest request, @RequestParam long id) {
        ModelAndView mv = new ModelAndView("withdraw_record_info");
        UserWithdraw record = userWithdrawDao.findWithdrawByID(id);
        User user = userManagerDao.findUserByUserID(record.getUserId());
        UserCertificationInfo certification = certificationInfoDao.findCertificationInfoByUserID(user.getUserId());
        request.setAttribute("user", user);
        request.setAttribute("certification", certification);
        request.setAttribute("record", record);
        return mv;
    }
}
