package com.flatstack.android.common;

import android.support.annotation.NonNull;
import de.devland.esperandro.annotations.Default;
import de.devland.esperandro.annotations.SharedPreferences;

public @SharedPreferences interface Preferences {
  @Default(ofString = "XXXX") public String defaultString();

  public void defaultString(@NonNull String s);
}
