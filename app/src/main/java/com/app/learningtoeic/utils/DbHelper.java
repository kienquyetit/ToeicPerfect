package com.app.learningtoeic.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.entity.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper{

    private Context mycontext;
    private String TABLE_NAME = "mytoeic600";
    private String DB_PATH =  "data/data/com.app.learningtoeic/databases/";
    private static String DB_NAME = "toeic600.db";
    public SQLiteDatabase myDataBase;

    public DbHelper(Context context) throws IOException {
        super(context, DB_NAME, null, 1);
        this.mycontext = context;
        boolean dbexist = checkdatabase();
        if (dbexist) {
            opendatabase();
        } else {
            System.out.println("Database doesn't exist");
            createdatabase();
        }
    }

    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if (dbexist) {
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

    private boolean checkdatabase() {
        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer)) > 0) {
            myoutput.write(buffer, 0, length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
        System.out.println("Database copy done");
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public ArrayList<Word> getListWord(){
        opendatabase();
        ArrayList<Word> listWord = new ArrayList<>();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM mytoeic600", null);
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

    public void update(Word word){
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

        int ret = myDataBase.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(word.getId())});
        if(ret == 0){

        }
        else{

        }
    }

    public Topic getTopic(String topicId){
        opendatabase();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM topic WHERE topicid='"+topicId+"'", null);
        cursor.moveToFirst();
        Topic topic = new Topic(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        return topic;
    }

    public ArrayList<Word> getListWordForTopic(String topicId)
    {
        opendatabase();
        ArrayList<Word> listWord = new ArrayList<>();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM mytoeic600 WHERE topicid='"+topicId+"'", null);
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

    public ArrayList<Topic> getListTopic()
    {
        opendatabase();
        ArrayList<Topic> listTopic = new ArrayList<>();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM topic",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Topic topic = new Topic();
            topic.id = cursor.getInt(0);
            topic.name = cursor.getString(1);
            topic.topicImageName = cursor.getString(2);
            listTopic.add(topic);
            cursor.moveToNext();
        }
        cursor.close();
        return listTopic;
    }
}
