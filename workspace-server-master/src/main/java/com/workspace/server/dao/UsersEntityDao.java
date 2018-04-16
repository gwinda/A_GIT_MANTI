package com.workspace.server.dao;

import com.workspace.server.model.UserEntity;
import com.workspace.server.model.UserEntityPK;
import com.workspace.server.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 51998 on 2018/2/26.
 */
@Repository
public interface UsersEntityDao extends JpaRepository<UsersEntity, Long> {

    List<UsersEntity> findUsersEntityByuNumber(String uNumber);
    UsersEntity findUsersEntityByuid(int  UId);


}
