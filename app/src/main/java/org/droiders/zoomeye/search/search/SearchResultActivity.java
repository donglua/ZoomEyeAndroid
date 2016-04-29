package org.droiders.zoomeye.search.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import org.droiders.zoomeye.R;
import org.droiders.zoomeye.databinding.ActivitySearchResultBinding;
import org.droiders.zoomeye.search.host.HostSearchFragment;

/**
 * Created by Donglua on 16/4/28.
 */
public class SearchResultActivity extends AppCompatActivity {

  public final static int TYPE_HOST = 666;
  public final static int TYPE_WEB  = 233;

  public final static String EXTRA_TYPE = "type";
  public final static String EXTRA_QUERY = "query";

  private ActivitySearchResultBinding binding;
  private ActionBar actionBar;

  public static void openActivity(Context context, String query, int type) {
    Intent intent = new Intent(context, SearchResultActivity.class);
    intent.putExtra(EXTRA_TYPE, type);
    intent.putExtra(EXTRA_QUERY, query);
    context.startActivity(intent);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result);
    setSupportActionBar(binding.toolbar);

    actionBar = getSupportActionBar();
    assert actionBar != null;
    actionBar.setDisplayHomeAsUpEnabled(true);

    final int type = getIntent().getIntExtra(EXTRA_TYPE, TYPE_HOST);
    final String query = getIntent().getStringExtra(EXTRA_QUERY);

    actionBar.setTitle(query);

    Fragment fragment;
    switch (type) {
      case TYPE_HOST:
        fragment = HostSearchFragment.newInstance(query);
        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.container, fragment)
            .commit();
        break;
      case TYPE_WEB:

        break;
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
