package org.droiders.zoomeye.search.host;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.devspark.appmsg.AppMsg;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.droiders.zoomeye.adapter.HostSearchResultAdapter;
import org.droiders.zoomeye.databinding.FragmentSearchBinding;
import org.droiders.zoomeye.di.AppModule;
import org.zoomeye.api.search.MatchHost;

import static org.droiders.zoomeye.search.search.SearchResultActivity.EXTRA_QUERY;

/**
 * Created by Donglua on 16/4/26.
 */
public class HostSearchFragment extends Fragment implements HostSearchContract.View {

  private FragmentSearchBinding binding;
  private HostSearchResultAdapter mResultAdapter;
  private List<MatchHost> mMatchList;

  @Inject HostSearchPresenter mPresenter;
  private int page = 1;

  public static HostSearchFragment newInstance(String query) {
    Bundle args = new Bundle();
    args.putString(EXTRA_QUERY, query);
    HostSearchFragment fragment = new HostSearchFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    DaggerHostSearchComponent.builder()
        .hostSearchModule(new HostSearchModule(this))
        .appModule(new AppModule(getContext()))
        .build()
        .inject(this);

    mMatchList = new ArrayList<>();
    mResultAdapter = new HostSearchResultAdapter(mMatchList);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentSearchBinding.inflate(inflater, container, false);

    binding.listResult.setLayoutManager(new LinearLayoutManager(getContext()));
    binding.listResult.setAdapter(mResultAdapter);

    final String query = getArguments().getString(EXTRA_QUERY);
    mPresenter.search(query, page);

    binding.listResult.setRefreshListener(() -> {
      page = 1;
      mPresenter.search(query, page);
    });
    binding.listResult.setupMoreListener(
        (overallItemsCount, itemsBeforeMore, maxLastVisiblePosition) -> {
          mPresenter.search(query, ++page);
        }, 1);

    return binding.getRoot();
  }

  @Override public void showMatches(List<MatchHost> matches) {
    if (page == 1) mMatchList.clear();
    mMatchList.addAll(matches);
    mResultAdapter.notifyDataSetChanged();
  }

  @Override public void showErrorMessage(String message) {
    AppMsg.makeText(getActivity(), message, AppMsg.STYLE_ALERT).show();

    binding.listResult.setRefreshing(false);
  }

}
