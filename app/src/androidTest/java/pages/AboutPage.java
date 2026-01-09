package pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.Step;
import ru.iteco.fmhandroid.R;
import utils.WaitUtils;

public class AboutPage {

    @Step("Открыть главное меню")
    public AboutPage openMainMenu() {
        WaitUtils.waitForVisible(withId(R.id.main_menu_image_button), 5000);
        onView(withId(R.id.main_menu_image_button))
                .perform(click());
        return this;
    }

    @Step("Выбрать пункт About в главном меню")
    public AboutPage openAboutFromMenu() {
        onView(withText(R.string.about))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());
        return new AboutPage();
    }

    @Step("Проверить отображение версии приложения")
    public AboutPage checkVersionBlockIsDisplayed() {
        onView(withId(R.id.about_version_title_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_version_value_text_view)).check(matches(isDisplayed()));
        return this;
    }

    @Step("Проверить отображение Privacy Policy")
    public AboutPage checkPrivacyPolicyIsDisplayed() {
        onView(withId(R.id.about_privacy_policy_label_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_privacy_policy_value_text_view)).check(matches(isDisplayed()));
        return this;
    }

    @Step("Проверить отображение Terms of Use")
    public AboutPage checkTermsOfUseIsDisplayed() {
        onView(withId(R.id.about_terms_of_use_label_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_terms_of_use_value_text_view)).check(matches(isDisplayed()));
        return this;
    }

    @Step("Проверить отображение информации о компании")
    public AboutPage checkCompanyInfoIsDisplayed() {
        onView(withId(R.id.about_company_info_label_text_view)).check(matches(isDisplayed()));
        return this;
    }
}