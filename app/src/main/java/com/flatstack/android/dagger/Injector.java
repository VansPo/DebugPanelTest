package com.flatstack.android.dagger;

import android.support.annotation.NonNull;
import dagger.ObjectGraph;

public interface Injector {
  @NonNull ObjectGraph getObjectGraph();
}
