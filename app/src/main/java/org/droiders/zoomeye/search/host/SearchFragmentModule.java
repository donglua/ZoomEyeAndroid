package org.droiders.zoomeye.search.host;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.droiders.zoomeye.search.SearchFragmentContract;

@Module
public class SearchFragmentModule {

  private final SearchFragmentContract.View view;

  public SearchFragmentModule(SearchFragmentContract.View view) {
    this.view = view;
  }

  @Provides @Singleton
  public SearchFragmentContract.View getHostSearchView() {
    return view;
  }
}
