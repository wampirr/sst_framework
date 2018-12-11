package ru.tinkoff.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;


@DefaultUrl("https://www.tinkoff.ru/")
@At("https://www.tinkoff.ru")
public class MainPage extends PageObject {

    @FindBy(xpath = "//a[text() = 'Платежи' and @href = '/payments/']")
    private WebElementFacade paymentLink;

    public void navigate_payments() {
        paymentLink.waitUntilClickable();
        paymentLink.click();
    }

}