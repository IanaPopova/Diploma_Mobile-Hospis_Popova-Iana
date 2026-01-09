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
public class EditNewsTest {


    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private static final String UPDATED_TITLE = "Updated Test Title!";
    private static final String UPDATED_DATE = "31.12.2026";
    private NewsPage newsPage;
    private AuthPage authPage;
    private MainPage mainPage;

    @Before
    public void setUp() {
        authPage = new AuthPage();
        newsPage = new NewsPage();
        mainPage = new MainPage();

        if (!authPage.isUserAuthorized()) {
            authPage.waitForAuthorizationScreen()
                    .enterLogin(VALID_LOGIN)
                    .enterPassword(VALID_PASSWORD)
                    .tapSignInButton()
                    .checkUserIsAuthorized();
        }

        mainPage.
                clickAllNews();
    }

    @Test
    public void shouldEditNewsTitleSuccessfully() {
        newsPage.openNewsControlPanel()
                .checkNewsControlPanelIsOpened()
                .clickEditNewsButton(0)
                .enterTitle(UPDATED_TITLE)
                .clickSave()
                .checkNewsControlPanelIsOpened()
                .checkNewsTitleIs(0, UPDATED_TITLE);
    }

    @Test
    public void shouldEditDateSuccessfully() {
        newsPage.openNewsControlPanel()
                .checkNewsControlPanelIsOpened()
                .clickEditNewsButton(0)
                .pickDate()
                .clickSave()
                .checkNewsControlPanelIsOpened()
                .checkNewsPublicationDateIs(0, UPDATED_DATE);
    }
}
