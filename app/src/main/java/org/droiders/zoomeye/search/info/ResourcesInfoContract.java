package org.droiders.zoomeye.search.info;

import org.zoomeye.api.info.ResourcesInfo;

/**
 * Created by Donglua on 16/4/27.
 */
public interface ResourcesInfoContract {

  interface View {
    void showResourcesInfo(ResourcesInfo resourcesInfo);

    void showErrorMsg(String message);
  }

  interface Prestener {
    void getResourcesInfo();
  }

}
