package com.mbase.monch.utils.encrypt;

import java.security.MessageDigest;

/**
 * Created by monch on 15/11/12.
 */
public final class MD5 {

    private MD5() {
    }

    public static String convert(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getHasnString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getHasnString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }

}
