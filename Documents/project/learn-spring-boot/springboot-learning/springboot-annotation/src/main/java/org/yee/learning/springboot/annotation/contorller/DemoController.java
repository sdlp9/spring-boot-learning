package org.yee.learning.springboot.annotation.contorller;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yee.learning.springboot.annotation.service.DemoService;

/**
 * package: org.yee.learning.springboot.annotation.contorller
 * class: DemoController
 * describe:
 *
 * @author: brucewong
 * time: 09/02/2018 11:51
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void testAnnotation() {
        System.out.printf(DateFormat.getDateTimeInstance().format(new Date()));
        demoService.testAnnotation1();
        demoService.testAnnotation2();
        demoService.testAnnotation3();
    }

}
