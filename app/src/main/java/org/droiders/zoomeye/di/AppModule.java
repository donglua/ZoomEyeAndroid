package org.droiders.zoomeye.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

  private final Context mContext;

  public AppModule(Context context) {
    mContext = context;
  }

  @Provides @Singleton
  Context provideContext() {
    return mContext;
  }

}
