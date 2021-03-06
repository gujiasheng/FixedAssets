package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.*;
import com.gjs.fixedassets.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FixedAssetTransferManageController {
    @Autowired
    private FixedcardService fixedcardService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private FixedTransferService fixedTransferService;

    @Autowired
    private CheckRecordStatusService checkRecordStatusService;

    @Autowired
    private MyMessageService myMessageService;

    @GetMapping("/toFixedAssetTransferManageList")
    public String toMyFixedAssetTransferApplyList() {
        return "fixedassettransfermanage/FixedAssetTransferManageList";
    }

    /*
     * @Description TODO
     *  打开某个固定资产领用详情列表
     * @Author
     * @Date 2021-05-10
     * @params
     * @Return
     **/
    @GetMapping("/toTransferRecordlist{fixedcardId}")
    public String toTransferRecordlist(@PathVariable("fixedcardId") Integer fixedcardId, Model model, HttpSession session) {
        session.setAttribute("fixedcardId", fixedcardId);
        model.addAttribute("fixedcardId", fixedcardId);
        return "fixedassettransfermanage/TransferRecordlist";
    }

    /*
     * @Description TODO
     * 查询某件固定资产所有的领用记录
     * @Author
     * @Date 2021-02-19
     * @params
     * @Return
     **/
    @ResponseBody
    @GetMapping("/selectAllTransferRecord")
    public Map<String, Object> selectAllTransferRecord(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                       @RequestParam(required = false, defaultValue = "5") Integer limit,
                                                       @RequestParam(required = false, defaultValue = "", value = "startdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startdate,//查询条件
                                                       @RequestParam(required = false, defaultValue = "", value = "enddate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date enddate,
                                                       @RequestParam(required = false, defaultValue = "", value = "checkNode") Integer checkNode,
                                                       @RequestParam(required = false, defaultValue = "", value = "fixedcardId") Integer fixedcardId,
                                                       HttpSession session, Model model) throws ParseException {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        String startdate2 = null;
        String enddate2 = null;
        //获取当前固定资产卡片的id
        fixedcardId = (Integer) session.getAttribute("fixedcardId");
        //两个查询时间返回需要格式化一下。
        if (startdate != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            startdate2 = f.format(startdate);

        }
        System.out.println(startdate);
        if (enddate != null) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            enddate2 = f.format(enddate);

        }

        List<FixedTransfer> fixedtransferList = fixedTransferService.selectAllTransferRecordBycardId(user.getCompanyId(), fixedcardId, page, limit, startdate2, enddate2, checkNode);
//        fixedtransferList.get(0).getCheckRecordStatus().getCheckTime()
        //查询所有结果数量
        List<FixedTransfer> fixedtransferCount = fixedTransferService.selectAllCountTransferRecordBycardId(user.getCompanyId(), fixedcardId, startdate2, enddate2, checkNode);
        int transferCount = fixedtransferCount.size();
        //用layui的table渲染数据的json有格式要求，需要封装一下
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", transferCount);
        map.put("data", fixedtransferList);
//        System.out.println(map);
        return map;
    }
}
