package com.example.cas_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

@SpringBootApplication
public class CasSpringbootApplication {

    public static void main(String[] args) {SpringApplication.run(CasSpringbootApplication.class, args); }

    @RequestMapping("/")
    public String index(Map<String, Object> map, HttpServletRequest request) {
        UserInfo userInfo =  (UserInfo) request.getSession().getAttribute("userInfo");
        map.put("userInfo", userInfo);
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
