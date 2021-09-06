package cn.xsdzq.platform.util;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendEmailUtil {
    
    public static boolean sendMail(String from, String to, 
    		 String emailMsg,
    		String authorizationCode, String smtpServer) {
        
       
        //新时代证券
        //final String username = "fanjingxi@xsdzq.cn"; // 此处填写发送的邮箱名
        //from; // 此处填写发送的邮箱名
       // authorizationCode;// 此处填写发送者邮箱登录的授权码
         
        // 定义properties对象，设置环境信息
        Properties properties = new Properties();
        
        /*
         * mail.smtp.host ：指定连接的邮件服务器的主机名。如：163邮箱就填写smtp.163.com 
         * 若在本地测试的话，需要在本地安装smtp服务器
         */
        properties.setProperty("mail.smtp.host", smtpServer);
        
        // mail.smtp.auth：指定客户端是否要向邮件服务器提交验证
        properties.setProperty("mail.smtp.auth", "true");
        
        /*
         * mail.transport.protocol：指定邮件发送协议：smtp。smtp：发邮件；pop3：收邮件
         * mail.store.protocol:指定邮件接收协议
         */
        properties.setProperty("mail.transport.protocol", "smtp");
        
        // 获取session对象
        Session session = Session.getInstance(properties);
        
        // 当设置为true，JavaMail AP就会将其运行过程和邮件服务器的交互命令信息输出到console中，用于JavaMail的调试
        session.setDebug(false);
        try {
            
            // 创建邮件对象
            MimeMessage message = new MimeMessage(session);
            
            // 设置邮件发送方
            message.setFrom(new InternetAddress(from));
            
            // 设置邮件发送的主题<邮件标题>
            message.setSubject("积分商城-数据异常提醒(请勿回复此邮件)");
            
            // 设置邮件发送的内容
            message.setContent(emailMsg,"text/html;charset=utf-8");
            Transport transport=session.getTransport();
            
            // 连接邮件服务器，“”中填写邮件服务器主机名
            transport.connect(smtpServer,25, from, authorizationCode);
            String[] strArray = to.replaceAll(" ", "").split(";");
            System.out.println("收件人为："+ to);
     
            for (int i = 0; i < strArray.length; i++) {
            	if(!"".equals(strArray[i])) {
            		if(strArray[i].contains("；")) {
            			//兼容中文分号
            			String[] subArray = strArray[i].split("；");
                		for(int k = 0; k < subArray.length; k++) {
                			if(!"".equals(subArray[k])) {
                				System.out.println(subArray[k]);
                        		transport.sendMessage(message,new Address[]{new InternetAddress(subArray[k])});
                 	           
                			}
                		}
            		}else {
            			System.out.println(strArray[i]);
                		transport.sendMessage(message,new Address[]{new InternetAddress(strArray[i])});
         	           
            		}
            		
            		
            		
            	}

	            }
            
            transport.close();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
    	/*String s = "  fanjingxi@xsdzq.cn" ;
    	String server = "smtp.263.net";
    	String code = "2A107DFcDEA7E011";
     boolean v = 	sendMail("fanjingxi@xsdzq.cn",s,
    		 "这是邮件内容：每天检查开户数据测试。。。",code,server);
     System.out.println(v);*/
	}
}