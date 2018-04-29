package com.workspace.server.dao;

import com.github.pagehelper.Page;
import com.workspace.server.model.UsercommoditylogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 51998 on 2018/2/26.
 */

@Repository
public interface UserCommoditiesLogEntityDao extends JpaRepository<UsercommoditylogEntity, Long> ,JpaSpecificationExecutor<UsercommoditylogEntity> {

    UsercommoditylogEntity findUsercommoditylogEntityByucId(int UCId);

    List<UsercommoditylogEntity> findUsercommoditylogEntityByuId(int UId);

    List<UsercommoditylogEntity> findUsercommoditylogEntityBycId(int CId);

}
