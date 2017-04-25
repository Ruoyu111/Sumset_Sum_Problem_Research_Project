import java.util.*;
import java.util.concurrent.*;

public class Initial {
    // random initialization
    // take size of instance n as input parameter
    // uniformly generate a random number from 0 to 2^n-1
    public boolean[] randomInit(long[] S, long target) {
        int n = S.length;
        boolean[] init = new boolean[n];
        double thred = 0.5;
        for (int i = 0; i < n; i++) {
            init[i] = (Math.random() < 0.5);
        }
        if (isValid(init, S, target)) {
            return init;
        } else {
            while (!isValid(init, S, target)) {
                thred = thred / 1.2;
                for (int i = 0; i < n; i++) {
                    init[i] = (Math.random() < thred);
                }
            }
            return init;
        }
    }

    // greedy initialization
    public boolean[] greedyInit(long[] S, long target) {
        GreedySolver gs = new GreedySolver();
        ArrayList<Long> A = gs.greedy(S, target);
        boolean[] init = arrToBits(A, S);
        return init;
    }

    // justify whether the initial solution is a valid solution
    public boolean isValid(boolean[] v, long[] S, long target) {
        long sum = 0;
        for (int i = 0; i < S.length; i++) {
            if (v[i]) {
                sum += S[i];
            }
        }
        return sum <= target;
    }

    // 1. long num --> boolean[] bits
    public boolean[] numToBits(long num, int len) {
        boolean[] ans = new boolean[len];
        for (int i = 0; i < len; i++) {
            ans[len - i - 1] = (num & (1 << i)) != 0;
        }
        return ans;
    }

    // 2. boolean[] bits --> ArrayList<Long> solution
    public ArrayList<Long> bitsToArray(boolean[] bits, long[] S) {
        // int len = bits.length;
        ArrayList<Long> ans = new ArrayList<Long>();
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]) {
                ans.add(S[i]);
            }
        }
        return ans;
    }

    // ************ 3. long num --> ArrayList<Long> solution
    public ArrayList<Long> numToArray(long num, long[] S) {
        int len = S.length;
        boolean[] bits = numToBits(num, len);
        ArrayList<Long> ans = bitsToArray(bits, S);
        return ans;
    }

    // 4. ArrayList<Long> solution --> boolean[] bits
    public boolean[] arrToBits(ArrayList<Long> arr, long[] S) {
        boolean[] bits = new boolean[S.length];
        for (int i = 0; i < S.length; i++) {
            bits[i] = arr.contains(S[i]);
        }
        return bits;
    }

    // 5. boolean[] bits --> long num
    public long bitsToNum(boolean[] bits) {
        long ans = 0;
        int len = bits.length;
        for (int i = 0; i < len; i++) {
            if (bits[i]) {
                ans += Math.pow(2, len - i - 1);
            }
        }
        return ans;
    }

    //--------------------- 6. ArrayList<Long> --> long num
    public long arrToNum(ArrayList<Long> arr, long[] S) {
        boolean[] bits = arrToBits(arr, S);
        long ans = bitsToNum(bits);
        return ans;
    }

    // helper function
    public long calSum(ArrayList<Long> A) {
        long sum = 0;
        for (long l : A) {
            sum += l;
        }
        return sum;
    }

    // unit test
    public static void main(String[] args) {
        Initial ini = new Initial();
        // boolean[] b = ini.numToBits(12, 13);
        // for (boolean i : b) {
        //     System.out.println(i);
        // }
        // boolean[] bits = {true, false, false};
        // long[] S = {2, 3, 4};
        // ArrayList<Long> arr = ini.bitsToArray(bits, S);
        // for (long l : arr) {
        //     System.out.println(l);
        // }
        // long num = 12;
        // long[] S = {1, 2, 3, 4};

        // ArrayList<Long> ans = ini.numToArray(num, S);
        // for (long l : ans) {
        //     System.out.println(l);
        // }
        // long[] S = {2, 3, 4};
        // ArrayList<Long> arr = new ArrayList<Long>(Arrays.asList(2L, 3L, 4L));
        // long num = ini.arrToNum(arr, S);
        // System.out.println(num);
        //
        // boolean[] bits = {true, false, false, false};
        // long num = ini.bitsToNum(bits);
        // System.out.println(num);
        // long[] S = {1, 2, 3, 4};
        // // long v = 12;
        // long target = 7;
        // // long num = ini.randomInit(S, target);
        // long num = ini.greedyInit(S, target);
        // System.out.println(num);

        long[] S = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        long T = 50;
        // boolean[] initial = ini.randomInit(S, T);
        boolean[] initial = ini.greedyInit(S, T);
        for (boolean b : initial) {
            System.out.println(b);
        }
        // long[] S = {1, 2, 3, 4, 5};
        // long T = 4;
        // // boolean[] v = {true, true, false, false};
        // // boolean flag = ini.isValid(v, S, T);
        // // System.out.println(flag);
        // boolean[] in = ini.randomInit(S, T);
        // for (boolean b : in) {
        //     System.out.println(b);
        // }

    }
}
