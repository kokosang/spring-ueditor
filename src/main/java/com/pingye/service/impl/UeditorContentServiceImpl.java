package com.pingye.service.impl;

import com.pingye.dao.UeditorDao;
import com.pingye.entity.ContentEntity;
import com.pingye.service.UeditorContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 * @author huping
 * @create 2018-10-27 22:38
 **/
@Service
public class UeditorContentServiceImpl implements UeditorContentService {

    @Autowired
    private UeditorDao ueditorDao;


    @Override
    public void saveContent(String content) {

        ContentEntity entity = new ContentEntity();
        entity.setContent(content);
        ueditorDao.insert(entity);

    }
}
