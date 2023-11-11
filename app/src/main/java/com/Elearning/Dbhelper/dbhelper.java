package com.Elearning.Dbhelper;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.Elearning.models.CourseModel;
import com.Elearning.models.InstructorModel;
import com.Elearning.models.QuestionModel;
import com.Elearning.models.QuizModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class dbhelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    byte[] imageInBytes;

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 6;

    private static final String DATABASE_NAME = "Elearning";
    private static final String TABLE_USER = "user";
    private static final String TABLE_SESSION = "session";
    public static final String TABLE_COURSES = "courses";
    private static final String TABLE_ENROLLMENT = "enrollment";
    private static final String TABLE_ASSIGNMENTS = "assignments";
    private static final String TABLE_HISTORY = "history";
    private static final String TABLE_QUIZ = "quiz";
    private static final String TABLE_QUIZ_DETAILS = "quizdetails";
    private static final String TABLE_QUIZ_STUDENT = "quizstudent";
    private static final String TABLE_STUDENT_RESULT = "studentresult";

    //User Table

    private static final String USER_ID = "userid";
    private static final String USER_FULLNAME = "Fullname";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_MOBILE = "mobile";
    private static final String USER_PHOTO = "photo";
    private static final String USER_ADDRESS = "address";
    private static final String USER_ROLE = "role";
    private static final String USER_TIME = "time";

    //TABLE SESSION
    private static final String SESSION_ID = "sessionid";
    private static final String SESSION_USER_ID = "userid";
    private static final String SESSION_STATUS = "status5";
    private static final String SESSION_TIME = "time5";


    // TABLE COURSES

    private static final String COURSES_ID = "coursesid";
    private static final String COURSES_USER_ID = "userid";
    private static final String COURSES_DESCRIPTION = "description";
    private static final String COURSES_NAME = "name";
    private static final String COURSES_PRICE = "price";
    private static final String COURSES_IMAGE = "image";
    private static final String COURSES_TIME = "time1";

    //TABLE ENROLLMENT

    private static final String ENROLLMENT_ID = "enrollmentid";
    private static final String ENROLLMENT_USER_ID = "userid";
    private static final String ENROLLMENT_COURSES_ID = "coursesid";
    private static final String ENROLLMENT_STATUS = "status";
    private static final String ENROLLMENT_TIME = "time6";

    // TABLE QUIZ

    private static final String QUIZ_ID = "quizid";
    private static final String QUIZ_USER_ID = "userid";
    private static final String QUIZ_COURSES_ID = "coursesid";
    private static final String QUIZ_TIME = "time7";


    //TABLE QUIZ DETAILS

    private static final String QUIZ_DETAILS_ID = "quizdetailsid";
    private static final String QUIZ_DETAILS_USER_ID = "userid";
    private static final String QUIZ_DETAILS_COURSES_ID = "coursesid";

    private static final String QUIZ_QUESTION = "question";
    private static final String QUIZ_OPTION_1 = "option1";
    private static final String QUIZ_OPTION_2 = "option2";
    private static final String QUIZ_OPTION_3 = "option3";
    private static final String QUIZ_OPTION_4 = "option4";
    private static final String QUIZ_CORRECT_OPTION = "correctoption";
    private static final String QUIZ_MARKS = "marks";
    private static final String QUIZ_DETAILS_TIME = "time8";

    //TABLE QUIZ STUDENT

    private static final String QUIZ_STUDENT_ID = "quizstudentid";
    private static final String QUIZ_STUDENT_USER_ID = "userid";
    private static final String QUIZ_STUDENT_COURSES_ID = "coursesid";
    private static final String QUIZ_STUDENT_TOTAL_MARKS = "totalmarks";
    private static final String QUIZ_STUDENT_OBTAINED_MARKS = "obtainedmarks";
    private static final String QUIZ_STUDENT_TIME = "time9";

    //TABLE ASSIGNMENTS

    private static final String ASSIGNMENT_ID = "assignmentid";
    private static final String ASSIGNMENT_ENROLLMENT_ID = "userid";
    private static final String ASSIGNMENT_DEADLINE = "deadline";
    private static final String ASSIGNMENT_SCORE = "status";
    private static final String ASSIGNMENT_DESCRIPTION = "description1";
    private static final String ASSIGNMENT_TIME = "time3";
    private static final String ASSIGNMENT_STATUS = "status1";


    //TABLE HISTORY

    private static final String HISTORY_ID = "historyid";
    private static final String HISTORY_ENROLLMENT_ID = "enrollmentid";
    private static final String HISTORY_DATE = "date";
    private static final String HISTORY_MODE = "mode";
    private static final String HISTORY_AMOUNT = "amount";
    private static final String HISTORY_TIME = "time4";


    //studentresult
    private static final String STUDENT_RESULT_ID = "historyid";
    private static final String STUDENT_RESULT_USER_ID = "userid";
    private static final String STUDENT_RESULT_QUIZ_DETAILS_ID = "quizdetailsid";
    private static final String STUDENT_RESULT_TOTAL_MARKS = "totalmarks";
    private static final String STUDENT_RESULT_SELECTED_OPTION = "selectedoption";
    private static final String STUDENT_RESULT_TIME = "time14";
    private static final String STUDENT_RESULT_RESULT = "result";

