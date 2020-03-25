package de.czoeller.jasypt.springboot.controller;

import de.czoeller.jasypt.springboot.service.EncryptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EncryptorController {

    final EncryptorService encryptorService;

    @Autowired
    public EncryptorController(EncryptorService encryptorService) {
        this.encryptorService = encryptorService;
    }

    @ResponseBody
    @RequestMapping(value = "/encrypt")
    public String encrypt(@RequestParam("value") String value) {
        return String.format("ENV(%s)", encryptorService.encrypt(value));
    }
}
