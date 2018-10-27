package com.pingye.dao;

import com.pingye.entity.ContentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${DESCRIPTION}
 * @author huping
 * @create 2018-10-27 22:39
 **/
@Mapper
public interface UeditorDao {

    void insert(ContentEntity entity);
}
