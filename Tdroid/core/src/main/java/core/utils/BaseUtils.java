package core.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lin on 2017/1/20.
 */
public class BaseUtils {

    public static boolean isNetworkReachable(Context context) {
        return true;
    }

    public static final int getHeightInPx(Context context) {
        final int height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    public static final int getWidthInPx(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static final int getHeightInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        int heightInDp = px2dip(context, height);
        return heightInDp;
    }

    public static final int getWidthInDp(Context context) {
        final float width = context.getResources().getDisplayMetrics().widthPixels;
        int widthInDp = px2dip(context, width);
        return widthInDp;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


    private static long lastClickTime = 0;

    /**
     * 快速点击
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        return timeD < 500;
    }

    public static boolean isEmpty(String context) {
        if (null == context)
            return true;
        if (context.equals("null"))
            return true;
        return TextUtils.isEmpty(context);
    }

    /**
     * 返回列表数据
     *
     * @param response
     * @param key
     * @return List<JSONObject>
     */
    public static List<JSONObject> getResponseList(JSONObject response,
                                                   String key) {
        List<JSONObject> temp = new ArrayList<JSONObject>();
        if (null != response) {
            try {
                if (isEmpty(key))
                    key = "data";
                JSONArray jsonArr = response.optJSONArray(key);
                if (jsonArr != null) {
                    for (int i = 0; i < jsonArr.length(); i++) {
                        temp.add(jsonArr.optJSONObject(i));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    public static void hideKeyBoard(Context context, View editText) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void showKeyBoard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    public static void showKeyBoard(Context context, View view, int type) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, type);
    }

    public static String getMyUUID(Activity activity) {
        final TelephonyManager tm = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, tmPhone, androidId;

        tmDevice = "" + tm.getDeviceId();

        tmSerial = "" + tm.getSimSerialNumber();

        androidId = ""
                + android.provider.Settings.Secure.getString(
                activity.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(),
                ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());

        String uniqueId = deviceUuid.toString();

        return uniqueId;

    }

    /**
     * 版本名
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    /**
     * 版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    /**
     * 包名信息
     *
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**
     * 获取电话号码
     */
    public static String getNativePhoneNumber(Context context) {
        String NativePhoneNumber = null;
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        NativePhoneNumber = telephonyManager.getLine1Number();
        return NativePhoneNumber;
    }

    /**
     * 关键字高亮显示
     *
     * @param target 需要高亮的关键字
     * @param text   需要显示的文字
     * @return spannable 处理完后的结果，记得不要toString()，否则没有效果
     */
    public static SpannableStringBuilder highlight(String text, String target) {
        if (text == null) return null;
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        if (TextUtils.isEmpty(target))
            return spannable;
        CharacterStyle span = null;

        Pattern p = Pattern.compile(target);
        Matcher m = p.matcher(text);
        while (m.find()) {
            span = new ForegroundColorSpan(Color.RED);// 需要重复！
            spannable.setSpan(span, m.start(), m.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }


    /**
     * 更新ViewGroup 所有子状态
     *
     * @param viewGroup
     * @param isSelected
     */
    public static void onSelectedAllChilds(ViewGroup viewGroup, boolean isSelected) {
        viewGroup.setSelected(isSelected);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setSelected(isSelected);
        }
    }

    //TODO：生成随机奇数
    public static int getRandomNumberOdd() {
        Random random = new Random();
        return 1;
    }


    public static String getSignInfo(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            Signature sign = signatures[0];
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(new
                    ByteArrayInputStream(sign.toByteArray()));
            byte[] buffer = certificate.getEncoded();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            //获得公钥
            byte[] publicKey = md.digest(buffer);
            //字节到十六进制的格式转换
            String hexString = byte2HexFormatted(publicKey);
            return hexString;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String byte2HexFormatted(byte[] arr) {
        StringBuilder str = new StringBuilder(arr.length * 2);
        for (int i = 0; i < arr.length; i++) {
            String h = Integer.toHexString(arr[i]);
            int l = h.length();
            if (l == 1)
                h = "0" + h;
            if (l > 2)
                h = h.substring(l - 2, l);
            str.append(h.toUpperCase());
            if (i < (arr.length - 1))
                str.append(':');
        }
        return str.toString();
    }

    public static void doStartApplicationWithPackageName(Context context, String packagename) {

        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = context.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = 参数packname
            String packageName = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            // 设置ComponentName参数1:packagename参数2:MainActivity路径
            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }

    /**
     * 判断一个程序是否显示在前端,根据测试此方法执行效率在11毫秒,无需担心此方法的执行效率
     *
     * @param packageName
     * @param context
     * @return
     */
    public static boolean isApplicationShowing(String packageName,
                                               Context context) {
        boolean result = false;
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
        if (appProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : appProcesses) {
                if (runningAppProcessInfo.processName.equals(packageName)) {
                    int status = runningAppProcessInfo.importance;
                    if (status == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE
                            || status == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 判断一个程序是否显示在前端,根据测试此方法执行效率在11毫秒,无需担心此方法的执行效率
     *
     * @param packageName
     * @param context
     * @return
     */
    public static int getApplicationStatus(String packageName,
                                           Context context) {
        int result = -1;
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
        if (appProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : appProcesses) {
                if (runningAppProcessInfo.processName.equals(packageName)) {
                    int status = runningAppProcessInfo.importance;
                    if (status == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE || status ==
                            ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        result = 1;
                    } else if (status == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                        result = 0;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 判断集合是否为null或者0个元素
     *
     * @param c
     * @return
     */
    public static boolean isNullOrEmpty(Collection c) {
        if (null == c || c.isEmpty()) {
            return true;
        }
        return false;
    }
    /**
     * 跳转至浏览器
     *
     * @param context
     * @param msg
     */
    public static void jumpToBrowse(Context context, String msg) {
        Uri uri = Uri.parse(msg);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    public static boolean inRangeOfView(View view, MotionEvent ev) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y + view.getHeight())) {
            return false;
        }
        return true;
    }

    public static void bringToFront(String packageName, Activity activity) {
        ActivityManager am = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        am.moveTaskToFront(activity.getTaskId(), 0);
        //

    }

    public static void launchApp(String packageName, Activity schemeServerActivity) {
        Intent LaunchIntent = schemeServerActivity.getPackageManager().getLaunchIntentForPackage(packageName);
        schemeServerActivity.startActivity(LaunchIntent);
    }

    //通过downLoadId查询下载的apk，解决6.0以后安装的问题
    public static File queryDownloadedApk(Context context, long downloadId) {
        File targetApkFile = null;
        DownloadManager downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadId != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
            Cursor cur = downloader.query(query);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    String uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    if (!TextUtils.isEmpty(uriString)) {
                        targetApkFile = new File(Uri.parse(uriString).getPath());
                    }
                }
                cur.close();
            }
        }
        return targetApkFile;
    }


}
