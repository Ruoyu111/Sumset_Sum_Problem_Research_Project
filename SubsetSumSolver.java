import java.util.concurrent.TimeUnit;

public class SubsetSumSolver {

    public long iterationCount = 0L;
    public long subsetCount = 0L;

    public ArrayList<Long> recursiveFind(long[] A, long sum) {
        recursiveFind(A, 0L, 0, sum, new long[A.length]);
    }

    private ArrayList<Long> recursiveFind(long[] A, long currSum, int index, long sum, long[] solution) {

        // every time the function run, count as one iteration
        iterationCount++;

        if (currSum == sum) {
            subsetCount++;
            // don't print details of each recursion now
            // System.out.println();
            // System.out.print("Yes. ");
            // System.out.print("Subset Count: " + subsetCount + " ");
            // System.out.print("Recursion Count: " + iterationCount);

            // this time do not print subset
            /**
            System.out.print("{");
            for (int i = 0; i < solution.length; i++) {
                if (solution[i] == 1) {
                    System.out.print(" " + A[i] + " ");
                }
            }
            System.out.print("}");
            */
            //System.out.println();
        } else if (index == A.length) {
            return;
        } else {
            solution[index] = 1; // select the element
            currSum += A[index];
            recursiveFind(A, currSum, index + 1, sum, solution);
            currSum -= A[index];
            solution[index] = 0; // do not select the element
            recursiveFind(A, currSum, index + 1, sum, solution);
        }
        return;
    }

    public static void main(String[] args) {
        // time record start
        final long startTime = System.currentTimeMillis();

        SubsetSumSolver s = new SubsetSumSolver();
        long[] arr = {1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        long sum = 20;
        s.recursiveFind(arr, sum);

        // time record end
        final long endTime   = System.currentTimeMillis();
        // final long totalTime = endTime - startTime;
        final long totalTime = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
        System.out.println();
        System.out.println("Total time used: " + totalTime);
        System.out.println();
    }
}
