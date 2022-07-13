package testCases;

import base.ConfigurationReader;
import base.TestBase;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.CurrencyExchangePage;

@Listeners({base.Listeners.class})
public class TestCurrencyExchange extends TestBase {

    @Test
    public void T001_SellBoxOrBuyBoxShouldBeEmpty_Number() {

        CurrencyExchangePage CurrencyExchangePage = new CurrencyExchangePage();
        CurrencyExchangePage.VerifyCurrencyExchangePageIsOpen();
        CurrencyExchangePage.SellBoxOrBuyBoxShouldBeEmpty("1");

    }

    @Test
    public void T002_SellBoxOrBuyBoxShouldBeEmpty_String() {

        CurrencyExchangePage CurrencyExchangePage = new CurrencyExchangePage();
        CurrencyExchangePage.VerifyCurrencyExchangePageIsOpen();
        CurrencyExchangePage.SellBoxOrBuyBoxShouldBeEmpty("aaa");

    }

    @Test
    public void T003_SellBoxOrBuyBoxShouldBeEmpty_SpecialCharacter() {

        CurrencyExchangePage CurrencyExchangePage = new CurrencyExchangePage();
        CurrencyExchangePage.VerifyCurrencyExchangePageIsOpen();
        CurrencyExchangePage.SellBoxOrBuyBoxShouldBeEmpty("?");

    }

    @Test
    public void T004_ChangeCountry() {

        CurrencyExchangePage CurrencyExchangePage = new CurrencyExchangePage();
        CurrencyExchangePage.UpdateCountry(ConfigurationReader.get("language"), ConfigurationReader.get("countryCode"));

    }

    @Test
    public void T005 ( ) {
        CurrencyExchangePage CurrencyExchangePage = new CurrencyExchangePage();
        CurrencyExchangePage.CheckLossAmount();
    }
}
