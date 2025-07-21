/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hotelreservationsystem.TokenGenerator;

import java.security.SecureRandom;

/**
 *
 * @author User
 */
public class TokenGenerator {
    
    private static final SecureRandom random = new SecureRandom();

    public static String generateToken(int length) {
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(random.nextInt(10));
        }
        return token.toString();
    }
    
}
