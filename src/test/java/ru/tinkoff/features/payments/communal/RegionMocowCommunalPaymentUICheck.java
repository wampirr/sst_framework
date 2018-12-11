package ru.tinkoff.features.payments.communal;


import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import ru.tinkoff.steps.AnonCommunalPaymentClientSteps;


@RunWith(SerenityRunner.class)
public class RegionMocowCommunalPaymentUICheck {

    @Managed
    WebDriver driver;

    @Steps
    AnonCommunalPaymentClientSteps client;

    //@TestData
    //todo add testdata and understand how it works =D

    @Test
    public void CheckLocalMoscowCommunalPaymentIsFirstInList() {
        //todo change step names to russian?
        client.enters_main_page();
        client.clicks_on_payments_in_page_footer();

        client.clicks_on_communal_payments_link_in_payments_page();
        if (!client.check_communal_payment_region_is("Москве")) {
            client.change_communal_payments_region_to("г. Москва", "Москве");
        }
        Serenity.setSessionVariable("ЖКУ-Москва").to(
                client.get_name_of_communal_payment_provider_positioned(0));
        Assert.assertEquals("ЖКУ-Москва", Serenity.sessionVariableCalled("ЖКУ-Москва"));
        client.navigate_to_communal_payment_positioned(0);

        client.is_current_page_at("https://www.tinkoff.ru/zhku-moskva/");
        client.select_zhku_moscow_pay_tab();
        client.validate_zhku_moscow_payment_input_fields();
        client.clicks_on_payments_in_page_footer();

        client.fast_search_and_check_is_first_previously_saved_local_payment_provider(Serenity.sessionVariableCalled("ЖКУ-Москва"));
        client.navigate_fast_searched_first_communal_provider();

        client.is_current_page_at("https://www.tinkoff.ru/zhku-moskva/");
        client.clicks_on_payments_in_page_footer();

        client.clicks_on_communal_payments_link_in_payments_page();

        client.change_communal_payments_region_to("г. Санкт-Петербург", "Санкт-Петербурге");
        client.check_there_is_no_provider("ЖКУ-Москва");
    }
}
