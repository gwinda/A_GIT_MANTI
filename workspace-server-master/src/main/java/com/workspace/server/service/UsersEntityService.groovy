package com.workspace.server.service

import com.workspace.server.dao.UserEntityDao
import com.workspace.server.dao.UsersEntityDao
import com.workspace.server.model.UserEntity
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

    List<UserEntity> findUsersEntityByuNumber (String uNumber) {
        return usersEntityDao.findUsersEntityByuNumber(uNumber)
    }
    List<UserEntity> findUsersEntityByUId(int UId) {
        return usersEntityDao.findUsersEntityByuid(UId)
    }

   boolean exists (String UNumber) {
       def list_size=  usersEntityDao.findUsersEntityByuNumber(UNumber).size()
       return list_size != 0
   }


}
