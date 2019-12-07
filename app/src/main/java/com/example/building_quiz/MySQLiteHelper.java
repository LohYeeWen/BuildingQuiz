package com.example.building_quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BuildingQuiz.db";
    private static final int DATABASE_VERSION = 6;

    private SQLiteDatabase db;

    public static final String TABLE_QUESTION = "questions";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_OPTION1 = "option1";
    public static final String COLUMN_OPTION2 = "option2";
    public static final String COLUMN_OPTION3 = "option3";
    public static final String COLUMN_OPTION4 = "option4";
    public static final String COLUMN_ANSWER = "answer";

    public static final String TABLE_SCORE = "scores";
    public static final String COLUMN_SCORE_ID = "_id";
    public static final String COLUMN_SCORE = "score";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_QUESTION
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_QUESTION + " TEXT, " +
            COLUMN_IMAGE + " INTEGER, "+
            COLUMN_OPTION1 + " TEXT, " +
            COLUMN_OPTION2 + " TEXT, " +
            COLUMN_OPTION3 + " TEXT, " +
            COLUMN_OPTION4 + " TEXT, " +
            COLUMN_ANSWER + " INTEGER" + ")";

    private static final String CREATE_TABLE_SCORE = "CREATE TABLE " + TABLE_SCORE +
            "(" + COLUMN_SCORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_SCORE + " INTEGER" + ")";

    public void addQuestion(Question question) throws SQLException{
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, question.getQuestion());
        values.put(COLUMN_IMAGE, question.getImage());
        values.put(COLUMN_OPTION1, question.getOption1());
        values.put(COLUMN_OPTION2, question.getOption2());
        values.put(COLUMN_OPTION3, question.getOption3());
        values.put(COLUMN_OPTION4, question.getOption4());
        values.put(COLUMN_ANSWER, question.getAnswer());
        db.insert(TABLE_QUESTION, null, values);
    }

    public void addScore(Score score) throws SQLException{
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, score.getScore());
        db.insert(TABLE_SCORE, null, values);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        this.db = db;
        db.execSQL(DATABASE_CREATE);
        //db.execSQL(CREATE_TABLE_SCORE);
        fillQuestion();
        addScore(new Score(0));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to " +
                        newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        db.execSQL(CREATE_TABLE_SCORE);
        onCreate(db);
    }

    public  void fillQuestion(){
        Question q1 = new Question("This building is located at which country?", R.drawable.acropolis_of_athens,
                "Italy","Greece","France","Czech",2);
        addQuestion(q1);
        Question q2 = new Question("Taj Mahal was built by Mughal emperor Shah Jahan to memory who?", R.drawable.taj_mahal,
                "Kandahari Begum", "Izz un-Nisa Begum", "Shahnawaz Khan", "Mumtaz Mahal", 4);
        addQuestion(q2);
        Question q3 = new Question("Where is Hagia Sophia located at?", R.drawable.hagia_sophia,
                "Istanbul, Turkey", "Kuala Lumpur, Malaysia", "Bursa, Turkey", "Baghdad, Iraq", 1);
        addQuestion(q3);
        Question q4 = new Question("Which pyramid is the largest Egyptian pyramid in Giza?", R.drawable.the_pyramid,
                "Pyramid of Khufu", "Pyramid of Djoser", "Pyramid of Menkaure", "Pyramid of Khafre", 1);
        addQuestion(q4);
        Question q5 = new Question("Where is The Gateway Arch located at?", R.drawable.the_gateway_arch,
                "London, UK", "Seoul, Korea", "St. Louis, Missouri, USA", "Paris, France", 3);
        addQuestion(q5);
        Question q6 = new Question("Musée d'Orsay was originally a: ", R.drawable.museed_orsay,
                "Railway station", "Library", "Church", "Government building",1);
        addQuestion(q6);
        Question q7 = new Question("How tall is The Gherkin is?", R.drawable.the_gherkin,
                "200 metres", "170 metres", "190 metres", "180 metres", 4);
        addQuestion(q7);
        Question q8 = new Question("What is the meaning of Notre-Dame de Paris?", R.drawable.notre_dame_de_pari,
                "Our Gentleman of Paris", "Our Son of Paris", "Our Lady of Paris", "Our Daughter of Paris", 3);
        addQuestion(q8);
        Question q9 = new Question("Where is Westminster Abbey located at?", R.drawable.westminster_abbey,
                "London, UK", "Manchester, UK", "Paris, France", "Birmingham, UK", 1);
        addQuestion(q9);
        Question q10 = new Question("The Colosseum is used to host:", R.drawable.the_colosseum,
                "Execution", "Sport Competition", "Circus", "Gladiatorial Shows", 4);
        addQuestion(q10);
        Question q11 = new Question("Where is The Lotus Temple located at?", R.drawable.the_lotus_temple,
                "India", "China", "Australia", "Malaysia", 1);
        addQuestion(q11);
        Question q12 = new Question("Which of the following options is wrong about Forbidden City?", R.drawable.forbidden_city,
                "It houses the Palace Museum", "It was the former Chinese imperial palace of Ming dynasty", "It was the former Chinese imperial palace of Qing dynasty", "It was the former Chinese imperial palace of Qin dynasty", 4);
        addQuestion(q12);
        Question q13 = new Question("Which of the following options is true about Petronas Twin Towers?", R.drawable.petronas_towers,
                "It is the tallest tower in the world", "It is the tallest twin towers in the world", "It is the 98-floor towers", "It is taller than Taipei 101", 2);
        addQuestion(q13);
        Question q14 = new Question("Which of the following options is wrong about Louvre Museum?", R.drawable.museum_louvre,
                "It is the world's largest art museum", "It is located beside the Eiffel Tower", "It is a historic monument in Paris, France", "It is the world's most visited art museum", 2);
        addQuestion(q14);
        Question q15 = new Question("Which of the following options is wrong about Sydney Opera House?", R.drawable.sydney_opera_house,
                "It is a multi-venue performing arts centre"," It is one of the 20th century's most famous and distinctive buildings","Designed by Australian architectural team headed up by Peter Hall","Designed by Danish architect Jørn Utzon",3);
        addQuestion(q15);
    }

    public ArrayList<Question> getAllQuestion(){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUESTION, null);

        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION)));
                question.setImage(cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(COLUMN_OPTION4)));
                question.setAnswer(cursor.getInt(cursor.getColumnIndex(COLUMN_ANSWER)));
                questionList.add(question);
            }while (cursor.moveToNext());

        }

        cursor.close();
        return questionList;
    }

    public List<Score> getAllScore(){
        List<Score> scoreList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SCORE, null);

        if(cursor.moveToFirst()){
            do{
                Score score = new Score();
                score.setScore(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE)));
                scoreList.add(score);
            }while (cursor.moveToNext());
        }

        cursor.close();
        return  scoreList;
    }

    public Score getHighScore(){
        Score score = new Score();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(" + COLUMN_SCORE + ")" + " FROM " + TABLE_SCORE + ";", null);
        if(cursor.moveToFirst()){
            do{
                score.setScore(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE)));
            }while (cursor.moveToNext());
        }

        cursor.close();
        return score;
    }

}
