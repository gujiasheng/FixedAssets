package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.*;
import com.gjs.fixedassets.service.DepartmentService;
import com.gjs.fixedassets.service.FixedcardService;
import com.gjs.fixedassets.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class FixedCardController {
    @Autowired
    private FixedcardService fixedcardService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;


    /*
     * @Description TODO
     *  前往资产卡片添加页面
     * @Author
     * @Date 2021-03-15
     * @params
     * @Return
     **/
    @GetMapping("/tofixedcardadd")
    public String toFixedCardAdd(Model model, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        Map<Integer, String> typeMap = FixedType.toList();
        model.addAttribute("typeMap", typeMap);
        Map<Integer, String> statusMap = FixedStatus.toStatusMap();
        model.addAttribute("sm", statusMap);
        Map<Integer, String> increMap = Increment.toIncreMap();
        model.addAttribute("im", increMap);
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(user.getCompanyId());
        model.addAttribute("de", departmentList);
        List<User> userList = userService.selectAllUserByCompanyId(user.getCompanyId());
        model.addAttribute("ul", userList);
        Map<Integer, String> unitMap = Unit.toUnitMap();
        model.addAttribute("un", unitMap);
        model.addAttribute("companyName", user.getCompany().getCompanyName());
        //格式化录入时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("nowDateVal", formatter.format(date));
        model.addAttribute("nowDateText", formatter.format(date).toString());
        model.addAttribute("maker", user.getUserName());
        return "fixedassetcard/FixedAssetCardAdd";
    }

    /*
     * @Description TODO
     *  添加资产卡片
     * @Author
     * @Date 2021-03-15
     * @params
     * @Return
     **/
    @PostMapping("/fixedcardadd")
    @ResponseBody
    public void fixedCardAdd(Fixedcard fixedcard, HttpSession session) {
        Object obj = session.getAttribute("user");
        User loginUser = (User) obj;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        fixedcard.setCompanyId(user.getCompanyId());

        Date date = new Date(System.currentTimeMillis());
        fixedcard.setEntryDate(date);
        fixedcardService.insertFixedAsssetCard(fixedcard);
//        return "redirect:/tofixedcardadd";
    }
}
