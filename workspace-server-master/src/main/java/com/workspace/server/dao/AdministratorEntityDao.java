package com.workspace.server.dao;

import com.workspace.server.model.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 51998 on 2018/2/26.
 */
@Repository
public interface AdministratorEntityDao extends JpaRepository<AdministratorEntity, Long> {

    List<AdministratorEntity> findAdministratorEntityByaNumber(String aNumber);
    AdministratorEntity findAdministratorEntityByaid(int AId);


}
