package org.yee.springboot.mybatis.postgres;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yee.springboot.mybatis.postgres.dao.model.UserInfo;
import org.yee.springboot.mybatis.postgres.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * package: org.yee.springboot.mybatis.postgres
 * class: UserInfoTest
 * describe:
 *
 * @author: brucewong
 * time: 07/02/2018 11:25
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserInfoTest {

    @Autowired
    UserInfoService userInfoService;

    @Test
    public void add() throws Exception {
        List<UserInfo> userInfoList = new ArrayList<UserInfo>(10);
        UserInfo userInfo;
        for (int i = 0; i < 10; i++) {
            userInfo = new UserInfo();
            userInfo.setUserName("yee" + i);
            userInfo.setId(Long.parseLong(i + ""));
            userInfoList.add(userInfo);
        }

        int count = userInfoService.batchAddUserInfo(userInfoList);
        assert (count == 10);
    }
    @Test
    public void addRollBack() throws Exception {
        List<UserInfo> userInfoList = new ArrayList<UserInfo>(10);
        UserInfo userInfo;
        for (int i = 0; i < 10; i++) {
            userInfo = new UserInfo();
            userInfo.setUserName("yee" + i);
            userInfo.setId(Long.parseLong(i + ""));
            userInfoList.add(userInfo);
        }

        int count = userInfoService.batchAddUserInfoRollback(userInfoList);
        assert (count == 10);
    }
}
