package org.droiders.zoomeye.di;

import com.f2prateek.rx.preferences.Preference;
import dagger.Component;
import javax.inject.Singleton;
import org.droiders.zoomeye.data.AccessToken;
import org.droiders.zoomeye.login.LogoutComponent;
import org.droiders.zoomeye.login.LogoutModule;
import org.droiders.zoomeye.search.info.ResourcesInfoComponent;
import org.droiders.zoomeye.search.info.ResourcesInfoModule;

@Singleton
@Component(
    modules = {
        ApiModule.class
    }
)
public interface ApiComponent {

    @AccessToken Preference<String> accessToken();

    LogoutComponent plus(LogoutModule logoutModule);
    ResourcesInfoComponent plus(ResourcesInfoModule resourcesInfoModule);


}
