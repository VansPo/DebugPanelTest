package com.flatstack.android;

import android.support.annotation.NonNull;
import com.flatstack.android.common.Lists;
import com.flatstack.android.dagger.ModulesTestModule;
import java.util.List;

public class TestApp extends App {
  @NonNull @Override protected List<Object> getDaggerModules() {
    return Lists.add(super.getDaggerModules(), new ModulesTestModule());
  }
}
