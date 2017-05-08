package com.mogsev.mapsdownloader;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mogsev.mapsdownloader.activity.RegionActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegionActivityTest {

    @Rule
    public ActivityTestRule<RegionActivity> mActivityRule = new ActivityTestRule<>(RegionActivity.class);

    @Test
    public void onViewDisplayed_Success() {
        // views can be displayed
        onView(withId(R.id.toolbar)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.recycler_view_default)).check(matches(isDisplayed()));
    }

}
