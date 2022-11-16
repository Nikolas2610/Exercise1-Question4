import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Teacher extends Thread {
    private ArrayList<Student> studentsList = new ArrayList<>();
    private String name;
    private static final DecimalFormat df = new DecimalFormat("0.0");   // Decimal Format

//    Constructor
    public Teacher(String name) {
        this.name = name;
    }

//    Get teacher name
    public String getTeacherName() {
        return name;
    }

//    Thread function
    @Override
    public void run() {
//        Run the thread of their student
        for (Student student : studentsList) {
            student.start();
        }
//      Wait students to finish
        for (Student student : studentsList) {
            try {
                student.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    Create class and add the students | Relation: One to Many
    public void createClassWithStudents(int sizeOfStudents) {
        for (int i = 0; i < sizeOfStudents; i++) {
            studentsList.add(new Student("S" + i + " (" + this.getTeacherName() + ")", this));
        }
    }

//    Print students per class
    public void printStudents() {
        System.out.println("=======================================");
        System.out.println("-------------Teacher " + getTeacherName() + "--------------");
        for (Student s : studentsList) {
            s.printStudent();
        }
        System.out.println("=======================================\n");
    }

//    Teacher Thread add grade to the assignment of his student
    public synchronized void addGradeToAssignment(int assignmentNumber, Student student) {
        //        Get exists assignment
        student.getStudentAssignment(assignmentNumber).correctionAssignment();
        int randomNum = randomNumber();// Get random number to wait
        //        Thread is sleeping
        try {
            Thread.sleep(randomNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        Print action
        System.out.println("\tCorrection Assignment | Teacher " + getTeacherName()
                + " add grade to student: " + student.getStudentName() + " for assignment: "
                + student.getStudentAssignment(assignmentNumber).getId()
                + ". Grade:" + df.format(student.getStudentAssignment(assignmentNumber).getGrade())
                + " | Time: " + randomNum);
    }

//    Teacher Thread add grade to the exam of his student
    public synchronized void addGradeToExam(int examNumber, Student student) {
//        Get exists exam
        student.getStudentExam(examNumber).correctionExam();
        int randomNum = randomNumber(); // Get random number to wait
//        Thread is sleeping
        try {
            Thread.sleep(randomNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Print action
        System.out.println("\t\t\tCorrection Exam | Teacher " + getTeacherName() + " add grade to student: " + student.getStudentName() + " for exam: " + student.getStudentExam(examNumber).getId()
                + ". Grade:" + df.format(student.getStudentExam(examNumber).getGrade()) + " | Time: " + randomNum);
    }

    //    Create random number function with min and max value
    private static int randomNumber() {
        int min = 1000;
        int max = 3000;
        Random random = new Random();
        return random.nextInt(max - min) + (min);
    }

}
