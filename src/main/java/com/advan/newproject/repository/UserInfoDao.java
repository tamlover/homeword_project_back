package com.advan.newproject.repository;

import com.advan.newproject.entity.Device;
import com.advan.newproject.entity.UserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;


public interface UserInfoDao extends CrudRepository<UserInfo,Long> {

    UserInfo getUserInfoByAccount(String account);

    UserInfo getUserInfoById(Long id);

    @Query(value = "from UserInfo ")
    List<UserInfo> getAll();

    @Modifying
    @Query(value = "delete from user_device as ud where ud.user_id = ?1",nativeQuery = true)
    void deleteBinding(long userId);

}
