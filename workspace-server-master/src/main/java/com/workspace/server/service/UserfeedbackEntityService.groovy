package com.workspace.server.service

import com.workspace.server.dao.UserfeedbackEntityDao
import com.workspace.server.dao.UsersEntityDao
import com.workspace.server.model.UserEntity
import com.workspace.server.model.UserfeedbackEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 51998 on 2018/2/26.
 */
@Service
@Transactional
class UserfeedbackEntityService {

    @Autowired UserfeedbackEntityDao userfeedbackEntityDao

    List<UserfeedbackEntity> findUserfeedbackEntityByuId (int  uid) {
        return userfeedbackEntityDao.findUserfeedbackEntityByuId(uid)
    }
    UserfeedbackEntity findUserfeedbackEntityByufId(int UFId) {
        return userfeedbackEntityDao.findUserfeedbackEntityByufId(UFId)
    }


   boolean exists (int uid) {
       def list_size=  userfeedbackEntityDao.findUserfeedbackEntityByuId(uid).size()
       return list_size != 0
   }


}
