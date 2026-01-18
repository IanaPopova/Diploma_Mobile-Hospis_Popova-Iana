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
public class EditNewsTest {


    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private static final String UPDATED_TITLE = "Hi Test";
    private static final String TITLE = "Test news title111";
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
    @Story("Редактирование заголовка")
    @Description("Редактирование заголовка новости")
    @DisplayName("Заголовок редактируется")
    public void shouldEditNewsTitleSuccessfully() {
        newsPage.openNewsControlPanel()
                .checkNewsControlPanelIsOpened()
                .clickEditNewsButton(TITLE)
                .enterTitle(UPDATED_TITLE)
                .clickSave()
                .checkNewsControlPanelIsOpened()
                .checkNewsTitleIs(UPDATED_TITLE);
    }

    @Test
    @Story("Редактирование даты публикации")
    @Description("Редактируется дата публикации написаной новости")
    @DisplayName("Дата публикации редактируется")
    public void shouldEditDateSuccessfully() {

        String updatedDate =
                newsPage.openNewsControlPanel()
                        .checkNewsControlPanelIsOpened()
                        .clickEditNewsButton(TITLE)
                        .pickDateAndGetValue();

        newsPage
                .clickSave()
                .checkNewsControlPanelIsOpened()
                .checkNewsPublicationDateIs(TITLE, updatedDate);
    }
}
