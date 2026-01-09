package utils;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class RecyclerViewMatchers {
    public static Matcher<View> atPosition(final int position, final Matcher<View> itemMatcher) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof RecyclerView)) return false;
                RecyclerView recyclerView = (RecyclerView) view;
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) return false;
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }
}

