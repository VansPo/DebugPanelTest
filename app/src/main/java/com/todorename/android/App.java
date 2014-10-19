package com.todorename.android;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import com.todorename.android.common.TimberCrashReportingTree;
import com.todorename.android.dagger.Injector;
import com.todorename.android.ui.ActivityHierarchyServer;
import dagger.ObjectGraph;
import javax.inject.Inject;
import timber.log.Timber;

@SuppressWarnings("WeakerAccess")
public class App extends Application implements Injector {
  private ObjectGraph objectGraph;

  @Inject ActivityHierarchyServer activityHierarchyServer;

  @Override public void onCreate() {
    super.onCreate();
    Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new TimberCrashReportingTree());

    objectGraph = ObjectGraph.create(Modules.list(this));
    objectGraph.inject(this);

    registerActivityLifecycleCallbacks(activityHierarchyServer);
  }

  @NonNull protected Object getDaggerModules() {
    return Modules.list(this);
  }

  public static App get(Context context) {
    return (App) context.getApplicationContext();
  }

  @NonNull @Override public ObjectGraph getObjectGraph() {
    return objectGraph;
  }
}
