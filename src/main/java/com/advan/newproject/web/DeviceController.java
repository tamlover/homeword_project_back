package com.advan.newproject.web;

import com.advan.newproject.entity.DTO.DeviceDTO;
import com.advan.newproject.entity.Device;
import com.advan.newproject.service.DeviceService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET,value = "userId")
    public String getServices (@RequestParam(value = "userId") long userId) {
        JSONObject jsonObject = new JSONObject();
        List<DeviceDTO> devices;
        try {
            devices = deviceService.getDevices(userId);
            jsonObject.put("devices",devices);
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
    public String deleteDevice (@RequestParam(value = "deviceId") long deviceId) {
        JSONObject jsonObject = new JSONObject();
        try {
            deviceService.deleteDevice(deviceId);
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateDevice (@RequestBody DeviceDTO device) {
        JSONObject jsonObject = new JSONObject();
        try {
            deviceService.updateDevice(device);
            jsonObject.put("Version","1.0");
            jsonObject.put("ErrorCode","0");
        }catch (Exception e){
            jsonObject.put("Version", "1.0");
            jsonObject.put("ErrorCode", "1");
            jsonObject.put("ErrorMessage", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }
}
