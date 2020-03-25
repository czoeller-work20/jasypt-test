package de.czoeller.jasypt.springboot;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EncryptablePropertySource(name = "EncryptedProperties", value = "classpath:encrypted.properties")
public class JasyptSpringBootTestApp implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(JasyptSpringBootTestApp.class);

    @Value("${encrypted.myproperty}")
    private String myProperty;

    @Bean(name = "encryptorBean")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        //TODO: Replace with ENV-Variable. This is an env secret that should be passed into the application
        config.setPassword("password");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    @Override
    public void run(String... args) {
        LOG.info("decrypted property: '{}'", myProperty);
    }

    public static void main(String[] args) {
        SpringApplication.run(JasyptSpringBootTestApp.class, args);
    }

}
