package com.nny.Demo.IOLearn;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class FileIOLearn {
    public static void main(String args[]) throws IOException {
        m2();
    }

    /**
     * 随机访问文件
     */
    public static void m1() throws IOException{

        Path file = null;

        String s = "I was here!\n";

        byte data[] = s.getBytes();

        /**
         * 有字节，用于通道I/O输出
         */
        ByteBuffer out = ByteBuffer.wrap(data);

        /**
         * 空的，用于通道I/O输入
         */
        ByteBuffer copy = ByteBuffer.allocate(12);

        try (FileChannel fc = (FileChannel.open(file, READ, WRITE))) {

            /**
             * 读取文件的前12个字节
             */
            int nread;

            do {
                nread = fc.read(copy);
            } while (nread != -1 && copy.hasRemaining());

            /**
             * 设置通道的位置，0
             * 把字节写入文件
             */
            fc.position(0);

            while (out.hasRemaining())
                fc.write(out);

            out.rewind();

            /**
             * 设置通道的位置,文件末尾
             * 通道I/O输出
             */
            long length = fc.size();

            fc.position(length-1);

            copy.flip();

            while (copy.hasRemaining())
                fc.write(copy);

            while (out.hasRemaining())
                fc.write(out);
        }
    }

    /**
     * 列出所有目录内容
     */
    public static void m2() throws IOException{

        Path dir = Paths.get(".");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {
                System.out.println(file.getFileName());
            }
        }
        catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }
    }


    /**
     * 自定义目录列表过滤器
     * 得到所有子目录
     * @throws IOException
     */
    public static void m3() throws IOException{

        /**
         * 使用匿名类
         */
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {

            public boolean accept(Path file) throws IOException {
                return (Files.isDirectory(file));
            }

        };

        /**
         * 使用创建好的filter进行过滤
         */
        Path dir = null;

        try (DirectoryStream<Path>
                     stream = Files.newDirectoryStream(dir, filter)) {
            for (Path entry: stream) {
                System.out.println(entry.getFileName());
            }
        }
    }



}
