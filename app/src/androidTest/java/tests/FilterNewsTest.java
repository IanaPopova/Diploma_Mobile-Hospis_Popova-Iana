package tests;

import static tests.AuthTest.VALID_LOGIN;
import static tests.AuthTest.VALID_PASSWORD;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
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
public class FilterNewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private AuthPage authPage;
    private MainPage mainPage;
    private NewsPage newsPage;

    @Before
    public void setUp() {
        authPage = new AuthPage();
        mainPage = new MainPage();
        newsPage = new NewsPage();

        if (!authPage.isUserAuthorized()) {
            authPage.waitForAuthorizationScreen()
                    .enterLogin(VALID_LOGIN)
                    .enterPassword(VALID_PASSWORD)
                    .tapSignInButton()
                    .checkUserIsAuthorized();
        }
        mainPage
                .clickAllNews();
    }

    @Test
    public void shouldShowFilterNewsByCategoryIfHaveNews() {
        newsPage.openNewsControlPanel()
                .checkNewsControlPanelIsOpened()
                .clickFilterButton()
                .selectCategoryForFilter(1)
                .applyFilter()
                .checkNewsTitleContains("День рождения445564");
    }
}
