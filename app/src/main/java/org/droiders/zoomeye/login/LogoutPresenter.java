package org.droiders.zoomeye.login;

import com.f2prateek.rx.preferences.Preference;
import javax.inject.Inject;
import org.droiders.zoomeye.data.AccessToken;

/**
 * Created by Donglua on 16/4/27.
 */
public class LogoutPresenter implements LogoutContract.Presenter {

  final Preference<String> tokenPreference;
  final LogoutContract.View view;

  @Inject
  public LogoutPresenter(@AccessToken Preference<String> tokenPreference, LogoutContract.View view) {
    this.tokenPreference = tokenPreference;
    this.view = view;
  }

  @Override public void onLogout() {
    tokenPreference.delete();
    view.logout();
  }
}
