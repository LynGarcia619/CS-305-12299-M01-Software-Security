package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SslServerApplication.class, args);
    }
}

@RestController
class HashController {

    @GetMapping("/hash")
    public String getChecksum() {
        // Static data string for checksum verification
        String data = "Hello World Check Sum!";

        try {
            // Create a SHA-256 MessageDigest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Compute the hash bytes from the data string
            byte[] hashBytes = digest.digest(data.getBytes("UTF-8"));

            // Convert the byte array to a hex string for display
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return "<p>Data: " + data + "</p>"
                 + "<p>SHA-256 Checksum: " + hexString.toString() + "</p>";

        } catch (Exception e) {
            return "Error computing checksum: " + e.getMessage();
        }
    }
}