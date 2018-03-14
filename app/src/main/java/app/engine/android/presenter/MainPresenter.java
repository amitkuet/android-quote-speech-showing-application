package app.engine.android.presenter;

import android.content.Context;

import java.util.List;

import app.engine.android.AppEngine;
import app.engine.android.database.DatabaseHelper;
import app.engine.android.model.MainCategory;
import app.engine.android.service.RestService;
import app.engine.android.view.MainCategoryInterface;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainPresenter {
    private Context context;
    private MainCategoryInterface mainCategoryInterface;
    private DatabaseHelper databaseHelper;
    public MainPresenter(Context context, MainCategoryInterface mainCategoryInterface){
        this.context = context;
        this.mainCategoryInterface = mainCategoryInterface;
    }

    public void getMainCategories(){
        databaseHelper = new DatabaseHelper(context);
        List<MainCategory> mainCategories = databaseHelper.getAllData();
        System.out.println("MISTY>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + mainCategories.size());
        AppEngine.getInstance().mainCategoryList.setMainCategories(mainCategories);
        mainCategoryInterface.generateEditProfileMenu(mainCategories);
    }
}
