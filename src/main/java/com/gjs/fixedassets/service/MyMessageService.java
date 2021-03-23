package com.gjs.fixedassets.service;


import com.gjs.fixedassets.entity.Mymessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyMessageService {
    List<Mymessage> selectMessageLimit(Integer userId);

    List<Mymessage> selectMessagePage(@Param("userId") Integer userId, @Param("page") Integer page, @Param("limit") Integer limit);

    List<Mymessage> selectMessageCount(Integer userId);

}