///////////////////////////////////////////////////TWO METHODS BELOW ARE IN STUDENTSUCCESSACTIVITY CLASS//////////////////////
public void newCourse (View view){
        // saving to instructorDB.db
        MyDBHandlerStudent sdbHandler = new MyDBHandlerStudent(this);

        //Check if instructor is already assigned
        String courseName = courseNameTXT.getText().toString();
        Course course = lookupCourseCourseDB(courseName);
        if(course == null) {
            showMessage("Error", "Course does not exist. Admin should create it first.");
        }

        else {

            //CHeck if there is a conflict

                boolean conflict = isAMatch(courseName);

              
                if(conflict == true){
                    showMessage("Error", "Course conflicts with enrolled course");
                    return;
                }

            //Add course to student database
            sdbHandler.addCourse(course);

            //d.addCourse(cc);

            courseNameTXT.setText("");
            courseCodeTXT.setText("");

        }
    }

    public boolean isAMatch(String name){
        //Check if conflict
        MyDBHandlerInstructor iDB = new MyDBHandlerInstructor(this); 
        Course course = iDB.findCourseTwo(name); //Search for course in instructor DB to have access to course details
     String courseMatchName =  iDB.findCourseMatch(course.getHours(), course.getDays()); //Get the name of the course that has a matching hour and day

        if(courseMatchName.equals(name)){ //If they are the same return true 
            return true;
        }
        return false;

    }

//////////////////////////////THIS METHOD IS IN MYDBHANDLER CLASS/////////////////////////////////
 public String findCourseMatch(String hour, String day){
        SQLiteDatabase db = this.getWritableDatabase();

        //run a query to find the product
        //SELECT * FROM TABLE_PRODUCT WHERE COLUMN_PRODUCTNAME = productname
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_HOURS +
                " = \"" + hour + "AND" + " WHERE " + COLUMN_DAYS + " = \""  + day + " \"";
//        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE " + COLUMN_HOURS +
//                " = \"" + hour +  "\"";

        Cursor cursor = db.rawQuery(query,  null);


        //Create an object and get the result
        Course course = new Course();
        if(cursor.moveToFirst()) {
            course.setCourseName(cursor.getString(1));
            course.setCourseCode(cursor.getInt(2));
            course.setCapacity(cursor.getInt(3));
            course.setHours(cursor.getString(4));
            course.setDays(cursor.getString(5));
            course.setDescription(cursor.getString(6));
            course.setInstructor(cursor.getString(7));
            cursor.close();
        }else{
            return null;
        }
        db.close();
        return cursor.getString(1);
    }
