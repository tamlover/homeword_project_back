package com.advan.newproject.service;

import com.advan.newproject.entity.DTO.DeviceDTO;
import com.advan.newproject.entity.Device;
import com.advan.newproject.repository.DeviceDao;
import com.advan.newproject.repository.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private UserInfoDao userInfoDao;

    public void addDevice(Device device) throws Exception {

        Device dev = deviceDao.getDeviceByDeviceNumber(device.getDeviceNumber());
        if (dev == null){
            deviceDao.save(device);
        }else {
            throw new Exception("The device exist");
        }
    }

    public void deleteDevice(Long deviceId){

        Device device = deviceDao.getDeviceByIdIs(deviceId);
        if (device != null){
            deviceDao.deleteBinding(deviceId);
            deviceDao.deleteById(deviceId);
        }
    }

    public Device getDevice(Long id){
        return deviceDao.getDeviceByIdIs(id);
    }

    public List<DeviceDTO> getDevices (Long userId) {
        List<Device> devices;
        List<DeviceDTO> deviceDTOList = new ArrayList<>();
        if (userId == (long)1){
            devices = deviceDao.getAll();
            for (Device device:devices){
                DeviceDTO deviceDTO = new DeviceDTO(device);
                deviceDTOList.add(deviceDTO);
            }
        }else {
           Set<Device> deviceList = userInfoDao.getUserInfoById(userId).getDevices();
            for (Device device:deviceList){
                DeviceDTO deviceDTO = new DeviceDTO(device);
                deviceDTOList.add(deviceDTO);
            }
        }
        return deviceDTOList;
    }

    public void updateDevice(DeviceDTO device) {
        Device d = deviceDao.getDeviceByIdIs(device.getId());
        if (d != null) {
            d.setDeviceName(device.getDeviceName());
            d.setDeviceDetail(device.getDeviceDetail());
            deviceDao.save(d);
        }
    }
}
