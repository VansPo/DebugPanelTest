package com.todorename.android.ui;

import com.todorename.android.ui.debug.DebugAppContainer;
import com.todorename.android.ui.debug.SocketActivityHierarchyServer;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by vans on 19.10.14.
 */
@Module(
    injects = DebugAppContainer.class,
    complete = false,
    library = true,
    overrides = true
)
public class DebugUiModule {
  @Provides @Singleton AppContainer provideAppContainer(DebugAppContainer debugAppContainer) {
    return debugAppContainer;
  }

  @Provides @Singleton ActivityHierarchyServer provideActivityHierarchyServer() {
    return new SocketActivityHierarchyServer();
  }
}
