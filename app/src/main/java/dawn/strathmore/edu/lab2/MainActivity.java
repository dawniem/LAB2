package dawn.strathmore.edu.lab2;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;





public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler db = new databaseHandler(this);
        Log.d("Insert: ", "Inserting....");
        db.addStudent(new Student("Kevin", "4357898989"));
        db.addStudent(new Student("Adeke", "245467779"));
        db.addStudent(new Student("Dawn", "678738760"));
        db.addStudent(new Student("Kidavi", "455716527"));


        //for the student
        Log.d("Reading: ", "Reading all student.. ");
        List<Student> student = db.getAllStudent();
        for (Student en : student) {
            String log = "Id: " + en.getId() + " ,Name: " + en.getName() + " ,Phone: " + en.getPhoneNumber();
            Log.d("Name:", log);
        }


        //DATA FOR THE COURSE TABLE
        Log.d("Insert: ", "Inserting...");
        db.addCourse(new Course("bbit", "fit"));
        db.addCourse(new Course("bcom", "smc"));
        db.addCourse(new Course("law", "sls"));


        //for the course
        Log.d("Reading: ", "Reading all student.. ");
        List<Course> course = db.getAllCourse();
        for (Course en : course) {
            String log = "Id: " + en.getid() + " ,Name: " + en.getname() + " ,Faculty: " + en.getfaculty();
            Log.d("Name:", log);
        }
        //DATA FOR IDENTIFICATION
        Log.d("Insert: ", "Inserting...");
        db.addIdentification(new Identification("Adeke"));
        db.addIdentification(new Identification("Dawn"));
        db.addIdentification(new Identification("Kidavi"));

        //for the identification
        Log.d("Reading: ", "Reading all student.. ");
        List<Identification> identification = db.getAllIdentification();
        for (Identification en : identification) {
            String log = "Id: " + en.getDId() + " ,DName: " + en.getDname();
            Log.d("DName:", log);
        }
    }
}










