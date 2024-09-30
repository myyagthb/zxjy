package edu.hut.fileasedemo.controller;

import edu.hut.fileasedemo.utils.EncryptionUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FileUploadController {




    private static final String ENCRYPTION_KEY = "1111111111111111";
    private static final String ENCRYPTION_IV = "1111111111111111";

    @GetMapping("/download-file")
    public ResponseEntity<byte[]> downloadFile() {
        System.out.println(666);
        try {
            // 读取要下载的文件
            Path filePath = Paths.get("C:\\Users\\wanghaixin\\Desktop\\Project\\FileAseDemo\\file\\账号密码.txt");
            byte[] fileData = Files.readAllBytes(filePath);

            // 加密文件数据
//            byte[] encryptedFileData = encryptFile(fileData);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment().filename("encrypted-file.txt").build());

//            return new ResponseEntity<>(encryptedFileData, headers, HttpStatus.OK);
            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private byte[] encryptFile(byte[] fileData) {
        try {
            // 使用 AES 加密
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
            IvParameterSpec iv = new IvParameterSpec(ENCRYPTION_IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            return cipher.doFinal(fileData);
        } catch (Exception e) {
            throw new RuntimeException("文件加密失败", e);
        }
    }




















    @PostMapping("/handleFileUpload2")
    public ResponseEntity<String> handleFileUpload2(@RequestParam("file") MultipartFile file) {
        try {
            // 获取上传的文件数据
            byte[] fileContent = file.getBytes();
            // 在这里对文件内容进行进一步处理,例如存储到磁盘
            String uploadPath = "C:\\Users\\wanghaixin\\Desktop\\Project\\FileAseDemo\\file";
            Path filePath = Paths.get(uploadPath, file.getOriginalFilename());
            Files.write(filePath, fileContent);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }


    @GetMapping("/downloadFile2")
    public void downloadFile(HttpServletResponse response) {
        String fileUrl = "C:\\Users\\wanghaixin\\Desktop\\Project\\FileAseDemo\\file\\";
        String fileName = "账号密码.txt";

        try {
            // 设置响应头
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            // 获取文件输入流并写入响应
            File file = new File(fileUrl, fileName);
            try (InputStream inputStream = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
            }
            response.flushBuffer();
        } catch (IOException e) {
            // 处理异常
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }






    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String encryptedFile = EncryptionUtil.encrypt(new String(file.getBytes()));
            return ResponseEntity.ok().body(new FileResponse(encryptedFile));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during file processing.");
        }
    }

    @GetMapping("/download")
    public void handleFileDownload(@RequestParam("filename") String filename, HttpServletResponse response) {
        try {
            String encryptedFile = EncryptionUtil.decrypt(filename);
            byte[] decryptedFileBytes = encryptedFile.getBytes();
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(decryptedFileBytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to download file", e);
        }
    }

    static class FileResponse {
        String encryptedFile;

        public FileResponse(String encryptedFile) {
            this.encryptedFile = encryptedFile;
        }
    }
}