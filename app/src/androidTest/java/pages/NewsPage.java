package pages;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Step;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import utils.MyViewAction;

public class NewsPage {

    @Step("Проверить, что страница News открылась")
    public NewsPage checkNewsPageIsOpened() {
        Allure.step("Проверить, что страница News открылась");
        onView(withId(R.id.edit_news_material_button))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Открыть control panel News")
    public NewsPage openNewsControlPanel() {
        Allure.step("Открыть control panel News");
        onView(withId(R.id.edit_news_material_button))
                .check(matches(isDisplayed()))
                .perform(click());
        return this;
    }

    @Step("Проверить, что открыт control panel News")
    public NewsPage checkNewsControlPanelIsOpened() {
        Allure.step("Проверить, что открыт control panel News");
        onView(withId(R.id.add_news_image_view))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Нажать кнопку добавления новости")
    public NewsPage clickAddNews() {
        Allure.step("Нажать кнопку добавления новости");
        onView(withId(R.id.add_news_image_view))
                .check(matches(isDisplayed()))
                .perform(click());
        return new NewsPage();
    }

    @Step("Проверить, что список новостей отображается")
    public NewsPage checkNewsListIsDisplayed() {
        Allure.step("Проверить, что список новостей отображается");
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Проверить, что экран создания новости открыт")
    public NewsPage checkPageOpened() {
        Allure.step("Проверить, что экран создания новости открыт");
        onView(withId(R.id.save_button))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Ввести заголовок новости")
    public NewsPage enterTitle(String title) {
        Allure.step("Ввести заголовок новости");
        onView(withId(R.id.news_item_title_text_input_edit_text))
                .perform(replaceText(title), closeSoftKeyboard());
        return this;
    }

    @Step("Выбрать категорию новости по {category}")
    public NewsPage selectCategory(String category) {
        Allure.step("Выбрать категорию новости");
        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click(), replaceText(category), closeSoftKeyboard());
        return this;
    }

    @Step("Ввести описание новости")
    public NewsPage enterDescription(String description) {
        Allure.step("Ввести описание новости");
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(replaceText(description), closeSoftKeyboard());
        return this;
    }

    @Step("Выбрать дату публикации")
    public NewsPage pickDate() {
        Allure.step("Выбрать дату публикации");
        onView(withId(R.id.news_item_publish_date_text_input_edit_text))
                .perform(click());
        onView(withId(android.R.id.button1))
                .perform(click());
        return this;
    }

    @Step("Выбрать время публикации")
    public NewsPage pickTime() {
        Allure.step("Выбрать время публикации");
        onView(withId(R.id.news_item_publish_time_text_input_edit_text))
                .perform(click());
        onView(withId(android.R.id.button1))
                .perform(click());
        return this;
    }

    @Step("Выбрать дату публикации и сохранить выбранную дату")
    public String pickDateAndGetValue() {
        Allure.step("Выбрать дату публикации и сохранить выбранную дату");
        onView(withId(R.id.news_item_publish_date_text_input_edit_text))
                .perform(click());

        onView(withId(android.R.id.button1))
                .perform(click());

        final String[] selectedDate = new String[1];

        onView(withId(R.id.news_item_publish_date_text_input_edit_text))
                .perform(new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return isAssignableFrom(TextView.class);
                    }

                    @Override
                    public String getDescription() {
                        return "Получить выбранную дату";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        selectedDate[0] = ((TextView) view).getText().toString();
                    }
                });

        return selectedDate[0];
    }


    @Step("Сохранить новость")
    public NewsPage clickSave() {
        Allure.step("Сохранить новость");
        onView(withId(R.id.save_button))
                .perform(click());
        return new NewsPage();
    }

    @Step("Проверить, что открыта пользовательская страница News")
    public NewsPage checkControlPanelIsOpen() {
        Allure.step("Проверить, что открыта пользовательская страница News");
        onView(withId(R.id.add_news_image_view))
                .check(matches(isDisplayed()));
        return this;
    }

    @Step("Нажать кнопку редактирования новости с заголовком {title}")
    public NewsPage clickEditNewsButton(String title) {
        Allure.step("Нажать кнопку редактирования новости с заголовком");
        onView(withId(R.id.news_list_recycler_view))
                .perform(
                        RecyclerViewActions.scrollTo(
                                hasDescendant(withText(title))
                        )
                );

        onView(withId(R.id.news_list_recycler_view))
                .perform(
                        RecyclerViewActions.actionOnItem(
                                hasDescendant(withText(title)),
                                MyViewAction.clickChildViewWithId(
                                        R.id.edit_news_item_image_view
                                )
                        )
                );

        return this;
    }

    @Step("Проверить, что в списке новостей отображается заголовок {expectedTitle}")
    public NewsPage checkNewsTitleIs(String expectedTitle) {
        Allure.step("Проверить, что в списке новостей отображается заголовок");
        onView(withId(R.id.news_list_recycler_view))
                .perform(
                        RecyclerViewActions.scrollTo(
                                hasDescendant(withText(expectedTitle))
                        )
                );

        onView(withId(R.id.news_list_recycler_view))
                .check(matches(hasDescendant(withText(expectedTitle))));

        return this;
    }


    @Step("Проверить, что дата публикации новости '{title}' равна {expectedDate}")
    public NewsPage checkNewsPublicationDateIs(String title, String expectedDate) {
        Allure.step("Проверить, что дата публикации новости совпадает с обновленной");
        onView(withId(R.id.news_list_recycler_view))
                .perform(
                        RecyclerViewActions.scrollTo(
                                hasDescendant(withText(title))
                        )
                );

        onView(withId(R.id.news_list_recycler_view))
                .check(
                        matches(
                                hasDescendant(
                                        allOf(
                                                withId(R.id.news_item_publication_date_text_view),
                                                withText(expectedDate)
                                        )
                                )
                        )
                );
        return this;
    }

    @Step("Нажать кнопку фильтрации новостей")
    public NewsPage clickFilterButton() {
        Allure.step("Нажать кнопку фильтрации новостей");
        onView(withId(R.id.filter_news_material_button))
                .perform(click());
        return this;
    }

    @Step("Выбрать категорию фильтра по позиции {position}")
    public NewsPage selectCategoryForFilter(int position) {
        Allure.step("Выбрать категорию фильтра по позиции");
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
        Allure.step("Применить фильтр");
        onView(withId(R.id.filter_button))
                .perform(click());
        return this;
    }

    @Step("Проверить, что в списке новостей есть заголовок, содержащий текст {expectedText}")
    public NewsPage checkNewsTitleContains(String expectedText) {
        Allure.step("Проверить, что в списке новостей есть заголовок, содержащий текст");
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(hasDescendant(withText(expectedText))));
        return this;
    }

    @Step("Проверить, что на control panel отображается новость с заголовком {title}")
    public NewsPage checkNewsTitleIsDisplayedOnControlPanel(String title) {
        Allure.step("Проверить, что на control panel отображается новость с заголовком");
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(isDisplayed()))
                .check(matches(hasDescendant(withText(title))));
        return this;
    }
}
