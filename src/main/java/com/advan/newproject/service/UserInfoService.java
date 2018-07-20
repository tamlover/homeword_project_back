package com.advan.newproject.service;

import com.advan.newproject.entity.DTO.PwdDTO;
import com.advan.newproject.entity.DTO.UserInfoDTO;
import com.advan.newproject.entity.Device;
import com.advan.newproject.entity.UserInfo;
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
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private DeviceDao deviceDao;

    public UserInfoDTO login(String account, String password) throws Exception {
        UserInfoDTO userInfoDTO;
        UserInfo u = userInfoDao.getUserInfoByAccount(account);
            if (u == null){
                throw new Exception("This account is not exist");
            }else {
                if (!u.getPassword().equals(password)) {
                    throw new Exception("The account or password is wrong");
                }else {
                    userInfoDTO = new UserInfoDTO(u);
                }
            }
        return userInfoDTO;
    }

    public void addUser(UserInfo userInfo) throws Exception {
        UserInfo u=userInfoDao.getUserInfoByAccount(userInfo.getAccount());
        if (u ==null){
            userInfoDao.save(userInfo);
        }else {
            throw new Exception("This user exist");
        }
    }

    public void deleteUser(Long userId){

        userInfoDao.deleteBinding(userId) ;
        userInfoDao.deleteById(userId);
    }


    public List<UserInfoDTO> getAllUserInfo(){
        List<UserInfo> userInfoList = userInfoDao.getAll();
        List<UserInfoDTO> userInfoDTOList = new ArrayList<>();
        for(UserInfo u:userInfoList){
            UserInfoDTO userInfoDTO = new UserInfoDTO(u);
            userInfoDTOList.add(userInfoDTO);
        }
        return  userInfoDTOList;
    }

    public void updateUser (UserInfoDTO userInfo) {
        UserInfo u = userInfoDao.getUserInfoById(userInfo.getUserId());
        u.setAccount(userInfo.getAccount());
        u.setUserName(userInfo.getUserName());

        userInfoDao.save(u);
    }

    public void updateUserPwd(PwdDTO pwdDTO) {
        UserInfo u = userInfoDao.getUserInfoById(pwdDTO.getUserId());
        if (u.getPassword().equals(pwdDTO.getOldPassword())) {
            u.setPassword(pwdDTO.getNewPassword());
            userInfoDao.save(u);
        }
    }
    public void addBinding(Long[] deviceIds, Long userId){
        Device device;
        UserInfo userInfo = userInfoDao.getUserInfoById(userId);
        Set<Device> deviceList = userInfo.getDevices();
        for (int i = 0;i < deviceIds.length;i++){
            device = deviceDao.getDeviceByIdIs(deviceIds[i]);
            if (device != null) {
                deviceList.add(device);
            }
        }
        userInfoDao.save(userInfo);
    }

    public void deleteBinding(Long[] deviceIds, Long userId){
        Device device;
        UserInfo userInfo = userInfoDao.getUserInfoById(userId);
        Set<Device> deviceList = userInfo.getDevices();
        for (int i = 0;i < deviceIds.length;i++){
            device = deviceDao.getDeviceByIdIs(deviceIds[i]);
            if (device != null) {
                deviceList.remove(device);
            }
        }
        userInfoDao.save(userInfo);
    }
}
