package com.freemarket.seller.sellerapi.business.passwordencoder;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SellerPasswordEncoderTest {

    @Test
    void givenAStringPassword_thenMustEncodeWithBCrypt() {
        String password = "this_is_a_password";
        String passwordEncoded = SellerPasswordEncoder.encode(password);

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        assertTrue(bcrypt.matches(password, passwordEncoded));
    }
}