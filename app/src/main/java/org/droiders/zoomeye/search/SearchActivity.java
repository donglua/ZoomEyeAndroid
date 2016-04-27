package org.droiders.zoomeye.search;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.devspark.appmsg.AppMsg;
import org.droiders.zoomeye.R;
import org.droiders.zoomeye.ZoomEyeApp;
import org.droiders.zoomeye.databinding.ActivitySearchBinding;
import org.droiders.zoomeye.login.LoginActivity;
import org.droiders.zoomeye.login.LogoutContract;
import org.droiders.zoomeye.login.LogoutModule;
import org.droiders.zoomeye.login.LogoutPresenter;
import org.droiders.zoomeye.search.info.ResourcesInfoContract;
import org.droiders.zoomeye.search.info.ResourcesInfoModule;
import org.droiders.zoomeye.search.info.ResourcesInfoPresenter;
import org.zoomeye.api.info.ResourcesInfo;

/**
 * Created by Donglua on 16/4/26.
 */
public class SearchActivity extends AppCompatActivity implements ResourcesInfoContract.View,
    LogoutContract.View {

  private ResourcesInfoPresenter resourcesInfoPresenter;
  private LogoutPresenter logoutPresenter;
  private ActivitySearchBinding binding;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
    setSupportActionBar(binding.toolbar);

    setTitle(R.string.app_name);

    logoutPresenter = ZoomEyeApp.apiComponent(this) //
        .plus(new LogoutModule(this)) //
        .getPresenter();
    resourcesInfoPresenter = ZoomEyeApp.apiComponent(this) //
        .plus(new ResourcesInfoModule(this)) //
        .getPresenter();

    final TabLayout.Tab hostSearchTab = binding.tabLayout.newTab().setText(R.string.tab_host_search);
    final TabLayout.Tab webSearchTab  = binding.tabLayout.newTab().setText(R.string.tab_web_search);

    binding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    binding.tabLayout.addTab(hostSearchTab);
    binding.tabLayout.addTab(webSearchTab);

  }

  @Override protected void onResume() {
    super.onResume();

    if (resourcesInfoPresenter != null) resourcesInfoPresenter.getResourcesInfo();
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

  @Override protected void onDestroy() {
    super.onDestroy();
    if (isFinishing()) {
      binding.unbind();
    }
  }

  @Override public void logout() {
    this.startActivity(new Intent(this, LoginActivity.class));
    finish();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_logout:
        logoutPresenter.onLogout();
        this.finish();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
