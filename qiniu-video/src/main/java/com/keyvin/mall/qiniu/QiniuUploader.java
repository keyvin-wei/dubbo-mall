package com.keyvin.mall.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.IOException;
import java.nio.file.Paths;


/**
 * 七牛云服务
 * @author weiwh
 * @date 2020/3/31 22:47
 */
public class QiniuUploader {
    private static final String accessKey = "66camnhN3G5RxddSRHrYZyRMij4sKetw8gT9wiFR";
    private static final String secretKey = "d25vxvza4RsuErkELfttytGCQFsiuuILKZXC8p3I";
    private static final String bucket = "keyvin20200331";
    private static final String bucketPrivate = "private002";
    private static final String domainUrlPrivate = "http://q846pihho.bkt.clouddn.com";


    private static Configuration cfg = new Configuration(Region.huanan());
    private static Auth auth = Auth.create(accessKey, secretKey);
    private static UploadManager uploadManager = new UploadManager(cfg);
    private static BucketManager bucketManager = new BucketManager(auth, cfg);


    /**'
     * 获取上传token信息
     * @return
     */
    public static String getToken(){
        // Auth auth = Auth.create(accessKey, secretKey);
        // String uploadToken = auth.uploadToken(bucket);
        // System.out.println("上传token为"+uploadToken);

        Auth auth1 = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        String upToken = auth1.uploadToken(bucket, null, expireSeconds, putPolicy);
        System.out.println("自定义这个返回的JSON token为"+upToken);
        return upToken;
    }

    /**'
     * 获取私有上传token信息
     * @return
     */
    public static String getTokenPrivate(){
        Auth auth1 = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        String upToken = auth1.uploadToken(bucketPrivate, null, expireSeconds, putPolicy);
        System.out.println("自定义这个返回的JSON private token为"+upToken);
        return upToken;
    }

    /**
     * 上传文件
     * @return
     */
    public static boolean upload(){
        try {
            String filePath = "D:\\douyin\\H.png";
            Response response = uploadManager.put(filePath, "H.png", getToken());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                ex2.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 断点续传
     * @return
     */
    public static boolean uploadResume(){
        String localFilePath = "D:\\douyin\\Falcon Heavy Animation001.mp4";
        String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucket).toString();
        //设置断点续传文件进度保存目录
        try {
        FileRecorder fileRecorder = new FileRecorder(localTempDir);
        UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
            Response response = uploadManager.put(localFilePath, "Falcon Heavy Animation001.mp4", getToken());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println("uploadResume key:"+putRet.key);
            System.out.println("uploadResume hash:"+putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                ex2.printStackTrace();
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    /**
     * 按文件名获取bucket下的文件信息
     * @return
     */
    public static String getFileInfo(){
        try {
            FileInfo fileInfo = bucketManager.stat(bucket, "Falcon Heavy Animation001.mp4");
            System.out.println(fileInfo.hash);
            System.out.println(fileInfo.fsize);
            System.out.println(fileInfo.mimeType);
            System.out.println(fileInfo.putTime);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean uploadprivate(){
        try {
            String filePath = "D:\\douyin\\H.png";
            Response response = uploadManager.put(filePath, "H.png", getTokenPrivate());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                ex2.printStackTrace();
            }
        }
        return true;
    }

    public static String genCredentials(String key){
        String url = domainUrlPrivate+"/"+key;
        return com.keyvin.mall.qiniu.Auth.create(accessKey, secretKey).privateDownloadUrl(url, 10);
    }


    public static void main(String[] args) {
        // upload();
        // uploadResume();
        // getFileInfo();
        // uploadprivate();
        System.out.println(genCredentials("H.png"));
        System.out.println(genCredentials("private-demo.mp4"));

    }

}
