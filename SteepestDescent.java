import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

public class SteepestDescent {

    Initial ini;

    public SteepestDescent() {
        super();
        ini = new Initial();
    }

    public boolean[] steepestDescentSolver(long[] A, long sum, boolean[] initial) {
        long startTime = System.currentTimeMillis();
        return stpd(startTime, initial, A, sum);
    }

    // steepest descent
    public boolean[] stpd(long startTime, boolean[] initial, long[] S, long target) {
        // 1 minute limit
        if (System.currentTimeMillis() - startTime <= 60000L) {
            long initialScore = getScore(initial, S, target);
            boolean[] bestNeighbor = findBestNeighbour(startTime, initial, S, target);
            long neighbourScore = getScore(bestNeighbor, S, target);
            if (neighbourScore == 0) {
                return bestNeighbor;
            } else if (neighbourScore > 0 && neighbourScore < initialScore) {
                return stpd(startTime, bestNeighbor, S, target);
            }
            return initial;
        }
        return initial;
    }

    // simulated annealing
    public boolean[] simuAn(long startTime, boolean[] initial, long[] S, long target, long timeLimit) {
        // initial score
        long score = getScore(initial, S, target);
        if (score == 0) return initial;
        while (System.currentTimeMillis() - startTime <= timeLimit) {
            // generate random neighbour
            boolean[] neighbour = findRandomNeighbour(initial, S, target);
            long neighbourScore = getScore(neighbour, S, target);
            if (neighbourScore < 0) {
                continue;
            } else if (neighbourScore == 0) {
                return neighbour;
            } else if (neighbourScore > 0 && neighbourScore < score) {
                // if found better answer, then replace
                score = neighbourScore;
                initial = neighbour;
            } else {
                // if found worse answer, then maybe choose it.
                double num = Math.random();
                // set dk = T
                // the possiblility to choose this solution is: e ^ ((score - neighbourScore) / T)
                double threshold = Math.exp((score - neighbourScore) / target);
                if (num <= threshold) {
                    score = neighbourScore;
                    initial = neighbour;
                }
            }
        }
        return initial;
    }

    public boolean[] findBestNeighbour(long startTime, boolean[] bits, long[] S, long target) {
        boolean[] neighbour = swap(bits, 0);
        long score = getScore(neighbour, S, target);

        // if the neighbor is closer to target, then update neighbor
        for (int i = 1; i < bits.length; i++) {
            if (System.currentTimeMillis() - startTime <= 60000L) {
                boolean[] temp = swap(bits, i);
                long tempScore = getScore(temp, S, target);
                if (tempScore == 0) {
                    return temp;
                } else if (tempScore > 0 && tempScore < score) {
                    // replace
                    neighbour = temp;
                    score = tempScore;
                }
            } else {
                return neighbour;
            }
        }
        return neighbour;
    }

    // find random neighbor
    public boolean[] findRandomNeighbour(boolean[] bits, long[] S, long target) {
        int len = S.length;
        // generate random position to swap the numbers
        int pos = ThreadLocalRandom.current().nextInt(0, len);
        // swap
        boolean[] neighbour = swap(bits, pos);
        return neighbour;
    }

    // switch one number in a boolean[] array
    public boolean[] swap(boolean[] bits, int pos) {
        int len = bits.length;
        boolean[] ans = new boolean[len];
        for (int i = 0; i < len; i++) {
            if (i == pos) {
                ans[i] = (bits[i] == false);
            } else {
                ans[i] = bits[i];
            }
        }
        return ans;
    }

    // get score of one result. result is boolean[], score = target - calSum(Array)
    public long getScore(boolean[] bits, long[] S, long target) {
        long sum = 0;
        for (int i = 0; i < S.length; i++) {
            if (bits[i]) {
                sum += S[i];
            }
        }
        return (target - sum);
    }

    public static void main(String[] args) {
        SteepestDescent st = new SteepestDescent();
        Initial ini = st.ini;
        // // boolean[] res = st.swap(bits, 0);
        // // for (boolean b : res) {
        // //     System.out.println(b);
        // // }
        // long[] S = {1, 2, 3};
        // long T = 100;
        // // long initNum = ini.randomInit(S, T);
        // // boolean[] initial = ini.numToBits(initNum, S.length);
        // boolean[] initial = new boolean[10];
        // initial[0] = true;
        // initial[1] = true;
        // initial[4] = true;
        // long score = st.getScore(initial, S, T);
        // for (boolean b : initial) {
        //     System.out.println(b);
        // }
        //
        // System.out.println("current score: " + score);
        // // System.out.println(score);
        // boolean[] optimal = st.steepestDescentSolver(S, T, initial);
        // for (boolean b : optimal) {
        //     System.out.println(b);
        // }
        // long score2 = st.getScore(optimal, S, T);
        // System.out.println("final score: " + score2);
        long[] S = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        long T = 50;
        boolean[] initial = ini.greedyInit(S, T);
        boolean[] optimal = st.simuAn(System.currentTimeMillis(), initial, S, T, 6000L);
        for (boolean b : optimal) {
            System.out.println(b);
        }
        long score = st.getScore(optimal, S, T);
        System.out.println("final score is: " + score);

        // System.out.println(score);
        // boolean[] bits = {true, true, true};
        // boolean[] neighbour = st.findRandomNeighbour(bits, S, T);
        // for (boolean b : neighbour) {
        //     System.out.println(b);
        // }
    }
}
