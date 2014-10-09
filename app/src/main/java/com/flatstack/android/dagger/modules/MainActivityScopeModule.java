package com.flatstack.android.dagger.modules;

import android.content.Context;
import com.flatstack.android.MainActivity;
import com.flatstack.android.utils.Preferences;
import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;
import javax.inject.Singleton;

@Module(addsTo = ApplicationScopeModule.class,
    injects = {
        MainActivity.class
    },
    library = true)
public class MainActivityScopeModule {
  @Provides @Singleton Preferences providePreferences(Context context) {
    return Esperandro.getPreferences(Preferences.class, context);
  }
}
