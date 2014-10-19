package com.todorename.android;

import com.todorename.android.dagger.modules.AppModule;
import com.todorename.android.ui.DebugUiModule;
import dagger.Module;

/**
 * Created by vans on 19.10.14.
 */
@Module(
    addsTo = AppModule.class,
    includes = {
        DebugUiModule.class,
        //DebugDataModule.class
    },
    overrides = true
)
public class DebugModule {
}
