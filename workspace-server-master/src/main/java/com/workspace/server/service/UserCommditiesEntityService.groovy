package com.workspace.server.service

import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import com.workspace.server.dao.UserCommoditiesLogEntityDao

import com.workspace.server.model.UsercommoditylogEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import java.awt.print.Book
import java.awt.print.Pageable

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
    /**
     *
     * @Title: getList
     * @Description: 从数据库中获取所有商品类型列表
     * @param pageNum 当前页
     * @param pageSize 当前页面展示数目
     * @return
     * @throws Exception
     */
    public List<UsercommoditylogEntity>  getList(int pageNum, int pageSize,int Uid) throws Exception {
        //使用分页插件,核心代码就这一行
      // PageHelper.startPage(pageNum, pageSize);
       // 获取
        int mincout = (pageNum -1 )*pageSize
        int maxcout = (pageNum )*pageSize-1
        List<UsercommoditylogEntity> typeList = UsercomDao.findUsercommoditylogEntityByuId(Uid)
        List<UsercommoditylogEntity> selectList = new  ArrayList<UsercommoditylogEntity>()
        typeList.sort()
        typeList.eachWithIndex{  entry, i ->
            def currentcount = i
            if(currentcount ==mincout &&maxcout>=mincout){
                selectList.add(entry)
                mincout++
            }
        }
        return selectList;
    }

}
