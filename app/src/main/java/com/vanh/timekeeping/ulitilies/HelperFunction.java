package com.vanh.timekeeping.ulitilies;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HelperFunction {

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
    private Bitmap getBitmapFromEncodedString(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
    public static void showYesNoDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false); // Ngăn người dùng tắt hộp thoại bằng cách chạm ra ngoài

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý sự kiện khi người dùng bấm nút Yes
                dialog.dismiss(); // Đóng hộp thoại
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý sự kiện khi người dùng bấm nút No
                dialog.dismiss(); // Đóng hộp thoại
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show(); // Hiển thị hộp thoại
    }
    public static String getDayNow() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        return dateFormat.format(date);
    }
    public static long getTimestampStartOfDay(long timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);

        // Lấy ngày từ LocalDateTime
        LocalDateTime startOfDay = dateTime.toLocalDate().atStartOfDay();

        // Chuyển startOfDay thành timestamp
        return startOfDay.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
    public static long getTimestampEndOfDay(long timestamp) {
        // Chuyển timestamp thành LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);

        // Lấy ngày từ LocalDateTime và thêm một ngày
        LocalDateTime endOfDay = dateTime.toLocalDate().atStartOfDay().plusDays(1).minusSeconds(1);

        // Chuyển endOfDay thành timestamp
        return endOfDay.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public static long getTimestampStartOfNow() {
        LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
        return startOfToday.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
    public static long getTimestampEndOfNow() {
        LocalDateTime endOfToday = LocalDate.now().atStartOfDay().plusDays(1).minusSeconds(1);
        return endOfToday.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
    public static long getTimestampNow() {
        return System.currentTimeMillis();
    }
    public static String convertFormTimestampToDay(long timestamp) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), java.time.ZoneId.systemDefault());

        // Tạo đối tượng DateTimeFormatter với định dạng "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển LocalDateTime thành chuỗi theo định dạng
        return localDateTime.format(formatter);
    }
}
