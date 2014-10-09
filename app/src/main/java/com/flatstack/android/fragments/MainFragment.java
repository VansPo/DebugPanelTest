package com.flatstack.android.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.flatstack.android.R;
import com.flatstack.android.dagger.Dagger;
import com.flatstack.android.dagger.ScopedFragment;
import com.flatstack.android.dagger.modules.MainFragmentScopeModule;
import com.flatstack.android.common.ActionBars;
import com.squareup.picasso.Picasso;
import dagger.Lazy;
import dagger.ObjectGraph;
import javax.inject.Inject;
import rx.subjects.Subject;

public class MainFragment extends ScopedFragment {
  @Inject @NonNull Lazy<Picasso> picasso; // application scope
  @Inject @NonNull Lazy<Subject<Boolean, Boolean>> truth; //fragment scope

  @NonNull @Override protected ObjectGraph createDaggerScope(@NonNull Context activity) {
    return Dagger.getObjectGraph(getActivity()).plus(new MainFragmentScopeModule());
  }

  @NonNull @Override protected View createScopedView(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.main, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ActionBars.configure(this, actionBar -> {
      ActionBars.homeAsUp(actionBar, false);
      actionBar.setTitle(R.string.app_name);
    });
    Dagger.inject(this);
  }
}
