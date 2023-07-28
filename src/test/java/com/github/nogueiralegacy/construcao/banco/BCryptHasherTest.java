package com.github.nogueiralegacy.construcao.banco;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BCryptHasherTest {
    @Test
     void test() {
        BCryptHasher bCryptHasher = new BCryptHasher();
        String hash = bCryptHasher.encode("test123");
        System.out.println(hash);
        assertTrue(bCryptHasher.matches("test123", hash));
    }
}
