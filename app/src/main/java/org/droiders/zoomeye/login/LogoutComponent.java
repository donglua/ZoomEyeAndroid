package org.droiders.zoomeye.login;

import dagger.Subcomponent;
import org.droiders.zoomeye.di.ViewScope;

@ViewScope
@Subcomponent(
    modules = {
        LogoutModule.class
    }
)
public interface LogoutComponent {

    LogoutPresenter getPresenter();
}