//create student result
private static final String CREATE_TABLE_STUDENT_RESULT = "CREATE TABLE " + TABLE_STUDENT_RESULT + " (" +
        STUDENT_RESULT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        STUDENT_RESULT_USER_ID + " INTEGER," +
        STUDENT_RESULT_QUIZ_DETAILS_ID + " INTEGER," +
        STUDENT_RESULT_TOTAL_MARKS + " INTEGER," +
        STUDENT_RESULT_SELECTED_OPTION + " TEXT," +
        STUDENT_RESULT_TIME + " DATETIME," +
        STUDENT_RESULT_RESULT + " TEXT" +
        ");";
    // Create User Table
    // Create User Table
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            USER_FULLNAME + " TEXT," +
            USER_EMAIL + " TEXT," +
            USER_PASSWORD + " TEXT," +
            USER_MOBILE + " TEXT," +
            USER_PHOTO + " TEXT," +
            USER_ADDRESS + " TEXT," +
            USER_ROLE + " TEXT," +
            USER_TIME + " DATETIME" +
            ");";
    // Create Session Table
    private static final String CREATE_TABLE_SESSION = "CREATE TABLE " + TABLE_SESSION + " (" +
            SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            SESSION_USER_ID + " INTEGER," +
            SESSION_STATUS + " TEXT," +
            SESSION_TIME + " DATETIME" +
            ");";

    // Create Courses Table
    private static final String CREATE_TABLE_COURSES = "CREATE TABLE " + TABLE_COURSES + " (" +
            COURSES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COURSES_USER_ID + " INTEGER," +
            COURSES_DESCRIPTION + " TEXT," +
            COURSES_NAME + " TEXT," +
            COURSES_PRICE + " TEXT," +
            COURSES_IMAGE + " TEXT," +
            COURSES_TIME + " DATETIME" +
            ");";

    // Create Enrollment Table
    private static final String CREATE_TABLE_ENROLLMENT = "CREATE TABLE " + TABLE_ENROLLMENT + " (" +
            ENROLLMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ENROLLMENT_USER_ID + " INTEGER," +
            ENROLLMENT_COURSES_ID + " INTEGER," +
            ENROLLMENT_STATUS + " TEXT," +
            ENROLLMENT_TIME + " DATETIME" +
            ");";

    // Create Assignments Table
    private static final String CREATE_TABLE_ASSIGNMENTS = "CREATE TABLE " + TABLE_ASSIGNMENTS + " (" +
            ASSIGNMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ASSIGNMENT_ENROLLMENT_ID + " INTEGER," +
            ASSIGNMENT_DEADLINE + " DATETIME," +
            ASSIGNMENT_SCORE + " TEXT," +
            ASSIGNMENT_DESCRIPTION + " TEXT," +
            ASSIGNMENT_TIME + " DATETIME," +
            ASSIGNMENT_STATUS + " TEXT" +
            ");";

    // Create History Table
    private static final String CREATE_TABLE_HISTORY = "CREATE TABLE " + TABLE_HISTORY + " (" +
            HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            HISTORY_ENROLLMENT_ID + " INTEGER," +
            HISTORY_DATE + " DATETIME," +
            HISTORY_MODE + " TEXT," +
            HISTORY_AMOUNT + " TEXT," +
            HISTORY_TIME + " DATETIME" +
            ");";

    // Create Quiz Table
    private static final String CREATE_TABLE_QUIZ = "CREATE TABLE " + TABLE_QUIZ + " (" +
            QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            QUIZ_USER_ID + " INTEGER," +
            QUIZ_COURSES_ID + " INTEGER," +
            QUIZ_TIME + " DATETIME" +
            ");";

    // Create QuizDetails Table
    private static final String CREATE_TABLE_QUIZ_DETAILS = "CREATE TABLE " + TABLE_QUIZ_DETAILS + " (" +
            QUIZ_DETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            QUIZ_DETAILS_USER_ID + " INTEGER," +
            QUIZ_QUESTION + " TEXT," +
            QUIZ_DETAILS_COURSES_ID  + " TEXT, " +
            QUIZ_OPTION_1 + " TEXT," +
            QUIZ_OPTION_2 + " TEXT," +
            QUIZ_OPTION_3 + " TEXT," +
            QUIZ_OPTION_4 + " TEXT," +
            QUIZ_CORRECT_OPTION + " INTEGER," +
            QUIZ_MARKS + " INTEGER," +
            QUIZ_DETAILS_TIME + " DATETIME" +
            ");";

    // Create QuizStudent Table
    private static final String CREATE_TABLE_QUIZ_STUDENT = "CREATE TABLE " + TABLE_QUIZ_STUDENT + " (" +
            QUIZ_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            QUIZ_STUDENT_USER_ID + " INTEGER," +
            QUIZ_STUDENT_COURSES_ID + " INTEGER," +
            QUIZ_STUDENT_TOTAL_MARKS + " INTEGER," +
            QUIZ_STUDENT_OBTAINED_MARKS + " INTEGER," +
            QUIZ_STUDENT_TIME + " DATETIME" +
            ");";


    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("fromdatabse", "hi");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_SESSION);
        db.execSQL(CREATE_TABLE_COURSES);
        db.execSQL(CREATE_TABLE_ENROLLMENT);
        db.execSQL(CREATE_TABLE_ASSIGNMENTS);
        db.execSQL(CREATE_TABLE_HISTORY);
        db.execSQL(CREATE_TABLE_QUIZ);
        db.execSQL(CREATE_TABLE_QUIZ_DETAILS);
        db.execSQL(CREATE_TABLE_QUIZ_STUDENT);
        db.execSQL(CREATE_TABLE_STUDENT_RESULT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENROLLMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_RESULT);

        // Recreate the tables
        onCreate(db);
    }

    public String login(String email, String password,String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String userRole = null;
        String sql = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "' AND role = '" + role + "' ;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            userRole = cursor.getString(cursor.getColumnIndexOrThrow("userid"));
        }

        return userRole;
    }



    public void register(String fullname, String email, String password,String role, String formats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_FULLNAME, fullname);
        values.put(USER_EMAIL, email);
        values.put(USER_PASSWORD, password);
        values.put(USER_ROLE, role);
        values.put(USER_TIME,formats);
        db.insert(TABLE_USER, null, values);
        db.close();
    }


    public long insertCourse(String courseName, Bitmap image, String courseDescription , String coursePrice, String userid,String formats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        ByteArrayOutputStream objectByteOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,20,objectByteOutputStream);
        imageInBytes = objectByteOutputStream.toByteArray();
        values.put("name", courseName);
        values.put("image", imageInBytes);
        values.put("description", courseDescription);
        values.put("price", coursePrice);
        values.put("userid", userid);
        values.put("time1", formats);
        long newRowId = -1;
        try {
            newRowId = db.insert("courses", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return newRowId;
    }


    public List<CourseModel> getCoursesList() {
        String sql = "SELECT * FROM courses";
        SQLiteDatabase db = this.getReadableDatabase();
        List<CourseModel> courses = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("coursesid"));
                String courseName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                byte[]  imageResource = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
               // courses.add(new CourseModel(courseName));
                courses.add(new CourseModel(courseName, imageResource, id));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return courses;
    }


    public List<CourseModel> getcorusedetail(Integer id) {
        String sql = " SELECT * FROM courses WHERE coursesid = '"+ id +"' ;";
        SQLiteDatabase db = this.getReadableDatabase();
        List<CourseModel> courses = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int ids = cursor.getInt(cursor.getColumnIndexOrThrow("coursesid"));
                String courseName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String coursedesc = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                int courseprice = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
                byte[]  imageResource = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
                courses.add(new CourseModel(courseName, ids,coursedesc,courseprice , imageResource));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return courses;
    }

    public List<InstructorModel> getcorlist(String id) {
        String sql = "SELECT * FROM courses WHERE userid = '"+ id +"' ;";
        SQLiteDatabase db = this.getReadableDatabase();
        List<InstructorModel> courses = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int ids = cursor.getInt(cursor.getColumnIndexOrThrow("coursesid"));
                String courseName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                byte[]  imageResource = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
                String courseDes = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String coursePrice = cursor.getString(cursor.getColumnIndexOrThrow("price"));

                courses.add(new InstructorModel(courseName, ids,imageResource,courseDes,coursePrice));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return courses;
    }


    public long insertQuiz(String questionText,String option1, String option2 , String option3, String option4,String correctoption,String marks, String userid,String coursesid,String formats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", questionText);
        values.put("option1", option1);
        values.put("option2", option2);
        values.put("option3", option3);
        values.put("option4", option4);
        values.put("correctoption", correctoption);
        values.put("marks", marks);
        values.put("userid", userid);
        values.put("coursesid", coursesid);
        values.put("time8", formats);

        long newRowId = -1;
        try {
            newRowId = db.insert("quizdetails", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return newRowId;
    }


    public List<QuizModel> getQuizQuestions(String id , String ids) {
        String sql = " SELECT * FROM quizdetails WHERE userid = '"+ id +"' AND coursesid = '"+ ids +"' ;";
        SQLiteDatabase db = this.getReadableDatabase();
        List<QuizModel> quizQuestions = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int questionId = cursor.getInt(cursor.getColumnIndexOrThrow("userid"));
                String questionText = cursor.getString(cursor.getColumnIndexOrThrow("question"));
                String option1 = cursor.getString(cursor.getColumnIndexOrThrow("option1"));
                String option2 = cursor.getString(cursor.getColumnIndexOrThrow("option2"));
                String option3 = cursor.getString(cursor.getColumnIndexOrThrow("option3"));
                String option4 = cursor.getString(cursor.getColumnIndexOrThrow("option4"));
                int correctoption = cursor.getInt(cursor.getColumnIndexOrThrow("correctoption"));

                quizQuestions.add(new QuizModel(questionId, questionText, option1, option2, option3, option4, correctoption));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return quizQuestions;
    }



    public List<QuestionModel> getstudentquestions( int ids) {
        String sql = "SELECT * FROM quizdetails WHERE coursesid = '"+ ids +"' ;";
        SQLiteDatabase db = this.getReadableDatabase();
        List<QuestionModel> quizQuestion = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int questionId = cursor.getInt(cursor.getColumnIndexOrThrow("coursesid"));
                int quizdetailsid = cursor.getInt(cursor.getColumnIndexOrThrow("quizdetailsid"));
                String questionText = cursor.getString(cursor.getColumnIndexOrThrow("question"));
                String option1 = cursor.getString(cursor.getColumnIndexOrThrow("option1"));
                String option2 = cursor.getString(cursor.getColumnIndexOrThrow("option2"));
                String option3 = cursor.getString(cursor.getColumnIndexOrThrow("option3"));
                String option4 = cursor.getString(cursor.getColumnIndexOrThrow("option4"));
                int correctoption = cursor.getInt(cursor.getColumnIndexOrThrow("correctoption"));

                quizQuestion.add(new QuestionModel(questionId, questionText, option1, option2, option3, option4, correctoption,quizdetailsid));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return quizQuestion;
    }

    public long insterenrol( String userid,int coursesid,String status , String formats) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM enrollment  WHERE userid = '"+ userid +"' AND coursesid = '"+ coursesid +"' ;";

        Cursor cursor = db.rawQuery(sql, null);
        long newRowId = -1;
        if(cursor.moveToFirst()){
              newRowId =0;

        }else{
            ContentValues values = new ContentValues();
            values.put("userid", userid);
            values.put("coursesid", coursesid);
            values.put("status", status);
            values.put("time6", formats);
            try {
                newRowId = db.insert("enrollment", null, values);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close();
            }
        }
        return newRowId;
    }



    public long insertsubmitquiz(String userid, int coursesid, String formats) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = " SELECT * FROM quizstudent  WHERE userid = '"+ userid +"' AND coursesid = '"+ coursesid +"' ;";
        Cursor cursor = db.rawQuery(sql, null);
        long newRowId = -1;

        if(cursor.moveToFirst()){
            newRowId =0;

        }else{
            ContentValues values = new ContentValues();
            values.put("userid", userid);
            values.put("coursesid", coursesid);
            values.put("time9", formats);
            try {
                newRowId = db.insert("quizstudent", null, values);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.close();
            }
        }
        return newRowId;
    }


    public List<QuestionModel> checkoption(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT * FROM quizdeatils WHERE quizdetailsid = '"+id+"';";
        Cursor cursor = db.rawQuery(query, null);
       List<QuestionModel> totalQuestions = new ArrayList<>();
        if (cursor.moveToFirst()) {
        String currectoption = cursor.getString(8);
           int marks = cursor.getInt(9);
            totalQuestions.add(new QuestionModel(currectoption,marks));
        }
        db.close();
        return totalQuestions;
    }

    public void insertquizdata(int quezid, String option, String id,String time){
        SQLiteDatabase db = this.getReadableDatabase();
         int totalmarks=0;
        //to check the data
        String qurie = " SELECT * FROM quizdetails WHERE quizdetailsid = '"+quezid+"' ;";
        Log.e("query",qurie);
        Cursor cursors = db.rawQuery(qurie, null);
        if(cursors.moveToFirst()){
            int marks = cursors.getInt(9);
            String correctoption =cursors.getString(8);

            if(correctoption.equals(option) ){

                    totalmarks += marks;


            }

        }

        //to enter the data
        String query = " SELECT * FROM studentresult WHERE quizdetailsid = '"+quezid+"' AND userid ='"+id+"';";
        Cursor cursor = db.rawQuery(query, null);
        ContentValues values = new ContentValues();
        if(cursor.moveToFirst()){
            int rowid = cursor.getInt(0);
            values.put("selectedoption", option);

            values.put("time14", time);
            values.put("totalmarks", totalmarks);
        String whereClause = "historyid = ?";
        String[] whereArgs = {String.valueOf(rowid)};
         db.update("studentresult", values, whereClause, whereArgs);
        }else{

            values.put("userid", id);
            values.put("quizdetailsid", quezid);
            values.put("selectedoption", option);
            values.put("time14", time);
            values.put("totalmarks", totalmarks);
            db.insert("studentresult", null, values);
            db.close();

        }
    }


    public int getSumOfMarksForUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        int sum = 0;

        String query = "SELECT SUM(" + STUDENT_RESULT_TOTAL_MARKS + ") FROM " + TABLE_STUDENT_RESULT;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            sum = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return sum;
    }


    @SuppressLint("Range")
    public String getUserTypeFromDatabase(String userID) {
        String userType = "";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("User", new String[]{"Fullname"}, "userid = ?", new String[]{userID}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userType = cursor.getString(cursor.getColumnIndex("Fullname"));
            }
            cursor.close();
        }

        return userType;
    }




}
