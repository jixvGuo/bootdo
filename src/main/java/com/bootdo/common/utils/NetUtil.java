package com.bootdo.common.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {

    public static boolean checkSourceIsOk(String url) {
        if(StringUtils.isBlank(url)) {
            return false;
        }
        try {
            URL urlFie = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) urlFie.openConnection();
            huc.setRequestMethod("GET");
            huc.connect();

            int code = huc.getResponseCode();
            if (code == 404){
                return false ;
            }else {
                return true ;
            }
        }catch (IOException ex){
        }
        return false;
    }
}
