package com.example.jwt.util.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;

public class PassEncoder {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String rawPass = input.nextLine();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(rawPass));
    }
}
