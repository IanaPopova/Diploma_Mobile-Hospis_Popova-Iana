package tests;

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
import pages.AuthPage;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
@Epic("AUTH")
@Feature("Авторизация")
public class AuthTest {

    private static final String VALID_LOGIN = "login2";
    private static final String VALID_PASSWORD = "password2";
    private static final String INVALID_LOGIN = "invalid_login";
    private static final String INVALID_PASSWORD = "invalid_password";

    private AuthPage authPage;

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        authPage = new AuthPage();

        authPage.logoutIfNeeded();

        authPage.waitForAuthorizationScreen();
    }

    @Test
    @Story("Успешная авторизация")
    @Description("Проверка входа с валидными учетными данными")
    @DisplayName("Успешный вход с валидным логином и паролем")
    public void shouldLoginWithValidCredentials() {
        authPage
                .enterLogin(VALID_LOGIN)
                .enterPassword(VALID_PASSWORD)
                .tapSignInButton()
                .checkUserIsAuthorized();
    }

    @Test
    @Story("Авторизация с неверными данными")
    @Description("Проверка, что вход невозможен с невалидными учетными данными")
    @DisplayName("Ошибка при вводе неверных учетных данных")
    public void shouldNotLoginWithInvalidCredentials() {
        authPage
                .enterLogin(INVALID_LOGIN)
                .enterPassword(INVALID_PASSWORD)
                .tapSignInButton()
                .checkUserIsNotAuthorized();
    }

    @Test
    @Story("Авторизация с пустыми полями")
    @Description("Проверка, что вход невозможен без логина и пароля")
    @DisplayName("Ошибка при попытке входа с пустыми полями")
    public void shouldNotLoginWithEmptyFields() {
        authPage
                .tapSignInButton()
                .checkUserIsNotAuthorized();
    }
}