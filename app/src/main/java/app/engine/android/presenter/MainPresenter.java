package app.engine.android.presenter;

import android.content.Context;

import java.util.List;

import app.engine.android.AppEngine;
import app.engine.android.model.MainCategory;
import app.engine.android.service.RestService;
import app.engine.android.view.MainCategoryInterface;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainPresenter {
    private Context context;
    private MainCategoryInterface mainCategoryInterface;

    public MainPresenter(Context context, MainCategoryInterface mainCategoryInterface){
        this.context = context;
        this.mainCategoryInterface = mainCategoryInterface;
    }

    public void getMainCategories(){
        AppEngine.getInstance().networkAdapter.subscriber(AppEngine.getInstance().networkAdapter.create(RestService.class).getMainCategories(), new Observer<List<MainCategory>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }
            @Override
            public void onError(@NonNull Throwable e) {

            }
            @Override
            public void onComplete() {

            }
            @Override
            public void onNext(@NonNull List<MainCategory> mainCategories) {
                AppEngine.getInstance().mainCategoryList.setMainCategories(mainCategories);
                mainCategoryInterface.generateEditProfileMenu(mainCategories);
            }
        });
    }
}
