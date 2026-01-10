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
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private AuthPage authPage;
    private MainPage mainPage;

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
    @Story("Элементы экрана Main")
    @Description("Элементы экрана Main отображаются")
    @DisplayName("Экран Main отображается корректно")
    public void mainScreenElementsTest() {
        mainPage
                .checkMainMenuButtonIsDisplayed()
                .checkOurMissionButtonIsDisplayed()
                .checkAuthorizationButtonIsDisplayed()
                .checkTrademarkIsDisplayed()
                .checkNewsBlockIsDisplayed()
                .checkAllNewsButtonIsDisplayed();
    }
}