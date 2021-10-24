package com.boot.web;

import com.boot.entity.Result;
import com.boot.util.FileUtil;
import com.boot.util.SystemParamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@Controller
@RequestMapping("/emoji")
public class EmojiController {

    private final ResourceLoader resourceLoader;

    @Autowired
    public EmojiController(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.win.upload-path}")
    private String winPath;

    @Value("${web.linux.upload-path}")
    private String linuxPath;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void getRandomEmoji(String word, String findType){

    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String entry(){
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method= RequestMethod.POST)
    public Result upload (@RequestParam("file") MultipartFile file) {
        // 上传成功或失败的提示
        Result result = new Result();
        String path = SystemParamUtils.isLinuxServer() ? linuxPath : winPath;
        // 生成新的文件名
        System.out.println(file.getOriginalFilename());
        String filename = FileUtil.newFileNameByUUID(file.getOriginalFilename());
        System.out.println(filename);
        if(FileUtil.upload(file, path, filename)){
            result.setMsg("上传成功");
            result.setData(filename);
        }else{
            result.setSuccess(false);
            result.setMsg("上传失败");
        }
        return result;
    }

    @RequestMapping(value="/batchUpload", method = RequestMethod.POST)
    @ResponseBody
    public String batchUpload(@RequestParam("files") MultipartFile[] files){
        if (files == null) return null;
        String path = SystemParamUtils.isLinuxServer() ? linuxPath : winPath;
        for (MultipartFile file : files) {
            String filename = FileUtil.newFileNameByUUID(file.getOriginalFilename());
            FileUtil.upload(file, path, filename);
        }
        return "";
    }

    @RequestMapping(value = "/show", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity showPhoto(String filename){
        try {
            String path = SystemParamUtils.isLinuxServer() ? linuxPath : winPath;
            return ResponseEntity.ok(resourceLoader.getResource("file:"+ path + File.separator + filename));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
