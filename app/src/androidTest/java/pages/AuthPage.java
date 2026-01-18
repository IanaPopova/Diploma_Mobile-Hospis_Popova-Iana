package pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static org.hamcrest.Matchers.allOf;

import com.google.android.material.textfield.TextInputEditText;

import io.qameta.allure.kotlin.Step;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import utils.WaitUtils;

public class AuthPage {
    private final int titleLogOutId = android.R.id.title;

    @Step("Проверка, открыт ли экран авторизации")
    public boolean isAuthorizationScreenDisplayed() {
        Allure.step("Проверить, открыт ли экран авторизации");
        try {
            WaitUtils.waitForVisible(withId(R.id.enter_button), 5000);
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    @Step("Проверка, авторизован ли пользователь")
    public boolean isUserAuthorized() {
        Allure.step("Проверить, авторизован ли пользователь");
        try {
            WaitUtils.waitForVisible(withId(R.id.authorization_image_button), 5000);
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    @Step("Ожидание экрана авторизации")
    public AuthPage waitForAuthorizationScreen() {
        Allure.step("Ожидание экрана авторизации");
        WaitUtils.waitForVisible(withId(R.id.enter_button), 10000);
        return this;
    }

    @Step("Ввод логина: {login}")
    public AuthPage enterLogin(String login) {
        Allure.step("Ввод логина");
        onView(allOf(
                isAssignableFrom(TextInputEditText.class),
                isDescendantOfA(withId(R.id.login_text_input_layout))
        )).perform(typeText(login), closeSoftKeyboard());
        return this;
    }

    @Step("Ввод пароля")
    public AuthPage enterPassword(String password) {
        Allure.step("Ввод пароля");
        onView(allOf(
                isAssignableFrom(TextInputEditText.class),
                isDescendantOfA(withId(R.id.password_text_input_layout))
        )).perform(typeText(password), closeSoftKeyboard());
        return this;
    }

    @Step("Нажатие кнопки Sign In")
    public AuthPage tapSignInButton() {
        Allure.step("Нажать кнопку Sign In");
        onView(withId(R.id.enter_button)).perform(click());
        return this;
    }

    @Step("Проверка успешной авторизации")
    public void checkUserIsAuthorized() {
        Allure.step("Проверка успешной авторизации");
        WaitUtils.waitForVisible(withId(R.id.authorization_image_button), 10000);
    }

    @Step("Проверка, что пользователь не авторизован")
    public void checkUserIsNotAuthorized() {
        Allure.step("Проверить, что пользователь не авторизован");
        WaitUtils.waitForVisible(withId(R.id.enter_button), 10000);
    }

    @Step("Выход из приложения")
    public void logout() {
        Allure.step("Выход из приложения");
        onView(withId(R.id.authorization_image_button))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(titleLogOutId)).perform(click());

        waitForAuthorizationScreen();
    }
}