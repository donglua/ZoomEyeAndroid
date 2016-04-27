package org.droiders.zoomeye.search.info;

import dagger.Subcomponent;
import org.droiders.zoomeye.di.ViewScope;

@ViewScope
@Subcomponent(
    modules = ResourcesInfoModule.class
)
public interface ResourcesInfoComponent {

  ResourcesInfoPresenter getPresenter();

}
