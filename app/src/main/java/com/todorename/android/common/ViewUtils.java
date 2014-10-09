package com.todorename.android.common;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * some useful view utils. Please keep it up to date!
 */
@SuppressWarnings("WeakerAccess")
public class ViewUtils {

  public static String extract(Editable editable) {
    String s = editable.toString();
    return s == null ? null : s.trim();
  }

  public static int dpToPx(Context context, int dp) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
  }

  /**
   * This method converts device specific pixels to density independent pixels.
   *
   * @param px A value in px (pixels) unit. Which we need to convert into db
   * @param context Context to get resources and device specific display metrics
   * @return A float value to represent dp equivalent to px value
   */
  public static float convertPixelsToDp(float px, Context context) {
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    return px / (metrics.densityDpi / 160f);
  }

  /**
   * track thi issue, https://code.google.com/p/android/issues/detail?id=25416 and use this method
   * until google reacts
   *
   * @param letterSpacing do not even know what
   */
  private void applyLetterSpacing(TextView textView, int letterSpacing) {
    CharSequence originalText = textView.getText();
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < originalText.length(); i++) {
      builder.append(originalText.charAt(i));
      if (i + 1 < originalText.length()) {
        builder.append("\u00A0");
      }
    }
    SpannableString finalText = new SpannableString(builder.toString());
    if (builder.toString().length() > 1) {
      for (int i = 1; i < builder.toString().length(); i += 2) {
        finalText.setSpan(new ScaleXSpan((letterSpacing + 1) / 10), i, i + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
    }
    textView.setText(finalText, TextView.BufferType.SPANNABLE);
  }
}
