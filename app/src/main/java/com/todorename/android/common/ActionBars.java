package com.todorename.android.common;

import android.app.ActionBar;
import android.app.Activity;
import android.support.annotation.NonNull;
import rx.functions.Action1;

@SuppressWarnings("WeakerAccess")
public class ActionBars {
  public static void configure(@NonNull Activity activity, @NonNull Action1<ActionBar> action) {
    action.call(activity.getActionBar());
  }

  public static void homeAsUp(@NonNull ActionBar actionBar, boolean enabled) {
    actionBar.setHomeButtonEnabled(enabled);
    actionBar.setDisplayHomeAsUpEnabled(enabled);
  }
}
