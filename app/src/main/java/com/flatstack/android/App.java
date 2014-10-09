package com.flatstack.android;

import android.app.Application;
import android.support.annotation.NonNull;
import com.flatstack.android.dagger.Injector;
import com.flatstack.android.dagger.modules.ApplicationScopeModule;
import com.flatstack.android.common.Lists;
import com.flatstack.android.common.TimberCrashReportingTree;
import dagger.ObjectGraph;
import java.util.List;
import lombok.Getter;
import timber.log.Timber;

public class App extends Application implements Injector {
  @NonNull @Getter
  final ObjectGraph objectGraph = ObjectGraph.create(getDaggerModules().toArray());

  @Override public void onCreate() {
    super.onCreate();
    Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new TimberCrashReportingTree());
  }

  @NonNull protected List<Object> getDaggerModules() {
    return Lists.mutableOf(new ApplicationScopeModule(this));
  }
}
