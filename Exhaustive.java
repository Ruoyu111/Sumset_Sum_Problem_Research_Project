import java.util.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;

public class Exhaustive {

    SteepestDescent sd;

    public Exhaustive() {
        super();
        sd = new SteepestDescent();
    }

    public long exhausitiveSolver(long[] A, long sum, long timeLimit) {
        long startTime = System.currentTimeMillis();
        return exhausitiveSolver(startTime, A, 0L, 0, sum, new boolean[A.length], timeLimit);
    }

    private long exhausitiveSolver(long startTime, long[] A, long currSum, int index, long sum, boolean[] solution, long timeLimit) {
        long score = sd.getScore(solution, A, sum);
        // 1 minute time limit
        if ((System.currentTimeMillis() - startTime) <= timeLimit) {
            if (score == 0 || index == A.length) {
                return score;
            } else if (score < 0) {
                score = sum;
                return score;
            } else {
                solution[index] = true; // select the element
                currSum += A[index];
                long score1 = exhausitiveSolver(startTime, A, currSum, index + 1, sum, solution, timeLimit);
                if (score1 == 0) {
                    return score1;
                } else if (score1 > 0 && score1 < score) {
                    score = score1;
                }
                currSum -= A[index];
                solution[index] = false; // do not select the element
                long score2 = exhausitiveSolver(startTime, A, currSum, index + 1, sum, solution, timeLimit);
                if (score2 == 0) {
                    return score2;
                } else if (score2 > 0 && score2 < score) {
                    score = score2;
                }
                // if not found solution, return a current best one
                // long s = (Math.abs(score) < Math.abs(score1)) ? score : score1;
                // s = (Math.abs(s) < Math.abs(score2)) ? s : score2;
                // return s;
                return score;
            }
        }
        return score;
    }

    // helper function
    // calculate sum of all elements in an ArrayList<Long>
    private Long calSum(ArrayList<Long> R) {
        Long sum = 0L;
        for (Long r : R) {
            sum += r;
        }
        return sum;
    }

    // // this function will take three scores and return a best score.
    // // the best score is the minimum non-negtive score
    // public bestScore3(score1, score2, score3) {
    //
    // }

    public static void main(String[] args) {
        // unit test
        // Exhaustive s = new Exhaustive();su -c 'yum provides javac'
        // long[] S = {5, 4, 3};
        // long T = 1;
        // long ans = s.exhausitiveSolver(S, T);
        // System.out.println(ans);

        // build Project5Run instance to use its readfiles function
        Project5Run p5 = new Project5Run();
        Exhaustive s = new Exhaustive();
        for (int i = 1; i <= 110; i++) {
            // startTime
            long st = System.currentTimeMillis();
            Data d = p5.readFiles(i);
            long[] A = d.A;
            long T = d.T;
            // run exhaustive method
            long ans = s.exhausitiveSolver(A, T, 60000L);
            long et = System.currentTimeMillis();
            long duration = et - st;
            System.out.println("A" + i + " result: " + ans + " excution time(milliseconds): " + duration);
        }
    }
}
