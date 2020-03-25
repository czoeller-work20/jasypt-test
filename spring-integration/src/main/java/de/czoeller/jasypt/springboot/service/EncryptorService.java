package de.czoeller.jasypt.springboot.service;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EncryptorService {

    final StringEncryptor encryptorBean;

    @Autowired
    public EncryptorService(@Qualifier("encryptorBean") StringEncryptor encryptorBean) {
        this.encryptorBean = encryptorBean;
    }

    public String encrypt(String value) {
        return encryptorBean.encrypt(value);
    }
}
