package com.todorename.android.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.todorename.android.App;
import com.todorename.android.common.Preferences;
import com.todorename.android.dagger.qualifiers.ApplicationContext;
import com.todorename.android.dagger.qualifiers.CacheDir;
import com.todorename.android.ui.UiModule;
import dagger.Module;
import dagger.Provides;
import de.devland.esperandro.Esperandro;
import java.io.File;
import java.io.IOException;
import javax.inject.Singleton;

@Module(
    includes = {
        UiModule.class
    },
    injects = {
        App.class
    },
    complete = false,
    library = true
)
public class AppModule {
  private final @NonNull App application;

  public AppModule(@NonNull App application) {
    this.application = application;
  }

  @Provides @ApplicationContext Context provideContext() {
    return application;
  }

  @Provides @Singleton Application provideApplication() {
    return application;
  }

  @Provides @CacheDir File provideCacheDir(@ApplicationContext Context context) {
    return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
        ? context.getExternalCacheDir() : context.getCacheDir();
  }

  @Provides @Singleton OkHttpClient provideOkHttpClient(@CacheDir File cacheDir) {
    OkHttpClient okHttpClient = new OkHttpClient();
    try {
      okHttpClient.setCache(new Cache(cacheDir, 20 * 1024 * 1024));
    } catch (IOException ignored) {
    }
    return okHttpClient;
  }

  /*
   *  Although Picasso is only needed in the UI scope, it subscribes to the network status updates
   *  and leaks the Activity's context, which is pretty bad
   */
  @Provides @Singleton Picasso providePicasso(@ApplicationContext Context context,
      OkHttpClient okHttpClient) {
    return new Picasso.Builder(context).downloader(new OkHttpDownloader(okHttpClient)).build();
  }

  @Provides @Singleton Preferences providePreferences(@ApplicationContext Context context) {
    return Esperandro.getPreferences(Preferences.class, context);
  }
}
