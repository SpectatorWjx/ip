package com.example.ip.controller;

import com.example.ip.service.IPService;
import com.example.ip.util.Render;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wjx
 * @Date:2018/12/25
 * @Description:
 */
@Controller
public class IPController {
    @Autowired
    IPService ipService;
    /**
     * 删除
     */
    @GetMapping("/")
    @ResponseBody
    public Render delete(HttpServletRequest request) throws IOException {
        ipService.getIP(request);
        return Render.success("没问题");
    }

}
