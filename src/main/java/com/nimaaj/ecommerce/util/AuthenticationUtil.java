package com.nimaaj.ecommerce.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class AuthenticationUtil {

    private AuthenticationUtil() {
    }

    private final static char[] SALT_CHARS =
            {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
            'o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D',
            'E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
            'U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0'};

    private final static char[] OTP_CHARS = {'1','2','3','4','5','6','7','8','9','0'};

    private final static char[] RESET_PASS_CODE_CHARS =
            {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
                    'o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D',
                    'E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
                    'U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0'};

    public static String generateSalt(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < length ; i++) {
            double cr = Math.random() * (double)SALT_CHARS.length;
            int index = (int)Math.floor(cr);
            sb.append(SALT_CHARS[index]);
        }
        return sb.toString();
    }

    public static String generateOtp(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < length ; i++) {
            double cr = Math.random() * (double)OTP_CHARS.length;
            int index = (int)Math.floor(cr);
            sb.append(OTP_CHARS[index]);
        }
        return sb.toString();
    }

    public static String hashPassword(String password, String userSalt, String key) {
        String seed = password + userSalt;
        return hmacSha1(seed, key);
    }

    public static String hmacSha1(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());

            // Convert raw bytes to Hex
            byte[] hexBytes = new Hex().encode(rawHmac);

            //  Covert array of Hex bytes to a String
            return new String(hexBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateResetPasswordCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < length ; i++) {
            double cr = Math.random() * (double)RESET_PASS_CODE_CHARS.length;
            int index = (int)Math.floor(cr);
            sb.append(RESET_PASS_CODE_CHARS[index]);
        }
        sb.append(System.currentTimeMillis());
        return sb.toString();
    }

}
