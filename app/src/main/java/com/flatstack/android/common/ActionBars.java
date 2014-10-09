package com.flatstack.android.common;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;
import rx.functions.Action1;

public class ActionBars {
  public static void configure(@NonNull Fragment fragment, @NonNull Action1<ActionBar> action) {
    configure(fragment.getActivity(), action);
  }

  public static void configure(@NonNull Activity activity, @NonNull Action1<ActionBar> action) {
    action.call(activity.getActionBar());
  }

  public static void homeAsUp(@NonNull ActionBar actionBar, boolean enabled) {
    actionBar.setHomeButtonEnabled(enabled);
    actionBar.setDisplayHomeAsUpEnabled(enabled);
  }
}
