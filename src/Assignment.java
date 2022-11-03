import java.util.Random;

public class Assignment {
    private final int id; //No. of assignment
    private double grade = -1;  // -1 = Assignemnt not start
    private boolean finishAssignemnt = false;

    public Assignment(int id) {
        this.id = id;
    }

    public boolean assignmentStart() {
        return grade != -1.0;
    }

    public void finishAssignment() {
        this.finishAssignemnt = true;
    }

    public void correctionAssignment() {
        this.grade = randomDoubleNumber(0,10);
    }

    public int getId() {
        return id;
    }

    public double getGrade() {
        return grade;
    }

    private static double randomDoubleNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + (min) * random.nextDouble();
    }
}
