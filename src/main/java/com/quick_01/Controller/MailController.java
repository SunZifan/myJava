package com.quick_01.Controller;

import com.quick_01.Service.EmailReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add")
public class MailController {
    @Autowired
    private EmailReaderService emailReaderService;
    @RequestMapping (value = "")
    public void add(){
        emailReaderService.readAndProcessEmail();
    }
}
