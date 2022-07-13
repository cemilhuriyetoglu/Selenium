package page;

import base.BrowserUtils;
import org.openqa.selenium.By;

import java.text.DecimalFormat;

public class CurrencyExchangePage {

    private final By currencyExchangePage = By.xpath("//*[text()='Online Currency Exchange']");
    private final By sellTextBoxNotEmpty = By.xpath("//input[contains(@class,'ng-not-empty') and @data-ng-model = 'currencyExchangeVM.filter.from_amount']");
    private final By sellTextBoxEmpty = By.xpath("//input[contains(@class,'ng-empty') and @data-ng-model = 'currencyExchangeVM.filter.from_amount']");
    private final By buyTextBoxNotEmpty = By.xpath("//input[contains(@class,'ng-not-empty') and @data-ng-model = 'currencyExchangeVM.filter.to_amount']");
    private final By buyTextBoxEmpty = By.xpath("//input[contains(@class,'ng-empty') and @data-ng-model = 'currencyExchangeVM.filter.to_amount']");
    private final By defaultCountry = By.xpath("//span[@class='flag-icon-small flag-icon-lt']");
    private final By dropupBtn = By.xpath("//span[@class='dropup']");
    private final By countriesDropdown = By.id("countries-dropdown");


    public void VerifyCurrencyExchangePageIsOpen() {

        BrowserUtils.checkWebElementCount(currencyExchangePage);
        BrowserUtils.verifyElementDisplayed(currencyExchangePage);

    }

    public void SellBoxOrBuyBoxShouldBeEmpty(String value) {


        BrowserUtils.verifyElementDisplayed(sellTextBoxNotEmpty);
        BrowserUtils.verifyElementDisplayed(buyTextBoxEmpty);
        BrowserUtils.waitAndSendKeys(buyTextBoxEmpty, value);
        BrowserUtils.wait(2000);
        BrowserUtils.verifyElementDisplayed(buyTextBoxNotEmpty);
        BrowserUtils.verifyElementDisplayed(sellTextBoxEmpty);

        BrowserUtils.waitAndSendKeys(sellTextBoxEmpty, value);
        BrowserUtils.verifyElementDisplayed(sellTextBoxNotEmpty);
        BrowserUtils.verifyElementDisplayed(buyTextBoxEmpty);

    }

    public void UpdateCountry(String language, String countyCode) {

        BrowserUtils.verifyElementDisplayed(defaultCountry);
        BrowserUtils.waitAndClick(dropupBtn);
        BrowserUtils.waitAndClick(countriesDropdown);
        BrowserUtils.waitAndClick(By.xpath("//a[contains(@href,'/v2/" + language + "-" + countyCode + "/fees/currency-conversion-calculator')]"));
        BrowserUtils.verifyElementDisplayed(By.xpath("//div[@id='currency-exchange-app' and @data-country='" + countyCode + "']"));

    }

    public void CheckLossAmount() {

        for (int i = 1; i <= 32; i++) {
            String payseraAmount = BrowserUtils.getText(By.xpath("(//*[@data-title='Paysera rate'])[" + i + "]"));
            System.out.println(payseraAmount);

            String swedbankAmount = BrowserUtils.getText(By.xpath("(//*[@data-title='Paysera rate'])[" + i + "]/following-sibling::td[1]//span[@class='ng-binding']"));
            System.out.println(swedbankAmount);

            float lossAmount = Float.parseFloat(swedbankAmount) - Float.parseFloat(payseraAmount);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            float twoDigitsLossAmount = Float.valueOf(decimalFormat.format(lossAmount));
            System.out.println(twoDigitsLossAmount);

            String getLossAmount = BrowserUtils.getText(By.xpath("(//*[@data-title='Paysera rate'])[" + i + "]/following-sibling::td[1]//span[@class='ng-binding']/following-sibling::span/child::node()[3]"));
            float getLossAmount2f = Float.parseFloat(getLossAmount);
            System.out.println(getLossAmount2f);
        }
    }

}
