package br.dev.rodrigocury.uaibugouapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

  public static String encodePassword(String unencryptedPassword){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    return encoder.encode(unencryptedPassword);
  }
}
