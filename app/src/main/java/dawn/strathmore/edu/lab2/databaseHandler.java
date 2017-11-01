package dawn.strathmore.edu.lab2;

/**
 * Created by Dawn on 10/31/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class databaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Students";

    private static final String TABLE_STUDENT = " Student";
    private static final String TABLE_IDENTIFICATION = "Identification";
    private static final String TABLE_COURSE = "Course";

    //Student table
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    //Identification table
    private static final String ST_ID = " student_id";
    private static final String ST_NAME = "name";

    //Course table
    private static final String CO_ID = "id";
    private static final String CO_NAME = "name";
    private static final String CO_FACULTY = "faculty";

    public databaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = " CREATE TABLE " + TABLE_STUDENT + "(" + KEY_ID
                + " INTEGER PRIMARY KEY , " + KEY_NAME + " TEXT, " + KEY_PH_NO + " TEXT " + ")";

        String CREATE_IDENTIFICATION_TABLE = " CREATE TABLE " + TABLE_IDENTIFICATION + "(" + ST_ID +
                " INTEGER PRIMARY KEY , " + ST_NAME + " TEXT" + ")";

        String CREATE_COURSE_TABLE = " CREATE TABLE " + TABLE_COURSE + "(" + CO_ID +
                " INTEGER PRIMARY KEY , " + CO_NAME + " TEXT, " + CO_FACULTY + " TEXT " + ")";

        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_IDENTIFICATION_TABLE);
        db.execSQL(CREATE_COURSE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_STUDENT + TABLE_IDENTIFICATION + TABLE_COURSE);
        onCreate(db);
    }

    //STUDENT
    //INSERTING NEW ROWS INTO THE STUDENT
    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName()); //Contact name
        values.put(KEY_PH_NO, student.getPhoneNumber()); // contact phone number

        // Inserting row
        db.insert(TABLE_STUDENT, null, values);
        db.close(); //Closing db connection

    }

    // Getting single contact
    public Student getContacts(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT, new String[]{KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Student student = new Student(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        //return student

        return student;
    }

    //Getting all student
    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<Student>();
        //select a query
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setPhoneNumber(cursor.getString(2));
                //Adding student list
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        //RETURN STUDENT LIST
        return studentList;


    }

    //getting student count
    public int getStudentCount() {
        String countQuery = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //Updating single student
    public int updateCStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_PH_NO, student.getPhoneNumber());

        return db.update(TABLE_STUDENT, values, KEY_ID + "=?",
                new String[]{String.valueOf(student.getId())});

    }

    //Deleting single student
    public int deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_PH_NO, student.getPhoneNumber());

        //updating row

        return db.update(TABLE_STUDENT, values, KEY_ID + " = ?",
                new String[]{String.valueOf(student.getId())});


    }


    //IDENTIFICATION
    //INSERTING A NEW VALUE INTO IDENTIFICATION
    public void addIdentification(Identification identification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ST_NAME, identification.getDname()); //name

        // Inserting row
        db.insert(TABLE_IDENTIFICATION, null, values);
        db.close(); //Closing db connection

    }

    //INSERTING A SINGLE VALUE INTO A IDENTIFICATION
    public Identification getIdentification(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_IDENTIFICATION, new String[]{ST_ID, ST_NAME}, ST_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Identification identification = new Identification(Integer.parseInt(cursor.getString(0)), cursor.getString(1));

        //return student

        return identification;
    }

    //GETTING ALL THE STUDENT
    public List<Identification> getAllIdentification() {
        List<Identification> identificationList = new ArrayList<Identification>();
        //select a query
        String selectQuery = "SELECT * FROM " + TABLE_IDENTIFICATION;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Identification identification = new Identification();
                identification.setDId(Integer.parseInt(cursor.getString(0)));
                identification.setDname(cursor.getString(1));
                //Adding contacts list
                identificationList.add(identification);
            } while (cursor.moveToNext());
        }

        //RETURN STUDENT LIST
        return identificationList;


    }

    //GETTING IDENTIFICATION COUNT
    public int getIdentificationCount() {
        String countQuery = "SELECT * FROM " + TABLE_IDENTIFICATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //UPDATING A SINGLE STUDENT
    public int updateIdentification(Identification identification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ST_NAME, identification.getDname());

        return db.update(TABLE_IDENTIFICATION, values, ST_ID + "=?",
                new String[]{String.valueOf(identification.getDId())});

    }

    //DELETING  A SINGLE STUDENT
    public int deleteIdentification(Identification identification) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ST_NAME, identification.getDname());

        //updating row

        return db.update(TABLE_IDENTIFICATION, values, ST_ID + " = ?",
                new String[]{String.valueOf(identification.getDId())});


    }


    //COURSE

    //INSERTING A NEW VALUE INTO COURSE
    public void addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CO_NAME, course.getname()); //name
        values.put(CO_FACULTY, course.getfaculty());//faculty

        // Inserting row
        db.insert(TABLE_COURSE, null, values);
        db.close(); //Closing db connection

    }

    //INSERTING A SINGLE VALUE INTO COURSE
    public Course getCourse(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COURSE, new String[]{CO_ID, CO_NAME, CO_FACULTY}, CO_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Course course = new Course(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        //return student

        return course;
    }

    //GETTING ALL THE COURSES
    public List<Course> getAllCourse() {
        List<Course> courseList = new ArrayList<Course>();
        //select a query
        String selectQuery = "SELECT * FROM " + TABLE_COURSE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setid(Integer.parseInt(cursor.getString(0)));
                course.setname(cursor.getString(1));
                course.setfaculty(cursor.getString(2));
                //Adding student list
                courseList.add(course);
            } while (cursor.moveToNext());
        }

        //RETURN STUDENT LIST
        return courseList;


    }

    //GETTING COURSE COUNT
    public int getCourseCount() {
        String countQuery = "SELECT * FROM " + TABLE_COURSE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();

    }

    //UPDATING A SINGLE COURSE
    public int updateEmail(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CO_NAME, course.getname());
        values.put(CO_FACULTY, course.getfaculty());

        return db.update(TABLE_COURSE, values, CO_ID + "=?",
                new String[]{String.valueOf(course.getid())});

    }

    //DELETING  A SINGLE COURSE
    public int deleteEmail(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CO_NAME, course.getname());
        values.put(CO_FACULTY, course.getfaculty());

        //updating row

        return db.update(TABLE_COURSE, values, CO_ID + " = ?",
                new String[]{String.valueOf(course.getid())});


    }

}