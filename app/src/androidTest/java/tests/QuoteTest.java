package tests;

import static tests.AuthTest.VALID_LOGIN;
import static tests.AuthTest.VALID_PASSWORD;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;

import pages.AuthPage;
import pages.QuotePage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("QUOTE")
@Feature("Цитаты")
public class QuoteTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private AuthPage authPage;
    private QuotePage quotePage;

    @Before
    public void setUp() {
        authPage = new AuthPage();
        quotePage = new QuotePage();

        if (!authPage.isUserAuthorized()) {
            authPage.waitForAuthorizationScreen()
                    .enterLogin(VALID_LOGIN)
                    .enterPassword(VALID_PASSWORD)
                    .tapSignInButton()
                    .checkUserIsAuthorized();
        }
    }

    @Test
    @Story("Открытие страницы цитат")
    @Description("Проверка отображения списка цитат")
    @DisplayName("Открытие страницы цитат")
    public void shouldOpenQuotesPage() {
        quotePage
                .openQuotesPage()
                .checkQuotesListIsDisplayed();
    }

    @Test
    @Story("Раскрытие и сворачивание цитаты")
    @Description("Проверка открытия страницы цитат и работы раскрытия карточек")
    @DisplayName("Раскрытие и сворачивание цитаты")
    public void shouldExpandAndCollapseQuote() {
        quotePage
                .openQuotesPage()
                .checkQuotesListIsDisplayed()
                .expandQuoteAtPosition(0)
                .checkExpandIconIsDisplayedAtPosition(0)
                .collapseQuoteAtPosition(0)
                .checkExpandIconIsDisplayedAtPosition(0);
    }
}