import java.util.ArrayList;
import java.util.Random;

public class Teacher extends Thread {
    private ArrayList<Student> studentsList = new ArrayList<>();
    private String name;
    private int id;

    private int nextAssignment = 0;

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    private boolean waiting = true;

    public void setAssignment(int assignment) {
        this.nextAssignment = assignment;
        process = true;
    }

    public String getTeacherName() {
        return name;
    }

    public int getTeacherId() {
        return id;
    }

    private boolean process = true;

    public Teacher(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void run() {
        addGradeToAssignment(nextAssignment);
    }

    public void createClassWithStudents(int sizeOfStudents, String[] randomNames) {
        for (int i = 0; i < sizeOfStudents; i++) {
            studentsList.add(new Student(randomNames[randomNumber(0, randomNames.length)], i));
        }
    }

    public void printStudents() {
        System.out.println("=======================================");
        System.out.println("ID: " + getTeacherId() + " | Teacher: " + getTeacherName() + "\n");
        System.out.println("Students list: ");
        for (Student s : studentsList) {
            s.printStudent();
        }
        System.out.println("=======================================");
    }

    public void addAssignment(int assignmentNumber) throws InterruptedException {
        System.out.println("Assignment: " + assignmentNumber);
        if (nextAssignment == 1) {
            for (Student student : studentsList) {
                student.setNextAssignment(assignmentNumber);
                student.start();
            }
//            for (Student student : studentsList) {
//                student.join();
//            }
        } else {
            for (Student student : studentsList) {
                student.setNextAssignment(assignmentNumber);
                student.avtiveStudent();
            }
        }
        System.out.println("continue join");
    }

    public void addGradeToAssignment(int assignmentNumber) {
        for (Student student : studentsList) {
            student.getStudentAssignment(assignmentNumber).correctionAssignment();
            int randomNum = randomNumber(2000, 5000);
            try {
                Thread.sleep(randomNum);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " | Teacher " + getTeacherId() + " add grade to student: " + student.getStudentId() + " for assignment: " + student.getStudentAssignment(assignmentNumber).getId() + " . Time:" + randomNum);
        }
//        System.out.println(getName() + " | Teacher " + getTeacherId() + " finish add grades for " + (assignmentNumber) + " assignment");

    }


    //    Create random number function with min and max value
    private static int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + (min);
    }

}
