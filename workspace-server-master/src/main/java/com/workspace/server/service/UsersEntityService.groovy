package com.workspace.server.service

import com.workspace.server.dao.UserEntityDao
import com.workspace.server.dao.UsersEntityDao
import com.workspace.server.model.UserEntity
import com.workspace.server.model.UsersEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 51998 on 2018/2/26.
 */
@Service
@Transactional
class UsersEntityService {

    @Autowired UsersEntityDao usersEntityDao

    List<UsersEntity> findUsersEntityByuNumber (String uNumber) {
        return usersEntityDao.findUsersEntityByuNumber(uNumber)
    }
    UsersEntity findUsersEntityByuid(int UId) {
        return usersEntityDao.findUsersEntityByuid(UId)
    }


   boolean exists (String UNumber) {
       def list_size=  usersEntityDao.findUsersEntityByuNumber(UNumber).size()
       return list_size != 0
   }
    boolean existUid (int Uid) {
        def list_size=  usersEntityDao.findUsersEntityByuid(Uid)
        return list_size != null
    }


}
