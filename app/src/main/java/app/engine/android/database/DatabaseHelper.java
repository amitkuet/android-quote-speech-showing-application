package app.engine.android.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import app.engine.android.model.Category;
import app.engine.android.model.Item;
import app.engine.android.model.MainCategory;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "quote";
    public static final String TABLE_MAIN_CATEGORY = "mainCategory";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_ITEM = "item";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_MAIN_CATEGORY +" (id INTEGER PRIMARY KEY, main_category TEXT)");
        db.execSQL("Create table " + TABLE_CATEGORY + " (id INTEGER PRIMARY KEY, category TEXT, mainCategory_id INTEGER," +
                "FOREIGN KEY(mainCategory_id) REFERENCES mainCategory(id))");
        db.execSQL("Create table " + TABLE_ITEM + " (id INTEGER PRIMARY KEY, item TEXT, content TEXT, imageUrl TEXT,category_id INTEGER," +
                "FOREIGN KEY(category_id) REFERENCES category(id))");


        //Main Category Data
        db.execSQL("INSERT INTO " + TABLE_MAIN_CATEGORY + "  VALUES (1, 'Quote')");
        db.execSQL("INSERT INTO " + TABLE_MAIN_CATEGORY + "  VALUES (2, 'Speech')");
        db.execSQL("INSERT INTO " + TABLE_MAIN_CATEGORY + "  VALUES (3, 'Written Speech')");

        //Category data
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "  VALUES (1, 'Q-Cat1', 1)");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "  VALUES (2, 'Q-Cat2', 1)");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "  VALUES (3, 'Q-Cat3', 1)");

        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "  VALUES (4, 'S-Cat1', 2)");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "  VALUES (5, 'S-Cat2', 2)");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "  VALUES (6, 'S-Cat3', 2)");

        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "  VALUES (7, 'WS-Cat1', 3)");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "  VALUES (8, 'WS-Cat2', 3)");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + "  VALUES (9, 'WS-Cat3', 3)");

        //Item data
        String [] categoryArr = {"Q-Cat1", "Q-Cat2", "Q-Cat3", "S-Cat1", "S-Cat2", "S-Cat3", "WS-Cat1",
                "WS-Cat2","WS-Cat3"};

        String [] itemArr = new String[500];
        int itemIndex = 0;
        for(int i=0; i<9; i++){
            int id = 1;
            for(int j=0; j<3; j++){
                String itemName = categoryArr[i] + "-item" + (j+1);
                String content = categoryArr[i] + "-item" + (j+1) + "-content" + (j+1);
                itemArr[itemIndex] = itemName;
                db.execSQL("INSERT INTO " + TABLE_ITEM + " VALUES ("+itemIndex+", '"+itemName+"', '"+content+"' , 'http://i.imgur.com/DvpvklR.png', "+(i+1)+")");
                itemIndex++;
            }
            id++;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAIN_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        onCreate(db);
    }

    public List<MainCategory> getAllData() {
        List<MainCategory> mainCategories = new ArrayList<MainCategory>();
        List<Category> categories = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        // Select All Query
        String selectQueryForMainCategory = "SELECT  * FROM " + TABLE_MAIN_CATEGORY;
        System.out.println(selectQueryForMainCategory);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorForMainCategory = db.rawQuery(selectQueryForMainCategory, null);

        // looping through all rows and adding to list
        if (cursorForMainCategory.moveToFirst()) {
            do {
                categories = new ArrayList<>();

                MainCategory mainCategory = new MainCategory();
                mainCategory.setName(cursorForMainCategory.getString(1));

                String selectQueryForCategories = "SELECT * FROM " + TABLE_CATEGORY + " WHERE mainCategory_id="+Integer.parseInt(cursorForMainCategory.getString(0))+"";
                System.out.println(selectQueryForCategories);
                Cursor cursorForCategories = db.rawQuery(selectQueryForCategories, null);
                if(cursorForCategories.moveToFirst()){
                    do{
                        items = new ArrayList<>();
                        Category category = new Category();
                        category.setName(cursorForCategories.getString(1));
                        String selectQueryForItems = "SELECT * FROM " + TABLE_ITEM + " WHERE category_id="+Integer.parseInt(cursorForCategories.getString(0))+"";
                        System.out.println(selectQueryForItems);
                        Cursor cursorForItems = db.rawQuery(selectQueryForItems, null);
                        if(cursorForItems.moveToFirst()){
                            do{
                                Item item = new Item();
                                item.setName(cursorForItems.getString(1));
                                item.setContent(cursorForItems.getString(2));
                                item.setImageUrl(cursorForItems.getString(3));
                                items.add(item);
                            }while (cursorForItems.moveToNext());
                            category.setItem(items);
                            categories.add(category);
                        }
                    }while (cursorForCategories.moveToNext());
                }
                mainCategory.setCategory(categories);
                mainCategories.add(mainCategory);
            } while (cursorForMainCategory.moveToNext());
        }

        // return contact list
        return mainCategories;
    }

}
