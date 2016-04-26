package org.droiders.zoomeye.search.info;

import dagger.Component;
import javax.inject.Singleton;
import org.droiders.zoomeye.di.ApiModule;
import org.droiders.zoomeye.search.SearchActivity;

@Singleton
@Component(
    modules = {
        ApiModule.class, ResourcesInfoModule.class
    }
)
public interface ResourcesInfoComponent {

  void inject(SearchActivity searchActivity);

}
