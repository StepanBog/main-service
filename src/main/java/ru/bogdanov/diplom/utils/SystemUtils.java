package ru.bogdanov.diplom.utils;

import lombok.experimental.UtilityClass;

import java.util.concurrent.TimeUnit;

/**
 * @author SBogdanov
 * Системные утилиты
 */
@UtilityClass
public class SystemUtils {

    public static void pause(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            //nothing
        }
    }
}
