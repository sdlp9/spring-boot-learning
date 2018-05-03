package org.yee.springboot.mybatis.postgres.service;

import org.yee.springboot.mybatis.postgres.dao.model.UserInfo;

import java.util.List;

/**
 * package: org.yee.springboot.mybatis.postgres.service
 * class: UserInfoService
 * describe:
 *
 * @author: brucewong
 * time: 07/02/2018 11:30
 **/
public interface UserInfoService {

    /**
     * 添加用户
     *
     * @return
     */
    int addUserInfo(UserInfo userInfo);

    /**
     * 批量添加用户
     *
     * @return
     */
    int batchAddUserInfo(List<UserInfo> userInfos) throws Exception;

    /**
     * 批量添加用户(异常回滚)
     *
     * @return
     */
    int batchAddUserInfoRollback(List<UserInfo> userInfos) throws Exception;

    /**
     * 更新用户信息
     *
     * @return
     */
    int modifyUserInfo(UserInfo userInfo);

    /**
     * 移除用户信息
     *
     * @return
     */
    int removeUserInfo(UserInfo userInfo);

    /**
     * 获取用户信息
     *
     * @param userInfo
     * @return
     */
    UserInfo getUserInfo(UserInfo userInfo);

    /**
     * 获取用户列表
     *
     * @param userInfo
     * @return
     */
    List<UserInfo> listUserInfo(UserInfo userInfo);


}
