
package com.xy.boot.open.util;

import java.util.Arrays;

public class Base64 {

    private static final boolean devLineSep = true;

    private static final char[]  CA         = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

                                            .toCharArray();

    private static final int[]   IA         = new int[256];

    static {

        Arrays.fill(IA, -1);

        for (int i = 0, iS = CA.length; i < iS; i++)

            IA[CA[i]] = i;

        IA['='] = 0;

    }

    // ****************************************************************************************

    // * byte[] version

    // ****************************************************************************************

    public final static byte[] encodeToByte(byte[] sArr) {

        return encodeToByte(sArr, devLineSep);

    }

    /**
     * Encodes a raw byte array into a BASE64 <code>byte[]</code> representation i accordance with RFC 2045.
     * @param sArr The bytes to convert. If <code>null</code> or length 0 an empty array will be returned.
     * @param lineSep Optional "/r/n" after 76 characters, unless end of file.<br>
     *            No line separator will be in breach of RFC 2045 which specifies max 76 per line but will be a little faster.
     * @return A BASE64 encoded array. Never <code>null</code>.
     */

    public final static byte[] encodeToByte(byte[] sArr, boolean lineSep) {

        // Check special case

        int sLen = sArr != null ? sArr.length : 0;

        if (sLen == 0)

            return new byte[0];

        int eLen = (sLen / 3) * 3; // Length of even 24-bits.

        int cCnt = ((sLen - 1) / 3 + 1) << 2; // Returned character count

        int dLen = cCnt + (lineSep ? (cCnt - 1) / 76 << 1 : 0); // Length of

        // returned

        // array

        byte[] dArr = new byte[dLen];

        // Encode even 24-bits

        for (int s = 0, d = 0, cc = 0; s < eLen;) {

            // Copy next three bytes into lower 24 bits of int, paying attension

            // to sign.

            int i = (sArr[s++] & 0xff) << 16 | (sArr[s++] & 0xff) << 8

            | (sArr[s++] & 0xff);

            // Encode the int into four chars

            dArr[d++] = (byte) CA[(i >>> 18) & 0x3f];

            dArr[d++] = (byte) CA[(i >>> 12) & 0x3f];

            dArr[d++] = (byte) CA[(i >>> 6) & 0x3f];

            dArr[d++] = (byte) CA[i & 0x3f];

            // Add optional line separator

            if (lineSep && ++cc == 19 && d < dLen - 2) {

                dArr[d++] = '\r';

                dArr[d++] = '\n';

                cc = 0;

            }

        }

        // Pad and encode last bits if source isn't an even 24 bits.

        int left = sLen - eLen; // 0 - 2.

        if (left > 0) {

            // Prepare the int

            int i = ((sArr[eLen] & 0xff) << 10)

            | (left == 2 ? ((sArr[sLen - 1] & 0xff) << 2) : 0);

            // Set last four chars

            dArr[dLen - 4] = (byte) CA[i >> 12];

            dArr[dLen - 3] = (byte) CA[(i >>> 6) & 0x3f];

            dArr[dLen - 2] = left == 2 ? (byte) CA[i & 0x3f] : (byte) '=';

            dArr[dLen - 1] = '=';

        }

        return dArr;

    }

    /**
     * Decodes a BASE64 encoded byte array. All illegal characters will be ignored and can handle both arrays with and without line separators.
     * @param sArr The source array. Length 0 will return an empty array. <code>null</code> will throw an Throwable.
     * @return The decoded array of bytes. May be of length 0. Will be <code>null</code> if the legal characters (including '=') isn't divideable by 4. (I.e. definitely corrupted).
     */

