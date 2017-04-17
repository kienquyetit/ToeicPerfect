package com.app.learningtoeic.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.learningtoeic.entity.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper{

    private Context mContext;
    private String VOCABULARY_TABLE_NAME = "mytoeic600";
    private String DB_PATH =  "data/data/com.app.learningtoeic/databases/";
    private static String DB_NAME = "toeic600.db";
    public SQLiteDatabase mSqLiteDatabase;

    public DbHelper(Context context) throws IOException {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
        boolean dbExist = CheckDatabase();
        if (dbExist) {
            OpenDatabase();
        } else {
            System.out.println("Database doesn't exist");
            CreateDatabase();
        }
    }

    public void CreateDatabase() throws IOException {
        boolean dbExist = CheckDatabase();
        if (dbExist) {
            //System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean CheckDatabase() {
        boolean checkDB = false;
        try {
            String path = DB_PATH + DB_NAME;
            File dbFile = new File(path);
            checkDB = dbFile.exists();
        } catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkDB;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream is = mContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String pathToFile = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream os = new FileOutputStream(pathToFile);

        // transfer byte to is to os
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }

        //Close the streams
        os.flush();
        os.close();
        is.close();
        System.out.println("Database copy done");
    }

    public void OpenDatabase() throws SQLException {
        //Open the database
        String path = DB_PATH + DB_NAME;
        mSqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if (mSqLiteDatabase != null) {
            mSqLiteDatabase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * get Data
     * @return
     */
    public ArrayList<Word> getListWord(){
        OpenDatabase();
        ArrayList<Word> listWord = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.rawQuery("SELECT * FROM mytoeic600", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Word word = new Word(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getInt(9));
            listWord.add(word);
            Log.d("DbHelper", word.getId() + "");
            cursor.moveToNext();
        }
        cursor.close();
        return listWord;
    }

    /**
     * update
     */
    public void updateWord(Word word){
        ContentValues values = new ContentValues();
        values.put("topicid", word.getTopic());
        values.put("id_temp", word.getId_temp());
        values.put("vocabulary", word.getVocabulary());
        values.put("vocalization", word.getVocalization());
        values.put("explanation", word.getExplanation());
        values.put("translate", word.getTranslate());
        values.put("example", word.getExample());
        values.put("example_translate", word.getExampleTranslate());
        values.put("favourite", word.getFavourite());

        update(values, word.getId());
    }

    public void update(ContentValues values, int id){
        int ret = mSqLiteDatabase.update(VOCABULARY_TABLE_NAME, values, "id=?", new String[]{String.valueOf(id)});
        if(ret == 0){

        }
        else{

        }
    }
}
