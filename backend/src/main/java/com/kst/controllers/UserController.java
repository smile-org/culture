package com.kst.controllers;

import com.kst.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(@RequestBody Map body) {
        logger.debug("RequestBody: " + body);
        String userName = (String) body.get("username");
        String password = (String) body.get("password");
        Map result = userService.login(userName, password);
        logger.debug("Response: " + result);
        return result;
    }
}
