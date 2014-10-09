package com.todorename.android.dagger.modules;

import android.content.Context;
import com.todorename.android.MainActivity;
import com.todorename.android.dagger.qualifiers.ActivityContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(addsTo = AppModule.class,
    injects = {
        MainActivity.class
    },
    library = true)
public class MainActivityScopeModule {
  private final MainActivity mainActivity;

  public MainActivityScopeModule(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }

  @Provides @Singleton @ActivityContext Context provideContext() {
    return mainActivity;
  }
}
