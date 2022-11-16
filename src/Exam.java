import java.util.Random;

public class Exam {
    private int id; //No of exam
    private double grade = -1;  // -1 = didn't do exam
    private boolean finishExam = false;

//    Constructor
    public Exam(int id) {
        this.id = id;
    }

//    Get the number of the exam
    public int getId() {
        return id;
    }
//  Get the grade og the exam
    public double getGrade() {
        return grade;
    }
//  Check if the student pass the exam
    public boolean successExam() {
        return this.grade >= 5;
    }

//    Set the exam status as complete
    public void finishExam() {
        this.finishExam = true;
    }

//    Teacher add grade to the exam
    public void correctionExam() {
        this.grade = randomDoubleNumber(0,10);
    }
//  Get random double number
    private static double randomDoubleNumber(int min, int max) {
        Random random = new Random();
        return (random.nextDouble() * (max - min) + min);
    }
}
