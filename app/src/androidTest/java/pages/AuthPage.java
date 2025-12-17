package pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.widget.EditText;

import org.hamcrest.Matchers;

import io.qameta.allure.Step;
import ru.iteco.fmhandroid.R;
import utils.WaitUtils;

public class AuthPage {

    @Step("Ожидание экрана авторизации")
    public AuthPage waitForAuthorizationScreen() {
        WaitUtils.waitForVisible(R.id.enter_button, 10000);
        return this;
    }

    @Step("Ввод логина: {login}")
    public AuthPage enterLogin(String login) {
        onView(Matchers.allOf(
                isDescendantOfA(withId(R.id.login_text_input_layout)),
                isAssignableFrom(EditText.class)
        )).perform(click(), replaceText(login), closeSoftKeyboard());

        WaitUtils.waitForVisible(R.id.enter_button, 2000);
        return this;
    }

    @Step("Ввод пароля: {password}")
    public AuthPage enterPassword(String password) {
        onView(Matchers.allOf(
                isDescendantOfA(withId(R.id.password_text_input_layout)),
                isAssignableFrom(EditText.class)
        )).perform(click(), replaceText(password), closeSoftKeyboard());

        WaitUtils.waitForVisible(R.id.enter_button, 2000);
        return this;
    }

    @Step("Нажатие кнопки Sign In")
    public AuthPage tapSignInButton() {
        onView(withId(R.id.enter_button)).perform(click());

        WaitUtils.waitForVisible(Matchers.allOf(
                withId(R.id.custom_app_bar_sub_title_text_view),
                isDescendantOfA(withId(R.id.container_custom_app_bar_include_on_fragment_main))
        ), 15000);

        return this;
    }

    @Step("Проверка успешной авторизации")
    public void checkUserIsAuthorized() {
        WaitUtils.waitForVisible(Matchers.allOf(
                withId(R.id.custom_app_bar_sub_title_text_view),
                isDescendantOfA(withId(R.id.container_custom_app_bar_include_on_fragment_main))
        ), 15000);
    }


    @Step("Проверка неуспешной авторизации")
    public void checkUserIsNotAuthorized() {
        WaitUtils.waitForVisible(R.id.enter_button, 15000);
    }

    @Step("Выйти из приложения, если пользователь авторизован")
    public void logoutIfNeeded() {
        try {
            onView(Matchers.allOf(
                    withId(R.id.authorization_image_button),
                    isDescendantOfA(withId(R.id.container_custom_app_bar_include_on_fragment_main))
            )).check((view, noViewFoundException) -> {
                if (noViewFoundException != null) throw noViewFoundException;
                if (!view.isShown()) throw new AssertionError("User not authorized");
            });
        } catch (Exception e) {
            return;
        }
        onView(Matchers.allOf(
                withId(R.id.authorization_image_button),
                isDescendantOfA(withId(R.id.container_custom_app_bar_include_on_fragment_main))
        )).perform(click());

        WaitUtils.waitForVisible(R.id.authorization_logout_menu_item, 5000);
        onView(withId(R.id.authorization_logout_menu_item)).perform(click());

        WaitUtils.waitForVisible(R.id.enter_button, 5000);
    }
}
