package org.droiders.zoomeye.search;

import java.util.List;
import org.zoomeye.api.search.MatchHost;
import org.zoomeye.api.search.MatchWeb;

/**
 * Created by Donglua on 16/4/25.
 */
public interface SearchFragmentContract {

  interface View {

    void showHostMatches(List<MatchHost> matches);
    void showWebMatches(List<MatchWeb> matches);

    void showErrorMessage(String message);
  }

  interface Presenter {
    void search(String query, int page);
  }
}
