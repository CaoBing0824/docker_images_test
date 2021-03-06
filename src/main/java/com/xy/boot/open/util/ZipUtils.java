package com.xy.boot.open.util;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class ZipUtils {

    public static byte[] compress(byte[] data)throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        byte[] result = null;
        try {
            gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.finish();
            result = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(gzip);
            IOUtils.closeQuietly(bos);
        }
        return result;
    }

    public static byte[] uncompress(byte[] data) throws IOException {
        byte[] result = null;
        ByteArrayOutputStream baos = null;
        ByteArrayInputStream bis = null;
        GZIPInputStream gzip = null;
        try {
            bis = new ByteArrayInputStream(data);
            gzip = new GZIPInputStream(bis);
            baos = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            int num = -1;
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            result = baos.toByteArray();
            baos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	IOUtils.closeQuietly(baos);
        	IOUtils.closeQuietly(gzip);
            IOUtils.closeQuietly(bis);
        }
        return result;
    }

    private ZipUtils() {
    }

}
