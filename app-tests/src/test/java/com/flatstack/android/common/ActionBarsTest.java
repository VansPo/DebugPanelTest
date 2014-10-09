package com.flatstack.android.common;

import android.app.Activity;
import com.flatstack.android.RobolectricGradleTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class ActionBarsTest {
  private Activity activity;

  @Before public void setUp() {
    activity = Robolectric.buildActivity(Activity.class).create().get();
  }

  @Test public void testConfigure1() throws Exception {
    com.flatstack.android.common.ActionBars.configure(activity,
        actionBar -> actionBar.setSubtitle("fuck"));
    assertThat(activity.getActionBar().getSubtitle()).isEqualTo("fuck");
  }
}