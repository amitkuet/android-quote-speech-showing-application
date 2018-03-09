package app.engine.android.service;

import java.util.List;

import app.engine.android.model.MainCategory;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestService {
    @GET("v2/5aa29cc82f00009e33d46698")
    Observable<List<MainCategory>> getMainCategories();
}
