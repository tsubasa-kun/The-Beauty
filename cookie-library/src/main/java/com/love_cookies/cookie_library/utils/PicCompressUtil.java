package com.love_cookies.cookie_library.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by xiekun on 2016/4/18 0018.
 *
 * 图片压缩工具类
 */
public class PicCompressUtil {

    private static PicCompressUtil instance;
    private static String mDir;//文件夹名称

    /**
     * DateTimeUtil
     *
     * @return DateTimeUtil
     */
    public static PicCompressUtil getInstance(String dir) {
        if (instance == null) {
            instance = new PicCompressUtil();
        }
        mDir = dir;
        return instance;
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 根据路径获得图片并压缩返回bitmap
     *
     * @param filePath
     * @return
     */
    public Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 获取保存图片的目录
     *
     * @return
     */
    public File getAlbumDir() {
        File dir = new File("/mnt/sdcard/" + mDir + "/uploadPhoto/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 压缩并保存图片
     * @param path
     * @return
     */
    public String save(String path) {
        String newPath = "";
        try {
            File f = new File(path);
            newPath = getAlbumDir() + "/" + f.getName();

            Bitmap bm = getSmallBitmap(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            FileOutputStream fos = new FileOutputStream(new File(newPath));
            int options = 100;
            // 如果大于80kb则再次压缩,最多压缩三次
            while (baos.toByteArray().length / 1024 > 80 && options != 10) {
                // 清空baos
                baos.reset();
                // 这里压缩options%，把压缩后的数据存放到baos中
                bm.compress(Bitmap.CompressFormat.JPEG, options, baos);
                options -= 30;
            }
            fos.write(baos.toByteArray());
            fos.close();
            baos.close();
            // bm.compress(Bitmap.CompressFormat.JPEG, 70, fos);
            Log.e("PictureUtil", "Compress OK!");

        } catch (Exception e) {
            Log.e("PictureUtil", "error", e);
        }
        return newPath;
    }

}
