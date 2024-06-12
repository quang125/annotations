package com.os.annotation.test.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Main log class
 *
 * @author QuangNN
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Logger {

    public static void info(String info) {
        log.info(info);
    }

    public static void warn(String warn) {
        log.warn(warn);
    }

    public static void debug(String debug) {
        log.debug(debug);
    }

    public static void error(Throwable error) {
        error(error.getMessage(), error);
    }

    public static void error(String message, Throwable error) {
        log.error(message, error);
    }
}
