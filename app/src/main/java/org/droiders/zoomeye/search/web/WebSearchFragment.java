package org.droiders.zoomeye.search.web;

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
import org.droiders.zoomeye.adapter.WebSearchResultAdapter;
import org.droiders.zoomeye.databinding.FragmentSearchBinding;
import org.droiders.zoomeye.di.AppModule;
import org.droiders.zoomeye.search.SearchFragmentContract;
import org.droiders.zoomeye.search.host.DaggerSearchFragmentComponent;
import org.droiders.zoomeye.search.host.SearchFragmentModule;
import org.zoomeye.api.search.MatchHost;
import org.zoomeye.api.search.MatchWeb;

import static org.droiders.zoomeye.search.search.SearchResultActivity.EXTRA_QUERY;

/**
 * Created by Donglua on 16/4/26.
 */
public class WebSearchFragment extends Fragment implements SearchFragmentContract.View {
  private FragmentSearchBinding binding;
  private List<MatchWeb> mMatchList;
  private int page = 1;

  @Inject WebSearchPresenter mPresenter;
  WebSearchResultAdapter mResultAdapter;

  public static WebSearchFragment newInstance(String query) {
    Bundle args = new Bundle();
    args.putString(EXTRA_QUERY, query);
    WebSearchFragment fragment = new WebSearchFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    DaggerSearchFragmentComponent.builder()
        .searchFragmentModule(new SearchFragmentModule(this))
        .appModule(new AppModule(getContext()))
        .build()
        .inject(this);

    mMatchList = new ArrayList<>();
    mResultAdapter= new WebSearchResultAdapter(mMatchList);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

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


  @Override public void showHostMatches(List<MatchHost> matches) {
  }

  @Override public void showWebMatches(List<MatchWeb> matches) {
    if (page == 1) mMatchList.clear();
    mMatchList.addAll(matches);
    mResultAdapter.notifyDataSetChanged();
  }

  @Override public void showErrorMessage(String message) {
    AppMsg.makeText(getActivity(), message, AppMsg.STYLE_ALERT).show();

    binding.listResult.setRefreshing(false);
  }
}
