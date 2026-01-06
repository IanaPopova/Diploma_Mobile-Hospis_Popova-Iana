package pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import utils.WaitUtils;

public class AboutPage {

    public AboutPage openMainMenu() {
        Allure.step("Открыть главное меню");
        WaitUtils.waitForVisible(withId(R.id.main_menu_image_button), 5000);
        onView(withId(R.id.main_menu_image_button))
                .perform(click());
        return this;
    }

    public AboutPage openAboutFromMenu() {
        Allure.step("Выбрать пункт About через текст в меню");
        WaitUtils.waitForVisible(withText("About"), 5000);
        onView(withText("About"))
                .inRoot(isPlatformPopup())
                .perform(click());
        return this;
    }

    public AboutPage checkVersionBlockIsDisplayed() {
        Allure.step("Проверить отображение версии приложения");
        onView(withId(R.id.about_version_title_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_version_value_text_view)).check(matches(isDisplayed()));
        return this;
    }

    public AboutPage checkPrivacyPolicyIsDisplayed() {
        Allure.step("Проверить отображение Privacy Policy");
        onView(withId(R.id.about_privacy_policy_label_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_privacy_policy_value_text_view)).check(matches(isDisplayed()));
        return this;
    }

    public AboutPage checkTermsOfUseIsDisplayed() {
        Allure.step("Проверить отображение Terms of Use");
        onView(withId(R.id.about_terms_of_use_label_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_terms_of_use_value_text_view)).check(matches(isDisplayed()));
        return this;
    }

    public AboutPage checkCompanyInfoIsDisplayed() {
        Allure.step("Проверить отображение информации о компании");
        onView(withId(R.id.about_company_info_label_text_view)).check(matches(isDisplayed()));
        return this;
    }
}