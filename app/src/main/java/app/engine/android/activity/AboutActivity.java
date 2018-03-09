package app.engine.android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.engine.android.AppEngine;
import app.engine.android.R;
import app.engine.android.presenter.MainPresenter;

public class AboutActivity extends BaseUIController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setLayoutType(AppEngine.getInstance().constants.DRAWER_LAYOUT);
        super.onCreate(savedInstanceState);
        this.addLayout(R.layout.activity_about);
        this.setNavBarTitle("About");
    }
}
