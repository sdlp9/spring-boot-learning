package org.yee.springboot.mybatis.postgres.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yee.springboot.mybatis.postgres.dao.mapper.UserInfoDao;
import org.yee.springboot.mybatis.postgres.dao.model.UserInfo;
import org.yee.springboot.mybatis.postgres.dao.model.UserInfoExample;
import org.yee.springboot.mybatis.postgres.service.UserInfoService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * package: org.yee.springboot.mybatis.postgres.service.impl
 * class: UserInfoServiceImpl
 * describe:
 *
 * @author: brucewong
 * time: 07/02/2018 11:36
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public int addUserInfo(UserInfo userInfo) {
        return userInfoDao.insert(userInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchAddUserInfo(List<UserInfo> userInfos) throws Exception {
        int size = userInfos.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            addUserInfo(userInfos.get(i));
            count++;
        }
        return count;
    }
    class XExpection extends Exception{

    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public int batchAddUserInfoRollback(List<UserInfo> userInfos) throws Exception {
            int size = userInfos.size();
            int count = 0;
            for (int i = 0; i < size; i++) {
                addUserInfo(userInfos.get(i));
                if (i == 3) {
                    throw new XExpection();
                }
                count++;
            }
        return count;
    }

    @Override
    public int modifyUserInfo(UserInfo userInfo) {
        UserInfoExample up = new UserInfoExample();
        up.createCriteria().andIdEqualTo(userInfo.getId());

        return userInfoDao.updateByExample(userInfo, up);
    }

    @Override
    public int removeUserInfo(UserInfo userInfo) {
        UserInfoExample up = new UserInfoExample();
        up.createCriteria().andIdEqualTo(userInfo.getId());

        return userInfoDao.delete(userInfo);
    }

    @Override
    public UserInfo getUserInfo(UserInfo userInfo) {
        UserInfoExample up = new UserInfoExample();
        up.createCriteria().andUserNameLike(userInfo.getUserName());
        List<UserInfo> listUser = userInfoDao.selectByExample(up);
        return listUser.get(0);
    }

    @Override
    public List<UserInfo> listUserInfo(UserInfo userInfo) {
        UserInfoExample up = new UserInfoExample();
        up.createCriteria().andUserNameLike(userInfo.getUserName());
        List<UserInfo> listUser = userInfoDao.selectByExample(up);
        return listUser;
    }

    public Page pageUserInfo(UserInfo userInfo, int pNum, int pSize) {
        UserInfoExample up = new UserInfoExample();
        up.createCriteria().andUserNameLike(userInfo.getUserName());
        Map<String, Object> data = new HashMap<>();
        Integer pageNum = pNum;
        Integer pageSize = pSize;
        Page page = PageHelper.startPage(pageNum, pageSize, true);
        List<UserInfo> list = userInfoDao.selectByExample(up);
        data.put("total", page.getTotal());
        data.put("nowPage", pageNum);
        data.put("data", list);
        return page;
    }
}