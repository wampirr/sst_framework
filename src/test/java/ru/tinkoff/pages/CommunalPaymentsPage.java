package ru.tinkoff.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;


@DefaultUrl("https://www.tinkoff.ru/payments/categories/kommunalnie-platezhi/")
@At("https://www.tinkoff.ru/payments/categories/kommunalnie-platezhi/(?:\\?popup=REGIONS_CHANGE-[0-9]+.[0-9]+)?")
public class CommunalPaymentsPage extends PageObject {

    @FindBy(xpath = "//div[text() = 'ЖКХ']/span[text() = 'в ']/span/span")
    private WebElementFacade communalRegionElement;

    @FindBys({@FindBy(xpath = "//li[@data-qa-file='UIMenuItemProvider']//div[@data-qa-file='FadeText']")})
    private List<WebElementFacade> allCommunalProviders;

    public String get_communal_payment_region_text() {
        return communalRegionElement.getText();
    }

    public String change_communal_payments_region_to(String newRegion, String newRegionCasedResult) {
        communalRegionElement.waitUntilClickable();
        communalRegionElement.click();

        WebElementFacade wantedRegion = this.findBy("//a/span[@class = 'ui-link__text' and text()='" + newRegion + "']");
        wantedRegion.click();

        Assert.assertEquals(communalRegionElement.getText(), newRegionCasedResult);

        return this.communalRegionElement.getText();
    }

    public String get_nth_communal_payment_provider_name(int pos) {
        return allCommunalProviders.get(pos).getText();
    }

    public void navigate_to_communal_payment_provider(int pos) {
        allCommunalProviders.get(pos).waitUntilClickable();
        allCommunalProviders.get(pos).click();
    }

    @FindBy(xpath = "//input[@placeholder='Поиск по платежам']")
    WebElementFacade fastSearchPaymentsField;
    //will find only first
    @FindBy(xpath = "//div[@data-qa-file='Grid']/div/div/div/div")
    WebElementFacade firstFastFoundProvider;

    public String get_fast_search_first_local_provider(String providerName) {
        fastSearchPaymentsField.type(providerName);
        return firstFastFoundProvider.getText();
    }

    public void navigate_fast_search_first_local_provider() {
        firstFastFoundProvider.click();
    }

    public boolean text_in_page_found(String text) {
        return this.getDriver().getPageSource().contains(text);
    }
}