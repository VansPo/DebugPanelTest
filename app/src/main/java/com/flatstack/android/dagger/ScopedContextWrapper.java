package com.flatstack.android.dagger;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import dagger.ObjectGraph;
import lombok.Getter;

/**
 * Created by adel on 6/7/14
 */
public class ScopedContextWrapper extends ContextWrapper implements Injector {
  final @NonNull @Getter ObjectGraph objectGraph;
  @Nullable LayoutInflater inflater;

  public ScopedContextWrapper(@NonNull Context base, @NonNull ObjectGraph objectGraph) {
    super(base);
    this.objectGraph = objectGraph;
  }

  @NonNull @Override public Object getSystemService(@NonNull String name) {
    if (LAYOUT_INFLATER_SERVICE.equals(name)) {
      if (inflater == null) {
        inflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
      }
      return inflater;
    }
    return getBaseContext().getSystemService(name);
  }
}
