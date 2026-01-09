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

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
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
    public void shouldOpenNewsFromMainPage() {
        mainPage
                .clickAllNews();

        newsPage
                .checkNewsPageIsOpened();
    }

    @Test
    public void shouldOpenNewsFromMainMenu() {
        mainPage
                .openMainMenu()
                .clickMenuNews();

        newsPage
                .checkNewsPageIsOpened();
    }
}