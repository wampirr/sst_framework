package ru.tinkoff.utils;

import javafx.util.Pair;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * members are List<Pair<String,String>>
 * пары: инпут + текст ошибки
 */
public class FieldsValidationData {
    //todo generalize and/or move out all input/errorText data


    public static void tryBadInputsAndCheckErrors(WebElementFacade inputField, WebElementFacade errorField, List<Pair<String, String>> inputsAndErrors) {
        for (Pair<String, String> pair : inputsAndErrors) {
            inputField.waitUntilClickable();
            inputField.typeAndEnter(pair.getKey());
            assertEquals(pair.getValue(), errorField.getText());
            inputField.clear();
        }
    }

    //поле код плательщика 10 цифр
    public static final List<Pair<String, String>> payerZHKHCode10numbers =
            Arrays.asList(
                    new Pair<>("\"~!@#$%^&*()_+-йцуqweû123", "Поле неправильно заполнено"),
                    new Pair<>("\"~!@#$%^&*()_+-йцуqweû", "Поле обязательное"));

    //поле период платежа mm.yy, mm.yyyy
    public static final List<Pair<String, String>> payPeriodZHKHMonthYear =
            Arrays.asList(
                    new Pair<>("~!@#$%^&qweйцу√1", "Поле заполнено некорректно"),
                    new Pair<>("\"~!@#$%^&*()_+-йцуqweû.01.1.qwe", "Поле заполнено некорректно"),
                    new Pair<>("\"~!@#$%^&*()_+-йцуqweû.00.2018.qwe", "Поле заполнено некорректно"),
                    new Pair<>("\"~!@#$%^&*()_+-йцуqweû.21.2018.qwe", "Поле заполнено некорректно"),
                    new Pair<>("\"~!@#$%^&*()_+-йцуqweûqwe", "Поле обязательное"));

    //todo ask potential minor bug: validation bypasses '/'
    //поле сумма платежа 10<=x<=15000 + () *
    public static final List<Pair<String, String>> paySum15kLimit =
            Arrays.asList(new Pair<>("*", "Поле заполнено неверно"),
                    new Pair<>("/", "Поле заполнено неверно"),
                    new Pair<>("()", "Поле заполнено неверно"),
                    new Pair<>("~!@#$%^&qweйцу√", "Поле обязательное"),
                    new Pair<>("9.99", "Минимум — 10 \u20BD"),
                    new Pair<>("15000.1", "Максимум — 15 000 \u20BD"),
                    new Pair<>("-777", "Поле заполнено неверно"));

    //поле сумма добровольного страхования из квитанции (должно быть меньше ака зависит от суммы платежа)
    public static final List<Pair<String, String>> voluntarilyHousingInsuranceSum =
            Arrays.asList(new Pair<>("\"~!@#$%^&*()_+-йцуqweû.01.1.qwe", "Поле заполнено неверно"),
                    new Pair<>("\"~!@#$%^&*()_+йцуqweû.,00000000000000000001.qwe", "Поле заполнено неверно"),
                    new Pair<>("\"~!@#$%^&*()_+йцуqweû9999999999999999999qwe", "Поле заполнено неверно"),
                    new Pair<>("777", "Сумма добровольного страхования не может быть больше итоговой суммы."));

}
