import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Student extends Thread {
    private final String name;
    private Teacher teacher;
    private ArrayList<Assignment> assignmentList = new ArrayList<>();
    private ArrayList<Exam> examList = new ArrayList<>();
    private double averageAssignments;
    private double averageGradeModule = -1;
    private static final DecimalFormat df = new DecimalFormat("0.0");   // Decimal Format

//    Constructor
    public Student(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
//        Create assignments and exams. Add them to ArrayLists
        for (int i = 0; i < 4; i++) {   //4 assignments
            if (i < 2) {    //2 exams
                examList.add(new Exam(i + 1));
            }
            assignmentList.add(new Assignment(i + 1));
        }
    }

    @Override
    public void run() {
//        Student thread is doing 4 assignments
        for (int i = 0; i < 4; i++) {
            doAssignment(i);
        }
//        Calculating the average of the assignments
        calcAverageAssignments();
        for (int i = 0; i < 2; i++) {
//            If available to give exam
            if (averageAssignments >= 5) {
                doExam(i);
//                Check if he pass the exam to end the procedure
                if (examList.get(i).successExam()) {
                    calcAverageModule();
                    break;
                }
            }
            averageGradeModule = 0.0;
        }
    }

//    Get student name
    public String getStudentName() {
        return name;
    }

//    Print Student
    public void printStudent() {
        System.out.println("Student: " + getStudentName() + " | Average assignments: " + df.format(averageAssignments) + " | Average module: " + df.format(averageGradeModule));
    }

//    Student do assignment
    public void doAssignment(int number) {
//        Get the assignment
        assignmentList.get(number).finishAssignment();
//        Get random number to sleep the thread
        int randomNum = randomNumber();
        try {
            Thread.sleep(randomNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Print action
        System.out.println("Assignment | Student " + getStudentName() + " has finish assignment: "
                + assignmentList.get(number).getId() + ". Time: " + randomNum);
//        Call the Teacher to add grade to his assignment
        teacher.addGradeToAssignment(number, this);
    }

    public void doExam(int examNumber) {
//        Get the exam
        examList.get(examNumber).finishExam();
//        Get random number to sleep the thread
        int randomNum = randomNumber();
        try {
            Thread.sleep(randomNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //        Print action
        System.out.println("\t\tExam |  Student " + getStudentName() + " has finish exams: " + examList.get(examNumber).getId() + ". Time: " + randomNum);
        //        Call the Teacher to add grade to his exam
        teacher.addGradeToExam(examNumber, this);
    }

//    Calculate of the average of module
    public void calcAverageModule() {
        for (Exam exam : examList) {
            if (exam.successExam()) {
                this.averageGradeModule = this.averageAssignments * 0.3 + exam.getGrade() * 0.7;
                break;
            }
        }
    }

//    Calculate the average of assignments
    public void calcAverageAssignments() {
        double sum = 0;
        for (Assignment a : assignmentList) {
            sum += a.getGrade();
        }
        this.averageAssignments = sum / 4;
    }

//    Get specific assignment
    public Assignment getStudentAssignment(int assignmentNumber) {
        return assignmentList.get(assignmentNumber);
    }

//    Get specific exam
    public Exam getStudentExam(int examNumber) {
        return examList.get(examNumber);
    }

//    Get random number
    private int randomNumber() {
        int min = 3000;
        int max = 5000;
        Random random = new Random();
        return random.nextInt(max - min) + (min);
    }
}
