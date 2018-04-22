package com.workspace.server.service

import com.workspace.server.dao.UserCommoditiesLogEntityDao
import com.workspace.server.model.UsercommoditylogEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 51998 on 2018/2/26.
 */
@Service
@Transactional
class UserCommditiesEntityService { //用户已订阅商品记录

    @Autowired UserCommoditiesLogEntityDao UsercomDao

    UsercommoditylogEntity findUsercommoditylogEntityByucId(int UCId){
        return UsercomDao.findUsercommoditylogEntityByucId(UCId)
    }

    List<UsercommoditylogEntity> findUsercommoditylogEntityByuId(int UId){
        return UsercomDao.findUsercommoditylogEntityByuId(UId)
    }
    List<UsercommoditylogEntity> findUsercommoditylogEntityBycId(int CId){
        return UsercomDao.findUsercommoditylogEntityBycId(CId)
    }

   boolean exists (int  UId) {
       def list_size=  UsercomDao.findUsercommoditylogEntityByuId(UId).size()
       return list_size != 0
   }
    boolean existsCUId (int  CUId) {
        def list_size=  UsercomDao.(CUId).size()
        return list_size != 0
    }
    boolean existsCId (int  CId) {
        def list_size=  UsercomDao.findUsercommoditylogEntityBycId(CId).size()
        return list_size != 0
    }


}
