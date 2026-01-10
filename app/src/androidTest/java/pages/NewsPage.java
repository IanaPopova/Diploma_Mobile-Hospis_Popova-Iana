package pages;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.anything;
import static utils.RecyclerViewMatchers.atPosition;

import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.Step;
import ru.iteco.fmhandroid.R;
import utils.MyViewAction;

public class NewsPage {

    @Step("Проверить, что страница News открылась")
    public NewsPage checkNewsPageIsOpened() {
        onView(withId(R.id.edit_news_material_button))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Открыть control panel News")
    public NewsPage openNewsControlPanel() {
        onView(withId(R.id.edit_news_material_button))
                .check(matches(isDisplayed()))
                .perform(click());
        return this;
    }

    @Step("Проверить, что открыт control panel News")
    public NewsPage checkNewsControlPanelIsOpened() {
        onView(withId(R.id.add_news_image_view))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Нажать кнопку добавления новости")
    public NewsPage clickAddNews() {
        onView(withId(R.id.add_news_image_view))
                .check(matches(isDisplayed()))
                .perform(click());
        return new NewsPage();
    }

    @Step("Проверить, что список новостей отображается")
    public NewsPage checkNewsListIsDisplayed() {
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Проверить, что экран создания новости открыт")
    public NewsPage checkPageOpened() {
        onView(withId(R.id.save_button))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Ввести заголовок новости")
    public NewsPage enterTitle(String title) {
        onView(withId(R.id.news_item_title_text_input_edit_text))
                .perform(replaceText(title), closeSoftKeyboard());
        return this;
    }

    @Step("Выбрать категорию новости по {category}")
    public NewsPage selectCategory(String category) {
        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click(), replaceText(category), closeSoftKeyboard());
        return this;
    }

    @Step("Ввести описание новости")
    public NewsPage enterDescription(String description) {
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(replaceText(description), closeSoftKeyboard());
        return this;
    }

    @Step("Выбрать дату публикации")
    public NewsPage pickDate() {
        onView(withId(R.id.news_item_publish_date_text_input_edit_text))
                .perform(click());
        onView(withId(android.R.id.button1))
                .perform(click());
        return this;
    }

    @Step("Выбрать время публикации")
    public NewsPage pickTime() {
        onView(withId(R.id.news_item_publish_time_text_input_edit_text))
                .perform(click());
        onView(withId(android.R.id.button1))
                .perform(click());
        return this;
    }

    @Step("Сохранить новость")
    public NewsPage clickSave() {
        onView(withId(R.id.save_button))
                .perform(click());
        return new NewsPage();
    }

    @Step("Проверить, что открыта пользовательская страница News")
    public NewsPage checkControlPanelIsOpen() {
        onView(withId(R.id.add_news_image_view))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Нажать кнопку редактирования новости по позиции в списке")
    public NewsPage clickEditNewsButton(int position) {
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position,
                        MyViewAction.clickChildViewWithId(R.id.edit_news_item_image_view)));
        return this;
    }

    @Step("Проверить, что заголовок новости на позиции {position} равен {expectedTitle}")
    public NewsPage checkNewsTitleIs(int position, String expectedTitle) {
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(position));

        onView(withId(R.id.news_list_recycler_view))
                .check(matches(atPosition(position, hasDescendant(withText(expectedTitle)))));

        return this;
    }

    @Step("Проверить, что дата публикации новости на позиции {position} равна {expectedDate}")
    public NewsPage checkNewsPublicationDateIs(int position, String expectedDate) {
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(position));
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(atPosition(position, hasDescendant(withId(R.id.news_item_publication_date_text_view)))))
                .check(matches(atPosition(position, hasDescendant(withText(expectedDate)))));
        return this;
    }

    @Step("Нажать кнопку фильтрации новостей")
    public NewsPage clickFilterButton() {
        onView(withId(R.id.filter_news_material_button))
                .perform(click());
        return this;
    }

    @Step("Выбрать категорию фильтра по позиции {position}")
    public NewsPage selectCategoryForFilter(int position) {
        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click());
        onData(anything())
                .inRoot(isPlatformPopup())
                .atPosition(position)
                .perform(click());

        return this;
    }

    @Step("Применить фильтр")
    public NewsPage applyFilter() {
        onView(withId(R.id.filter_button))
                .perform(click());
        return this;
    }

    @Step("Проверить, что в списке новостей есть заголовок, содержащий текст {expectedText}")
    public NewsPage checkNewsTitleContains(String expectedText) {
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(hasDescendant(withText(expectedText))));
        return this;
    }
}
