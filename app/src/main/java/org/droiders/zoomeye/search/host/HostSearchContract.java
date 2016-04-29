package org.droiders.zoomeye.search.host;

import java.util.List;
import org.zoomeye.api.search.MatchHost;

/**
 * Created by Donglua on 16/4/25.
 */
public interface HostSearchContract {

  interface View {

    void showMatches(List<MatchHost> matches);

    void showErrorMessage(String message);
  }

  interface Presenter {
    void search(String query, int page);
  }
}
