package com.example.ip.service;

import com.example.ip.po.IPPo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wjx
 * @Date:2018/12/26
 * @Description:
 */
public interface IPService {
    IPPo getIP(HttpServletRequest request);
}
