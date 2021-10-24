package com.boot.service.impl;

import com.boot.dao.PlatformDao;
import com.boot.dao.PswDao;
import com.boot.dao.UserDao;
import com.boot.entity.*;
import com.boot.service.PswService;
import com.boot.util.CreateExcelCommonUtils;
import com.boot.util.FileUtil;
import com.boot.util.PswUtil;
import com.boot.util.RSACoder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * 密码信息的业务实现
 * Created by eleven on 2018/12/20.
 */
@Service
public class PswServiceImpl implements PswService {

    @Autowired
    private PswDao pswDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PlatformDao platformDao;

    @Override
    public List<PswMsg> queryPswList(Integer userId) {
        return pswDao.getPswMsgByUserId(userId);
    }

    @Override
    public PswMsg queryPswById(Integer id) {
        return pswDao.getPswMsgById(id);
    }

    @Override
    @Transactional
    public boolean addPswMsg(PswMsg pswMsg) {

        if(!"".equals(pswMsg.getUsername()) && !"".equals(pswMsg.getPassword())){
            if(null == pswMsg.getPlatformId() || "".equals(pswMsg.getPlatformId())){
                Platform platform = platformDao.queryPlatformByName(pswMsg.getPlatformName());
                if(null == platform) {
                    platform = new Platform();
                    platform.setCreateTime(new Date());
                    platform.setUpdateTime(new Date());
                    platform.setPlatformName(pswMsg.getPlatformName());
                    platform.setIconUrl("404.png");
                    platformDao.addPlatform(platform);
                    Platform platform0 = platformDao.queryPlatformByName(pswMsg.getPlatformName());
                    pswMsg.setPlatformId("" + platform0.getId());
                } else {
                    platformDao.autoIncrementCount(platform);
                }
            }
            pswMsg.setPassword(pswMsg.getPassword());
            pswMsg.setCreateTime(new Date());
            pswMsg.setLastModifyTime(new Date());
            try {
                int effectedNum = pswDao.insertPswMsg(pswMsg);
                if(effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("插入密码信息失败！");
                }
            }catch(RuntimeException e){
                throw new RuntimeException("插入密码信息失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("用户登录信息失效！");
        }
    }

    @Override
    @Transactional
    public boolean deletePswMsg(Integer id) {
        if (null != id && id > 0){
            try {
                int effectedNum = pswDao.deletePswMsg(id);
                if(effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("删除密码信息失败！");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException("删除密码信息失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("密码编号不能小于零！");
        }
    }

    @Override
    @Transactional
    public boolean modifyPswMsg(PswMsg pswMsg) {
        if (null != pswMsg.getId() && pswMsg.getId() > 0) {
            pswMsg.setLastModifyTime(new Date());
            try {
                int effectedNum = pswDao.updatePswMsg(pswMsg);
                if (effectedNum > 0){
                    return true;
                } else {
                    throw new RuntimeException("更新密码信息失败！");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException("更新密码信息失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("密码编号不能为空或者小于零！");
        }
    }

    /*
    * 读取用户上传的<账号密码>文件，返回一个List对象
    * */
    @Override
    @Transactional
    public List<PswMsg> readFile2PwdList(File file,Integer userId) {
        List<PswMsg> list = new ArrayList<PswMsg>();
        try {
            List<String> pwdList = new FileUtil().readTxtByLine(file);
            for(int i=0; i<pwdList.size(); i++){
                String pwdMsg = pwdList.get(i);
                String[] pwdArr = pwdMsg.split("\\t");
                if (pwdArr.length!=3) continue;
                pwdArr[1] = PswUtil.encode(pwdArr[1],null);
                PswMsg pswMsg = new PswMsg(userId, pwdArr);
                list.add(pswMsg);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getInitKeys(Integer userId) {
        try {
            Map<String,Object> keyMap = RSACoder.initKey();
            // 存储用户公钥
            Map<String,Object> params = new HashMap();
            params.put("id", userId);
            params.put("key", Base64.encodeBase64String(RSACoder.getPublicKey(keyMap)));
            int result = userDao.saveUserPublicKey(params);
            if(result > 0){
                return Base64.encodeBase64String(RSACoder.getPrivateKey(keyMap));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageData queryPswPageList(PageData pageData) {
        List<PswMsg> list = pswDao.getPswPageList(pageData);
        pageData.setData(list);
        pageData.setTotalCount(pswDao.getPswByUserCount(pageData));
        pageData.firstCount++;
        return pageData;
    }

    @Override
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String userId) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) throw new Exception("用户登录身份已过期");
        String decodeQueue = ((UserMsg) session.getAttribute("user")).getDecodeQueue();
        List<String> columns = Arrays.asList(new String [] {"平台" ,"用户名", "密码", "电话", "邮箱", "身份证", "备注"});
        CreateExcelCommonUtils createExcel = CreateExcelCommonUtils.getInstance("测试",columns, "测试", response);
        pswDao.getCursorForExport(userId, resultContext -> {
            // 回调处理逻辑
            LinkedHashMap data = resultContext.getResultObject();
            try {
                data.put("password", RSACoder.decrypt("" + data.get("password"), decodeQueue));
            } catch (Exception e) {
                e.printStackTrace();
            }
            createExcel.insertData(resultContext.getResultObject());
        });
        createExcel.output();
    }

    @Override
    @Transactional
    public boolean parseExcel(HttpServletRequest request, MultipartFile file, Result result) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) throw new Exception("用户登录身份已过期");
        Integer userId = ((UserMsg) session.getAttribute("user")).getId();
        String encodeQueue = ((UserMsg) session.getAttribute("user")).getEncodeQueue();
        Workbook workBook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workBook.getSheetAt(0);   // 读取sheet1
        int rowsOfSheet = sheet.getPhysicalNumberOfRows(); // 获取sheet1的行数
//        int physicalNumberOfCells = sheet.getRow(0).getPhysicalNumberOfCells();   // 获取每行的单元格数
        List<PswMsg> pswMsgList = new ArrayList<>();
        Date current = new Date();
        int addCount = 0;

        for (int i = 2; i < rowsOfSheet; i++) {
            try{
                PswMsg pswMsg = new PswMsg();
                Row row = sheet.getRow(i);
                pswMsg.setUserId(userId);
                pswMsg.setCreateTime(current);
                checkExcelStrVal(row.getCell(0).getStringCellValue());
                pswMsg.setPlatformName(row.getCell(0).getStringCellValue());
                Platform platform = platformDao.queryPlatformByName(pswMsg.getPlatformName());
                if(null == platform) {
                    platform = new Platform();
                    platform.setCreateTime(current);
                    platform.setUpdateTime(current);
                    platform.setPlatformName(pswMsg.getPlatformName());
                    platform.setIconUrl("404.png");
                    platformDao.addPlatform(platform);
                    platform = platformDao.queryPlatformByName(pswMsg.getPlatformName());
                }
                pswMsg.setPlatformId("" + platform.getId());
                checkExcelStrVal(row.getCell(1).getStringCellValue());
                pswMsg.setUsername(row.getCell(1).getStringCellValue());
                checkExcelStrVal(row.getCell(2).getStringCellValue());
                pswMsg.setPassword(RSACoder.encrypt(row.getCell(2).getStringCellValue(), encodeQueue));
                pswMsg.setPhone(row.getCell(3).getStringCellValue());
                pswMsg.setEmail(row.getCell(4).getStringCellValue());
                pswMsg.setIdCard(row.getCell(5).getStringCellValue());
                pswMsg.setRemark(row.getCell(6).getStringCellValue());
                pswMsg.setStatus((int)row.getCell(7).getNumericCellValue());
                pswMsgList.add(pswMsg);
            }catch (Exception e){
                continue;
            }
            if(pswMsgList.size() > 100) {
                addCount += pswDao.batchInsert(pswMsgList);
                pswMsgList.clear();
            }
        }
        if(pswMsgList.size() > 0) addCount += pswDao.batchInsert(pswMsgList);
        result.setMsg("共" + (rowsOfSheet - 2) + "条信息，成功录入" + addCount + "条信息。");
        return addCount > 0;
    }

    private void checkExcelStrVal(String value) throws Exception {
        if(null == value || "".equals(value)) throw new Exception();
    }

    public static void main(String[] args){
        List<PswMsg> list = new PswServiceImpl().readFile2PwdList(new File("C:\\Users\\Raytine\\Desktop\\pwd.txt"),12332);
        for(PswMsg pm : list){
            System.out.print(pm.getUserId()+",");
            System.out.print(pm.getUsername()+",");
            System.out.print(PswUtil.decode(pm.getPassword(),null)+",");
            System.out.println(pm.getRemark());
        }
    }
}
