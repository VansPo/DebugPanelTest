package com.todorename.android;

import com.todorename.android.dagger.Dagger;
import com.todorename.android.ui.MainActivity;
import dagger.ObjectGraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.tester.android.view.TestMenuItem;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by adelnizamutdinov on 03/03/2014
 */
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {
  private ActivityController<MainActivity> activityController;

  @Before public void setUp() throws Exception {
    activityController = Robolectric.buildActivity(MainActivity.class);
  }

  @Test public void testOnCreate() throws Exception {
    MainActivity mainActivity = activityController.create().get();
    assertNotNull(mainActivity.getObjectGraph());
  }

  @Test public void testOnLaunch() throws Exception {
    MainActivity mainActivity = activityController.get();
    ObjectGraph objectGraph = Dagger.getObjectGraph(mainActivity);
    mainActivity.onLaunch();
    assertNotEquals(Dagger.getObjectGraph(mainActivity), objectGraph);
  }

  @Test public void testOnOptionsItemSelected() throws Exception {
    MainActivity mainActivity = activityController.create().get();
    assertTrue(mainActivity.onOptionsItemSelected(new TestMenuItem(android.R.id.home)));
  }
}
