package org.droiders.zoomeye.di;

import com.f2prateek.rx.preferences.Preference;
import dagger.Component;
import javax.inject.Singleton;
import org.droiders.zoomeye.data.AccessToken;

@Singleton
@Component(
    modules = {
        ApiModule.class
    }
)
public interface ApiComponent {

    @AccessToken Preference<String> accessToken();
}
