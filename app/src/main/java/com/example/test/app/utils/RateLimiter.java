package com.example.test.app.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Utility class that decides whether we should fetch data from remote or not.
 */
public class RateLimiter {
    private final long timeout;
    private SharedPreferences sharedPreferences;

    @Inject
    public RateLimiter(@NonNull Application context) {
        this(context, 1, TimeUnit.HOURS);
    }

    public RateLimiter(Application context, int timeout, TimeUnit timeUnit) {
        this.timeout = timeUnit.toMillis(timeout);
        sharedPreferences = context.getSharedPreferences(RateLimiter.class.getName(), Context.MODE_PRIVATE);
    }

    public synchronized boolean shouldFetch(String key) {
        long lastFetched = sharedPreferences.getLong(key, 0);
        long now = now();
        if (lastFetched == 0) {
            sharedPreferences.edit().putLong(key, now).apply();
            return true;
        }
        if (now - lastFetched > timeout) {
            sharedPreferences.edit().putLong(key, now).apply();
            return true;
        }
        return false;
    }

    private long now() {
        return SystemClock.uptimeMillis();
    }

    public synchronized void reset(String key) {
        sharedPreferences.edit().remove(key).apply();
    }
}
