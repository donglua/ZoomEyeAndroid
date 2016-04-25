package org.droiders.zoomeye;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import org.droiders.zoomeye.di.ApiComponent;
import org.droiders.zoomeye.di.AppModule;
import org.droiders.zoomeye.di.DaggerApiComponent;
import timber.log.Timber;

/**
 * Created by Donglua on 16/4/25.
 */
public final class ZoomEyeApp extends Application {

  ApiComponent component;

  @Override public void onCreate() {
    super.onCreate();

    Timber.plant(new Timber.DebugTree());
    // setup default typefaces
    TypefaceProvider.registerDefaultIconSets();

    component = DaggerApiComponent.builder().appModule(new AppModule(this)).build();
  }

  public static ApiComponent apiComponent(Context context) {
    return ((ZoomEyeApp)context.getApplicationContext()).component;
  }

  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }
}
