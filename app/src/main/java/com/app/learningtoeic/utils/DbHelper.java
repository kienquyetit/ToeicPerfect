package com.app.learningtoeic.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.app.learningtoeic.entity.HighScore;
import com.app.learningtoeic.entity.Topic;
import com.app.learningtoeic.entity.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {

    private Context mycontext;
    private String TABLE_NAME = "word";
    private String DB_PATH = "data/data/com.app.learningtoeic/databases/";
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


    public ArrayList<Word> getListWord() {
        opendatabase();
        ArrayList<Word> listWord = new ArrayList<>();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM word", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Word word = new Word(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getInt(9));
            listWord.add(word);
            cursor.moveToNext();
        }
        cursor.close();
        return listWord;
    }

    public void update(Word word) {
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
        if (ret == 0) {

        } else {

        }
    }

    public Topic getTopic(String topicId) {
        opendatabase();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM topic WHERE topicid='" + topicId + "'", null);
        cursor.moveToFirst();
        Topic topic = new Topic(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        return topic;
    }

    public ArrayList<Word> getListWordForTopic(String topicId) {
        opendatabase();
        ArrayList<Word> listWord = new ArrayList<>();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM word WHERE topicid='" + topicId + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Word word = new Word(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getInt(9));
            listWord.add(word);
            cursor.moveToNext();
        }
        cursor.close();
        return listWord;
    }

    public ArrayList<Topic> getListTopic() {
        opendatabase();
        ArrayList<Topic> listTopic = new ArrayList<>();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM topic", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Topic topic = new Topic();
            topic.id = cursor.getInt(0);
            topic.name = cursor.getString(1);
            topic.topicImageName = cursor.getString(2);
            topic.translateVie = cursor.getString(3);
            listTopic.add(topic);
            cursor.moveToNext();
        }
        cursor.close();
        return listTopic;
    }

    public Word getWord(String wordId) {
        opendatabase();
        Word word = new Word();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM word WHERE id='" + wordId + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            word = new Word(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getInt(9));
            cursor.moveToNext();
        }
        cursor.close();
        return word;
    }

    public void saveScore(HighScore highScore) {
        try {
            opendatabase();
            myDataBase.execSQL("INSERT INTO highscore (name_highscore , number_highscore ,time_highscore,number_question) values('" + highScore.getName() + "'," + highScore.getScore() + ",'" + highScore.getTime() + "'," + highScore.getNumberQuestion() + ")");
        } catch (Exception e) {

        }
        Toast.makeText(mycontext, "Done ! ", Toast.LENGTH_SHORT).show();
    }

    public List<HighScore> GetListHighScore() {
        opendatabase();
        List<HighScore> highScores = new ArrayList<>();
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM highscore ORDER BY number_highscore DESC,number_question DESC,time_highscore", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            HighScore item = new HighScore();
            item.setId(cursor.getString(0));
            item.setName(cursor.getString(1));
            item.setScore(cursor.getInt(2));
            item.setTime(cursor.getString(3));
            item.setNumberQuestion(cursor.getInt(4));
            highScores.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return highScores;
    }

    // Read records related to the search term
    public ArrayList<Word> readRecordsSearch(String searchTerm) {
        opendatabase();
        ArrayList<Word> recordsList = new ArrayList<Word>();
        String sql = "SELECT * FROM word WHERE vocabulary LIKE '%" + searchTerm + "%' ORDER BY id DESC LIMIT 0,5";
        Cursor cursor = myDataBase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Word word = new Word(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getInt(9));
            recordsList.add(word);
            cursor.moveToNext();
        }
        cursor.close();
        // return the list of records
        return recordsList;
    }
}
