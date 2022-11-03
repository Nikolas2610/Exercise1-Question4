import java.util.ArrayList;

public class Question4 {

    public Question4(int THREADS_COUNT, ArrayList<Teacher> teacherList) throws InterruptedException {

        ProcessThread[] threads = new ProcessThread[THREADS_COUNT];

        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new ProcessThread();
            threads[i].start();
        }

        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i].join();
        }
    }
}
