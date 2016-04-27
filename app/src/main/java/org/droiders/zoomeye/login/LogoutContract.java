package org.droiders.zoomeye.login;

/**
 * Created by Donglua on 16/4/27.
 */
public interface LogoutContract {

  interface View {
    void logout();
  }

  interface Presenter {
    void onLogout();
  }

}
