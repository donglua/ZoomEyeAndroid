package org.droiders.zoomeye.login;

import javax.inject.Inject;
import org.zoomeye.api.ZoomEyeApiService;

/**
 * Created by Donglua on 16/4/25.
 */
public class LoginPresenter implements LoginContract.Prestener {

  private final LoginContract.View loginView;
  private final ZoomEyeApiService api;

  @Inject
  public LoginPresenter(LoginContract.View loginView, ZoomEyeApiService api) {
    this.loginView = loginView;
    this.api = api;
  }
}
