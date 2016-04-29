package org.droiders.zoomeye.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import org.droiders.zoomeye.databinding.ItemSearchResultBinding;
import org.zoomeye.api.search.MatchHost;

/**
 * Created by Donglua on 16/4/29.
 */
public class HostSearchResultAdapter extends RecyclerView.Adapter<DataBoundViewHolder<ItemSearchResultBinding>> {

  private List<MatchHost> mMatches;

  public HostSearchResultAdapter(List<MatchHost> matches) {
    mMatches = matches;
  }

  @Override public DataBoundViewHolder<ItemSearchResultBinding> onCreateViewHolder(ViewGroup parent,
      int viewType) {
    return new DataBoundViewHolder<>(
        ItemSearchResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(DataBoundViewHolder<ItemSearchResultBinding> holder, int position) {
    final MatchHost match = mMatches.get(position);
    holder.getBinding().setMatch(match);
    holder.getBinding().executePendingBindings();
  }

  @Override public int getItemCount() {
    return mMatches.size();
  }
}
