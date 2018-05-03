package org.yee.springboot.mybatis.postgres;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yee.springboot.mybatis.postgres.dao.uitl.MyMapper;

/**
 * package: org.yee.springboot.mybatis.postgres
 * class: Application
 * describe:
 *
 * @author: brucewong
 * time: 06/02/2018 14:01
 **/
@SpringBootApplication
@MapperScan(basePackages = {"org.yee.springboot.mybatis.postgres.dao.mapper"},
        markerInterface = MyMapper.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
