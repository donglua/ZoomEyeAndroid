package org.droiders.zoomeye.search.info;

import java.net.SocketTimeoutException;
import javax.inject.Inject;
import org.droiders.zoomeye.R;
import org.zoomeye.api.ErrorBodyHandler;
import org.zoomeye.api.ZoomEyeApiService;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Donglua on 16/4/27.
 */
public class ResourcesInfoPresenter implements ResourcesInfoContract.Presenter {

  private final ErrorBodyHandler errorBodyHandler;
  private final ZoomEyeApiService api;
  private final ResourcesInfoContract.View view;

  @Inject
  public ResourcesInfoPresenter(ErrorBodyHandler errorBodyHandler, ZoomEyeApiService api,
      ResourcesInfoContract.View view) {
    this.errorBodyHandler = errorBodyHandler;
    this.api = api;
    this.view = view;
  }

  @Override public void getResourcesInfo() {
    api.getResourcesInfo()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(resourcesInfoResponse -> {
          if (resourcesInfoResponse.isSuccessful()) {
            view.showResourcesInfo(resourcesInfoResponse.body());
          } else {
            view.showErrorMsg(errorBodyHandler.parseError(resourcesInfoResponse.errorBody()).getMessage());
            if (resourcesInfoResponse.code() == 401) { // 请求未授权，缺少 token，或者 token 已过期失效
              // 退出登录
              view.logout();
            }
          }
        }, throwable -> {
          if (throwable instanceof SocketTimeoutException) {
            view.showErrorMsg(R.string.toast_time_out);
          }
        });
  }
}
