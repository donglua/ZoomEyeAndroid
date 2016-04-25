package org.droiders.zoomeye.login;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class LoginModule {

  private final LoginContract.View view;

  public LoginModule(LoginContract.View view) {
    this.view = view;
  }

  @Provides @Singleton
  public LoginContract.View providesLoginView() {
    return view;
  }
}
