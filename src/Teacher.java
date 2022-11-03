import java.util.ArrayList;
import java.util.Random;

public class Teacher extends Thread {
    private ArrayList<Student> studentsList = new ArrayList<>();
    private String name;
    private int id;

    public String getTeacherName() {
        return name;
    }

    public int getTeacherId() {
        return id;
    }

    public Teacher(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 1; i < 3; i++) {
            System.out.println("Again: " + i);
            try {
                addAssignment(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            addGradeToAssignment(i);
            synchronized (this) {
                try {
                    wait();
                    System.out.println("Continue");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Continue2");
        }

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

    private void addAssignment(int assignmentNumber) throws InterruptedException {
        System.out.println("Assignment: " + assignmentNumber);
        for (Student student : studentsList) {
            student.setNextAssignment(assignmentNumber);
            student.start();
        }
        for (Student student : studentsList) {
            student.join();
        }

    }

    private void addGradeToAssignment(int assignmentNumber) {
        System.out.println("Correction Assignments");
        for (Student student : studentsList) {
            student.getStudentAssignment(assignmentNumber).correctionAssignment();
            try {
                Thread.sleep(randomNumber(2000, 5000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " | Teacher " + getTeacherId() + " add grade to student: " + student.getStudentId());
        }
        synchronized (this) {
            System.out.println(getName() + " | Teacher " + getTeacherId() + " finish add grades for " + assignmentNumber + " assignment");
            notify();
        }

    }


    //    Create random number function with min and max value
    private static int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + (min);
    }

}
