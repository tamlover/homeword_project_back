package com.advan.newproject.entity.DTO;

import com.advan.newproject.entity.Device;

public class DeviceDTO {

    private Long id;

    private String deviceName;
    private String deviceNumber;
    private String deviceDetail;

    public DeviceDTO () {

    }

    public DeviceDTO (Device device) {
        this.id = device.getId();
        this.deviceName = device.getDeviceName();
        this.deviceDetail = device.getDeviceDetail();
        this.deviceNumber = device.getDeviceNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceDetail() {
        return deviceDetail;
    }

    public void setDeviceDetail(String deviceDetail) {
        this.deviceDetail = deviceDetail;
    }
}
