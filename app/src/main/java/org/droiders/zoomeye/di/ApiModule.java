package org.droiders.zoomeye.di;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.droiders.zoomeye.data.OauthInterceptor;
import org.zoomeye.api.ErrorBodyHandler;
import org.zoomeye.api.ZoomEyeApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

@Module(
    includes = DataModule.class
)
public final class ApiModule {
  public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("http://api.zoomeye.org");

  @Provides @Singleton HttpUrl provideBaseUrl() {
    return PRODUCTION_API_URL;
  }

  @Provides @Singleton @Named("Api") OkHttpClient provideApiClient(OkHttpClient client,
      OauthInterceptor oauthInterceptor) {
    return createApiClient(client, oauthInterceptor).build();
  }

  @Provides @Singleton Retrofit provideRetrofit(HttpUrl baseUrl, @Named("Api") OkHttpClient client,
      Gson gson) {
    return new Retrofit.Builder() //
        .client(client) //
        .baseUrl(baseUrl) //
        .addConverterFactory(GsonConverterFactory.create(gson)) //
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())) //
        .build();
  }

  @Provides @Singleton ZoomEyeApiService provideZoomEyeApiService(Retrofit retrofit) {
    return retrofit.create(ZoomEyeApiService.class);
  }

  @Provides @Singleton ErrorBodyHandler provideErrorHandler(Retrofit retrofit) {
    return new ErrorBodyHandler(retrofit);
  }

  static OkHttpClient.Builder createApiClient(OkHttpClient client, OauthInterceptor oauthInterceptor) {
    return client.newBuilder().addInterceptor(oauthInterceptor);
  }
}