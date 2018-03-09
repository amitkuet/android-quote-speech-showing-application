package app.engine.android.activity;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.engine.android.AppEngine;
import app.engine.android.R;
import app.engine.android.model.Category;
import app.engine.android.model.MainCategory;

public class CategoryFragment extends Fragment{

    private Context context;
    private EditProfilePagesActivity editProfilePagesActivity;
    private ListView categoryList;
    private String mainCategory = "";
    private List<Category> categories = new ArrayList<>();

    public CategoryFragment setContext(Context context, EditProfilePagesActivity editProfilePagesActivity, String mainCategory) {
        this.context = context;
        this.editProfilePagesActivity = editProfilePagesActivity;
        this.mainCategory = mainCategory;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.category_fragment, container, false);
        this.init(view);
        List<MainCategory> mainCategories = AppEngine.getInstance().mainCategoryList.getMainCategories();
        for (MainCategory mainCategory: mainCategories) {
            if(mainCategory.getName().equals(this.mainCategory)){
                this.categories = mainCategory.getCategory();
            }
        }
        CategoryMenuAdapter adapter = new CategoryMenuAdapter(view.getContext(),this.categories);
        categoryList.setAdapter(adapter);

        return view;
    }

    private void init(View view){
        categoryList = (ListView) view.findViewById(R.id.categoryList);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.editProfilePagesActivity.setNavBarTitle(getResources().getString(R.string.category_page_title));
    }

}
