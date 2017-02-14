package core.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import okhttp3.Cache;

/**
 * Created by Lin on 2017/1/20.
 */
public class CacheUtil {

    private static final long MAX_FILE_SIZE_OKHTTP = 50 * 1024 * 1024;

    public static final String CACHEDIR = "app";
    public static final String CACHEDIR_IMAGE = "ImageCache";
    public static final String CACHEDIR_VIDEO = "VideoCache";
    public static final String CACHEDIR_IMAGE_TAKE = "ImageTake";
    public static final String CACHEDIR_VIDEO_TAKE = "VideoTake";
    public static final String CACHEDIR_IMAGE_SAVE = "ImageSave";
    public static final String CACHEDIR_HEAD = "HeadImage";
    public static final String CACHEDIR_FILE = "File";
    public static final String CACHEDIR_OKHTTP = "Okhttp";
    public static final String CACHEDIR_APK = "Apk";
    public static final String CACHEDIR_CRASH = "Crash";
    public static final String WEB_VIEW_CACHE = "h5Cache";

    public static String getCachDir(Context context) {
        String dir = getSDPath(context) + "/" + CACHEDIR;
        return dir;
    }

    public static File getCachFile(Context context) {
        return createFile(getCachDir(context));
    }

    public static String getImageCachDir(Context context) {
        File file = getCachFile(context);
        String dir = file.getAbsolutePath() + "/" + CACHEDIR_IMAGE;
        return dir;
    }

    public static File getImageCachFile(Context context) {
        return createFile(getImageCachDir(context));
    }

    public static String getVideoCachDir(Context context) {
        File file = getCachFile(context);
        String dir = file.getAbsolutePath() + "/" + CACHEDIR_VIDEO;
        return dir;
    }

    public static File getVideoCachFile(Context context) {
        return createFile(getVideoCachDir(context));
    }

    public static String getImageSaveDir(Context context) {
        String dir = getSDPath(context) + "/" + CACHEDIR_IMAGE_SAVE;
        return dir;
    }

    public static File getImageSaveFile(Context context) {
        return createFile(getImageSaveDir(context));
    }

    public static String getWebViewCacheSaveDir(Context context) {
        String dir = getSDPath(context) + "/" + WEB_VIEW_CACHE;
        createFile(dir);
        return dir;
    }

    public static File getWebViewCacheDir(Context context) {
        return createFile(getWebViewCacheSaveDir(context));
    }


    public static String getImageTakeDir(Context context) {
        File file = getCachFile(context);
        String dir = file.getAbsolutePath() + "/" + CACHEDIR_IMAGE_TAKE;
        return dir;
    }

    public static File getImageTakeFile(Context context) {
        return createFile(getImageTakeDir(context));
    }

    public static String getVideoTakeDir(Context context) {
        File file = getImageSaveFile(context);
        String dir = file.getAbsolutePath() + "/" + CACHEDIR_VIDEO_TAKE;
        return dir;
    }

    public static File getVideoTakeFile(Context context) {
        return createFile(getVideoTakeDir(context));
    }

    public static String getApkCachDir(Context context) {
        File file = getCachFile(context);
        String dir = file.getAbsolutePath() + "/" + CACHEDIR_APK;
        return dir;
    }

    public static String getHeadCachDir(Context context) {
        File file = getCachFile(context);
        String dir = file.getAbsolutePath() + "/" + CACHEDIR_HEAD;
        return dir;
    }

    public static String getFileCachDir(Context context) {
        File file = getCachFile(context);
        String dir = file.getAbsolutePath() + "/" + CACHEDIR_FILE;
        return dir;
    }

    public static File getFile(Context context) {
        return createFile(getFileCachDir(context));
    }

    public static String getOkhttpCachDir(Context context) {
        File file = getFile(context);
        String dir = file.getAbsolutePath() + "/" + CACHEDIR_OKHTTP;
        return dir;
    }

    public static File getFileOkhttp(Context context) {
        return createFile(getOkhttpCachDir(context));
    }

    public static String getFileSecondCachDir(Context context, String name) {
        File file = getCachFile(context);
        String cache = file.getAbsolutePath() + "/" + CACHEDIR_FILE;
        File f = createFile(cache);
        String dir = f.getAbsolutePath() + "/" + name;
        return dir;
    }

    public static String getCrashDir(Context context) {
        String dir = getCachDir(context) + "/" + CACHEDIR_CRASH;
        createFile(dir);
        return dir;
    }

    public static File getCrashFile(Context context) {
        return createFile(getCrashDir(context));
    }

    public static File getFileSecondFile(Context context, String name) {
        return createFile(getFileSecondCachDir(context, name));
    }

    public static File createFile(String path) {
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        return file;
    }


    public static String getSDPath(Context context) {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!sdCardExist) {
            File path = new File(context.getFilesDir().getAbsolutePath());
            if (!path.exists()) {
                path.mkdirs();
            }
            sdDir = path;
        } else {
            sdDir = Environment.getExternalStorageDirectory();
        }
        if (sdDir != null) {
            return sdDir.toString();
        } else {
            return "";
        }
    }


    public static Cache getOkhttpCache(Context mAppContext) {
        final Cache cache = new Cache(getFileOkhttp(mAppContext), MAX_FILE_SIZE_OKHTTP);
        return cache;
    }


}
