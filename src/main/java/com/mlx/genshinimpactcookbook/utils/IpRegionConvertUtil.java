package com.mlx.genshinimpactcookbook.utils;

import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.List;

/**
 * @author Santa Antilles
 * @Date 2023/12/9-0:15
 */
public class IpRegionConvertUtil {
    private static final Searcher searcher;

    static {
        try {
            searcher = Searcher.newWithFileOnly("./src/main/resources/ip2region/ip2region.xdb");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String ipConvertToAddress(String ip){
        try {
            String plainConverted = searcher.search(ip);
            List<String> ipList = StringUtils.str2List(plainConverted, "\\|", true, true);
            if(ipList.get(0).equals("0")){
                return "内网ip";
            }else{
                return ipList.get(2);
            }
        } catch (Exception e) {
            return "未知";
        }
    }
}
