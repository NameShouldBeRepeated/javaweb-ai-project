package com.itheima.tliaswebmanagement.controller;

import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MulticastSocket;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /**
     * 往本地磁盘进行存储，不推荐
     * //@param name 姓名
     * //@param age 年龄
     * //@param file 文件名
     * //@return
     * //@throws IOException
     */
    /*@PostMapping("/upload")
    public Result upload(String name, String age, MultipartFile file) throws IOException {
        log.info("接收到的参数：{}，{}，{}",name,age,file);
        //获取文件原名
        String originalFilename = file.getOriginalFilename();
        //生成新文件名
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString()+ext;
        //将文件保存到本地
        file.transferTo(new File("E:\\黑马-2025新版JavaWeb+AI【完整】\\资料\\web-project\\uselessfile\\"+newFileName));
        return Result.success();
    }*/
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件：{}",file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传oss，URL：{}",url);
        return Result.success(url);
    }
}
