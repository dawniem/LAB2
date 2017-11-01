package dawn.strathmore.edu.lab2;

/**
 * Created by Dawn on 10/31/2017.
 */

public class Course {

        int _id;
        String _name;
        String _faculty;

        //empty constructor
        public Course(){}

        //First constructor
        public Course (int id, String name, String faculty) {

            this._id = id;
            this._name = name;
            this._faculty = faculty;
        }

        //second constructor
        public Course(String name, String faculty){
            this. _name = name;
            this. _faculty= faculty;
        }


        public int getid() {return _id;}

        public void setid(int _id) {this._id = _id;}

        public String getname() {return _name;}

        public void setname(String _name) {this._name = _name;}

        public String getfaculty() {return _faculty;}

        public void setfaculty(String _faculty) {this._faculty = _faculty;}
    }


