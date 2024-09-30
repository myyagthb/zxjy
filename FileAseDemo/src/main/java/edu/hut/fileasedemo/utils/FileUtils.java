package edu.hut.fileasedemo.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class FileUtils {

    public static final int CRYPTO_SECRET_KEY = 0x99;

    /**
     * 加密/解密文件
     *
     * @param srcFile 原文件
     * @param encFile 加密/解密后的文件
     * @throws IOException
     */
    public static void cryptoFile(File srcFile, File encFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(srcFile);
             OutputStream outputStream = new FileOutputStream(encFile)) {
            int fileData;
            while ((fileData = inputStream.read()) > -1) {
                outputStream.write(fileData ^ CRYPTO_SECRET_KEY);
            }
        }
    }

    /**
     * 将 MultipartFile 转换为临时文件
     *
     * @param multipartFile 文件
     * @param tempFilePath 临时文件路径
     * @return File 临时文件
     */
    public static File multipartFileToFile(MultipartFile multipartFile, String tempFilePath) {
        File file = new File(tempFilePath);
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        file.mkdirs();
        File tempFile = new File(tempFilePath + "\\" + UUID.randomUUID().toString().replaceAll("-", "") + suffix);
        try {
            multipartFile.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    /**
     * 上传文件到服务器
     *
     * @param fileServerPath 文件服务器地址
     * @param folderPath     存放的文件夹路径（例如："/upload"）
     * @param uploadFile     需要上传的文件
     * @param isCrypto       是否加密
     * @return String 文件上传后的全路径
     */
    public static String uploadByJersey(String fileServerPath, String folderPath, File uploadFile, boolean isCrypto) {
        String suffix = uploadFile.getName().substring(uploadFile.getName().lastIndexOf("."));
        String randomFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        String fullPath = fileServerPath + folderPath + "/" + randomFileName;
        try {
            if (isCrypto) {
                File cryptoFile = new File(uploadFile.getPath().substring(0, uploadFile.getPath().lastIndexOf(".")) + "crypto" + uploadFile.getPath().substring(uploadFile.getPath().lastIndexOf(".")));
                cryptoFile(uploadFile, cryptoFile);
                uploadFile = cryptoFile;
            }
            Client client = Client.create();
            WebResource wr = client.resource(fullPath);
            wr.put(String.class, fileToByte(uploadFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullPath;
    }

    /**
     * 下载文件
     *
     * @param url         文件路径
     * @param filePath    文件保存路径
     * @param fileName    文件名称（包含文件后缀）
     * @param isCrypto    是否解密
     * @return File 下载后的文件
     */
    public static File downloadByURL(String url, String filePath, String fileName, boolean isCrypto) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.connect();
            try (InputStream inputStream = httpURLConnection.getInputStream();
                 FileOutputStream fileOut = new FileOutputStream(new File(filePath + "\\" + fileName))) {
                byte[] bytes = new byte[4096];
                int length;
                while ((length = inputStream.read(bytes)) != -1) {
                    fileOut.write(bytes, 0, length);
                }
            }
            httpURLConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isCrypto) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            File cryptoFile = new File(request.getServletContext().getRealPath("/") + "\\temp\\" + UUID.randomUUID().toString().replaceAll("-", "") + file.getName().substring(file.getName().lastIndexOf(".")));
            cryptoFile(file, cryptoFile);
            file.delete();
            file = cryptoFile;
        }
        return file;
    }

    /**
     * 删除文件服务器上的文件
     *
     * @param url 文件路径
     * @return boolean
     */
    public static boolean deleteByJersey(String url) {
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(url);
            webResource.delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将 File 转换为 byte[]
     *
     * @param file 文件
     * @return byte[]
     */
    public static byte[] fileToByte(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] bytes = new byte[1024];
            int n;
            while ((n = fileInputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, n);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}