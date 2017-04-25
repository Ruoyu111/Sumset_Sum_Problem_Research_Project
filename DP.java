public class DP {
    public static boolean subsetDP(int[] A, int sum) {
        // using boolean matrix for DP
        // +1 in row and column
        boolean[][] dp = new boolean[A.length + 1][sum + 1];

        // If the length of the array is variable, and sum is 0, fill True, since sum = 0
        for (int row = 0; row < dp.length; row++) {
            dp[row][0] = true;
        }

        // if the sum is variable and length is 0 then false, since (sum = variable && length = 0)
        for (int column = 1; column < dp[0].length; column++) {
            dp[0][column] = false;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                /*
                check if sum can be obtained by any of the following:
                (a) including the last element
                (b) excluding the last element
                */

                // very important: this is the same as "excluding the last element" which is represented in DP.
                dp[i][j] = dp[i - 1][j];    // the current position[i][j] would be same as previous position.
                                            // the previous position means sum is achieved or not-achieved.

                // very important: this is the same as "including the last element" which is represented in DP.
                // If the column
                if (j >= A[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - A[i - 1]];
                }
            }
        }
        return dp[A.length][sum];
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3};
        int sum = 10;
        boolean b = subsetDP(A, sum);
        System.out.println(b);
    }
}
