package com.example;

import encrypter.Encrypter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptTest {

    @Test
    void testEncryptEmptyString() {
        assertEquals("", Encrypter.encrypt(""));
    }

    @Test
    void testEncryptabc() {
        assertEquals("def", Encrypter.encrypt("abc"));
    }

}
