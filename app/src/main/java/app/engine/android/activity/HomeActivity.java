package app.engine.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import java.util.List;

import app.engine.android.AppEngine;
import app.engine.android.R;
import app.engine.android.model.MainCategory;
import app.engine.android.presenter.MainPresenter;
import app.engine.android.view.MainCategoryInterface;

public class HomeActivity extends BaseUIController implements MainCategoryInterface {

    private ListView menuList;
    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.setLayoutType(AppEngine.getInstance().constants.DRAWER_LAYOUT);
        super.onCreate(savedInstanceState);
        this.addLayout(R.layout.activity_edit_profile_menu);
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.getMainCategories();
        this.init();
        if(AppEngine.getInstance().sharedPrefUtils.getPref("THEME_COLOR", this)!=""){
            getWindow().getDecorView().setBackgroundColor(Integer.parseInt(AppEngine.getInstance().sharedPrefUtils.getPref("THEME_COLOR", this)));
        }
    }

    private void init(){
        menuList = (ListView) findViewById(R.id.menuList);
        this.setNavBarTitle(getResources().getString(R.string.edit_profile_nav_title));
    }

    @Override
    public void generateEditProfileMenu(List<MainCategory> mainCategories){
        ProfileMenuAdapter adapter = new ProfileMenuAdapter(this, mainCategories);
        menuList.setAdapter(adapter);
    }
}
