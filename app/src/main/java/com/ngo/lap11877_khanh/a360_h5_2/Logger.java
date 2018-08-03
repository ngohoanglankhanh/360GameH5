package com.ngo.lap11877_khanh.a360_h5_2;

import android.util.Log;

import java.util.Locale;

import static android.util.Log.DEBUG;
import static android.util.Log.ERROR;
import static android.util.Log.INFO;
import static android.util.Log.VERBOSE;
import static android.util.Log.WARN;
import static android.util.Log.isLoggable;

public final class Logger {
    private final String TAG;

    private final boolean ENABLE;

    private Logger(Class<?> clazz, boolean enable) {
        String tag = clazz.getSimpleName();
        if (tag.length() > 23) {
            tag = tag.substring(0, 22);
        }
        TAG = tag;
        ENABLE = enable;
    }

    private Logger(String tag, boolean enable) {
        if (tag.length() > 23) {
            tag = tag.substring(0, 22);
        }
        TAG = tag;
        ENABLE = enable;
    }

    public void e(String fmt, Object... args) {
        if (isLoggable(TAG, ERROR)) {
            Log.e(TAG, format(fmt, args));
        }
    }

    public void e(Throwable t, String fmt, Object... args) {
        if (isLoggable(TAG, ERROR)) {
            Log.e(TAG, format(fmt, args), t);
        }
    }

    public void d(String fmt, Object... args) {
        if (isLoggable(TAG, DEBUG)) {
            Log.d(TAG, format(fmt, args));
        }
    }

    public void d(Throwable t, String fmt, Object... args) {
        if (isLoggable(TAG, DEBUG)) {
            Log.d(TAG, format(fmt, args), t);
        }
    }

    public void i(String fmt, Object... args) {
        if (isLoggable(TAG, INFO)) {
            Log.i(TAG, format(fmt, args));
        }
    }

    public void i(Throwable t, String fmt, Object... args) {
        if (isLoggable(TAG, INFO)) {
            Log.i(TAG, format(fmt, args), t);
        }
    }

    public void w(String fmt, Object... args) {
        if (isLoggable(TAG, WARN)) {
            Log.w(TAG, format(fmt, args));
        }
    }

    public void w(Throwable t, String fmt, Object... args) {
        if (isLoggable(TAG, WARN)) {
            Log.w(TAG, format(fmt, args), t);
        }
    }

    public void wtf(String fmt, Object... args) {
        Log.wtf(TAG, format(fmt, args));
    }

    public void wtf(Throwable t, String fmt, Object... args) {
        Log.wtf(TAG, format(fmt, args), t);
    }

    public void v(String fmt, Object... args) {
        if (isLoggable(TAG, VERBOSE)) {
            Log.v(TAG, format(fmt, args));
        }
    }

    public void v(Throwable t, String fmt, Object... args) {
        if (isLoggable(TAG, VERBOSE)) {
            Log.v(TAG, format(fmt, args), t);
        }
    }

    private static String format(String fmt, Object... args) {
        return String.format(Locale.US, fmt, args);
    }

    public static Logger getLogger(Class<?> clazz, boolean enabled) {
        return new Logger(clazz, enabled);
    }

    public static Logger getLogger(String tag, boolean enable) {
        return new Logger(tag, enable);
    }
}
