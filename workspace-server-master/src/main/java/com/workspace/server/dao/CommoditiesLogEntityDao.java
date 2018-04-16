package com.workspace.server.dao;

import com.workspace.server.model.CommoditiesLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 51998 on 2018/2/26.
 */
@Repository
public interface CommoditiesLogEntityDao extends JpaRepository<CommoditiesLogEntity, Long> {

    List<CommoditiesLogEntity> findCommoditiesLogEntityBycid(int cid);

    CommoditiesLogEntity findCommoditiesLogEntityByclId(int clId);


}
