package org.droiders.zoomeye.login;

import com.f2prateek.rx.preferences.Preference;
import javax.inject.Inject;
import org.droiders.zoomeye.data.AccessToken;
import org.zoomeye.api.ErrorBody;
import org.zoomeye.api.ErrorBodyHandler;
import org.zoomeye.api.ZoomEyeApiService;
import org.zoomeye.api.auth.LoginRequest;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class LoginPresenter implements LoginContract.Prestener {

  private final LoginContract.View loginView;
  private final ZoomEyeApiService api;
  private final ErrorBodyHandler errorHandler;
  private final Preference<String> tokenPreference;

  @Inject public LoginPresenter(LoginContract.View loginView,
      ZoomEyeApiService api,
      ErrorBodyHandler errorHandler,
      @AccessToken Preference<String> tokenPreference) {

    this.loginView = loginView;
    this.api = api;
    this.errorHandler = errorHandler;
    this.tokenPreference = tokenPreference;
  }

  @Override public void login(String email, String password) {
    api.login(new LoginRequest(email, password))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(accessTokenResponse -> {
          if (accessTokenResponse.isSuccessful()) {
            String accessToken = accessTokenResponse.body().toString();
            Timber.d("accessToken = %s", accessToken);
            tokenPreference.set(accessToken);
            loginView.loginSuccess();
          } else {
            ErrorBody errorBody = errorHandler.parseError(accessTokenResponse.errorBody());
            loginView.loginFail(errorBody.getMessage());
          }
        });
  }
}
