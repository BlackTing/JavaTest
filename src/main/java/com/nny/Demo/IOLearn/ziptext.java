package com.nny.Demo.IOLearn;

//使用ZipInputStream和ZipOutputStream简单压缩和解压缩

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ziptext{

    private String zipname; //待处理文件名
    private String zipdirname; //目标目录名（格式：D:\\dir\\）
    private int zipUnotNo; //判断是压缩还是解压缩 0 将压缩文件解压缩 1 将文件压缩。

    public ziptext(String zipname,String zipfilename,int zipUnorNo) {
        this.zipname = zipname;
        this.zipdirname = zipfilename;
        this.zipUnotNo = zipUnorNo;
        if(zipUnorNo==1){
            try{
                File zipfile = new File(zipfilename);
                ZipOutputStream zfo = new ZipOutputStream(new FileOutputStream(zipname));//zip输出流
                System.out.println(zipfile.listFiles());
//                for(File i : zipfile.listFiles()){
//                    byte [] buf = new byte[(int)i.length()];
//                    FileInputStream fo = new FileInputStream(i.getPath());
//                    // read file into byte[]
//                    fo.read(buf);
//                    zfo.putNextEntry(new ZipEntry(i.getName()));//写入zip输出流条目
//                    // write byte[] into file
//                    zfo.write(buf);
//                    fo.close();
//                }
                zfo.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            //进行解压缩操作
            try{
                ZipInputStream zfo2 = new ZipInputStream(new FileInputStream(zipname)); //zip输入流
                ZipEntry unfile;
                while(true){
                    unfile = zfo2.getNextEntry();
                    if(unfile==null){
                        break;
                    }
                    BufferedInputStream bzfo2 = new BufferedInputStream(zfo2,(int)unfile.getSize());//包装zip输入流
                    byte [] buf2 = new byte[(int)unfile.getSize()];
                    //read the file into byte[] through inputstream
                    bzfo2.read(buf2);
                    FileOutputStream unfin = new FileOutputStream(zipdirname + unfile.getName());
                    // write the byte[] into file through outputstream
                    unfin.write(buf2);
                    unfin.close();
                }
                zfo2.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] arge){
        //使用ZipInputStream和ZipOutputStream简单压缩和解压缩
        new ziptext("F:\\write.zip","F:\\",0);
    }

}
