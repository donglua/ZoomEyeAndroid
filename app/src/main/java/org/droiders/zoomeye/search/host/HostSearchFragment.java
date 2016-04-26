package org.droiders.zoomeye.search.host;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Donglua on 16/4/26.
 */
public class HostSearchFragment extends Fragment {

  public static HostSearchFragment newInstance() {
    Bundle args = new Bundle();
    HostSearchFragment fragment = new HostSearchFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }
}
