package org.droiders.zoomeye.search.host;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class HostSearchModule {

  private final HostSearchContract.View view;

  public HostSearchModule(HostSearchContract.View view) {
    this.view = view;
  }

  @Provides @Singleton
  public HostSearchContract.View getHostSearchView() {
    return view;
  }
}
