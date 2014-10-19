package com.todorename.android.ui;

import android.app.Activity;
import android.view.ViewGroup;

/** An indirection which allows controlling the root container used for each activity. */
public interface AppContainer {
  /** The root {@link android.view.ViewGroup} into which the activity should place its contents. */
  ViewGroup get(Activity activity);

  /** An {@link AppContainer} which returns the normal activity content view. */
  AppContainer DEFAULT = new AppContainer() {
    @Override public ViewGroup get(Activity activity) {
      return (ViewGroup) activity.findViewById(android.R.id.content);
    }
  };
}
