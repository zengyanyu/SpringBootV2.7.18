package com.zengyanyu.system.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩工具类
 *
 * @author zengyanyu
 */
public class ZipUtil {

    /**
     * 是否保留原来的目录结构
     * true:  保留目录结构;
     * false: 所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     */
    private static final boolean KeepDirStructure = true;
    private static final Logger log = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * 压缩成ZIP
     *
     * @param srcDir       压缩 文件/文件夹 路径
     * @param outPathFile  压缩 文件/文件夹 输出路径+文件名 D:/xx.zip
     * @param isDelSrcFile 是否删除原文件: 压缩前文件
     * @param suffix       后缀过滤
     */
    public static void toZip(String srcDir, String outPathFile, boolean isDelSrcFile, String suffix) throws Exception {
        long start = System.currentTimeMillis();
        try (FileOutputStream out = new FileOutputStream(outPathFile);
             ZipOutputStream zos = new ZipOutputStream(out);) {

            File sourceFile = new File(srcDir);
            if (!sourceFile.exists()) {
                throw new Exception("需压缩文件或者文件夹不存在");
            }
            compress(sourceFile, zos, sourceFile.getName(), suffix);
            if (isDelSrcFile) {
                delDir(srcDir);
            }
            log.info("原文件:{}. 压缩到:{}完成. 是否删除原文件:{}. 耗时:{}ms. ", srcDir, outPathFile, isDelSrcFile, System.currentTimeMillis() - start);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("zip error from ZipUtils: {}. ", e.getMessage());
            throw new Exception("zip error from ZipUtils");
        }
    }

