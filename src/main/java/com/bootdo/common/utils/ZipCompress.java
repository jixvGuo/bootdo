package com.bootdo.common.utils;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Jhonbo on 2017/12/9.
 */
public class ZipCompress {
    public static void zip(String zipFileName, String sourceFileName) {
        try {
            compress(zipFileName, sourceFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void compress(String zipFileName, String sourceFileName) throws Exception {
        // 定义要压缩的文件夹
        File file = new File(sourceFileName);
        // 定义压缩文件名称
        File zipFile = new File(zipFileName);
        // 定义文件输入流
        InputStream input = null;
        // 声明压缩流对象
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
//        zipOut.setComment("www.mldnjava.cn") ;  // 设置注释
        int temp = 0;
        // 判断是否是文件夹
        if (file.isDirectory()) {
            // 列出全部文件
            File lists[] = file.listFiles();
           /* for (int i = 0; i < lists.length; i++) {
                // 定义文件的输入流
                input = new FileInputStream(lists[i]);
                // 设置ZipEntry对象
                zipOut.putNextEntry(new ZipEntry(file.getName()
                        + File.separator + lists[i].getName()));
                // 读取内容
                while ((temp = input.read()) != -1) {
                    // 读取内容
                    zipOut.write(temp);
                }
                // 关闭输入流
                input.close();
            }*/

            WritableByteChannel writableByteChannel = Channels.newChannel(zipOut);
            for(int i=0; i< lists.length; i++) {
                long startTime = System.currentTimeMillis();
                File iFile = lists[i];
                if(!iFile.exists()) {
                    continue;
                }
                FileChannel fileChannel = new FileInputStream(iFile).getChannel();
                zipOut.putNextEntry(new ZipEntry(file.getName()
                        + File.separator + lists[i].getName()));
                fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
                System.out.println((System.currentTimeMillis() - startTime) + "," + iFile.getName());
            }

        }
        zipOut.close();    // 关闭输出流
    }


    public static void compress(String zipPath,String zipFileName, List<String> filePathList) throws Exception {
        // 定义要压缩的文件夹
        // 定义压缩文件名称
        File zipFile = new File(zipPath + "/" + zipFileName + ".zip");
        // 声明压缩流对象
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
//        zipOut.setComment("www.mldnjava.cn") ;  // 设置注释
        WritableByteChannel writableByteChannel = Channels.newChannel(zipOut);
        for(int i=0; i<filePathList.size(); i++) {
            String fPath = filePathList.get(i);
            File iFile = new File(fPath);
            if(!iFile.exists()) {
                continue;
            }
            FileChannel fileChannel = new FileInputStream(fPath).getChannel();
            zipOut.putNextEntry(new ZipEntry(zipFileName + File.separator + iFile.getName()));
            fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
        }
        zipOut.close();    // 关闭输出流
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try {
            ZipCompress.zip("F:\\TEMP\\tempFile11aaaa1.zip", "F:\\TEMP\\中国石油天然气管道通信电力工程有限公司_1280_1616303537078");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
