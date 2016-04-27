package org.droiders.zoomeye.login;

import dagger.Module;
import dagger.Provides;
import org.droiders.zoomeye.di.ViewScope;

/**
 * Created by Donglua on 16/4/27.
 */
@Module
public class LogoutModule {

  final LogoutContract.View view;

  public LogoutModule(LogoutContract.View view) {
    this.view = view;
  }

  @Provides @ViewScope
  public LogoutContract.View provideView() {
    return view;
  }
}
