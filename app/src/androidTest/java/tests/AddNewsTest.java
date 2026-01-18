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
import io.qameta.allure.Story;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import pages.AuthPage;
import pages.MainPage;
import pages.NewsPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AddNewsTest {

    static final String CATEGORY = "Объявление";
    static final String TITLE = "Test news title111";
    static final String DESCRIPTION = "Test news description";


    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private NewsPage newsPage = new NewsPage();
    private MainPage mainPage = new MainPage();
    private AuthPage authPage;


    @Before
    public void setUp() {
        authPage = new AuthPage();
        newsPage = new NewsPage();

        if (!authPage.isUserAuthorized()) {
            authPage.waitForAuthorizationScreen()
                    .enterLogin(VALID_LOGIN)
                    .enterPassword(VALID_PASSWORD)
                    .tapSignInButton()
                    .checkUserIsAuthorized();
        }
    }

    @Test
    @Story("Добавление новости")
    @Description("Добавление новости с заполнением полей")
    @DisplayName("Новость добавляется")
    public void shouldAddNewsSuccessfully() {

        mainPage
                .clickAllNews();

        newsPage
                .openNewsControlPanel()
                .checkNewsControlPanelIsOpened()
                .clickAddNews()
                .checkPageOpened()
                .selectCategory(CATEGORY)
                .enterTitle(TITLE)
                .enterDescription(DESCRIPTION)
                .pickDate()
                .pickTime()
                .clickSave()
                .checkControlPanelIsOpen()
                .checkNewsTitleIsDisplayedOnControlPanel(TITLE);
    }
}
