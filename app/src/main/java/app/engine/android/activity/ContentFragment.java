package app.engine.android.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.engine.android.R;

public class ContentFragment extends Fragment{

    private Context context;
    private EditProfilePagesActivity editProfilePagesActivity;
    private int year, month, day;

    public ContentFragment setContext(Context context, EditProfilePagesActivity editProfilePagesActivity) {
        this.context = context;
        this.editProfilePagesActivity = editProfilePagesActivity;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.category_fragment, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.editProfilePagesActivity.setNavBarTitle(getResources().getString(R.string.category_page_title));
    }

}
