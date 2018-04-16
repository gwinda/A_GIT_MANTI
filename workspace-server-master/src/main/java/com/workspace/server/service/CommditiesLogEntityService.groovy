package com.workspace.server.service

import com.workspace.server.dao.CommoditiesEntityDao
import com.workspace.server.dao.CommoditiesLogEntityDao
import com.workspace.server.model.CommoditiesLogEntity
import com.workspace.server.model.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 51998 on 2018/2/26.
 */
@Service
@Transactional
class CommditiesLogEntityService {

    @Autowired CommoditiesLogEntityDao CommLodEntityDao

    List<CommoditiesLogEntity> findCommoditiesLogEntityBycid (int cid) {
        return CommLodEntityDao.findCommoditiesLogEntityBycid(cid)
    }
    CommoditiesLogEntity findUsersEntityLogByclId(int clId) {
        return CommLodEntityDao.findCommoditiesLogEntityByclId(clId)
    }

   boolean exists (int cid) {
       def list_size=  CommLodEntityDao.findCommoditiesLogEntityBycid(cid).size()
       return list_size != 0
   }


}
