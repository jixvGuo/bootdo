package com.bootdo.cpe.utils;

import com.bootdo.common.utils.NetUtil;
import com.bootdo.cpe.petroleum_engineering_award.domain.QCImageObj;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bootdo.common.config.Constant.*;

/**
 * 公共工具类
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-10 22:03
 */
public class CpeCommonUtils {

    public static boolean isCompanyUserRole(List<Long> userRoleIds) {
        boolean isCompanyFlg = userRoleIds.contains(ROLE_ENTERPRISE_SCIENCE_ID) || userRoleIds.contains(ROLE_ENTERPRISE_ENGINEER_ID)
                || userRoleIds.contains(ROLE_ENTERPRISE_QC_ID) || userRoleIds.contains(ROLE_ENTERPRISE_SURVER_ID)
                || userRoleIds.contains(ROLE_GONGFA_ENTERPRISE_ID);
        return isCompanyFlg;
    }
    public static boolean isCompanyUserRole(Long roleId) {
        List<Long> userRoleIds = new ArrayList<>();
        userRoleIds.add(roleId);
        return isCompanyUserRole(userRoleIds);
    }


    private static final Pattern IMAGE_TAG_PATTERN = Pattern.compile("<(img|IMG)(.*?)>");
    private static Pattern IMAGE_SRC_PATTERN = Pattern.compile("(src|SRC)=\"(.*?)\"");
    private static Pattern IMAGE__SRC_PATTERN = Pattern.compile("(_src|_SRC)=\"(.*?)\"");


    public static Pictures.PictureBuilder getPicBuilderFromUrl(String imgPath, PictureType type) throws FileNotFoundException {
        Pictures.PictureBuilder builder = Pictures.ofUrl(imgPath, type);
        return builder;
    }
    public static List<QCImageObj> getImages(String desInfo,String baseUrl)   {
        List<QCImageObj> images = new ArrayList<>();
        try {
            List<String> imgUrls = CpeCommonUtils.matchImgSrcTag(desInfo);

            if (imgUrls.size()  > 0){
                int allSize = imgUrls.size() ;
                for (int a = 0 ; a< allSize;a++){
                    String imgPath = "";
                    if (NetUtil.checkSourceIsOk(imgUrls.get(a))){
                          imgPath =  imgUrls.get(a);
                    }else {
                          imgPath = baseUrl+imgUrls.get(a);
                    }
                    Pictures.PictureBuilder builder = null;
                    try {
                        builder = getPicBuilderFromUrl(imgPath, PictureType.PNG);
                    }catch (FileNotFoundException ex) {
                    }
                    if(builder == null) {
                        continue;
                    }
                    Object obj = builder.size(117, 145).create();
                    QCImageObj img = new QCImageObj();
                    img.setImg(obj);
                    images.add(img);
                }
            }
        }catch (Exception e){

        }

        return images;
    }
    public static List<String> matchImgSrcTag(String srcStr) {

        List<String> targets = new ArrayList<>();

        // 针对src标签
        // 先匹配img标签
        Matcher imageTagMatcher = IMAGE_TAG_PATTERN.matcher(srcStr);
        while (imageTagMatcher.find()) {
            String image = imageTagMatcher.group(2).trim();
            // 获取src后面的内容
            Matcher imageSrcMatcher = IMAGE_SRC_PATTERN.matcher(image);
            String src = null;
            if (imageSrcMatcher.find()) {
                src = imageSrcMatcher.group(2).trim();
            }
            if (src == null || src.isEmpty()) {
                continue;
            }
            System.out.println("src:" + src);
            targets.add(src);
        }

        // 针对_src标签
        while (imageTagMatcher.find()) {
            String image = imageTagMatcher.group(2).trim();
            Matcher imageSrcMatcher = IMAGE__SRC_PATTERN.matcher(image);
            String src = null;
            if (imageSrcMatcher.find()) {
                src = imageSrcMatcher.group(2).trim();
            }
            if (src == null || src.isEmpty()) {
                continue;
            }
            System.out.println("_src_:" + src);
            targets.add( src);
        }

        return targets;
    }




}
