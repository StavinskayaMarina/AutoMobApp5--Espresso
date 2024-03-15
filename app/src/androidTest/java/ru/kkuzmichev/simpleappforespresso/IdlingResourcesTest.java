package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

    @LargeTest
    @RunWith(AndroidJUnit4.class)
    public class IdlingResourcesTest {

        @Rule
        public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
                new ActivityScenarioRule<>(MainActivity.class);

        @Before
        public void registerIdlingResources() {
            IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
        }

        @After
        public void unregisterIdlingResources() {
            IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
        }

        @Test
        public void idlingResourcesTest() {
            ViewInteraction appButton = onView(withContentDescription("Open navigation drawer"));
            appButton.check(matches(isDisplayed()));
            appButton.perform(click());
            ViewInteraction textGallery = onView(withId(R.id.nav_gallery));
            textGallery.check(matches(isDisplayed()));
            textGallery.perform(click());
            ViewInteraction elementNumber7 = onView(allOf(withId(R.id.item_number), withText("7")));
            elementNumber7.check(matches(isDisplayed()));
            elementNumber7.check(matches(withText("7")));
        }


    }
