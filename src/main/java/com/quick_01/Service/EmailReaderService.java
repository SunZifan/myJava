package com.quick_01.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

@Service
public class EmailReaderService {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("{spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    public void readAndProcessEmail(){
        try {
            Properties prop = new Properties();
            prop.put("mail.imap.host", host);
            prop.put("mail.imap.port", port);
            prop.put("mail.imap.ssl.enable", "true");

            Session session = Session.getInstance(prop);
            Store store = session.getStore("imap");
            store.connect(host, username, password);

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();

            for (Message message : messages) {
                if (message.getFrom().equals("statement@vpass.ne.jp")){
                    String content = message.getContent().toString();
                    System.out.println(content);
                }
            }
            inbox.close(false);
            store.close();


        }catch (Exception e){
            // 处理异常
        }
    }
}
