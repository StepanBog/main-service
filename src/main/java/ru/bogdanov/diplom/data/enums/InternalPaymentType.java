package ru.bogdanov.diplom.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author SBogdanov
 * Типы внутренних платежей
 */
@AllArgsConstructor
public enum InternalPaymentType {

    ODP_PAYMENT("Получено в рамках сервиса");

    @Getter
    private String text;
}
