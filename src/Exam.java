public class Exam {
    private int id; //No of exam
    private double grade = -1;
    private boolean takePartToExam = false;
    private boolean finishExam = false;

    public Exam(int id) {
        this.id = id;
    }

    public boolean examTaken() {
        return grade != -1;
    }

    public double getGrade() {
        return grade;
    }

    public boolean successExam() {
        return this.grade >= 5;
    }

    public void finishExam() {
        this.finishExam = true;
    }

    public void setTakePartToExam(boolean takePartToExam) {
        this.takePartToExam = takePartToExam;
    }
}
