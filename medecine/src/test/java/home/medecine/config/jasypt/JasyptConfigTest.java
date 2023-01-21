package home.medecine.config.jasypt;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class JasyptConfigTest {

    @Test
    @DisplayName("양방향 암호화 테스트")
    public void stringEncryptor(){
        String plainText = "암호화 하는데 사용할 키";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(plainText);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        String encryptText = encryptor.encrypt(plainText);
        System.out.println(encryptText);
        String decryptText = encryptor.decrypt(encryptText);
        System.out.println(decryptText);
        Assertions.assertThat(plainText).isEqualTo(decryptText);
    }
}