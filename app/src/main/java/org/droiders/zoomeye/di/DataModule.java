package org.droiders.zoomeye.di;

import android.content.Context;
import android.content.SharedPreferences;
import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.droiders.zoomeye.data.AccessToken;
import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;
import static com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES;

/**
 * Created by Donglua on 16/1/21.
 */
@Module(
    includes = AppModule.class
)
public class DataModule {

  static final int DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(50);

  @Provides @Singleton SharedPreferences provideSharedPreferences(Context context) {
    return context.getSharedPreferences("Github", MODE_PRIVATE);
  }

  @Provides @Singleton RxSharedPreferences provideRxSharedPreferences(SharedPreferences prefs) {
    return RxSharedPreferences.create(prefs);
  }

  @Provides @Singleton @AccessToken Preference<String> provideAccessToken(RxSharedPreferences prefs) {
    return prefs.getString("access-token");
  }

  @Provides @Singleton OkHttpClient provideOkHttpClient(Context context, HttpLoggingInterceptor loggingInterceptor) {
    return createOkHttpClient(context).addInterceptor(loggingInterceptor).build();
  }

  static OkHttpClient.Builder createOkHttpClient(Context context) {
    // Install an HTTP cache in the application cache directory.
    File cacheDir = new File(context.getCacheDir(), "http");
    Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

    return new OkHttpClient.Builder().cache(cache);
  }

  @Provides @Singleton
  public Gson provideGson() {
    return new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        //.registerTypeAdapter(Instant.class, new InstantDeserializer())
        .create();
  }


  @Provides @Singleton HttpLoggingInterceptor provideLoggingInterceptor() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").v(message));
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return loggingInterceptor;
  }


}
