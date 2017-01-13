package org.buaa.ztang.utils;

import java.sql.Timestamp;

/**
 * Created by qixiang on 1/6/17.
 */
public class TimeUtils {

    public static final long MS_IN_DAY = 24L * 3600L * 1000L;

    public static Timestamp currentTime(long offset)
    {
        return new Timestamp(System.currentTimeMillis()+offset);
    }

}
