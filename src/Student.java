import java.util.ArrayList;
import java.util.Random;

public class Student extends Thread {
    private final String name;
    private final int id;

    public String getStudentName() {
        return name;
    }

    public int getStudentId() {
        return id;
    }

    private ArrayList<Assignment> assignmentList = new ArrayList<>();
    private ArrayList<Exam> examList = new ArrayList<>();
    private double averageAssignments;
    private double averageGradeModule = -1;
    private int nextAssignment = 0;
    private boolean hasAssignment = true;

    private boolean process = true;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                examList.add(new Exam(i + 1));
            }
            assignmentList.add(new Assignment(i + 1));
        }
    }

    @Override
    public void run() {
        doAssignment(nextAssignment);
    }

    public void avtiveStudent() {
        System.out.println(getName() + " : Student " + getStudentId() + " notify");
        notify();
    }

    public void setNextAssignment(int nextAssignment) {
        this.nextAssignment = nextAssignment;
        process = true;
    }

    public boolean isHasAssignment() {
        return hasAssignment;
    }

    public void printStudent() {
        System.out.println("ID: " + getStudentId() + " | Student: " + getStudentName());
    }

    public void doAssignment(int number) {
        hasAssignment = false;
        assignmentList.get(number).finishAssignment();
        int randomNum = randomNumber(2000, 5000);
        try {
            Thread.sleep(randomNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " | Student " + getStudentId() + " has finish assignment: " + assignmentList.get(number).getId() + " .Time: " + randomNum);
    }

    public void doExam() {
        examList.get(nextAssignment - 4).finishExam();
        try {
            Thread.sleep(randomNumber(2000, 5000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calcAverageModule() {
        for (Exam exam : examList) {
            if (exam.successExam()) {
                this.averageGradeModule = this.averageAssignments * 0.3 + exam.getGrade() * 0.7;
            }
        }
    }

    public void calcAverageAssignments() {
        double sum = 0;
        for (Assignment a : assignmentList) {
            sum += a.getGrade();
        }
        this.averageAssignments = sum / 4;
        if (this.averageAssignments >= 5) {
            for (Exam exam : examList) {
                if (exam.examTaken()) {
                    exam.setTakePartToExam(true);
                    break;
                }
            }
        }
    }

    public Assignment getStudentAssignment(int assignmentNumber) {
        return assignmentList.get(assignmentNumber);
    }

    private int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + (min);
    }


}
