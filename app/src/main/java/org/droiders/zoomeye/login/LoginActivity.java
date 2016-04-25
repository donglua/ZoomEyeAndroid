package org.droiders.zoomeye.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.devspark.appmsg.AppMsg;
import com.jakewharton.rxbinding.view.RxView;
import javax.inject.Inject;
import org.droiders.zoomeye.R;
import org.droiders.zoomeye.databinding.ActivityLoginBinding;
import org.droiders.zoomeye.di.AppModule;
import rx.android.schedulers.AndroidSchedulers;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

  private ActivityLoginBinding binding;
  @Inject LoginPresenter mPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

    LoginComponent loginComponent = DaggerLoginComponent.builder()
        .loginModule(new LoginModule(this))
        .appModule(new AppModule(this))
        .build();
    loginComponent.inject(this);

    RxView.clicks(binding.buttonLogin)
        .debounce(300, MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(aVoid -> this.loginClick());
  }

  @Override
  public void loginClick() {
    final String email    = binding.etEmail.getText().toString();
    final String password = binding.etPassword.getText().toString();
    if (TextUtils.isEmpty(email)) {
      AppMsg.makeText(this, R.string.email_not_null, AppMsg.STYLE_ALERT).show();
      return;
    }
    if (TextUtils.isEmpty(password)) {
      AppMsg.makeText(this, R.string.password_not_null, AppMsg.STYLE_ALERT).show();
      return;
    }
    mPresenter.login(email, password);
  }

  @Override public void loginSuccess() {
    AppMsg.makeText(this, getString(R.string.login_success), AppMsg.STYLE_INFO).show();


  }

  @Override public void loginFail(String errorMessage) {
    AppMsg.makeText(this, errorMessage, AppMsg.STYLE_ALERT).show();

  }
}
