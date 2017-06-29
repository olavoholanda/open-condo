package com.opencondo.accountservice.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class to digest a String to a MD5 hash algorithm.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
public class Cryptography {

  private static final Logger LOGGER = Logger.getLogger(Cryptography.class.getName());

  private static final Integer SIGN = 1;
  private static final Integer RADIX = 16;

  /**
   * Parses a String to a new String representing its MD5 hash.
   *
   * @param message the String to be parsed.
   * @return the message in md5.
   */
  public static String md5(String message) {
    String messageHash = null;
    try {
      byte[] bytesOfMessage = message.getBytes();

      MessageDigest md = MessageDigest.getInstance("MD5");

      BigInteger hash = new BigInteger(SIGN, md.digest(bytesOfMessage));

      messageHash = hash.toString(RADIX);
    } catch (NoSuchAlgorithmException e) {
      LOGGER.log(Level.SEVERE, e.getMessage());
    }

    return messageHash;
  }
}
