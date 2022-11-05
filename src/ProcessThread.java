import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessThread {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;

    private Object lock = new Object();
    private Lock lockNew = new ReentrantLock();

    public void students(ArrayList<Teacher> teacherList) throws InterruptedException {
        int nextAssignment = 0;
//      Run Students
        while (true) {
            synchronized (lock) {
                if (nextAssignment != 0) {
                    System.out.println("\tStudents waiting\n");
                    lock.wait();
                    System.out.println("\tStudents Continue\n");
                }
                startAssignment(teacherList, nextAssignment);
                System.out.println("\tStudents Notify\n");
                lock.notify();
                nextAssignment++;
            }
        }
    }

    public void teachers(ArrayList<Teacher> teacherList) throws InterruptedException {
        int nextAssignment = 0;
//      Run Teachers
        while (true) {
            synchronized (lock) {
                if (nextAssignment == 0) {
                    System.out.println("\tTeachers waiting\n");
                    lock.wait();
                    System.out.println("\tTeachers Continue\n");
                }

                correctAssignment(teacherList, nextAssignment);
                System.out.println("\tTeachers Notify\n");
                nextAssignment++;
            }
        }
    }

    private void correctAssignment(ArrayList<Teacher> teacherList, int nextAssignment) throws InterruptedException {
        System.out.println("\tTeachers");
        System.out.println("-----------------\n");
        if (nextAssignment == 0) {
            for (Teacher t: teacherList) {
                t.start();
            }
        } else {
            for (Teacher t: teacherList) {
                t.addGradeToAssignment(nextAssignment);
            }
        }
        Thread.sleep(15000);
    }

    private void startAssignment(ArrayList<Teacher> teacherList, int nextAssignment) throws InterruptedException {
        System.out.println("\tStudents");
        System.out.println("-----------------\n");
        if (nextAssignment == 0) {
            for (Teacher t: teacherList) {
                for (Student s: t.getStudentsList()) {
                    s.start();
                }
            }
        } else {
            for (Teacher t: teacherList) {
                for (Student s: t.getStudentsList()) {
                    s.doAssignment(nextAssignment);
                }
            }
        }
        Thread.sleep(5000);
    }
}
