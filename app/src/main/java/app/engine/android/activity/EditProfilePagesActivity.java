package app.engine.android.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import app.engine.android.AppEngine;
import app.engine.android.R;
import app.engine.android.util.StaticConstants;

public class EditProfilePagesActivity extends BaseUIController implements View.OnClickListener {

    private EditProfileViewPagerAdapter editProfileViewPagerAdapter;
    boolean initActivity;
    String mainCategory = "";
    String catName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initActivity = true;
        mainCategory = getIntent().getStringExtra("MAIN_CATEGORY");
        if(mainCategory!=null && !mainCategory.isEmpty())this.selectFrag("MAIN_CATEGORY", mainCategory);

        catName = getIntent().getStringExtra("CAT_NAME");
        if(catName !=null && !catName.isEmpty())this.selectFrag("CATEGORY",catName);

        this.setLayoutType(AppEngine.getInstance().constants.TOPBAR_LAYOUT);
        super.onCreate(savedInstanceState);
        this.addLayout(R.layout.edit_profile_view_pager);
        editProfileViewPagerAdapter = new EditProfileViewPagerAdapter(getSupportFragmentManager());
        if(AppEngine.getInstance().sharedPrefUtils.getPref("THEME_COLOR", this)!=""){
            getWindow().getDecorView().setBackgroundColor(Integer.parseInt(AppEngine.getInstance().sharedPrefUtils.getPref("THEME_COLOR", this)));
        }
    }

    public void selectFrag(String identifier, String name) {
        Fragment fr;
        if(identifier.equals("MAIN_CATEGORY")) {
            StaticConstants.MAIN_CATEGORY = mainCategory;
            fr = new CategoryFragment().setContext(this, this, mainCategory);
        }else if(identifier.equals("CATEGORY")){
            StaticConstants.CATEGORY=catName;
            fr = new ItemFragment().setContext(this, this, catName);
        }
        else {
            fr = new CategoryFragment().setContext(this, this, catName);
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if(this.initActivity) {
            fragmentTransaction.replace(R.id.viewPager, fr);
            this.initActivity = false;
        } else {
            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
            fragmentTransaction.replace(R.id.viewPager, fr).addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        this.onBackPressed();
    }
}
