package pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class QuotePage {

    @Step("Открыть страницу Цитаты")
    public QuotePage openQuotesPage() {
        Allure.step("Открыть страницу Цитаты");
        onView(withId(R.id.our_mission_image_button))
                .check(matches(isDisplayed()))
                .perform(click());
        return this;
    }

    @Step("Проверить, что список цитат отображается")
    public QuotePage checkQuotesListIsDisplayed() {
        Allure.step("Проверить, что список цитат отображается");
        onView(withId(R.id.our_mission_item_list_recycler_view))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Раскрыть цитату на позиции {position}")
    public QuotePage expandQuoteAtPosition(int position) {
        Allure.step("Раскрыть цитату с позицией " + position);
        onView(withId(R.id.our_mission_item_list_recycler_view))
                .perform(actionOnItemAtPosition(position, click()));
        return this;
    }

    @Step("Свернуть цитату на позиции {position}")
    public QuotePage collapseQuoteAtPosition(int position) {
        Allure.step("Свернуть цитату с позицией " + position);
        onView(withId(R.id.our_mission_item_list_recycler_view))
                .perform(actionOnItemAtPosition(position, click()));
        return this;
    }

    @Step("Проверить, что иконка раскрытия цитаты присутствует на позиции {position}")
    public QuotePage checkExpandIconIsDisplayedAtPosition(int position) {
        Allure.step("Проверяем, что иконка раскрытия цитаты присутствует на позиции " + position);
        onView(withId(R.id.our_mission_item_list_recycler_view))
                .check(matches(hasDescendant(withId(R.id.our_mission_item_open_card_image_button))));
        return this;
    }
}