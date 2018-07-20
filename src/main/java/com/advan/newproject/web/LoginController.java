package com.advan.newproject.web;

import com.advan.newproject.entity.DTO.UserInfoDTO;
import com.advan.newproject.entity.UserInfo;
import com.advan.newproject.jwt.JwtUtil;
import com.advan.newproject.service.UserInfoService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestBody UserInfo userInfo){
        JSONObject jsonObject = new JSONObject();
        UserInfoDTO userInfoDTO;
        try{
            userInfoDTO = userInfoService.login(userInfo.getAccount(),userInfo.getPassword());
            String token = JwtUtil.sign(userInfoDTO.getUserId(),60*1000);

            jsonObject.put("token", token);
            jsonObject.put("user",userInfoDTO);
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/refresh")
    public String refresh(@RequestBody UserInfoDTO userInfo, HttpServletResponse res){
        JSONObject jsonObject = new JSONObject();
        try{
            String token = JwtUtil.sign(userInfo.getUserId(),60*1000);
            res.setHeader("token", token);
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
