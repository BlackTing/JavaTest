package com.nny.Demo.IOLearn;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * 文件I/O
 * Path类
 * Path操作
 */
public class PathLearn {

    public static void main(String[] args){
        m6();
    }

    /**
     * 获取路径的信息
     */
    public static void m0() {

        /**
         * 在Windows系统中使用path1，在Solaris系统中使用path
         * 虽然只是对路径本身进行操作，不访问文件系统，但是语法还是要和文件系统兼容。
         */
        Path path1 = Paths.get("C:\\home\\joe\\foo");

        Path path = Paths.get("/home/joe/foo");

        String a = path.toString(); // /home/joe/foo

        Path b = path.getFileName();// foo

        Path c = path.getName(0);// home 距离根节点最近的名称元素

        int d = path.getNameCount();//3

        Path e = path.subpath(0, 2);// home/joe 包括索引0，但不包括索引2

        Path f = path.getParent();// /home/joe

        Path g = path.getRoot();// /

        System.out.println(a);
    }

    /**
     * 转换Path
     */
    public static void m1(){
        //转换成浏览器能打开的字符串
        Path p1 = Paths.get("/home/logfile");
        URI a = p1.toUri(); // file:///home/logfile

        //转换成绝对路径
        Path inputPath = Paths.get("foo");
        Path fullPath = inputPath.toAbsolutePath(); // /Users/litingyang/Documents/Liting/javaTest/foo

        //转换成文件的真实路径
        Path path = Paths.get("xanad.txt");
        Path fp = null; ///Users/litingyang/Documents/Liting/javaTest/xanadu.txt
        try {
            fp = path.toRealPath();
        }
        catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        }
        catch (IOException x) {
            System.err.format("%s%n", x);
        }

        System.out.println(fp);
    }

    /**
     * 合并路径
     */
    public static void m2(){
        Path p1 = Paths.get("/home/joe/foo");
        Path p2 = p1.resolve("bar"); // /home/joe/foo/bar

        //传入绝对路径，返回传入路径
        Path p3 = Paths.get("foo").resolve("/home/joe"); // /home/joe

        System.out.println(p3);
    }

    /**
     * 生成两路径间的路径
     */
    public static void m3(){
        Path p1 = Paths.get("joe");
        Path p2 = Paths.get("sally");

        Path p1_to_p2 = p1.relativize(p2);// ../sally
        Path p2_to_p1 = p2.relativize(p1);// ../joe

        Path p3 = Paths.get("home");
        Path p4 = Paths.get("home/sally/bar");

        Path p3_to_p4 = p3.relativize(p4);// sally/bar
        Path p4_to_p3 = p4.relativize(p3);// ../..

        System.out.println(p1);
        /**
         * 绝对路径最前面都有/，相对路径最前面都没有/？
         */
    }

    /**
     * 比较两个路径
     */
    public static void m4(){

        Path path = Paths.get("/home/joe/foo");
        Path otherPath = Paths.get("/home/joe/foo");
        Path beginning = Paths.get("/home");
        Path ending = Paths.get("foo");

        boolean a = path.equals(otherPath);
        boolean b = path.startsWith(beginning);
        boolean c = path.endsWith(ending);

        System.out.println(c);

        /**
         * 迭代
         * 如果是相对路径，不会转换为绝对路径
         */
        for(Path name : ending){
            System.out.println(name);
        }


    }

    /**
     * Files
     * 检查、删除、复制、移动文件或目录
     */
    public static void m5(){
        Path p1 = Paths.get("xanadu.txt");
        Path p2 = Paths.get("xana.txt");
        Path p3 = Paths.get("oldDirectory");
        Path p4 = Paths.get("oldT/newDirectory/one.txt");
        Path p5 = Paths.get("one.txt");

        boolean a = Files.exists(p1);

        boolean b = Files.notExists(p1);

        boolean isRegularExecutableFile = Files.isRegularFile(p1) & Files.isReadable(p1) & Files.isExecutable(p1);

        boolean c = true;
        try{
            c = Files.isSameFile(p1,p2);

            Files.copy(p1, p2, REPLACE_EXISTING);

            //Files.move(p3,p4,REPLACE_EXISTING);
            /**
             * 移动目录(包括重命名目录)时，目录内容也跟着移动了
             */
            Files.move(p4,p5);
        }
        catch(IOException e){
            e.printStackTrace();
        }


        System.out.println(c);
    }

    /**
     * Files
     * 元数据
     */
    public static void m6(){
        Path file = Paths.get("xanadu.txt");
        BasicFileAttributes attr = null;

        try{
            attr = Files.readAttributes(file, BasicFileAttributes.class);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("creationTime: " + attr.creationTime());
        System.out.println("lastAccessTime: " + attr.lastAccessTime());
        System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());

        /**
         * POSIXFileAttributes
         */
        try{
            PosixFileAttributes attr1 = Files.readAttributes(file,PosixFileAttributes.class);

            attr1.owner().getName();
            attr1.group().getName();
            String a = PosixFilePermissions.toString(attr1.permissions());
            PosixFilePermissions.fromString(a);

            System.out.println(a);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}


