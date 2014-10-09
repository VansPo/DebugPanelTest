package com.flatstack.android.dagger.modules;

import android.content.Context;
import com.flatstack.android.MainActivity;
import com.flatstack.android.dagger.qualifiers.ActivityContext;
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
