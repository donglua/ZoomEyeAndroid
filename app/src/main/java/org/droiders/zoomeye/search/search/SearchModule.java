package org.droiders.zoomeye.search.search;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class SearchModule {

  final SearchContract.View view;

  public SearchModule(SearchContract.View view) {
    this.view = view;
  }

  @Provides @Singleton
  public SearchContract.View provideSearchView() {
    return view;
  }
}
