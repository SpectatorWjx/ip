package com.example.ip.service;

import com.example.ip.po.IPPo;
import com.example.ip.util.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wjx
 * @Date:2018/12/26
 * @Description:
 */
@Service
public class IPServiceImpl implements IPService {

    @Override
    public IPPo getIP(HttpServletRequest request) {
        IPPo ipPo = new IPPo();
        String ip = getClientIP(request);
        ipPo.setIp(ip);
        ipPo = getAddr(ipPo, ip);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        ipPo.setDate(date);

        String address = "ip:"+ipPo.getIp()+" addr:"+ipPo.getAddr()+" address:"+ipPo.getAddress()+" date:"+ipPo.getDate();
        WriteStringToFile(address);

        return ipPo;
    }


    public static String getMatcher(String regex, String source) {
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 根据IP获取地址
     *
     * @param IP
     */
    public IPPo getAddr(IPPo ipPo, String IP) {
        String sr = HttpRequest.sendPost("https://www.ipip.net/ip.html", "ip=" + IP);
        String pattern1 = "<td style=\"clear: both\">\\s*<span style=\"float: left; line-height: 40px; display: inline-block; width: 720px;\">.{0,30}</span>";
        String pattern2 = "<td style=\"clear: both;\">\\s*<span style=\"display: inline-block;text-align: center;width: 720px;float: left;line-height: 46px;\">.{0,50}<span";

        String pattern3 = "px;\">.{0,30}</span>";
        String pattern4 = "px;\">.{0,1000}<span";

        String result1 = getMatcher(pattern3, getMatcher(pattern1, sr));
        String result2 = getMatcher(pattern4, getMatcher(pattern2, sr));

        String addr = "未获取";
        String address = "未获取";
        if (result1.length() > 12 && result2.length() > 10) {
            addr = result1.substring(5, result1.length() - 7);
            address = result2.substring(5, result2.length() - 5);
        }

        ipPo.setAddr(addr);
        ipPo.setAddress(address);
        return ipPo;
    }


    public void WriteStringToFile(String str) {
        try {
            FileWriter fw = null;
            File file = new File("./address.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file,true);
            PrintWriter pw = new PrintWriter(fw);
            pw.append("\n"+str+"\n");
            pw.flush();
            try {
                fw.flush();
                pw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
