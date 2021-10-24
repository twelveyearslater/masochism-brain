package com.boot.util;

import com.boot.entity.UserMsg;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class MailUtil {

    private static final Properties props  = new Properties();
    private String[] to; // 发送
    private String[] CCReceivers;   // 抄送
    private String[] BCCReceivers;   // 密送
    private String[] files; // 附件
    private String subject; // 主题
    private String content; // 内容

    static {
        // 配置发送邮件的环境属性
        config();
    }

    public MailUtil(){}

    public MailUtil(String[] to, String[] CCReceivers, String[] BCCReceivers,
                    String[] files, String subject, String content){
        this.to = to;
        this.CCReceivers = CCReceivers;
        this.BCCReceivers = BCCReceivers;
        this.files = files;
        this.subject = subject;
        this.content = content;
    }

    public void send() throws MessagingException,UnsupportedEncodingException{

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constants.SERVERMAIL, PswUtil.decode(Constants.SERVERMAILTOKEN, null));
            }
        };
        // 使用环境信息和授权信息,创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        BodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        // 设置发件人
        InternetAddress from = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(from);
        // 发送
        if(null != to){
            String toList = getMailList(to);
            InternetAddress[] toArray  = new InternetAddress().parse(toList);
            message.setRecipients(Message.RecipientType.TO, toArray); // 收件人
        }
        // 抄送
        if(null != CCReceivers){
            String CCReceiverList = getMailList(CCReceivers);
            InternetAddress[] CCReceiverArr  = new InternetAddress().parse(CCReceiverList);
            message.setRecipients(Message.RecipientType.TO, CCReceiverArr); // 抄送人
        }
        // 密送
        if (null != BCCReceivers) {
            String BCCReceiverList = getMailList(BCCReceivers);
            InternetAddress[] BCCReceiverArr = new InternetAddress().parse(BCCReceiverList);
            message.setRecipients(Message.RecipientType.BCC, BCCReceiverArr); // 密送人
        }
        // 发送日期 该日期可以随意写,你可以写上昨天的日期（效果很特别,亲测,有兴趣可以试试）,或者抽象出来形成一个参数。
        message.setSentDate(new Date());
        message.setSubject(subject); // 主题
        message.setText(content); // 内容
        //显示以html格式的文本内容
        messageBodyPart.setContent(content,"text/html;charset=utf-8");
        multipart.addBodyPart(messageBodyPart);
        //保存多个附件
        if(files!=null){
            addTach(files, multipart);
        }
        message.setContent(multipart);
        // 发送邮件
        Transport.send(message);

    }

    private static void config(){
        // 设置SSL
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // 设置端口
        props.setProperty("mail.smtp.socketFactory.port", "465");
        // 设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        // 设置验证身份
        props.put("mail.smtp.auth", true);
        // 设置邮箱服务器
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        // 设置发送人的账号
        props.setProperty("mail.user", Constants.SERVERMAIL);
        // 访问SMTP服务时提供的密码
        props.setProperty("mail.password", PswUtil.decode(Constants.SERVERMAILTOKEN, null));
    }

    private String getMailList(String[] mailArray) {
        StringBuffer toList = new StringBuffer();
        int length = mailArray.length;
        if (mailArray != null && length < 2) {
            toList.append(mailArray[0]);
        } else {
            for (int i = 0; i < length; i++) {
                toList.append(mailArray[i]);
                if (i != (length - 1)) {
                    toList.append(",");
                }
            }
        }
        return toList.toString();
    }

    //添加多个附件
    public void addTach(String fileList[], Multipart multipart) throws MessagingException, UnsupportedEncodingException {
        for (int i = 0; i < fileList.length; i++) {
            MimeBodyPart mailArchive = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(fileList[i]);
            mailArchive.setDataHandler(new DataHandler(fds));
            mailArchive.setFileName(MimeUtility.encodeText(fds.getName(),"UTF-8","B"));
            multipart.addBodyPart(mailArchive);
        }
    }

    public static boolean activeAccount(UserMsg user) {
        MailUtil mail = new MailUtil();
        mail.setSubject(ACTIVE_MAIL_TITLE);
        mail.setContent(ACTIVE_MAIL_CONTENT_FRONT + ACTIVE_REQUEST_URL + user.getEncodeQueue() + ACTIVE_MAIL_CONTENT_AFTER);
        mail.setTo(new String[] { user.getEmail() });
        try {
            mail.send();
            System.out.println("发送邮件成功！");
        } catch (Exception e) {
            System.out.println("发送邮件失败！");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static final String ACTIVE_MAIL_TITLE = "激活您的Masochism的账号";
    private static final String ACTIVE_REQUEST_URL = "http://www.masochism.top:2333/demo/user/active?code=";
    private static final String ACTIVE_MAIL_CONTENT_FRONT = "<div style='color:gray'>Masochism账户</div><br/><div style='color:blue;font-size:41px'><a href='";
    private static final String ACTIVE_MAIL_CONTENT_AFTER = "'>点击此处完成账户激活</a></div>";

    public void setTo(String[] to) {
        this.to = to;
    }

    public void setCCReceivers(String[] CCReceivers) {
        this.CCReceivers = CCReceivers;
    }

    public void setBCCReceivers(String[] BCCReceivers) {
        this.BCCReceivers = BCCReceivers;
    }

    public void setFiles(String[] files) {
        files = files;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        MailUtil mail = new MailUtil();
        mail.setSubject("测试邮件标题");
        mail.setContent("<div style='color:gray'>Masochism账户</div><br/><div style='color:blue;font-size:41px'><a href='http://www.baidu.com'>点击此处完成账户激活</a></div>");
        //收件人 可以发给其他邮箱(163等) 下同
        mail.setTo(new String[] {"774872149@qq.com"});
        //抄送
        mail.setCCReceivers(new String[] {"774872149@qq.com"});
        //密送
        mail.setBCCReceivers(new String[] {"774872149@qq.com"});
        //发送附件列表 可以写绝对路径 也可以写相对路径(起点是项目根目录)
        //mail.setFiles(new String[] {"file\\附件1.txt","file\\附件2.txt"});
        //发送邮件
        try {
            mail.send();
            System.out.println("发送邮件成功！");
        } catch (Exception e) {
            System.out.println("发送邮件失败！");
            e.printStackTrace();
        }
    }
}
