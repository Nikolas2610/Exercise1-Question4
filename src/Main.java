import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        Create Teacher List
        ArrayList<Teacher> teacherList = new ArrayList<>();

//        Exercise
        int numberOfStudents = randomNumber(4, 200);     // Random number for students
        int numberOfTeachers = randomNumber(1, (numberOfStudents / 2) );   //Random number for teachers smaller from the students number

        int classCount = numberOfTeachers;
        System.out.println("Teachers: " + numberOfTeachers + " , Students: " + numberOfStudents);
        System.out.println("+++++++++++++++++++++++++++++++++++++\n");
//        Create Teachers
        for (int i = 0; i < numberOfTeachers; i++) {
            teacherList.add(new Teacher(("T" + i+1)));
        }

//        Create Student class and add students
        for (int i = 0; i < classCount; i++) {
//            Get upper round number of the division
            int size = (int) Math.ceil((double) numberOfStudents / numberOfTeachers);
            teacherList.get(i).createClassWithStudents(size);
            numberOfStudents -= size;   // Remove the added students
            numberOfTeachers--; //Decrease count for the new division

        }
//        Get start time of  the app
        long start = System.currentTimeMillis();
//        Start the threads for the teachers
        for (Teacher t : teacherList) {
            t.start();
        }
//      Wait the App to finish
        for (Teacher t : teacherList) {
            t.join();
        }
//        Get the end time of the app
        long end = System.currentTimeMillis();
        System.out.println("\n\n--------FINAL----------");
//      Print the students with their grades
        for (Teacher t : teacherList) {
            t.printStudents();
        }

//        Print the duration of the app
        System.out.println("--------DURATION OF APP----------");
        System.out.println("Duration is " + (end - start) + "msec");
        System.out.println("---------------------------------");
    }

    //    Create random number function with min and max value
    private static int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + (min);
    }
}