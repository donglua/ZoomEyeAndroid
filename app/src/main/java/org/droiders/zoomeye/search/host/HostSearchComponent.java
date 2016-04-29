package org.droiders.zoomeye.search.host;

import dagger.Component;
import javax.inject.Singleton;
import org.droiders.zoomeye.di.ApiModule;

/**
 * Created by Donglua on 16/4/29.
 */
@Singleton
@Component(
  modules = {
      HostSearchModule.class,
      ApiModule.class
  }
)
public interface HostSearchComponent {

  void inject(HostSearchFragment fragment);

}
