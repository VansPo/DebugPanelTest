package com.flatstack.android.utils;

import de.devland.esperandro.annotations.Default;
import de.devland.esperandro.annotations.SharedPreferences;
import org.jetbrains.annotations.NotNull;

public @SharedPreferences interface Preferences {
  @Default(ofString = "XXXX") public String defaultString();

  public void defaultString(@NotNull String s);
}
