package com.diandian.common.utils;

import com.diandian.common.dto.UploadFileResponse;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TencentCOSUtil
 * @description:
 * @author: ancin
 * @create: 2020-04-07 16:58
 * @Version 1.0
 **/
@Slf4j
@Component
public class TencentCOSUtil implements InitializingBean {


    @Value("${diandian.oss.accessKey}")
    private String accessKeyId;
    @Value("${diandian.oss.accessSecret}")
    private String accessKeySecret;
    @Value("${diandian.oss.bucketName}")
    private String bucketName;
    private  COSClient cosClient;

    @Value("${diandian.tempFile.dir}")
    private String tempFileDir;

    /***
     * 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
     * clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
     */
    private static ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
    /**
     * 生成cos客户端
     */
   // private static COSClient cosClient = new COSClient(accessKeyId, accessKeySecret);
    /***
     * bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
     */
    //private static String bucketName = "jingshu-att-1301793313";
    @Override
    public void afterPropertiesSet() throws Exception {
        COSCredentials cred = new BasicCOSCredentials(accessKeyId, accessKeySecret);
        cosClient = new COSClient(cred, clientConfig);
    }
    public TencentCOSUtil() {
    }
    public void init() {
        COSCredentials cred = new BasicCOSCredentials(accessKeyId, accessKeySecret);
        cosClient = new COSClient(cred, clientConfig);
    }
    public void destory() {
        if (cosClient != null ){
            cosClient.shutdown();
        }
        log.info("腾讯COS 客户端销毁!");
    }

    public UploadFileResponse uploadFile(File file) {
        UploadFileResponse  response = new UploadFileResponse();
        String originalFilename = file.getName();

        String fileKey = getDate()+"/" + System.currentTimeMillis()+originalFilename;
        long start = System.currentTimeMillis();
        cosClient.putObject(bucketName,fileKey, file);
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        String url = this.getUrl(fileKey);
        cosClient.shutdown();
        response.setUrl(url);
        long end = System.currentTimeMillis();
        log.info("文件上传耗时:"+(end - start)/1000);
        log.info("#上传 url:"+url);
        response.setPdfUrl(url);
        return response;
    }

    public UploadFileResponse  uploadFileFromStream(MultipartFile file, String fileType){
        UploadFileResponse response = null;
        if (file.getSize() >150* 1024 * 1024) {
            try {
                throw new Exception("上传文件大小不能超过150M！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String originalFilename = file.getOriginalFilename();
        try {
            long start = System.currentTimeMillis();
            response = new UploadFileResponse();
            InputStream inputStream = file.getInputStream();
            String fileKey = getDate()+"/" + System.currentTimeMillis()+originalFilename;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 从输入流上传必须制定content length, 否则http客户端可能会缓存所有数据，存在内存OOM的情况
            objectMetadata.setContentLength(10);
            objectMetadata.setContentType("video/mp4");

            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, fileKey, inputStream, objectMetadata);
            putObjectRequest.setStorageClass(StorageClass.Standard_IA);
            cosClient.putObject(putObjectRequest);

            String url = this.getUrl(fileKey);
            response.setUrl(url);
            response.setOriginalName(file.getOriginalFilename());
            response.setByteLength(file.getSize());
            response.setByteToStr(StringUtil.formatFileSize(file.getSize()));
            response.setObjType(fileType);
            long end = System.currentTimeMillis();
            log.info("文件上传耗时:"+(end - start)/1000);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cosClient.shutdown();
        }

        return response;
    }


    public UploadFileResponse uploadToCOS(MultipartFile file, String fileType) {
        UploadFileResponse response = null;
        if (file.getSize() >150* 1024 * 1024) {
            try {
                throw new Exception("上传文件大小不能超过150M！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String originalFilename = file.getOriginalFilename();

        try {
            long start = System.currentTimeMillis();
            response = new UploadFileResponse();
            InputStream inputStream = file.getInputStream();
            String fileKey = getDate()+"/"+System.currentTimeMillis()+originalFilename;
            this.uploadFile(inputStream,fileKey);
            String url = this.getUrl(fileKey);
            response.setUrl(url);
            response.setOriginalName(file.getOriginalFilename());
            response.setByteLength(file.getSize());
            response.setByteToStr(StringUtil.formatFileSize(file.getSize()));
            response.setObjType(fileType);
            long end = System.currentTimeMillis();
            log.info("文件上传耗时:"+(end - start)/1000);
        } catch (Exception e) {
            log.error("腾讯云上传失败",e);
        }

        return response;
    }

    public String uploadFile(InputStream instream,String fileKey) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            File localeFile = File.createTempFile(fileKey,".mp4");
            java.nio.file.Files.copy(instream, localeFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileKey, localeFile);
            //PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            ExecutorService threadPool = Executors.newFixedThreadPool(3);
            TransferManager transferManager = new TransferManager(cosClient, threadPool);
           // System.out.println(putObjectResult);
            Upload upload = transferManager.upload(putObjectRequest);
            // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
            log.info("线程池上传文件");
            upload.waitForUploadResult();
            // 关闭 TransferManger
            transferManager.shutdownNow();

            if (localeFile.exists()){
                log.info("删除临时文件");
                localeFile.delete();
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
    public String getUrl(String fileKey) {
        /***
         * 10Y
         */
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        URL url = cosClient.generatePresignedUrl(bucketName, fileKey, expiration);
        log.info("服务端上传COS URL:"+url);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
    private String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMdd");
        Date date = new Date();
        return  sdf.format(date);
    }


    public  String upload() throws InterruptedException {
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        //file里面填写本地图片的位置 我这里是相对项目的位置，在项目下有src/test/demo.jpg这张图片
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        COSCredentials cred = new BasicCOSCredentials("AKIDA6mBKBj7D1dy34pa6MK1X1NafU2MrYXO", "qXP1Qho6KSjzzMVb8f4NgP8bmG81cwmw");
        cosClient = new COSClient(cred, clientConfig);
        File localFile = new File("C:\\Diandian\\test0411.mp4");

        //ExecutorService threadPool = Executors.newFixedThreadPool(4);
       // TransferManager transferManager = new TransferManager(cosClient, threadPool);
        bucketName = "jingshu-att-1301793313";
        String fileKey = System.currentTimeMillis()+localFile.getName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "20200411/"+fileKey, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        //Upload upload = transferManager.upload(putObjectRequest);
        // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
        //upload.waitForUploadResult();
        // 关闭 TransferManger
       // transferManager.shutdownNow();

        System.out.println(putObjectResult);
        String etag = putObjectResult.getETag();
        System.out.println(etag);
        /***
         * 10Y
         */
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        URL url = cosClient.generatePresignedUrl(bucketName, fileKey, expiration);
        System.out.print(url);
        return "";
    }



    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        new TencentCOSUtil().upload();
        long end = System.currentTimeMillis();
        System.out.println("耗费时间："+(end-start)/1000);
        //closeClient();
        String fileUrl = "http://jingshu-att-1301793313.cos.ap-shanghai.myqcloud.com/1586258210265test1.5.mp4?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDA6mBKBj7D1dy34pa6MK1X1NafU2MrYXO%26q-sign-time%3D1586258210%3B1901618210%26q-key-time%3D1586258210%3B1901618210%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3Dc2ac8443a3adfb3077671e2c0623fae53e4f35e0";
        String name = fileUrl.substring(fileUrl.lastIndexOf("/")+1);
        name = name.substring(0,name.indexOf("?"));
        System.out.println(name);
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMdd");
        Date date = new Date();
        System.out.println("现在时间：" + sdf.format(date));
    }
}
