/**
This class is used to run the subsetsum solver on the benchmark and record results.
*/

import java.util.concurrent.TimeUnit;
import java.util.*;

public class AlgorithmTest {
    public static void main(String[] args) {
        // Build BenchmarkGenerator
        BenchmarkGenerator bg =  new BenchmarkGenerator();
        // generator all data
        // bg.generateAllData();

        // generator group 2 data
        bg.groupTwo();

        // Build Algorithm
        // SubsetSumSolver solver = new SubsetSumSolver();
        GreedySolver gs = new GreedySolver();

        // Group 1 data tests
        // for (int i = 0; i < 10; i++) {
        //     // clear iterationCount and subsetCount
        //     solver.iterationCount = 0L;
        //     solver.subsetCount = 0L;
        //     long[] set = bg.groupOneSets[i];
        //     long target = bg.groupOneTargets[i];
        //     System.out.println("\nGroup 1 test A" + (i + 1) + " begin------------------------------------");
        //     // Record time
        //     final long startTime = System.currentTimeMillis();
        //     solver.recursiveFind(set, target);
        //     final long endTime   = System.currentTimeMillis();
        //     final long totalTime = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
        //     System.out.println("\nGroup 1 test A" + (i + 1) + " finished. " + "#Subset: " + solver.subsetCount + " #Recursion: " + solver.iterationCount + " Total time used: " + totalTime);
        //     System.out.println(" ---------------------------------------");
        // }

        // Group 2 data tests
        // greedy algorithm
        // for (int i = 0; i < 25; i++) {
        //     long[] set = bg.groupTwoSets[i];
        //     long target = bg.groupTwoTargets[i];
        //     System.out.println("\nGroup 2 test A" + (i + 1) + " begin------------------------------------");
        //     // Record time
        //     final long startTime = System.currentTimeMillis();
        //     ArrayList<Long> res = gs.greedy(set, target);
        //     boolean opt = gs.optimalChecker(res, target);
        //     final long endTime   = System.currentTimeMillis();
        //     final long totalTime = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
        //     System.out.println("\nGroup 2 test A" + (i + 1) + " finished. " + " Total time used: " + totalTime + "Does the result optimal? --> " + opt);
        //     System.out.println(" ---------------------------------------");
        // }

        // test whether it is possible to let greedy algorithm find optimal solution
        long[] set = new long[]{7, 6, 3, 1};
        long target = 8;
        ArrayList<Long> res = gs.greedy(set, target);
        boolean opt = gs.optimalChecker(res, target);
        System.out.println("optimal? -->" + opt);

    }
}
