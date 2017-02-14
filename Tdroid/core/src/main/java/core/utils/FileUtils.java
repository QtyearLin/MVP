package core.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * Created by Lin on 2017/1/20.
 */

public class FileUtils {
    /**
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file);
            }
        }
        return dirSize;
    }

    /**
     * @param fileSize
     * @return
     */
    public static String getDirSizeToString(Long fileSize) {
        double size = 0;
        size = (fileSize + 0.0) / (1024 * 1024);
        DecimalFormat df = new DecimalFormat("0.00");//
        String filesize = df.format(size) + "MB";
        return filesize;
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                deleteFileWithDelay(file); //
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            LogUtils.e("deletefile:", file.getAbsolutePath());
            deleteFileWithDelay(file); //
        } else {
            System.out.println("file not exist" + '\n');
        }
    }

    public static void deleteFileWithDelay(File file) {
        if (null != file && file.exists()) {
            file.delete();
            SystemClock.sleep(50);
        }
    }

    public static void writeFileSdcard(String fileName, String message) {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(fileName);
            // FileOutputStream fout = openFileOutput(fileName, MODE_PRIVATE);
            byte[] bytes = message.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            try {
                fout.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public static String readFile(File file) {
        String content = "";
        if (file.isDirectory()) {
            LogUtils.d("TestFile", "The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(
                            instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    while ((line = buffreader.readLine()) != null) {
                        content += line + "\n";
                    }
                    instream.close();
                }
            } catch (FileNotFoundException e) {
                LogUtils.d("TestFile", "The File doesn't not exist.");
            } catch (IOException e) {
                LogUtils.d("TestFile", e.getMessage());
            }
        }
        return content;
    }

    public static String readFile(String strFilePath) {
        String path = strFilePath;
        File file = new File(path);
        return readFile(file);
    }

    public static String saveImageToGallery(Context context, File tempFile) {
        // 其次把文件插入到系统图库
        String url = null;
        try {
            url = MediaStore.Images.Media.insertImage(
                    context.getContentResolver(), tempFile.getAbsolutePath(),
                    tempFile.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new
                Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" +
                url)));
        // context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
        // Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
        return url;
    }

    public static String getAbsoluteImagePath(Uri uri, ContentResolver resolver) {
        Cursor cursor = null;
        String path = null;
        try {
            // can post image
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = resolver.query(uri, proj, // Which columns to return
                    null, // WHERE clause; which rows to return (all rows)
                    null, // WHERE clause selection arguments (none)
                    null); // Order-by clause (ascending by name)

            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(column_index);
        } catch (IllegalArgumentException e) {
        } finally {
            if (null != cursor)
                cursor.close();
        }
        return path;
    }
}
