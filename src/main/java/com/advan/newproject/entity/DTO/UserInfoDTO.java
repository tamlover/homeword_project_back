package com.advan.newproject.entity.DTO;

import com.advan.newproject.entity.Device;
import com.advan.newproject.entity.UserInfo;

import java.util.HashSet;
import java.util.Set;

public class UserInfoDTO {

    private Long userId;
    private String userName;
    private String account;
    private String role;
    private String password;
    private Set<DeviceDTO> devices;

    public Set<DeviceDTO> getDevices() {
        return devices;
    }

    public void setDevices(Set<DeviceDTO> devices) {
        this.devices = devices;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfoDTO () {

    }

    public UserInfoDTO (UserInfo u) {
        this.account = u.getAccount();
        this.userId = u.getId();
        this.role = u.getRole();
        this.userName = u.getUserName();
        this.devices = new HashSet<>();
        for (Device device: u.getDevices()){
            DeviceDTO deviceDTO = new DeviceDTO(device);
            if (deviceDTO != null) {
                this.devices.add(deviceDTO);
            }
        }
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
