package org.yee.learning.springboot.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * package: org.yee.learning.springboot.demo.controller
 * class: Handler
 * describe:
 *
 * @author: brucewong
 * time: 05/02/2018 14:59
 **/
@RestController
@RequestMapping("time")
public class Handler {
    @RequestMapping("/echo")
    String echo() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return "现在时间是：" + DateFormat.getDateTimeInstance().format(new Date());
    }
}
