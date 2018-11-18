import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GoogleMainPage;
import pages.GoogleResultPage;
import pages.TinkoffJobPage;
import test.BaseRunner;

import java.time.Duration;
import java.util.List;

public class Case1 extends BaseRunner {
    @Test
    public void  exe(){
        GoogleMainPage googleMainPage = app.google;
        googleMainPage.open();
        googleMainPage.openSearchResultsPageByRequest("тинькофф мобайл тарифы");
        GoogleResultPage googleResultPage = app.googleResults;
        googleResultPage.clickSearchResultsByLinkContains("https://www.tinkoff.ru/mobile-operator/tariffs/");
        TinkoffJobPage job = app.tinkoffJob;
        job.switchToMainTab();
        job.closeCurrentTab();
        job.switchToMainTab();
        job.checkUrl("https://www.tinkoff.ru/mobile-operator/tariffs/");
    }

}