    public final static byte[] decode(byte[] sArr) {

        // Check special case

        int sLen = sArr.length;

        // Count illegal characters (including '/r', '/n') to know what size the

        // returned array will be,

        // so we don't have to reallocate & copy it later.

        int sepCnt = 0; // Number of separator characters. (Actually illegal

        // characters, but that's a bonus...)

        for (int i = 0; i < sLen; i++)

            // If input is "pure" (I.e. no line separators or illegal chars)

            // base64 this loop can be commented out.

            if (IA[sArr[i] & 0xff] < 0)

                sepCnt++;

        // Check so that legal chars (including '=') are evenly divideable by 4

        // as specified in RFC 2045.

        if ((sLen - sepCnt) % 4 != 0)

            return null;

        int pad = 0;

        for (int i = sLen; i > 1 && IA[sArr[--i] & 0xff] <= 0;)

            if (sArr[i] == '=')

                pad++;

        int len = ((sLen - sepCnt) * 6 >> 3) - pad;

        byte[] dArr = new byte[len]; // Preallocate byte[] of exact length

        for (int s = 0, d = 0; d < len;) {

            // Assemble three bytes into an int from four "valid" characters.

            int i = 0;

            for (int j = 0; j < 4; j++) { // j only increased if a valid char

                // was found.

                int c = IA[sArr[s++] & 0xff];

                if (c >= 0)

                    i |= c << (18 - j * 6);

                else

                    j--;

            }

            // Add the bytes

            dArr[d++] = (byte) (i >> 16);

            if (d < len) {

                dArr[d++] = (byte) (i >> 8);

                if (d < len)

                    dArr[d++] = (byte) i;

            }

        }

        return dArr;

    }

    public final static byte[] decode(String str) {

        // Check special case

        int sLen = str != null ? str.length() : 0;

        if (sLen == 0)

            return new byte[0];

        // Count illegal characters (including '/r', '/n') to know what size the

        // returned array will be,

        // so we don't have to reallocate & copy it later.

        int sepCnt = 0; // Number of separator characters. (Actually illegal

        // characters, but that's a bonus...)

        for (int i = 0; i < sLen; i++)

            // If input is "pure" (I.e. no line separators or illegal chars)

            // base64 this loop can be commented out.

            if (IA[str.charAt(i)] < 0)

                sepCnt++;

        // Check so that legal chars (including '=') are evenly divideable by 4

        // as specified in RFC 2045.

        if ((sLen - sepCnt) % 4 != 0)

            return null;

        // Count '=' at end

        int pad = 0;

        for (int i = sLen; i > 1 && IA[str.charAt(--i)] <= 0;)

            if (str.charAt(i) == '=')

                pad++;

        int len = ((sLen - sepCnt) * 6 >> 3) - pad;

        byte[] dArr = new byte[len]; // Preallocate byte[] of exact length

        for (int s = 0, d = 0; d < len;) {

            // Assemble three bytes into an int from four "valid" characters.

            int i = 0;

            for (int j = 0; j < 4; j++) { // j only increased if a valid char

                // was found.

                int c = IA[str.charAt(s++)];

                if (c >= 0)

                    i |= c << (18 - j * 6);

                else

                    j--;

            }

            // Add the bytes

            dArr[d++] = (byte) (i >> 16);

            if (d < len) {

                dArr[d++] = (byte) (i >> 8);

                if (d < len)

                    dArr[d++] = (byte) i;

            }

        }

        return dArr;

    }

    public static String encode(byte[] data) {
        int start = 0;
        int len = data.length;
        StringBuffer buf = new StringBuffer(data.length * 3 / 2);

        int end = len - 3;
        int i = start;
        int n = 0;

        while (i <= end) {
            int d = (((data[i]) & 0x0ff) << 16) | (((data[i + 1]) & 0x0ff) << 8) | ((data[i + 2]) & 0x0ff);

            buf.append(CA[(d >> 18) & 63]);
            buf.append(CA[(d >> 12) & 63]);
            buf.append(CA[(d >> 6) & 63]);
            buf.append(CA[d & 63]);

            i += 3;

            if (n++ >= 14) {
                n = 0;
                buf.append(" ");
            }
        }

        if (i == start + len - 2) {
            int d = (((data[i]) & 0x0ff) << 16) | (((data[i + 1]) & 255) << 8);

            buf.append(CA[(d >> 18) & 63]);
            buf.append(CA[(d >> 12) & 63]);
            buf.append(CA[(d >> 6) & 63]);
            buf.append("=");
        } else if (i == start + len - 1) {
            int d = ((data[i]) & 0x0ff) << 16;

            buf.append(CA[(d >> 18) & 63]);
            buf.append(CA[(d >> 12) & 63]);
            buf.append("==");
        }

        return buf.toString();
    }

}