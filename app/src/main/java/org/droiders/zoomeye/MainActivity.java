package org.droiders.zoomeye;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.jakewharton.rxbinding.view.RxView;
import javax.inject.Inject;
import org.droiders.zoomeye.databinding.ActivityMainBinding;
import org.droiders.zoomeye.di.AppModule;
import org.droiders.zoomeye.login.DaggerLoginComponent;
import org.droiders.zoomeye.login.LoginComponent;
import org.droiders.zoomeye.login.LoginContract;
import org.droiders.zoomeye.login.LoginModule;
import org.droiders.zoomeye.login.LoginPresenter;
import rx.android.schedulers.AndroidSchedulers;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class MainActivity extends AppCompatActivity implements LoginContract.View {

  @Inject LoginPresenter mPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    LoginComponent loginComponent = DaggerLoginComponent.builder()
        .loginModule(new LoginModule(this))
        .appModule(new AppModule(this))
        .build();
    loginComponent.inject(this);

    RxView.clicks(binding.buttonLogin)
        .debounce(300, MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(aVoid -> {

        });

  }

}
