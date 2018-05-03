package org.yee.springboot.mybatis.postgres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yee.springboot.mybatis.postgres.dao.model.UserInfo;
import org.yee.springboot.mybatis.postgres.service.UserInfoService;

import java.util.List;

/**
 * package: org.yee.springboot.mybatis.postgres.controller
 * class: UserInfoController
 * describe:
 *
 * @author: brucewong
 * time: 07/02/2018 14:02
 **/
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserInfo> ListUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Long.parseLong("10000"));
        userInfo.setUserName("yee");
        return userInfoService.listUserInfo(userInfo);
    }
}
