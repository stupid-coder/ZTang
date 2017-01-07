package org.buaa.ztang.utils;

import java.sql.Timestamp;

/**
 * Created by qixiang on 1/6/17.
 */
public class TimeUtils {

    public static Timestamp currentTime()
    {
        return new Timestamp(System.currentTimeMillis());
    }

}
