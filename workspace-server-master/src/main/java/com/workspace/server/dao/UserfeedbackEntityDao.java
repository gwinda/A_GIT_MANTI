package com.workspace.server.dao;

import com.workspace.server.model.UserfeedbackEntity;
import com.workspace.server.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 51998 on 2018/2/26.
 */
@Repository
public interface UserfeedbackEntityDao extends JpaRepository<UserfeedbackEntity, Long> {

    List<UserfeedbackEntity> findUserfeedbackEntityByuId(int uId);
    UserfeedbackEntity findUserfeedbackEntityByufId(int ufId);


}
