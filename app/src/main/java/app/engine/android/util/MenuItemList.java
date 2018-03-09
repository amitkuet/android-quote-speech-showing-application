package app.engine.android.util;

import android.content.Context;
import java.util.ArrayList;

import app.engine.android.R;
import app.engine.android.activity.AboutActivity;
import app.engine.android.activity.HomeActivity;
import app.engine.android.activity.SettingActivity;

public class MenuItemList {
    public ArrayList<MenuItem> getMenuList(Context context) {
        ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();

        MenuItem menuItem3 = new MenuItem();
        menuItem3.setTitle("Home");
        menuItem3.setActivityClass(HomeActivity.class);
        menuItem3.setImageResId(R.mipmap.ic_launcher);
        menuItemList.add(menuItem3);

        MenuItem menuItem = new MenuItem();
        menuItem.setTitle("Setting");
        menuItem.setActivityClass(SettingActivity.class);
        menuItem.setImageResId(R.mipmap.ic_launcher);
        menuItemList.add(menuItem);

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setTitle("About");
        menuItem2.setImageResId(R.mipmap.ic_launcher);
        menuItem2.setActivityClass(AboutActivity.class);
        menuItemList.add(menuItem2);

        return menuItemList;
    }
}
