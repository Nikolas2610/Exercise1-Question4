import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.exit;

public class Main {

    private static String[] randomNames =  new String[] { "Adam", "Alex", "Aaron", "Ben", "Carl", "Dan", "David", "Edward", "Fred",
            "Frank", "George", "Hal", "Hank", "Ike", "John", "Jack", "Joe", "Larry", "Monte", "Matthew",
            "Mark", "Nathan", "Otto", "Paul", "Peter", "Roger", "Roger", "Steve", "Thomas", "Tim", "Ty", "Victor", "Walter"};

    public static void main(String[] args) throws InterruptedException {

        ArrayList<Teacher> teacherList = new ArrayList<>();

//        Exercise
        int numberOfStudents = 6;
        int numberOfTeachers = 2;
        int batch = numberOfStudents / numberOfTeachers;

//        Create Teacher
        for (int i = 0; i < numberOfTeachers; i++) {
            teacherList.add(new Teacher(randomNames[randomNumber(0, randomNames.length)], i));
        }

//        Create Students
        for (int i = 0; i < numberOfTeachers; i++) {
            teacherList.get(i).createClassWithStudents(batch, randomNames);
            teacherList.get(i).printStudents();
        }

        for (Teacher t: teacherList) {
            t.start();
        }

        for (Teacher t: teacherList) {
            t.join();
        }

//        Print
//        for (int i = 0; i < numberOfTeachers; i++) {
//            teacherList.get(i).printStudents();
//        }



    }

    //    Create random number function with min and max value
    private static int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + (min);
    }
}