package org.droiders.zoomeye.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import org.droiders.zoomeye.databinding.ItemWebSearchResultBinding;
import org.zoomeye.api.search.MatchWeb;

/**
 * Created by Donglua on 16/4/29.
 */
public class WebSearchResultAdapter extends RecyclerView.Adapter<DataBoundViewHolder<ItemWebSearchResultBinding>> {

  private List<MatchWeb> mMatches;

  public WebSearchResultAdapter(List<MatchWeb> matches) {
    mMatches = matches;
  }

  @Override public DataBoundViewHolder<ItemWebSearchResultBinding> onCreateViewHolder(ViewGroup parent,
      int viewType) {
    return new DataBoundViewHolder<>(
        ItemWebSearchResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(DataBoundViewHolder<ItemWebSearchResultBinding> holder, int position) {
    final MatchWeb match = mMatches.get(position);
    holder.getBinding().setMatch(match);
    holder.getBinding().executePendingBindings();
  }

  @Override public int getItemCount() {
    return mMatches.size();
  }
}
