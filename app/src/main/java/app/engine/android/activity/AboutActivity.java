package app.engine.android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import app.engine.android.AppEngine;
import app.engine.android.R;
import app.engine.android.presenter.MainPresenter;

public class AboutActivity extends BaseUIController {

    private TextView aboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setLayoutType(AppEngine.getInstance().constants.DRAWER_LAYOUT);
        super.onCreate(savedInstanceState);
        this.addLayout(R.layout.activity_about);
        this.setNavBarTitle("About");

        this.aboutText =findViewById(R.id.aboutText);
        if(AppEngine.getInstance().sharedPrefUtils.getPref("THEME_COLOR", this) != "") {
            this.aboutText.setBackgroundColor(Integer.parseInt(AppEngine.getInstance().sharedPrefUtils.getPref("THEME_COLOR", this)));
        }
    }
}
