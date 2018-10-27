package com.pingye.controller;

import com.alibaba.fastjson.JSONObject;
import com.pingye.config.UeditorConfig;
import com.pingye.config.UploadConfig;
import com.pingye.entity.Ueditor;
import com.pingye.service.UeditorContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by ldb on 2017/4/9.
 */
@Controller
public class UEditorController {


    @Autowired
    private UploadConfig uploadConfig;

    @Autowired
    private UeditorContentService ueditorContentService;

    public static String imgResouse = "D:\\IDEAWorkspace\\spring-ueditor\\src\\main\\resources\\static\\ueditor\\jsp\\upload\\image\\";


    @RequestMapping("/")
    private String showPage(){
        return "demo";
    }

   /* @RequestMapping(value="/ueditor/config")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return UeditorConfig.UEDITOR_CONFIG;
    }*/


    /**
     *
     * @param param
     * @param upfile
     * @param request
     * @return
     */
    @RequestMapping(value = "/ueditor")
    @ResponseBody
    public String ueditor(@RequestParam("action") String param, MultipartFile upfile, HttpServletRequest request) {
        Ueditor ueditor = new Ueditor();
        if (null != param && "config".equals(param)) {
            return UeditorConfig.UEDITOR_CONFIG;
        } else if (null != param && ("uploadimage".equals(param) ||  "uploadscrawl".equals(param))) {
            if(null != upfile) {
                return imgUpload(upfile, ueditor, request);
            } else {
                ueditor.setState("文件为空！");
                return JSONObject.toJSONString(ueditor);
            }
        }else {
            ueditor.setState("不支持该操作！");
            return JSONObject.toJSONString(ueditor);
        }
    }

    /**
     *
     * @param file
     * @param ueditor
     * @param request
     * @return
     */
    @RequestMapping(value = "/ueditor/uploadimage")
    @ResponseBody
    public String imgUpload(MultipartFile file,Ueditor ueditor, HttpServletRequest request) {
        if (file.isEmpty()) {
            ueditor.setState("文件为空！");
            return JSONObject.toJSONString(ueditor);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String filePath = uploadConfig.getFilepath();
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            ueditor.setState("SUCCESS");
            ueditor.setUrl(fileName);
            return JSONObject.toJSONString(ueditor);
          //  return filePath + fileName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ueditor.setState("文件上传失败！");
        return JSONObject.toJSONString(ueditor);
    }


    @RequestMapping(value = "/ueditor/save/content")
    @ResponseBody
    public void saveContent(@RequestParam(name = "str") String str) {
       ueditorContentService.saveContent(str);
    }


}
