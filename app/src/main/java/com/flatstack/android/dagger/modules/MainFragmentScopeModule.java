package com.flatstack.android.dagger.modules;

import com.flatstack.android.fragments.MainFragment;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by adelnizamutdinov on 10/1/14
 */
@Module(addsTo = MainActivityScopeModule.class,
    injects = {
        MainFragment.class
    })
public class MainFragmentScopeModule {
  @Provides @Singleton Subject<Boolean, Boolean> provideTruth() {
    return PublishSubject.create();
  }
}
