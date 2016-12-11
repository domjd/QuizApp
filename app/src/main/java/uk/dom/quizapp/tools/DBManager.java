package uk.dom.quizapp.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import uk.dom.quizapp.models.Category;

/**
 * Created by Dom on 10/12/2016.
 */
public class DBManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "quiz.db";

    public static final String TABLE_CATEGORIES = "categories";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CATEGORY_NAME = "cat_name";
    public static final String COLUMN_CATEGORY_ID = "cat_id";
    public static final String COLUMN_CORRECT_ANSWERS = "cat_correct";
    public static final String COLUMN_WRONG_ANSWERS = "cat_wrong";
    public static final String COLUMN_CATEGORY_ISLOCKED = "cat_is_locked";
    public static final String COLUMN_CATEGORY_IMG_RESOURCE = "cat_img";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CATEGORIES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_CATEGORY_NAME + " TEXT ," +
                COLUMN_CATEGORY_ID + " INTEGER ," +
                COLUMN_CORRECT_ANSWERS + " INTEGER ," +
                COLUMN_WRONG_ANSWERS + " INTEGER ," +
                COLUMN_CATEGORY_IMG_RESOURCE + " INTEGER ," +
                COLUMN_CATEGORY_ISLOCKED + " INTEGER" +
                ");";

        db.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        onCreate(db);
    }

    public void addCategory(Category c){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, c.getName());
        values.put(COLUMN_CATEGORY_ID, c.getId());
        values.put(COLUMN_CORRECT_ANSWERS, c.getCorrectAnswerTotal());
        values.put(COLUMN_WRONG_ANSWERS, c.getWrongAnswerTotal());
        values.put(COLUMN_CATEGORY_IMG_RESOURCE, c.getImage());

        if(c.isLocked() == true){
            values.put(COLUMN_CATEGORY_ISLOCKED, 1);
        }

        else{
            values.put(COLUMN_CATEGORY_ISLOCKED, 0);
        }

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CATEGORIES, null, values);
        db.close();
    }

    public void removeCategory(Category t){
        SQLiteDatabase db = getWritableDatabase();
        String selection = COLUMN_CATEGORY_ID + "= ?";
        String[] selectionArgs = { String.valueOf(t.getId()) };

        db.delete(TABLE_CATEGORIES, selection, selectionArgs);
        db.close();
    }

    public Category returnCategory(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_CATEGORIES,
                new String[]{COLUMN_ID, COLUMN_CATEGORY_ID, COLUMN_CATEGORY_NAME,
                        COLUMN_CORRECT_ANSWERS, COLUMN_WRONG_ANSWERS,
                        COLUMN_CATEGORY_ISLOCKED, COLUMN_CATEGORY_IMG_RESOURCE},
                null, null, null, null, null);

        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String categoryName = c.getString(c.getColumnIndex("cat_name"));
                    int categoryID = c.getInt(c.getColumnIndex("cat_id"));
                    int correctAnswers = c.getInt(c.getColumnIndex("cat_correct"));
                    int wrongAnswers = c.getInt(c.getColumnIndex("cat_wrong"));
                    int isLocked = c.getInt(c.getColumnIndex("cat_is_locked"));
                    int imageResource = c.getInt(c.getColumnIndex("cat_img"));

                    if(categoryID == id){
                        Category category = new Category();
                        category.setName(categoryName);
                        category.setId(categoryID);
                        category.setCorrectAnswerTotal(correctAnswers);
                        category.setWrongAnswerTotal(wrongAnswers);


                    if(isLocked == 0)
                        category.setLocked(false);
                    else
                        category.setLocked(true);

                        category.setImage(imageResource);

                        return category;
                    }

                }while (c.moveToNext());
            }
        }
        db.close();
        return null;

    }

    public List<Category> returnCategories(){
        SQLiteDatabase db = getReadableDatabase();
        List<Category> categoryList = new ArrayList<>();
        Cursor c = db.query(TABLE_CATEGORIES,
                new String[]{COLUMN_ID, COLUMN_CATEGORY_ID, COLUMN_CATEGORY_NAME,
                        COLUMN_CORRECT_ANSWERS, COLUMN_WRONG_ANSWERS,
                        COLUMN_CATEGORY_ISLOCKED, COLUMN_CATEGORY_IMG_RESOURCE},
                null, null, null, null, null);

        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String categoryName = c.getString(c.getColumnIndex("cat_name"));
                    int categoryID = c.getInt(c.getColumnIndex("cat_id"));
                    int correctAnswers = c.getInt(c.getColumnIndex("cat_correct"));
                    int wrongAnswers = c.getInt(c.getColumnIndex("cat_wrong"));
                    int isLocked = c.getInt(c.getColumnIndex("cat_is_locked"));
                    int imageResource = c.getInt(c.getColumnIndex("cat_img"));

                    Category category = new Category();
                    category.setName(categoryName);
                    category.setId(categoryID);
                    category.setCorrectAnswerTotal(correctAnswers);
                    category.setWrongAnswerTotal(wrongAnswers);

                    if(isLocked == 0)
                        category.setLocked(false);
                    else
                        category.setLocked(true);

                    category.setImage(imageResource);

                    categoryList.add(category);
                }while (c.moveToNext());
            }
        }
        c.close();
        db.close();
        return categoryList;
    }

/*    public void changeTaskStatus(Task t){
        Log.v("Before: ",String.valueOf(t.getDone()));
        t.setDone(!t.getDone());

        ContentValues values = new ContentValues();
        values.put(COLUMN_TASKNAME, t.getTaskName());
        values.put(COLUMN_TASKISDONE, t.getDone());

        String selection = COLUMN_ID + "= ?";
        String[] selectionArgs = {String.valueOf(t.getTaskID())};

        SQLiteDatabase db = getReadableDatabase();

        db.update(TABLE_TASKS,
                values,
                selection,
                selectionArgs);
        db.close();
        Log.v("After: ",String.valueOf(t.getDone()));
    }*/
}
