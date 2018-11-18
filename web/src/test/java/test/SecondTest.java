import org.junit.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import pages.TinkoffJobPage;
import test.BaseRunner;

public class SecondTest extends BaseRunner {


    @Test
    public void test1() {
        TinkoffJobPage tinkoffJob = app.tinkoffJob;
        tinkoffJob.getPage("https://moscow-job.tinkoff.ru/");
        tinkoffJob.typeNameField("fail_fio");
        tinkoffJob.typeEmailField("fail_mail");
        tinkoffJob.checkErrorNameField();
        tinkoffJob.typePhoneField("+7 (111) 111-11-11");
        tinkoffJob.checkErrorEmailField();
        tinkoffJob.typeCityField("fail");
        tinkoffJob.checkErrorPhoneField();
        tinkoffJob.typeVacancyField();
    }
    @Test
    public void test2() {
        TinkoffJobPage tinkoffJob = app.tinkoffJob;
        tinkoffJob.getPage("https://moscow-job.tinkoff.ru/");
        tinkoffJob.checkErrorEmptyNameField();
        tinkoffJob.checkErrorEmptyEmailField();
        tinkoffJob.checkErrorEmptyPhoneField();
        tinkoffJob.checkErrorEmptyCityField();
        tinkoffJob.checkEmptyVacancyField();
    }
}
