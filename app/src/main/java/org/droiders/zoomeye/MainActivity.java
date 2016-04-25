package org.droiders.zoomeye;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.jakewharton.rxbinding.view.RxView;
import org.droiders.zoomeye.databinding.ActivityMainBinding;
import rx.android.schedulers.AndroidSchedulers;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


    RxView.clicks(binding.buttonLogin)
        .debounce(300, MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(aVoid -> {

        });

  }

}
