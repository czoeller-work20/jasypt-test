package de.czoeller.jasypt.springboot;

import de.czoeller.jasypt.springboot.service.EncryptorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JasyptSpringBootTestApp.class)
//@EncryptablePropertySource(name = "EncryptedProperties", value = "classpath:encrypted.properties")
//@TestConfiguration()
//@PropertySource("classpath:encrypted.properties")
public class TestDecryptedProperties {

    final EncryptorService encryptorService;

    @Autowired
    public TestDecryptedProperties(EncryptorService encryptorService) {
        this.encryptorService = encryptorService;
    }

    @Value("${encrypted.myproperty}")
    private String myProperty;

    @Test
    public void testDecryptedmyProperty() {
        assertThat(myProperty, is("HelloWorld123"));
    }
}
