package pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import io.qameta.allure.kotlin.Step;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import utils.WaitUtils;

public class MainPage {

    @Step("Ожидание открытия главной страницы")
    public MainPage waitForMainScreen() {
        Allure.step("Ожидание открытия главной страницы");
        WaitUtils.waitForVisible(withId(R.id.container_custom_app_bar_include_on_fragment_main), 10_000);
        return this;
    }

    @Step("Проверить кнопку Main menu")
    public MainPage checkMainMenuButtonIsDisplayed() {
        Allure.step("Проверить кнопку Main menu");
        onView(withId(R.id.main_menu_image_button))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Проверить кнопку Our Mission")
    public MainPage checkOurMissionButtonIsDisplayed() {
        Allure.step("Проверить кнопку Our Mission");
        onView(withId(R.id.our_mission_image_button))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Проверить кнопку Authorization")
    public MainPage checkAuthorizationButtonIsDisplayed() {
        Allure.step("Проверить кнопку Authorization");
        onView(withId(R.id.authorization_image_button))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Проверить логотип приложения")
    public MainPage checkTrademarkIsDisplayed() {
        Allure.step("Проверить логотип приложения");
        onView(withId(R.id.trademark_image_view))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Проверить блок News")
    public MainPage checkNewsBlockIsDisplayed() {
        Allure.step("Проверить блок News");
        onView(withId(R.id.container_list_news_include_on_fragment_main))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Проверить кнопку ALL NEWS")
    public MainPage checkAllNewsButtonIsDisplayed() {
        Allure.step("Проверить кнопку ALL NEWS");
        onView(withId(R.id.all_news_text_view))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Открыть страницу News через кнопку ALL NEWS на главной")
    public MainPage clickAllNews() {
        Allure.step("Открыть страницу News через кнопку ALL NEWS на главной");
        onView(withId(R.id.all_news_text_view))
                .check(matches(isDisplayed()))
                .perform(click());
        return this;
    }
    @Step("Открыть главное меню")
    public MainPage openMainMenu() {
        Allure.step("Открыть главное меню");
        onView(allOf(
                withId(R.id.main_menu_image_button),
                withContentDescription("Main menu"),
                isDisplayed()
        )).perform(click());
        return this;
    }

    @Step("Выбрать пункт News в главном меню")
    public MainPage clickMenuNews() {
        Allure.step("Выбрать пункт News в главном меню");
        onView(withText(R.string.news))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());
        return this;
    }
}
