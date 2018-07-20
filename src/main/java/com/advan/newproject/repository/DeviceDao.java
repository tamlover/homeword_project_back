package com.advan.newproject.repository;

import com.advan.newproject.entity.Device;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DeviceDao extends CrudRepository<Device,Long> {


    Device getDeviceByDeviceNumber(String number);

    Device getDeviceByIdIs(Long id);

    @Query(value = "from Device ")
    List<Device> getAll();

    @Modifying
    @Query(value = "delete from user_device as ud where ud.device_id = ?1",nativeQuery = true)
    void deleteBinding(long deviceId);

}
