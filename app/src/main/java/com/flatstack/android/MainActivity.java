package com.flatstack.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import com.flatstack.android.common.Preferences;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.dagger.Injector;
import com.flatstack.android.dagger.modules.MainActivityScopeModule;
import com.github.mttkay.memento.Memento;
import com.github.mttkay.memento.MementoCallbacks;
import com.github.mttkay.memento.Retain;
import dagger.ObjectGraph;
import javax.inject.Inject;
import lombok.Getter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity implements MementoCallbacks, Injector {
  static {
    CalligraphyConfig.initDefault(null);
  }

  @Inject @NonNull Preferences preferences; // injected from activity scope

  @Retain @NonNull @Getter ObjectGraph objectGraph;

  @Override protected void attachBaseContext(@NonNull Context newBase) {
    super.attachBaseContext(new CalligraphyContextWrapper(newBase));
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Memento.retain(this);
    Dagger.inject(this);
  }

  @Override public void onLaunch() {
    objectGraph = Dagger.getObjectGraph(getApplication()).plus(new MainActivityScopeModule());
  }

  @Override public boolean onCreateOptionsMenu(@NonNull Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        return true;

      case R.id.action_settings:
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }
}