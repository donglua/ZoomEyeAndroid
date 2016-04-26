package org.droiders.zoomeye.search.info;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Donglua on 16/4/27.
 */
@Module
public class ResourcesInfoModule {
  final ResourcesInfoContract.View view;

  public ResourcesInfoModule(ResourcesInfoContract.View view) {
    this.view = view;
  }

  @Provides @Singleton
  public ResourcesInfoContract.View provideResourcesInfoView() {
    return view;
  }
}
