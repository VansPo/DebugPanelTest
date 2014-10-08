package com.flatstack.android.dagger;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dagger.ObjectGraph;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * Created by adel on 6/7/14
 */
public abstract class ScopedFragment extends Fragment {
  @NonNull @Getter(AccessLevel.PROTECTED) Context scopedContext;
  @Nullable ObjectGraph objectGraph;

  @NonNull protected abstract View createScopedView(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup container, @Nullable Bundle savedInstanceState);

  @NonNull protected abstract ObjectGraph createDaggerScope(@NonNull Context activity);

  @Override public void onAttach(@NonNull Activity activity) {
    super.onAttach(activity);
    scopedContext = new ScopedContextWrapper(activity, createDaggerScope(activity));
  }

  @Override public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    if (container == null) {
      throw new AssertionError("no nulls here");
    }
    return createScopedView(LayoutInflater.from(scopedContext), container, savedInstanceState);
  }

  @Override public void onDetach() {
    scopedContext = null;
    super.onDetach();
  }
}
