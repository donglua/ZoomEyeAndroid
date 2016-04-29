package org.droiders.zoomeye.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import org.droiders.zoomeye.databinding.ItemSearchResultBinding;
import org.zoomeye.api.search.Match;

/**
 * Created by Donglua on 16/4/29.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<DataBoundViewHolder<ItemSearchResultBinding>> {

  private List<Match> mMatches;

  public SearchResultAdapter(List<Match> matches) {
    mMatches = matches;
  }

  @Override public DataBoundViewHolder<ItemSearchResultBinding> onCreateViewHolder(ViewGroup parent,
      int viewType) {
    return new DataBoundViewHolder<>(
        ItemSearchResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(DataBoundViewHolder<ItemSearchResultBinding> holder, int position) {
    final Match match = mMatches.get(position);
    holder.getBinding().setMatch(match);
    holder.getBinding().executePendingBindings();
  }

  @Override public int getItemCount() {
    return mMatches.size();
  }
}
