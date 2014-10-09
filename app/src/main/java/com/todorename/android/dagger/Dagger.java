package com.todorename.android.dagger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import dagger.ObjectGraph;

public class Dagger {
  @NonNull public static ObjectGraph getObjectGraph(@NonNull Context context) {
    if (context instanceof Injector) {
      return ((Injector) context).getObjectGraph();
    }
    throw new IllegalArgumentException(String.format("Your %s should implement Injector interface",
        context.getClass().getSimpleName()));
  }

  public static void inject(@NonNull Context context) {
    getObjectGraph(context).inject(context);
  }

  public static void inject(@NonNull View view) {
    getObjectGraph(view.getContext()).inject(view);
  }
}