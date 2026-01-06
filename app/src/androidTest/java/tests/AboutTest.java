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
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;

import pages.AboutPage;
import pages.AuthPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
@Epic("ABOUT")
@Feature("Экран About")
public class AboutTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);


    private AuthPage authPage;
    private AboutPage aboutPage;

    @Before
    public void setUp() {
        authPage = new AuthPage();
        aboutPage = new AboutPage();

        if (!authPage.isUserAuthorized()) {
            authPage.waitForAuthorizationScreen()
                    .enterLogin(VALID_LOGIN)
                    .enterPassword(VALID_PASSWORD)
                    .tapSignInButton()
                    .checkUserIsAuthorized();
        }
    }

    @Test
    @Story("Открытие экрана About")
    @Description("Проверка отображения экрана About и его основных элементов")
    @DisplayName("Экран About отображается корректно")
    public void shouldOpenAboutScreen() {
        aboutPage
                .openMainMenu()
                .openAboutFromMenu()
                .checkVersionBlockIsDisplayed()
                .checkPrivacyPolicyIsDisplayed()
                .checkTermsOfUseIsDisplayed()
                .checkCompanyInfoIsDisplayed();
    }
}