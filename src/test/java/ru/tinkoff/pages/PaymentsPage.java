package ru.tinkoff.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;


@DefaultUrl("https://www.tinkoff.ru/payments/")
@At("https://www.tinkoff.ru/payments/")
public class PaymentsPage extends PageObject {

    @FindBy(xpath = "//div[@aria-label='ЖКХ' and @data-qa-file='PaymentsCategoryItem']")
    private WebElementFacade communalPaymentsLink;

    public void navigate_communal_payments() {
        communalPaymentsLink.waitUntilClickable();
        communalPaymentsLink.click();
    }

}