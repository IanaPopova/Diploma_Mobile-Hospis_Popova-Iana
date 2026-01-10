package tests;

import static tests.AuthTest.VALID_LOGIN;
import static tests.AuthTest.VALID_PASSWORD;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import pages.AuthPage;
import pages.MainPage;
import pages.NewsPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsOpenPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private MainPage mainPage = new MainPage();
    private final NewsPage newsPage = new NewsPage();
    private AuthPage authPage;

    @Before
    public void setUp() {
        authPage = new AuthPage();
        mainPage = new MainPage();

        if (!authPage.isUserAuthorized()) {
            authPage.waitForAuthorizationScreen()
                    .enterLogin(VALID_LOGIN)
                    .enterPassword(VALID_PASSWORD)
                    .tapSignInButton()
                    .checkUserIsAuthorized();
        }
    }

    @Test
    @Story("Страница новостей открывается с главной страницы")
    @Description("Страницу новостей можно открыть с главной страницы")
    @DisplayName("Переход на страницу новостей с главной страницы")
    public void shouldOpenNewsFromMainPage() {
        mainPage
                .clickAllNews();

        newsPage
                .checkNewsPageIsOpened();
    }

    @Test
    @Story("Страница новостей открывается с главного меню")
    @Description("Страницу новостей можно открыть с главного меню")
    @DisplayName("Переход на страницу новостей с главного меню")
    public void shouldOpenNewsFromMainMenu() {
        mainPage
                .openMainMenu()
                .clickMenuNews();

        newsPage
                .checkNewsPageIsOpened();
    }
}