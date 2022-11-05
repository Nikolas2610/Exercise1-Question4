import java.util.Random;

public class Assignment {
    private final int id; //No. of assignment
    private double grade = -1;  // -1 = Assignemnt not start
    private boolean finishAssignemnt = false;

    //    Constructor
    public Assignment(int id) {
        this.id = id;
    }

//    Student set the status of the assignment as complete
    public void finishAssignment() {
        this.finishAssignemnt = true;
    }

//    Teacher add grade to the assignment
    public void correctionAssignment() {
        this.grade = randomDoubleNumber(0,10);
    }

//    Get the number of the  assignment
    public int getId() {
        return id;
    }

//    Get the grade of the assignment
    public double getGrade() {
        return grade;
    }

    //  Get random double number
    private static double randomDoubleNumber(int min, int max) {
        Random random = new Random();
        return (random.nextDouble() * (max - min) + min);
    }
}
