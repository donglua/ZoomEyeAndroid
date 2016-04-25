package org.droiders.zoomeye.login;

/**
 * Created by Donglua on 16/4/25.
 */
public interface LoginContract {

  interface View {
    void loginClick();

    void loginSuccess();

    void loginFail(String errorMessage);
  }

  interface Prestener {
    void login(String email, String password);
  }
}
