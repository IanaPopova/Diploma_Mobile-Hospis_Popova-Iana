package utils;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.util.HumanReadables;

import org.hamcrest.Matcher;

public class WaitUtils {
    public static void waitForVisible(Matcher matcher, long timeoutMillis) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeoutMillis;

        while (System.currentTimeMillis() < endTime) {
            try {
                onView(matcher).check(isDisplayedAssertion());
                return;
            } catch (NoMatchingViewException | AssertionError ignored) {
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        throw new AssertionError("Элемент не появился на экране за " + timeoutMillis + " мс: " + matcher);
    }

    private static ViewAssertion isDisplayedAssertion() {
        return (view, noViewFoundException) -> {
            if (noViewFoundException != null) throw noViewFoundException;
            if (!view.isShown()) {
                throw new AssertionError("View не отображается: " + HumanReadables.describe(view));
            }
        };
    }
}