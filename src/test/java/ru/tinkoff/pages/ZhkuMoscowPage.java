package ru.tinkoff.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import ru.tinkoff.utils.FieldsValidationData;


@DefaultUrl("https://www.tinkoff.ru/zhku-moskva/")
@At("https://www.tinkoff.ru/zhku-moskva/(?:oplata/\\?tab=pay)?")
public class ZhkuMoscowPage extends PageObject {

    @FindBy(xpath = "//div[child::a/span[text()='Оплатить ЖКУ в Москве']]")
    private WebElementFacade payTab;

    public void select_pay_tab() {
        payTab.click();
    }

    @FindBy(xpath = "//input[@id='payerCode']")
    private WebElementFacade payerCode;
    @FindBy(xpath = "//div[child::div/label/div/input[@id='payerCode']]/div[@data-qa-file='UIFormRowError']")
    private WebElementFacade payerCodeUIFormRowError;

    @FindBy(xpath = "//input[@id='period']")
    private WebElementFacade period;
    @FindBy(xpath = "//div[child::div/div/label/div/input[@id='period']]/div[@data-qa-file='UIFormRowError']")
    private WebElementFacade periodUIFormRowError;

    @FindBy(xpath = "//label[child::span[contains(text(),'Сумма платежа')]]/div")
    private WebElementFacade paymentSum;
    @FindBy(xpath = "//div[child::div/label/span[contains(text(),'Сумма платежа')]]")
    private WebElementFacade paymentSumClickHelper;
    @FindBy(xpath = "//div[child::div/label/span[contains(text(),'Сумма платежа')]]/div[@data-qa-file='UIFormRowError']")
    private WebElementFacade paymentSumUIFormRowError;

    @FindBy(xpath = "//label[child::span[contains(text(),'Сумма добровольного страхования жилья из квитанции за ЖКУ')]]/div/input")
    private WebElementFacade voluntarilyHousingInsurance;
    @FindBy(xpath = "//div[child::div/label/span[contains(text(),'Сумма добровольного страхования жилья из квитанции за ЖКУ в Москве')]]/div[@data-qa-file='UIFormRowError']")
    private WebElementFacade voluntarilyHousingInsuranceUIFormRowError;

    public void validate_payment_fields() {
        FieldsValidationData.tryBadInputsAndCheckErrors(payerCode, payerCodeUIFormRowError, FieldsValidationData.payerZHKHCode10numbers);
        //fill them so they stop automove
        payerCode.typeAndEnter("1231231231");
        FieldsValidationData.tryBadInputsAndCheckErrors(period, periodUIFormRowError, FieldsValidationData.payPeriodZHKHMonthYear);
        period.typeAndEnter("10.2018");

        //todo GET THROUGH NOT VISIBLE EX
//        FieldsValidationData.tryBadInputsAndCheckErrors(paymentSum, paymentSumUIFormRowError, FieldsValidationData.paySum15kLimit);

        //чтобы страховая могла быть больше и выдать ошибку
//        paymentSum.typeAndEnter("77");
//        FieldsValidationData.tryBadInputsAndCheckErrors(voluntarilyHousingInsurance, voluntarilyHousingInsuranceUIFormRowError, FieldsValidationData.voluntarilyHousingInsuranceSum);

    }
}