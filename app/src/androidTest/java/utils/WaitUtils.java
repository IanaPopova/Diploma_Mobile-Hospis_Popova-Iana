package utils;

import static androidx.test.espresso.Espresso.onView;
import androidx.test.espresso.NoMatchingViewException;
import android.view.View;
import org.hamcrest.Matcher;

public class WaitUtils {
    public static void waitForVisible(Matcher<View> matcher, long timeoutMillis) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeoutMillis;

        while (System.currentTimeMillis() < endTime) {
            try {
                onView(matcher).check((view, noViewFoundException) -> {
                    if (noViewFoundException != null) throw noViewFoundException;
                    if (!view.isShown()) throw new AssertionError("View is not visible yet");
                });
                return;
            } catch (Exception ignored) {
            }
        }
        throw new AssertionError("Элемент не появился на экране за " + timeoutMillis + " мс");
    }
    public static void waitForVisible(int viewId, long timeoutMillis) {
        waitForVisible(androidx.test.espresso.matcher.ViewMatchers.withId(viewId), timeoutMillis);
    }
}