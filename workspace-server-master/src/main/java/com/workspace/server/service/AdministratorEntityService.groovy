package com.workspace.server.service

import com.workspace.server.dao.AdministratorEntityDao
import com.workspace.server.dao.UsersEntityDao
import com.workspace.server.model.AdministratorEntity
import com.workspace.server.model.UsersEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by 51998 on 2018/2/26.
 */
@Service
@Transactional
class AdministratorEntityService {

    @Autowired AdministratorEntityDao AdministratorEntityDao

    List<AdministratorEntity> findAdministratorEntityByaNumber(String aNumber){
        AdministratorEntityDao.findAdministratorEntityByaNumber(aNumber)
    }
    AdministratorEntity findAdministratorEntityByaid(int AId){

        AdministratorEntityDao.findAdministratorEntityByaid(AId)
    }



   boolean exists (String aNumber) {
       def list_size=  AdministratorEntityDao.findAdministratorEntityByaNumber(aNumber).size()
       return list_size != 0
   }


}
