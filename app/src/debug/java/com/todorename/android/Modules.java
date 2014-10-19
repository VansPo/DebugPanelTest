package com.todorename.android;

import com.todorename.android.dagger.modules.AppModule;

final class Modules {
  static Object[] list(App app) {
    return new Object[] {
        new AppModule(app),
        new DebugModule()
    };
  }

  private Modules() {
    // No instances.
  }
}
