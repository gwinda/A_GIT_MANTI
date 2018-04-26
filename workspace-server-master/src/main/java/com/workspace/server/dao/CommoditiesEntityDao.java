package com.workspace.server.dao;

import com.workspace.server.model.CommoditiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 51998 on 2018/2/26.
 */
@Repository
public interface CommoditiesEntityDao extends JpaRepository<CommoditiesEntity, Long> {

    List<CommoditiesEntity> findCommoditiesEntityByCLink(String CLink);
    CommoditiesEntity findCommoditiesEntityBycid(int cid);



}
