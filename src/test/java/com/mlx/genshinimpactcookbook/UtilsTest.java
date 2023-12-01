package com.mlx.genshinimpactcookbook;

import com.mlx.genshinimpactcookbook.utils.RSAUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@SpringBootTest
public class UtilsTest {

    @Value("${backend.public-key}")
    private String publicKey;

    @Value("${backend.private-key}")
    private String privateKey;

    @Test
    void testStringEncryptionAndDecryptionInStringEdition(){
        try {
            String ori = "dongyandmmK3K4";
            String encrypted = RSAUtil.encrypt(ori, publicKey);
            String decrypted = RSAUtil.decrypt(encrypted, privateKey);
            System.out.println(encrypted);
            System.out.println(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
