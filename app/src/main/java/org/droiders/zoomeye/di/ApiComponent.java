package org.droiders.zoomeye.di;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {
        ApiModule.class
    }
)
public interface ApiComponent {
}
