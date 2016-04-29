package org.droiders.zoomeye;

import android.databinding.BindingAdapter;
import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapText;

/**
 * Created by Donglua on 16/4/25.
 */
public class DataBindingAttributeUtil {

  @BindingAdapter("bootstrapText")
  public static void setBootstrapText(AwesomeTextView textView, String text) {
    textView.setBootstrapText(new BootstrapText.Builder(textView.getContext()).addText(text).build());
  }

}
