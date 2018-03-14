package app.engine.android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import app.engine.android.AppEngine;
import app.engine.android.R;
import app.engine.android.model.Category;
import app.engine.android.model.Item;
import app.engine.android.model.MainCategory;
import app.engine.android.util.Constants;
import app.engine.android.util.StaticConstants;

public class ContentActivity extends BaseUIController implements View.OnClickListener{

    private TextView itemName;
    private TextView itemContent;
    private ImageView imageView;
    private ImageView shareIcon;
    private String imageUrl = "";
    private Button btnMinus;
    private Button btnPlus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setLayoutType(AppEngine.getInstance().constants.DRAWER_LAYOUT);
        super.onCreate(savedInstanceState);
        this.addLayout(R.layout.activity_content);
        this.setNavBarTitle(getResources().getString(R.string.content_page_title));

        this.itemContent = findViewById(R.id.itemContent);
        this.itemName = findViewById(R.id.itemName);
        this.imageView = findViewById(R.id.imageView);
        this.shareIcon = findViewById(R.id.shareIcon);

        this.btnMinus = findViewById(R.id.btnMinus);
        this.btnPlus = findViewById(R.id.btnPlus);
        this.btnPlus.setOnClickListener(this);
        this.btnMinus.setOnClickListener(this);


        String item_name = getIntent().getStringExtra("ITEM_NAME");
        List<MainCategory> mainCategories = AppEngine.getInstance().mainCategoryList.getMainCategories();
        for (MainCategory mainCategory: mainCategories) {
            if(mainCategory.getName().equals(StaticConstants.MAIN_CATEGORY)){
                for (Category category: mainCategory.getCategory()) {
                    if(category.getName().equals(StaticConstants.CATEGORY)){
                        for (Item item: category.getItem()){
                            if(item.getName().equals(item_name)){
                                this.itemName.setText(item.getName());
                                this.itemContent.setText(item.getContent());
                                this.imageUrl = item.getImageUrl();
                            }
                        }
                    }
                }
            }
        }

        if(this.imageUrl!=null && !this.imageUrl.isEmpty()) {
            System.out.println("Bou>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + this.imageUrl);
            Picasso.with(this).load(this.imageUrl).into(this.imageView);
        }
        else{
            Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(this.imageView);
        }

        this.shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = itemContent.getText().toString();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, StaticConstants.SHARE_SUBJECT);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.equals(this.btnPlus)){
            int size = Integer.parseInt(AppEngine.getInstance().sharedPrefUtils.getPref("FONT", this)) + 4;
            AppEngine.getInstance().sharedPrefUtils.putPref("FONT", String.valueOf(size), this);
            this.itemContent.setTextSize(size);
        }
        if(view.equals(this.btnMinus)){
            int size = Integer.parseInt(AppEngine.getInstance().sharedPrefUtils.getPref("FONT", this)) - 4;
            AppEngine.getInstance().sharedPrefUtils.putPref("FONT", String.valueOf(size), this);
            this.itemContent.setTextSize(size);
        }

    }
}
