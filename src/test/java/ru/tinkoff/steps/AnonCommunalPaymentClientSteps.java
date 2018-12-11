package ru.tinkoff.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.tinkoff.pages.CommunalPaymentsPage;
import ru.tinkoff.pages.MainPage;
import ru.tinkoff.pages.PaymentsPage;
import ru.tinkoff.pages.ZhkuMoscowPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnonCommunalPaymentClientSteps extends ScenarioSteps {

    private MainPage mainPage;
    private PaymentsPage paymentsPage;
    private CommunalPaymentsPage communalPaymentsPage;
    private ZhkuMoscowPage zhkuMoscowPage;

    @Step
    public void enters_main_page() {
        mainPage.open();
    }

    @Step
    public void clicks_on_payments_in_page_footer() {
        mainPage.navigate_payments();
    }

    @Step
    public void clicks_on_communal_payments_link_in_payments_page() {
        paymentsPage.navigate_communal_payments();
    }

    @Step
    public boolean check_communal_payment_region_is(String region) {
        return region.equals(communalPaymentsPage.get_communal_payment_region_text());
    }

    @Step
    public String change_communal_payments_region_to(String wantedRegion, String wantedRegionCasedResult) {
        return communalPaymentsPage.change_communal_payments_region_to(wantedRegion, wantedRegionCasedResult);
    }

    @Step
    public String get_name_of_communal_payment_provider_positioned(int pos) {
        return communalPaymentsPage.get_nth_communal_payment_provider_name(pos);
    }

    @Step
    public void navigate_to_communal_payment_positioned(int pos) {
        communalPaymentsPage.navigate_to_communal_payment_provider(pos);
    }

    @Step
    public void select_zhku_moscow_pay_tab() {
        zhkuMoscowPage.select_pay_tab();
    }

    @Step
    public void is_current_page_at(String url) {
        new WebDriverWait(pages().getDriver(), 10).until(ExpectedConditions.urlToBe(url));
    }

    @Step
    public void validate_zhku_moscow_payment_input_fields() {
        zhkuMoscowPage.validate_payment_fields();
    }

    @Step
    public void fast_search_and_check_is_first_previously_saved_local_payment_provider(String providerName) {
        assertEquals(providerName, communalPaymentsPage.get_fast_search_first_local_provider(providerName));
    }

    @Step
    public void navigate_fast_searched_first_communal_provider() {
        communalPaymentsPage.navigate_fast_search_first_local_provider();
    }

    @Step
    public void check_there_is_no_provider(String providerName) {
        assertTrue(communalPaymentsPage.text_in_page_found(providerName));
    }

}
