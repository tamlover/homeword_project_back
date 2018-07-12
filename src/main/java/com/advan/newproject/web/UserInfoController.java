package com.advan.newproject.web;

import com.advan.newproject.entity.DTO.BindingDTO;
import com.advan.newproject.entity.DTO.PwdDTO;
import com.advan.newproject.entity.DTO.UserInfoDTO;
import com.advan.newproject.entity.UserInfo;
import com.advan.newproject.service.UserInfoService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@RequestBody UserInfo userInfo){
        JSONObject jsonObject = new JSONObject();
        try{
            userInfoService.addUser(userInfo);
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

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam(value = "userId") long userId){
        JSONObject jsonObject = new JSONObject();
        try{
            userInfoService.deleteUser(userId);
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

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUser(){
        JSONObject jsonObject = new JSONObject();
        List<UserInfoDTO> userInfoDTOList;
        try{
            userInfoDTOList = userInfoService.getAllUserInfo();
            jsonObject.put("users",userInfoDTOList);
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return JSONObject.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public String updateUser(@RequestBody UserInfoDTO userInfo){
        JSONObject jsonObject = new JSONObject();
        try{
            userInfoService.updateUser(userInfo);
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

    @RequestMapping(method = RequestMethod.POST,value = "binding")
    public String addBinding(@RequestBody BindingDTO bindingData){
        JSONObject jsonObject = new JSONObject();
        try{
            userInfoService.addBinding(bindingData.getDeviceIds(),bindingData.getUserId());
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "binding")
    public String deleteBinding(@RequestBody BindingDTO bindingData){
        JSONObject jsonObject = new JSONObject();
        try{
            userInfoService.deleteBinding(bindingData.getDeviceIds(),bindingData.getUserId());
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
    @RequestMapping(method = RequestMethod.PUT,value = "pwd")
    public String deleteBinding(@RequestBody PwdDTO pwdDTO){
        JSONObject jsonObject = new JSONObject();
        try{
            userInfoService.updateUserPwd(pwdDTO);
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
