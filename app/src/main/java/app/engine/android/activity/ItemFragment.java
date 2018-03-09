package app.engine.android.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.engine.android.AppEngine;
import app.engine.android.R;
import app.engine.android.model.Category;
import app.engine.android.model.Item;
import app.engine.android.model.MainCategory;
import app.engine.android.util.StaticConstants;

public class ItemFragment extends Fragment{

    private Context context;
    private EditProfilePagesActivity editProfilePagesActivity;
    private ListView itemList;
    private String catName = "";
    private List<Item> items = new ArrayList<>();
    public ItemFragment setContext(Context context, EditProfilePagesActivity editProfilePagesActivity, String catName) {
        this.context = context;
        this.editProfilePagesActivity = editProfilePagesActivity;
        this.catName = catName;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.item_fragment, container, false);

        this.init(view);

        List<MainCategory> mainCategories = AppEngine.getInstance().mainCategoryList.getMainCategories();
        for (MainCategory mainCategory: mainCategories) {
            if(mainCategory.getName().equals(StaticConstants.MAIN_CATEGORY)){
                for (Category category: mainCategory.getCategory()) {
                    if(category.getName().equals(catName)){
                        this.items = category.getItem();
                        break;
                    }
                }
            }
        }
        ItemMenuAdapter adapter = new ItemMenuAdapter(view.getContext(), this.items);
        itemList.setAdapter(adapter);

        return view;
    }

    private void init(View view){
        itemList = (ListView) view.findViewById(R.id.itemList);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.editProfilePagesActivity.setNavBarTitle(getResources().getString(R.string.item_page_title));
    }

}