    /**
     * 压缩成ZIP
     *
     * @param files        需要压缩的文件集合
     * @param outPathFile  压缩文件输出路径+文件名 D:/xx.zip
     * @param isDelSrcFile 是否删除原文件: 压缩前文件
     */
    public static void toZip(List<File> files, String outPathFile, boolean isDelSrcFile) throws Exception {
        long start = System.currentTimeMillis();
        try (FileOutputStream out = new FileOutputStream(outPathFile);
             ZipOutputStream zos = new ZipOutputStream(out);) {
            for (File file : files) {
                if (!file.exists()) {
                    log.warn("文件不存在: {}. 跳过压缩.", file.getAbsolutePath());
                    continue;
                }
                compress(file, zos, file.getName(), null);
            }
            if (isDelSrcFile) {
                for (File file : files) {
                    delFile(file);
                }
            }
            log.info("文件集合:{}. 压缩到:{}完成. 是否删除原文件:{}. 耗时:{}ms. ", files, outPathFile, isDelSrcFile, System.currentTimeMillis() - start);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("zip error from ZipUtils: {}. ", e.getMessage());
            throw new Exception("zip error from ZipUtils");
        }
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       压缩后的名称
     * @param suffix     后缀过滤
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, String suffix) throws Exception {
        byte[] buf = new byte[1024 * 1024];
        if (sourceFile.isFile()) {
            if (StringUtils.isNotEmpty(suffix)) {
                String[] splitArr = suffix.split(",");
                boolean shouldCompress = false;
                for (String s : splitArr) {
                    if (sourceFile.getName().endsWith(s)) {
                        shouldCompress = true;
                        break;
                    }
                }
                if (!shouldCompress) {
                    return;
                }
            }
            zos.putNextEntry(new ZipEntry(name));
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                if (KeepDirStructure) {
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    if (KeepDirStructure) {
                        compress(file, zos, name + "/" + file.getName(), suffix);
                    } else {
                        compress(file, zos, file.getName(), suffix);
                    }
                }
            }
        }
    }

    /**
     * 解压文件到指定目录
     *
     * @param zipPath
     * @param descDir
     * @throws IOException
     */
    public static void unZipFiles(String zipPath, String descDir) throws IOException {
        log.info("文件:{}. 解压路径:{}. 解压开始.", zipPath, descDir);
        long start = System.currentTimeMillis();
        ZipFile zip = null;

        try {
            File zipFile = new File(zipPath);
            log.error("需解压文件名称:{}", zipFile.getName());
            if (!zipFile.exists()) {
                throw new IOException("需解压文件不存在.");
            }
            File pathFile = new File(descDir);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            zip = new ZipFile(zipFile, Charset.forName("GBK"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    ZipEntry entry = (ZipEntry) entries.nextElement();
                    String zipEntryName = entry.getName();
                    log.info("要解压的文件名称为:{}", zipEntryName);
                    in = zip.getInputStream(entry);
                    String outPath = (descDir + File.separator + zipEntryName).replaceAll("\\\\", "/");
                    log.info("要解压的文件路径为:{}", outPath);
                    // 判断路径是否存在,不存在则创建文件路径
                    File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                    if (new File(outPath).isDirectory()) {
                        continue;
                    }
                    // 输出文件路径信息
                    out = new FileOutputStream(outPath);
                    byte[] buf1 = new byte[1024];
                    int len;
                    while ((len = in.read(buf1)) > 0) {
                        out.write(buf1, 0, len);
                    }
                } finally {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
            }
            log.info("文件:{}. 解压路径:{}. 解压完成. 耗时:{}ms. ", zipPath, descDir, System.currentTimeMillis() - start);
        } catch (Exception e) {
            log.info("文件:{}. 解压路径:{}. 解压异常:{}. 耗时:{}ms. ", zipPath, descDir, e, System.currentTimeMillis() - start);
            throw new IOException(e);
        } finally {
            if (zip != null) {
                zip.close();
            }
        }
    }

    /**
     * 解压文件到指定目录
     */
    public static void unZipFilesSuffix(String zipPath, String descDir, String suffix) throws IOException {
        log.info("文件:{}. 解压路径:{}. 解压开始.", zipPath, descDir);
        long start = System.currentTimeMillis();
        try {
            File zipFile = new File(zipPath);
            if (!zipFile.exists()) {
                log.error("需解压文件不存在,文件名称:{}", zipFile.getName());
                throw new IOException("需解压文件不存在.");
            }
            File pathFile = new File(descDir);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                String[] split = suffix.split(",");
                boolean flag = false;
                for (String s : split) {
                    if (zipEntryName.endsWith(s)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    continue;
                }
                log.info("要解压的文件名称为:{}", zipEntryName);
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + File.separator + zipEntryName).replaceAll("\\\\", "/");
                log.info("要解压的文件路径为:{}", outPath);
                // 判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                // 输出文件路径信息
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
            log.info("文件:{}. 解压路径:{}. 解压完成. 耗时:{}ms. ", zipPath, descDir, System.currentTimeMillis() - start);
        } catch (Exception e) {
            log.info("文件:{}. 解压路径:{}. 解压异常:{}. 耗时:{}ms. ", zipPath, descDir, e, System.currentTimeMillis() - start);
            throw new IOException(e);
        }
    }

    /**
     * 删除文件或文件夹以及文件夹下所有文件
     *
     * @param dirPath
     * @throws IOException
     */
    public static void delDir(String dirPath) throws IOException {
        log.info("删除文件开始:{}.", dirPath);
        long start = System.currentTimeMillis();
        try {
            File dirFile = new File(dirPath);
            if (!dirFile.exists()) {
                return;
            }
            if (dirFile.isFile()) {
                dirFile.delete();
                return;
            }
            File[] files = dirFile.listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                delDir(file.toString());
            }
            dirFile.delete();
            log.info("删除文件:{}. 耗时:{}ms. ", dirPath, System.currentTimeMillis() - start);
        } catch (Exception e) {
            log.info("删除文件:{}. 异常:{}. 耗时:{}ms. ", dirPath, e, System.currentTimeMillis() - start);
            throw new IOException("删除文件异常.");
        }
    }

    /**
     * 删除单个文件
     *
     * @param file
     * @throws IOException
     */
    private static void delFile(File file) throws IOException {
        if (file.isDirectory()) {
            delDir(file.getAbsolutePath());
        } else {
            if (file.exists()) {
                file.delete();
                log.info("删除文件:{}. ", file.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        toZip("D:/sourceCode", "D:/sourceCode/xxxx.zip", false, null);

//        List<File> arrayList = new ArrayList<>(Arrays.asList(new File("D:\\SoftwarePackage\\IDEA安装包和激活工具")));
//        toZip(arrayList, "D:/sourceCode/大文件.zip", false);

        unZipFiles("D://sourceCode/大文件.zip", "D:\\sourceCode/xxxxxxx");
    }
}
