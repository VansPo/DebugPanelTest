package com.flatstack.android.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Waiting for Evan Tatarka with his holdr. https://github.com/evant/holdr/issues/20
 * Until we can use this quick fluent replace for boilerplate.
 *
 * example:
 * Attrs.from(context, attributeSet, R.styleable.MenuItemView)
 * .resourceId(R.styleable.MenuItemView_icon, holdr.menuitemIcon::setImageResource)
 * .string(R.styleable.MenuItemView_title, holdr.menuitemTitle::setText)
 * .recycle();
 */
public class Attrs {
  private static final int INVALID_INT = -1;

  private final TypedArray typedArray;

  private Attrs(Context context, AttributeSet attrs, int[] styleable) {
    this.typedArray = context.obtainStyledAttributes(attrs, styleable);
  }

  public static Attrs from(Context context, AttributeSet attrs, int[] styleable) {
    return new Attrs(context, attrs, styleable);
  }

  public Attrs color(int attr, Action1<Integer> action) {
    int color = typedArray.getColor(attr, INVALID_INT);
    if (color != INVALID_INT) {
      action.call(color);
    }
    return this;
  }

  public Attrs resourceId(int attr, Action1<Integer> action) {
    int resourceId = typedArray.getResourceId(attr, INVALID_INT);
    if (resourceId != INVALID_INT) {
      action.call(resourceId);
    }
    return this;
  }

  public Attrs string(int attr, Action1<String> action) {
    String string = typedArray.getString(attr);
    action.call(string);
    return this;
  }

  public Attrs integer(int attr, Action1<Integer> action) {
    int anInt = typedArray.getInt(attr, INVALID_INT);
    if (anInt != INVALID_INT) {
      action.call(anInt);
    }
    return this;
  }

  public <T> Attrs enumv(int attr, Func1<Integer, T> map, Action1<T> action) {
    int anInt = typedArray.getInt(attr, INVALID_INT);
    if (anInt != INVALID_INT) {
      action.call(map.call(anInt));
    }
    return this;
  }

  public Attrs dimen(int attr, Action1<Integer> action) {
    int pixelSize = typedArray.getDimensionPixelSize(attr, INVALID_INT);
    if (pixelSize != INVALID_INT) {
      action.call(pixelSize);
    }
    return this;
  }

  public void recycle() {
    typedArray.recycle();
  }
}
