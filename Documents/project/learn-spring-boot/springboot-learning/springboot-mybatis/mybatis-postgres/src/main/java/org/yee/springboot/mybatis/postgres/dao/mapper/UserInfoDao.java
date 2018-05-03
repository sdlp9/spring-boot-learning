package org.yee.springboot.mybatis.postgres.dao.mapper;

import org.yee.springboot.mybatis.postgres.dao.model.UserInfo;
import org.yee.springboot.mybatis.postgres.dao.model.UserInfoExample;
import org.yee.springboot.mybatis.postgres.dao.uitl.MyMapper;

public interface UserInfoDao extends MyMapper<UserInfo> {
    long countByExample(UserInfoExample example);
}