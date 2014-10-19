package com.todorename.android;

import android.support.annotation.NonNull;
import com.todorename.android.common.Lists;
import com.todorename.android.dagger.ModulesTestModule;
import java.util.List;

public class TestApp extends App {
  @NonNull @Override protected List<Object> getDaggerModules() {
    return Lists.add(super.getDaggerModules(), new ModulesTestModule());
  }
}
