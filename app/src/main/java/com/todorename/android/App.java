package com.todorename.android;

import android.app.Application;
import android.support.annotation.NonNull;
import com.todorename.android.common.Lists;
import com.todorename.android.common.TimberCrashReportingTree;
import com.todorename.android.dagger.Injector;
import com.todorename.android.dagger.modules.AppModule;
import dagger.ObjectGraph;
import java.util.List;
import lombok.Getter;
import timber.log.Timber;

@SuppressWarnings("WeakerAccess")
public class App extends Application implements Injector {
  @NonNull @Getter
  final ObjectGraph objectGraph = ObjectGraph.create(getDaggerModules().toArray());

  @Override public void onCreate() {
    super.onCreate();
    Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new TimberCrashReportingTree());
  }

  @NonNull protected List<Object> getDaggerModules() {
    return Lists.mutableOf(new AppModule(this));
  }
}
