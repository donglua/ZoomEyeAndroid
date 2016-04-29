package org.droiders.zoomeye.search.host;

import dagger.Component;
import javax.inject.Singleton;
import org.droiders.zoomeye.di.ApiModule;
import org.droiders.zoomeye.search.web.WebSearchFragment;

/**
 * Created by Donglua on 16/4/29.
 */
@Singleton
@Component(
  modules = {
      SearchFragmentModule.class,
      ApiModule.class
  }
)
public interface SearchFragmentComponent {

  void inject(HostSearchFragment fragment);
  void inject(WebSearchFragment fragment);

}
