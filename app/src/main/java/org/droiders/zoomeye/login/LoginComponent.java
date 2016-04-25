package org.droiders.zoomeye.login;

import dagger.Component;
import javax.inject.Singleton;
import org.droiders.zoomeye.di.ApiModule;

@Singleton
@Component(
    modules = {
        ApiModule.class,
        LoginModule.class
    }
)
public interface LoginComponent {

  void inject(LoginActivity activity);

}
