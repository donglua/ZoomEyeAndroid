package org.droiders.zoomeye.search.search;

import javax.inject.Inject;
import org.zoomeye.api.ZoomEyeApiService;

/**
 * Created by Donglua on 16/4/27.
 */
public class SearchPresenter implements SearchContract.Presenter {

  final ZoomEyeApiService api;
  final SearchContract.View view;

  @Inject
  public SearchPresenter(ZoomEyeApiService api, SearchContract.View view) {
    this.api = api;
    this.view = view;
  }


}
