package org.droiders.zoomeye.search.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

/**
 * Created by Donglua on 16/4/28.
 */
public class SearchFragmentAdapter extends FragmentStatePagerAdapter {

  final List<Fragment> mFragmentList;

  public SearchFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
    super(fm);
    mFragmentList = fragmentList;
  }

  @Override public Fragment getItem(int position) {
    return mFragmentList.get(position);
  }

  @Override public int getCount() {
    return mFragmentList.size();
  }
}
