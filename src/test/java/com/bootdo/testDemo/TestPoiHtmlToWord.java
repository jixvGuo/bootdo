package com.bootdo.testDemo;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import org.ddr.poi.html.HtmlRenderPolicy;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestPoiHtmlToWord {

    public static void test() throws IOException {
        HtmlRenderPolicy htmlRenderPolicy = new HtmlRenderPolicy();
        Configure config = Configure.builder()
                .bind("content", htmlRenderPolicy)
                .build();
        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("content", "<p>aaaaaa</a><p><img alt=\"下载.jpg\" class=\"selected\" src=\"http://123.57.10.88:5555/files/258bc607-3f9d-4df7-84fb-3778988e7bb7.jpg\" data-image-size=\"640,512\"></p>");
//        datas.put("mainCompleteUserInfo", "<p>是发生的深粉色分水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费</p><p>是发生的深粉色分水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费<br></p><p>是发生的深粉色分水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费</p><p>是发生的深粉色分水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费</p><p><img alt=\"下载.jpg\" src=\"/files/f4c0d721-b408-48a6-9bfb-84559fe81328.jpg\" data-image-size=\"640,512\"><br></p>");
//        datas.put("mainCompleteUserInfo", "aaaa");
        String templatePath = "D:\\tmp\\word\\word_team.docx";
//        String templatePath = "D:\\tmp\\word\\testhtml.docx";
//        String templatePath = "D:\\tmp\\testhtml.docx";
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(datas);
        String createWordPath = "D:\\tmp\\testhtml_aaaaaa.docx";
        FileOutputStream out = new FileOutputStream(createWordPath);
        template.write(out);
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
         test();
    }
}
