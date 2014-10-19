package com.todorename.android.ui.debug;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.StatsSnapshot;
import com.todorename.android.BuildConfig;
import com.todorename.android.R;
import com.todorename.android.holdr.Holdr_DebugActivityFrame;
import com.todorename.android.holdr.Holdr_DebugDrawerContent;
import com.todorename.android.ui.AppContainer;
import com.todorename.android.util.Strings;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.inject.Inject;

/**
 * Created by vans on 19.10.14.
 */
public class DebugAppContainer implements AppContainer {
  private static final DateFormat DATE_DISPLAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

  private final Application app;
  private final Picasso picasso;

  Holdr_DebugActivityFrame holdrFrame;
  Holdr_DebugDrawerContent holdrContent;
  Activity activity;
  Context drawerContext;

  @Inject public DebugAppContainer(Application app, Picasso picasso) {
    this.app = app;
    this.picasso = picasso;
  }

  @Override public ViewGroup get(Activity activity) {
    this.activity = activity;
    drawerContext = activity;

    activity.setContentView(R.layout.debug_activity_frame);
    holdrFrame = new Holdr_DebugActivityFrame(LayoutInflater.from(drawerContext)
        .inflate(R.layout.debug_activity_frame, null));
    ViewGroup drawer = (ViewGroup) activity.findViewById(R.id.debug_drawer);
    holdrContent = new Holdr_DebugDrawerContent(LayoutInflater.from(drawerContext)
        .inflate(R.layout.debug_drawer_content, drawer));

    holdrFrame.debugDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
      @Override public void onDrawerOpened(View drawerView) {
        refreshPicassoStats();
      }
    });

    setupBuildSection();
    setupDeviceSection();

    return holdrFrame.debugContent;
  }

  private void setupBuildSection() {
    holdrContent.debugBuildName.setText(BuildConfig.VERSION_NAME);
    holdrContent.debugBuildCode.setText(String.valueOf(BuildConfig.VERSION_CODE));

    try {
      // Parse ISO8601-format time into local time.
      DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
      inFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      Date buildTime = inFormat.parse(BuildConfig.BUILD_TIME);
      holdrContent.debugBuildDate.setText(DATE_DISPLAY_FORMAT.format(buildTime));
    } catch (ParseException e) {
      throw new RuntimeException("Unable to decode build time: " + BuildConfig.BUILD_TIME, e);
    }
  }

  private void setupDeviceSection() {
    DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
    String densityBucket = getDensityString(displayMetrics);
    holdrContent.debugDeviceMake.setText(Strings.truncateAt(Build.MANUFACTURER, 20));
    holdrContent.debugDeviceModel.setText(Strings.truncateAt(Build.MODEL, 20));
    holdrContent.debugDeviceResolution.setText(displayMetrics.heightPixels + "x" + displayMetrics.widthPixels);
    holdrContent.debugDeviceDensity.setText(displayMetrics.densityDpi + "dpi (" + densityBucket + ")");
    holdrContent.debugDeviceRelease.setText(Build.VERSION.RELEASE);
    holdrContent.debugDeviceApi.setText(String.valueOf(Build.VERSION.SDK_INT));
  }

  private void refreshPicassoStats() {
    StatsSnapshot snapshot = picasso.getSnapshot();
    String size = getSizeString(snapshot.size);
    String total = getSizeString(snapshot.maxSize);
    int percentage = (int) ((1f * snapshot.size / snapshot.maxSize) * 100);
    holdrContent.debugPicassoCacheSize.setText(size + " / " + total + " (" + percentage + "%)");
    holdrContent.debugPicassoCacheHit.setText(String.valueOf(snapshot.cacheHits));
    holdrContent.debugPicassoCacheMiss.setText(String.valueOf(snapshot.cacheMisses));
    holdrContent.debugPicassoDecoded.setText(String.valueOf(snapshot.originalBitmapCount));
    holdrContent.debugPicassoDecodedTotal.setText(getSizeString(snapshot.totalOriginalBitmapSize));
    holdrContent.debugPicassoDecodedAvg.setText(getSizeString(snapshot.averageOriginalBitmapSize));
    holdrContent.debugPicassoTransformed.setText(String.valueOf(snapshot.transformedBitmapCount));
    holdrContent.debugPicassoTransformedTotal.setText(getSizeString(snapshot.totalTransformedBitmapSize));
    holdrContent.debugPicassoTransformedAvg.setText(getSizeString(snapshot.averageTransformedBitmapSize));
  }

  private static String getDensityString(DisplayMetrics displayMetrics) {
    switch (displayMetrics.densityDpi) {
      case DisplayMetrics.DENSITY_LOW:
        return "ldpi";
      case DisplayMetrics.DENSITY_MEDIUM:
        return "mdpi";
      case DisplayMetrics.DENSITY_HIGH:
        return "hdpi";
      case DisplayMetrics.DENSITY_XHIGH:
        return "xhdpi";
      case DisplayMetrics.DENSITY_XXHIGH:
        return "xxhdpi";
      case DisplayMetrics.DENSITY_XXXHIGH:
        return "xxxhdpi";
      case DisplayMetrics.DENSITY_TV:
        return "tvdpi";
      default:
        return "unknown";
    }
  }

  private static String getSizeString(long bytes) {
    String[] units = new String[] { "B", "KB", "MB", "GB" };
    int unit = 0;
    while (bytes >= 1024) {
      bytes /= 1024;
      unit += 1;
    }
    return bytes + units[unit];
  }
}
