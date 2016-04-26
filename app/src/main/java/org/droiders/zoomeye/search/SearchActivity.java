package org.droiders.zoomeye.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import com.devspark.appmsg.AppMsg;
import javax.inject.Inject;
import org.droiders.zoomeye.R;
import org.droiders.zoomeye.databinding.ActivitySearchBinding;
import org.droiders.zoomeye.di.AppModule;
import org.droiders.zoomeye.search.info.DaggerResourcesInfoComponent;
import org.droiders.zoomeye.search.info.ResourcesInfoComponent;
import org.droiders.zoomeye.search.info.ResourcesInfoContract;
import org.droiders.zoomeye.search.info.ResourcesInfoModule;
import org.droiders.zoomeye.search.info.ResourcesInfoPresenter;
import org.zoomeye.api.info.ResourcesInfo;

/**
 * Created by Donglua on 16/4/26.
 */
public class SearchActivity extends AppCompatActivity implements ResourcesInfoContract.View {

  @Inject ResourcesInfoPresenter mPresenter;
  ActivitySearchBinding binding;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
    setSupportActionBar(binding.toolbar);

    setTitle(R.string.app_name);

    ResourcesInfoComponent component = DaggerResourcesInfoComponent.builder().appModule(new AppModule(this))
        .resourcesInfoModule(new ResourcesInfoModule(this))
        .build();
    component.inject(this);

    final TabLayout.Tab hostSearchTab = binding.tabLayout.newTab().setText(R.string.tab_host_search);
    final TabLayout.Tab webSearchTab  = binding.tabLayout.newTab().setText(R.string.tab_web_search);

    binding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    binding.tabLayout.addTab(hostSearchTab);
    binding.tabLayout.addTab(webSearchTab);

  }

  @Override protected void onResume() {
    super.onResume();

    mPresenter.getResourcesInfo();
  }

  @Override public void showResourcesInfo(ResourcesInfo resourcesInfo) {
    binding.setInfo(resourcesInfo);
  }

  @Override public void showErrorMsg(String message) {
    AppMsg.makeText(this, message, AppMsg.STYLE_ALERT).show();
  }

  @Override public void showErrorMsg(@StringRes int stringResId) {
    showErrorMsg(getString(stringResId));
  }
}
