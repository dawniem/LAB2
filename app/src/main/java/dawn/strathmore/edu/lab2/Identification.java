package dawn.strathmore.edu.lab2;


import static android.R.attr.id;

/**
 * Created by Dawn on 10/31/2017.
 */

public class Identification {

        int _student_id;
        String _name;

        //EMPTY CONSTRUCTOR
        public Identification(){

        }

        //first constructor
        public Identification (int student_id, String name) {

            this._student_id = student_id;
            this._name = name;
        }

        //second constructor
        public Identification(String name){
            this. _name = name;
        }

        public int getDId(){
            return this._student_id;
        }

        public void setDId(int _student_id ){this._student_id= _student_id;}

        public String getDname() {return _name;}

        public void setDname(String _name) {this._name = _name;}
    }


