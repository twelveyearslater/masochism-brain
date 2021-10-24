package com.boot.web;

import com.alibaba.fastjson.JSONObject;
import com.boot.entity.PageData;
import com.boot.entity.PswMsg;
import com.boot.entity.Result;
import com.boot.service.PlatformService;
import com.boot.service.PswService;
import com.boot.util.RSACoder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * Created by eleven on 2018/12/20.
 */
@Controller
@RequestMapping("/psw")
public class PswController {

    @Autowired
    private PswService pswService;

    @Autowired
    private PlatformService platformService;

    /*
    * 获取某个用户下的密码列表
    * */
    @ResponseBody
    @RequestMapping("/getPswList")
    public Map<String,Object> queryPswList(Integer userId){
        Map<String,Object> modelMap = new HashMap<>();
        List<PswMsg> pswList = pswService.queryPswList(userId);
        modelMap.put("data", pswList);
//        Map platformMap = platformService.queryPlatforms2MapArr();
//        modelMap.put("p_name", platformMap.get("name"));
//        modelMap.put("p_url", platformMap.get("url"));
        return modelMap;
    }

    /*
    * 获取某个用户下的密码列表
    * */
    @ResponseBody
    @RequestMapping("/getAll")
    public Result queryAll(Integer userId){
        Result result = new Result();
        List<PswMsg> pswList = pswService.queryPswList(userId);
        result.setData(pswList);
        return result;
    }

    /*
     * 获取某个用户下的密码列表
     * */
    @ResponseBody
    @RequestMapping("/getPageListByUser")
//    @ActionLog(log = "分页查询密码列表")
    public Result queryPswPageList(Integer userId, int currentPage, int pageCount){
        Result result = new Result();
        PageData pageData = new PageData(currentPage, pageCount);
        pageData.addPosition("userId", userId);
        pageData = pswService.queryPswPageList(pageData);
        result.setData(pageData);
        return result;
    }

    /*
    * 通过id获取一条密码信息
    * */
    @ResponseBody
    @RequestMapping("/getItById")
    public Result queryPswById(Integer id){
        Result result = new Result();
        PswMsg psw = pswService.queryPswById(id);
        result.setSuccess(psw != null);
        result.setData(psw);
        return result;
    }

    /*
    * 添加一条密码信息
    * */
    @ResponseBody
    @RequestMapping("/add")
    public Result addPsw(String pswMsg) throws Exception {
        Result result = new Result();
        boolean success = pswService.addPswMsg(JSONObject.parseObject(pswMsg, PswMsg.class));
        result.setSuccess(success);
        return result;
    }

    /*
    * 删除一条密码信息
    * */
    @ResponseBody
    @RequestMapping("/remove")
    public Result removePsw(Integer id){
        Result result = new Result();
        boolean success = pswService.deletePswMsg(id);
        result.setSuccess(success);
        return result;
    }

    /*
    * 修改一条密码信息（接收json数据后直接转为对象）
    * */
    @ResponseBody
    @RequestMapping("/modifypsw")
    public Map<String, Object> modifyPsw(@RequestBody PswMsg pswMsg){
        Map<String, Object> modelMap = new HashMap<>();
        boolean success = pswService.modifyPswMsg(pswMsg);
        modelMap.put("success",success);
        return modelMap;
    }

    /*
     * 修改一条密码信息(单纯接受Json数据)
     * */
    @ResponseBody
    @RequestMapping("/edit")
    public Result editPsw(String data){
        PswMsg pswMsg = JSONObject.parseObject(data, PswMsg.class);
        Result result = new Result();
        boolean success = pswService.modifyPswMsg(pswMsg);
        result.setSuccess(success);
        return result;
    }

    @ResponseBody
    @RequestMapping("/initkeys")
    public Map<String, Object> initUserKeys(Integer userId){
        Map<String,Object> modelMap = new HashMap<>();
        String privateKey = pswService.getInitKeys(userId);
        if(privateKey != null){
            modelMap.put("success","success");
            modelMap.put("privateKey", privateKey);
        }else{
            modelMap.put("success","error");
        }
        return modelMap;
    }

    /*
     * 上传excel，解析数据后存库
     * */
    @ResponseBody
    @RequestMapping("/import")
    public Result importExcel(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception {
        Result result = new Result();
        Boolean success = pswService.parseExcel(request, file, result);
        result.setSuccess(success);
        return result;

    }

    @RequestMapping("/download2")
    public ResponseEntity download(String userId){
        return ResponseEntity.ok(new File(""));
    }

    @RequestMapping("/download")
    public void download2(HttpServletRequest request, HttpServletResponse response, String userId) throws Exception {
        pswService.exportExcel(request, response, userId);
    }

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String test(){
        return "index";
    }

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(){
        return "pwd";
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Base64.encodeBase64String(RSACoder.encryptByPublicKey("an347852901".getBytes(), Base64.decodeBase64("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALArK1SpndwQ9B45VCbHLOjVe9LKxm20mAMNCgCAGP1iurw9XR3QIKcRFYDngMqJX+/Wsm3/KABWPMlmSzwrqWsCAwEAAQ=="))));
        System.out.println(Base64.encodeBase64String(RSACoder.encryptByPublicKey("a347852901".getBytes(),Base64.decodeBase64("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALArK1SpndwQ9B45VCbHLOjVe9LKxm20mAMNCgCAGP1iurw9XR3QIKcRFYDngMqJX+/Wsm3/KABWPMlmSzwrqWsCAwEAAQ=="))));
        System.out.println(Base64.encodeBase64String(RSACoder.encryptByPublicKey("a347852901".getBytes(),Base64.decodeBase64("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALArK1SpndwQ9B45VCbHLOjVe9LKxm20mAMNCgCAGP1iurw9XR3QIKcRFYDngMqJX+/Wsm3/KABWPMlmSzwrqWsCAwEAAQ=="))));
        System.out.println(Base64.encodeBase64String(RSACoder.encryptByPublicKey("abcd@123".getBytes(),Base64.decodeBase64("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALArK1SpndwQ9B45VCbHLOjVe9LKxm20mAMNCgCAGP1iurw9XR3QIKcRFYDngMqJX+/Wsm3/KABWPMlmSzwrqWsCAwEAAQ=="))));
        System.out.println(Base64.encodeBase64String(RSACoder.encryptByPublicKey("An347852901".getBytes(),Base64.decodeBase64("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALArK1SpndwQ9B45VCbHLOjVe9LKxm20mAMNCgCAGP1iurw9XR3QIKcRFYDngMqJX+/Wsm3/KABWPMlmSzwrqWsCAwEAAQ=="))));
        System.out.println(Base64.encodeBase64String(RSACoder.encryptByPublicKey("a347852901".getBytes(),Base64.decodeBase64("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALArK1SpndwQ9B45VCbHLOjVe9LKxm20mAMNCgCAGP1iurw9XR3QIKcRFYDngMqJX+/Wsm3/KABWPMlmSzwrqWsCAwEAAQ=="))));
        System.out.println(Base64.encodeBase64String(RSACoder.encryptByPublicKey("a347852901".getBytes(),Base64.decodeBase64("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALArK1SpndwQ9B45VCbHLOjVe9LKxm20mAMNCgCAGP1iurw9XR3QIKcRFYDngMqJX+/Wsm3/KABWPMlmSzwrqWsCAwEAAQ=="))));
    }

}
