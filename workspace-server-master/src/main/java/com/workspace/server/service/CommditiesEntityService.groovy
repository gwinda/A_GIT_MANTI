package com.workspace.server.service

import com.workspace.server.dao.CommoditiesEntityDao
import com.workspace.server.model.CommoditiesEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 51998 on 2018/2/26.
 */
@Service
@Transactional
class CommditiesEntityService { //商品记录

    @Autowired CommoditiesEntityDao CommEntityDao

    List<CommoditiesEntity> findCommoditiesEntityByCLink (String CLink) {
        return CommEntityDao.findCommoditiesEntityBycLink(CLink)
    }
    CommoditiesEntity findCommoditiesEntityBycid(int cid) {
        return CommEntityDao.findCommoditiesEntityBycid(cid)
    }
    List<CommoditiesEntity> findCommoditiesEntityBycNameContaining( String cName){
        return CommEntityDao.findCommoditiesEntityBycNameContaining(cName)
    }

   boolean exists (String CLink) {
       def list_size=  CommEntityDao.findCommoditiesEntityBycLink(CLink).size()
       return list_size != 0
   }


}
