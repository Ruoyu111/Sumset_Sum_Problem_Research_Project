import java.util.*;

public class GreedySolver {

    // This algorithm will find a subset that the sum is largetest but still <= target T
    public ArrayList<Long> greedy(long[] S, long target) {
        // sort
        Arrays.sort(S);
        // result arraylist
        ArrayList<Long> A = new ArrayList<>();
        // loop through S in non-increasing order
        for (int i = S.length - 1; i >= 0; i--) {
            if (addNew(A, S[i]) <= target) {
                A.add(S[i]);
            }
        }
        return A;
    }

    // This function will add the arraylist A and the new element
    public long addNew(ArrayList<Long> A, long num) {
        long sum = calSum(A);
        sum += num;
        return sum;
    }

    public long calSum(ArrayList<Long> A) {
        long sum = 0;
        for (long l : A) {
            sum += l;
        }
        return sum;
    }

    // check whether the greedy algorithm finds the optimal solution
    // optimal solution will be that the sum of the subset = target
    public boolean optimalChecker(ArrayList<Long> A, long target) {
        return calSum(A) == target;
    }

    // check the difference between the result of greedy algorithm and the target
    public long calDiff(ArrayList<Long> A, long target) {
        return target - calSum(A);
    }

    public static void main(String[] args) {
        GreedySolver gs = new GreedySolver();
        long[] S = {7, 6, 3, 1};
        long target = 9;
        ArrayList<Long> A = gs.greedy(S, target);
        System.out.println("Find sum is: " + gs.calSum(A));
        System.out.print("[");
        for (long l : A) {
            System.out.print(" " + l);
        }
        System.out.println("]");
    }

}
