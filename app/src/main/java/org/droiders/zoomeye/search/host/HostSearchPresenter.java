package org.droiders.zoomeye.search.host;

import javax.inject.Inject;
import org.zoomeye.api.ZoomEyeApiService;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Donglua on 16/4/29.
 */
public class HostSearchPresenter implements HostSearchContract.Presenter {

  private final ZoomEyeApiService api;
  private final HostSearchContract.View view;

  @Inject
  public HostSearchPresenter(ZoomEyeApiService api, HostSearchContract.View view) {
    this.api = api;
    this.view = view;
  }

  @Override public void search(String query, int page) {
    api.search(query, page, "")
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(searchResultResponse -> {

          if (searchResultResponse.isSuccessful()) {

            view.showMatches(searchResultResponse.body().getMatches());

          } else {

          }

        });
  }
}
