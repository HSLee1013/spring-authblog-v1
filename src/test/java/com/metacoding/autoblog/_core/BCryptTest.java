package com.metacoding.autoblog._core;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {

    @Test
    public void encode_test() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encode = encoder.encode("1234");
        System.out.println(encode);
    }
}
