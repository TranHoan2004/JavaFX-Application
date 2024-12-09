/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.he180030.agentmanagement.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ADMIN
 */
public class PasswordEncoding {

    public static String getEncodingPassword(String rawPassword) {
        String salt = generateSalt();
        String encryptedPassword = null;

        // Thêm salt code vào để tăng tính bảo mật 
        rawPassword = rawPassword + salt;
        try {
            byte[] dataBytes = rawPassword.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            encryptedPassword = Base64.getEncoder().encodeToString(md.digest(dataBytes));
        } catch (Exception e) {
            Logger.getLogger(PasswordEncoding.class.getName()).log(Level.ALL, e.getMessage());
        }
        return encryptedPassword;
    }

    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public static void main(String[] args) {
        System.out.println(PasswordEncoding.getEncodingPassword("123456"));
    }
}
