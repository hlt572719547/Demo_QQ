package com.example.administrator.myqqdemo.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Environment;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * User: Zenos Date: 12-10-18
 */
public class Utility {
    public final static int UTILITY_NORMAL = 0;
    public final static int UTILITY_GOBACK = 1;

    private static final long PREVENT_TIEM = 500;
    private static long mLastClickTime = 0L;
    private static final int ACCOUNT_FORMATE_LENGTH = 12;

    //阻止双击事件
    public static boolean preventDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - mLastClickTime;
        if (0 < timeD && timeD < PREVENT_TIEM) {
            return true;
        }
        mLastClickTime = time;
        return false;
    }


    /**
     * 对银行卡进行加*号保护设置
     *
     * @param cardNo
     * @return result
     */
    public static String protectBankCardNo(String cardNo) {
        String star = "*";
        int cardNoLength = cardNo.length();
        String start = cardNo.substring(0, 6);
        String end = cardNo.substring(cardNoLength - 4, cardNoLength);// cardNo.substring(6
        // +
        // stars.length(),
        // cardNo.length());
        String stars = "";
        for (int i = 0; i < cardNoLength - 10; i++) {
            stars += star;
        }
        String result = start + stars + end;
        return result;
    }

    /**
     * 对银行卡进行加*号保护设置并格式化
     *
     * @param cardNo
     * @return 如：6222 **** **** 1234
     */
    public static String protectBankNoAndFormat(String cardNo) {
        String star = " **** **** ";
        int cardNoLength = cardNo.length();
        if (cardNoLength >= 4) {
            String start = cardNo.substring(0, 4);
            String end = cardNo.substring(cardNoLength - 4, cardNoLength);
            return start + star + end;
        } else {
            return cardNo;
        }
    }


    /**
     * 对银行卡好进行加空格格式化
     *
     * @param cardNo
     * @return
     */
    public static String formatCard(String cardNo) {
        return cardNo.substring(0, 4) + "  " + cardNo.substring(4, 8) + "  "
                + cardNo.substring(8, 12) + "  " + cardNo.substring(12, 16)
                + "  " + cardNo.substring(16, cardNo.length());
    }

    /**
     * 对不确定的银行卡号进行加空格格式化
     *
     * @param cardNo
     * @return
     */
    public static String formatUnknownLengthCard(String cardNo) {
        String formatCardNo = "";
        for (int i = 0; i < cardNo.length(); i++) {
            if (i % 4 == 0) {
                formatCardNo += "  ";
            }
            if ((i + 1) <= cardNo.length()) {
                formatCardNo += cardNo.substring(i, i + 1);
            }
        }

        return formatCardNo;
    }

    /**
     * 对日期格式化方法
     *
     * @param dateStr
     * @return
     */
    public static String dateFormat(String dateStr) {
        String date = "";
        if (dateStr.length() < 6) {
            date = dateStr;
        } else if (dateStr.length() == 6) {
            date = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6);
        } else if (dateStr.length() == 8) {
            date = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6)
                    + "-" + dateStr.substring(6, 8);
        } else if (dateStr.length() == 14) {
            date = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6)
                    + "-" + dateStr.substring(6, 8) + " "
                    + dateStr.substring(8, 10) + ":"
                    + dateStr.substring(10, 12) + ":"
                    + dateStr.substring(12, 14);
        } else if (dateStr.length() == 12) {
            date = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6)
                    + "-" + dateStr.substring(6, 8) + " "
                    + dateStr.substring(8, 10) + ":"
                    + dateStr.substring(10, 12);
        } else {
            date = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6)
                    + "-" + dateStr.substring(6, 8) + " "
                    + dateStr.substring(8, 10) + ":"
                    + dateStr.substring(10, 12) + ":"
                    + dateStr.substring(12, 14) + " "
                    + dateStr.substring(14, dateStr.length());
        }
        return date;
    }

    /**
     * 代码创建一个selector 代码生成会清除padding
     */
    public static Drawable CreateStateDrawable(Context context, int bulr, int focus) {
        Drawable bulrDrawable = context.getResources().getDrawable(bulr);
        Drawable focusDrawable = context.getResources().getDrawable(focus);
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, focusDrawable);
        drawable.addState(new int[]{}, bulrDrawable);
        return drawable;
    }

    /**
     * 展示信息
     *
     * @param msg 展示信息
     */
    public static void showInfo(String msg, Context context) {
        Toast.makeText(context, msg, 5000).show();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 补位金额
     *
     * @param balance
     * @return
     */
    public static String formatAmount(String balance) {
        if (0 == balance.length()) {
            balance = "000";
        } else if (1 == balance.length()) {
            balance = "00" + balance;
        } else if (2 == balance.length()) {
            balance = "0" + balance;
        }
        return balance;
    }


    /**
     * 将传入的金额（单位分）格式化为元，保留两位小数
     * 格式如：5.00元
     *
     * @param s
     * @return
     */
    public static String formatMoney(String s) {
        if (TextUtils.isEmpty(s)) {
            return "0.00元";
        }
        double num = Double.parseDouble(s);
        return formatMoney(num);
    }

    /**
     * 将传入的金额（单位元）格式化为元，保留两位小数
     * 格式如：5.00元
     *
     * @param s
     * @return
     */
    public static String formatMoney2(String s) {
        if (TextUtils.isEmpty(s)) {
            return "0.00元";
        }
        double num = Double.parseDouble(s) * 100;
        return formatMoney(num);
    }

    /**
     * 将传入的金额（单位分）格式化为元，保留两位小数
     * 格式如：5.00元
     *
     * @param amount
     * @return
     */
    public static String formatMoney(double amount) {
        DecimalFormat formater = new DecimalFormat("###,##0.00元");
        String result = formater.format(amount / 100.0d);
        return result;
    }

    /**
     * 格式化时间字符串 xxxx-xx-xx xx:xx
     *
     * @param dateString
     * @return
     */
    public static String formatStringDate(String dateString) {
        if (dateString == null || dateString.length() < 1) {
            return "";
        }
        String year = dateString.substring(0, 4); // 年份
        String mouth = dateString.substring(4, 6); // 月份
        String day = dateString.substring(6, 8); // 日期
        String hour = dateString.substring(8, 10); // 时
        String minute = dateString.substring(10, 12); // 分

        return year + "-" + mouth + "-" + day + " " + hour + ":" + minute;
    }

    /**
     * 格式化时间字符串 xxxx-xx-xx xx:xx:xx
     *
     * @param dateString
     * @return
     */
    public static String formatStringDate3(String dateString) {
        if (dateString == null || dateString.length() < 1) {
            return "";
        }
        String year = dateString.substring(0, 4); // 年份
        String mouth = dateString.substring(4, 6); // 月份
        String day = dateString.substring(6, 8); // 日期
        String hour = dateString.substring(8, 10); // 时
        String minute = dateString.substring(10, 12); // 分
        String second = dateString.substring(12, 14); // 分

        return year + "-" + mouth + "-" + day + " " + hour + ":" + minute + ":" + second;
    }


    /**
     * 将yyyy-MM-dd HH:mm:ss格式的时间转换为yyyyMMddHHmmss格式时间
     *
     * @param dateString
     * @return
     */
    public static String formatStringDate4(String dateString) {
        if (dateString == null || dateString.length() < 1) {
            return "";
        }
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = f.parse(dateString);
            return formatDateToString(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 格式化时间字符串 xxxx-xx-xx
     *
     * @param dateString
     * @return
     */
    public static String formatStringDate5(String dateString) {
        if (dateString == null || dateString.length() < 1) {
            return "";
        }
        String year = dateString.substring(0, 4); // 年份
        String mouth = dateString.substring(4, 6); // 月份
        String day = dateString.substring(6, 8); // 日期

        return year + "-" + mouth + "-" + day;
    }

    /**
     * 格式化时间字符串
     *
     * @param dateString
     * @return
     */
    public static String formatStringDate2(String dateString) {
        if (dateString == null || dateString.length() < 1) {
            return "";
        }
        String year = dateString.substring(0, 4); // 年份
        String mouth = dateString.substring(4, 6); // 月份
        String day = dateString.substring(6, 8); // 日期

        return year + "年" + mouth + "月" + day + "日";
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String formatDateToString(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 转换为时间
     */
    public static Date parseToDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 截取时间窜
     *
     * @return yyyy-MM-dd HH:mm:ss返回字符串格式 yyyy-MM-dd
     */
    public static String formatDateStringSubstring(String date) {
        if (date.length() >= 10) {
            return date.substring(0, 10);
        }
        return date;
    }

    public static boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /**
     * 设置视图背景图片
     *
     * @param context
     * @param view
     * @param resource
     */
    public static void setViewBackground(Context context, View view,
                                         int resource) {
        // 设置平铺分割栏背景样式
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(
                    context.getResources(), resource);
            BitmapDrawable drawable = new BitmapDrawable(bitmap);
            drawable.setTileModeXY(Shader.TileMode.REPEAT,
                    Shader.TileMode.REPEAT);
            drawable.setDither(true);
            view.setBackgroundDrawable(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static boolean isHaveExternalStorage() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    // 编辑图片大小，保持图片不变形。
    public static Bitmap resetImage(Bitmap sourceBitmap, int resetWidth,
                                    int resetHeight) {
        int width = sourceBitmap.getWidth();
        int height = sourceBitmap.getHeight();
        int tmpWidth;
        int tmpHeight;
        float scaleWidth = (float) resetWidth / (float) width;
        float scaleHeight = (float) resetHeight / (float) height;
        float maxTmpScale = scaleWidth >= scaleHeight ? scaleWidth : scaleHeight;
        // 保持不变形
        tmpWidth = (int) (maxTmpScale * width);
        tmpHeight = (int) (maxTmpScale * height);
        Matrix m = new Matrix();
        m.setScale(maxTmpScale, maxTmpScale, tmpWidth, tmpHeight);
        sourceBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0, width, height, m, false);
        // 切图
        int x = (tmpWidth - resetWidth) / 2;
        int y = (tmpHeight - resetHeight) / 2;
        return Bitmap.createBitmap(sourceBitmap, x, y, resetWidth, resetHeight);
    }

    /**
     * 获取习惯意义金额单位为分，比如formateAccountBalance为"000000012345"，转化后为12345
     *
     * @param formateAccountBalance 按银联标准格式化金额，长度必须为12
     * @return 转化后的金额值（单位为分），若转化失败返回null
     */
    public static String getAccountBalance(String formateAccountBalance) {
        if (null == formateAccountBalance
                || formateAccountBalance.length() != ACCOUNT_FORMATE_LENGTH) {
            return null;
        }
        try {
            int a = Integer.parseInt(formateAccountBalance);
            return "" + a;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 按银联格式要求格式化后金额
     *
     * @param accountBalance 习惯意义上的金额单位为分
     * @return 格式化后金额
     */
    public static String formateAccountBalance(String accountBalance) {
        String s = accountBalance;
        int cout = ACCOUNT_FORMATE_LENGTH - s.length();
        while (cout-- > 0) {
            s = "0" + s;
        }
        return s;
    }

    /**
     * 姓名加星保护
     *
     * @param name
     * @return 如：*返回
     */
    public static String protectName(String name) {
        if (TextUtils.isEmpty(name)) {
            return "";
        }
        String star = "";
        for (int i = 0; i < name.length() - 1; i++) {
            star += "*";
        }
        return name.substring(0, 1) + star;
    }

    /**
     * 手机号码加星保护
     *
     * @param phone
     * @return 如：136*****256
     */
    public static String protectphone(String phone) {
        String star = "******";
        return phone.substring(0, 3) + star + phone.substring(phone.length() - 3, phone.length());
    }

    /**
     * 验证邮箱格式
     *
     * @param strEmail
     * @return
     */
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 验证身份证是否有效15位或18位<包括对年月日的合法性进行验证>
     *
     * @param inputString
     * @return
     */
    public static boolean isIDCard(String inputString) {
        Pattern pattern = Pattern.compile("^\\d{15}(\\d{2}[0-9xX])?$");
        Matcher macher = pattern.matcher(inputString);
        if (macher.find()) {//对年月日字符串的验证
            String power = inputString.substring(inputString.length() - 12, inputString.length() - 4);
            pattern = Pattern.compile("^[1-2]+([0-9]{3})+(0[1-9][0-2][0-9]|0[1-9]3[0-1]|1[0-2][0-3][0-1]|1[0-2][0-2][0-9])");
            macher = pattern.matcher(power);
        }
        return macher.find();
    }

    /**
     * 根据输入内容自动格式化银行卡
     * 格式如：6226 1234 1234 1234
     *
     * @param mEditText
     */
    public static void autoFormatBankCardNoWithSpace(final EditText mEditText) {
        mEditText.addTextChangedListener(new TextWatcher() {
            char delimiter = ' ';
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;//记录光标的位置
            private char[] tempChar;
            private final StringBuffer buffer = new StringBuffer();
            int spaceNumberB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                spaceNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == delimiter) {
                        spaceNumberB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = mEditText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == delimiter) {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    index = 0;
                    int spaceNumberC = 0;
                    while (index < buffer.length()) {
                        if ((index == 4 || index == 9 || index == 14 || index == 19)) {
                            buffer.insert(index, delimiter);
                            spaceNumberC++;
                        }
                        index++;
                    }

                    if (spaceNumberC > spaceNumberB) {
                        location += (spaceNumberC - spaceNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }

                    mEditText.setText(str);
                    String realCardNo = str.replace(delimiter + "", "");
                    mEditText.setTag(realCardNo);
                    Editable etable = mEditText.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
    }

    /**
     * 通用处理金额输入
     *
     * @param edit
     */
    public static void formatInput(EditText edit) {
        String str = edit.getText().toString();
        String newstr = "" + str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.contains(".")) {
            int index = str.indexOf(".");

            if (index == 0) {
                newstr = "0" + str;
            }
            int lastIndex = str.lastIndexOf(".");
            if (index != lastIndex) {
                newstr = str.substring(0, lastIndex)
                        + str.substring(lastIndex + 1);
            }

            String[] temp2 = newstr.split("\\.");
            if (temp2.length == 2) {
                if (temp2[1].length() > 2) {
                    newstr = temp2[0] + "." + temp2[1].substring(0, 2);
                }
            }
            if (!newstr.equals(str)) {
                edit.setText(newstr);
                CharSequence text = edit.getText();
                if (text instanceof Spannable) {
                    Spannable spanText = (Spannable) text;
                    Selection.setSelection(spanText, text.length());
                }
            }
        } else {
            if (str.charAt(0) == '0' && str.length() > 1) {
                edit.setText(str.substring(1));
                CharSequence text = edit.getText();
                if (text instanceof Spannable) {
                    Spannable spanText = (Spannable) text;
                    Selection.setSelection(spanText, text.length());
                }
            }
        }
    }

    /**
     * 将字符串转换为date
     *
     * @param dateString
     * @return
     */
    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }

    //验证金额
    public static boolean isAmountNumber(String str) {
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$"); // 判断小数点后一位的数字的正则表达式
        Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    //去除错误码方法
    public static String removeErrCode(String errStr) {
        String newStr = "";
        if (!TextUtils.isEmpty(errStr) && errStr.contains("【") && errStr.contains("】") && errStr.lastIndexOf("【") < errStr.lastIndexOf("】")) {
            newStr = errStr.substring(0, errStr.lastIndexOf("【"));
        } else {
            newStr = errStr == null ? "" : errStr;
        }
        return newStr;
    }

    /**
     * 是否为纯数字
     */
    public static boolean isAllNumbers(String str) {
        return str.matches("^[0-9]+$");
    }

    /**
     * 是否为纯字母
     */
    public static boolean isAllLetters(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("^[A-Za-z]+$");
    }

    /**
     * 是否为纯特殊字符
     *
     * @param str
     * @return
     */
    public static boolean isAllSpeLetters(String str) {
        boolean isspeletter = false;
        if (TextUtils.isEmpty(str)) {
            isspeletter = false;
        }
        if (str.matches("^\\p{Punct}+$")) {
            isspeletter = true;
        }

        if (!str.matches("^[A-Za-z0-9\\p{Punct}]+$")) {
            isspeletter = true;
        }
        return isspeletter;
    }

    /**
     * 判断是否是中文
     * @param str
     * @return
     */
    public static boolean isChinessWord(String str) {
        if (str.matches("^[\\u4E00-\\u9FA5]+$")) {
            return true;
        }
        return false;
    }

    /**
     * 将图片的四角圆化
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //得到画布
        Canvas canvas = new Canvas(output);

        //将画布的四角圆化
        final int color = Color.RED;
        final Paint paint = new Paint();
        //得到与图像相同大小的区域  由构造的四个值决定区域的位置以及大小
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        //值越大角度越明显
        final float roundPx = bitmap.getWidth() / 2;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        //drawRoundRect的第2,3个参数一样则画的是正圆的一角，如果数值不同则是椭圆的一角
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static boolean isPhone(String phone) {
        return !TextUtils.isEmpty(phone) && phone.matches("^(1(([35][0-9])|(47)|[8][01236789]))\\d{8}$");
    }
    
}
