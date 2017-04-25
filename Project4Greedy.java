import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Project4Greedy {

    // data class to record A and T
    private class Data {
        long[] A;
        Long T;
    }

    public Data readFiles(int i) {
        String fileName = "A" + i + ".dat";
        long[] A = null;
        Long T = null;

        try {
            File myFile = new File(fileName);
            FileReader fileReader = new FileReader(myFile);

            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            while ((line = reader.readLine()) != null) {
                // make an arraylist for this line
                String[] lineSplit = line.trim().split("\\s+");
                // System.out.println(lineSplit[0]);
                int length = lineSplit.length;
                // find the line which define A
                if (lineSplit[0].equals("set")) {
                    A = new long[length - 3];
                    for (int j = 3; j < length; j++) {
                        if (j != length - 1) {
                            A[j-3] = Long.parseLong(lineSplit[j]);
                        } else {
                            // cut the ";" out
                            String[] temp = lineSplit[j].split(";");
                            String lastElement = temp[0];
                            // System.out.println(lastElement);
                            long last = Long.parseLong(lastElement);
                            A[j-3] = last;
                        }
                    }
                } else if (lineSplit[0].equals("param")) {
                    // System.out.println("rest.");
                    String[] temp = lineSplit[length-1].split(";");
                    String lastElement = temp[0];
                    T = Long.parseLong(lastElement);
                }
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (A != null && A.length != 0 && T != null) {
            Data data = new Data();
            data.A = A;
            data.T = T;
            return data;
        }
        return null;
    }

    public long calSum(ArrayList<Long> A) {
        long sum = 0;
        for (long l : A) {
            sum += l;
        }
        return sum;
    }

    public void recordResult(int i, Data d, ArrayList<Long> result) {
        long sum = calSum(result);
        // method to record data to a file
        // System.out.println("----------------------------------");
        // System.out.println("A" + i + ".dat" + " Results:");
        // System.out.println("Sum of result: " + sum);
        // System.out.println("This result is optimal? " + (d.T == sum));
        // System.out.println("Difference between T and the sum: " + (d.T - sum));
        //
        // System.out.println("T is: " + d.T);
        // System.out.print("result is: ");
        // for (long a : result) {
        //     System.out.print(a + " ");
        // }
        // System.out.println();
        System.out.println(d.T - sum);
        // System.out.println("Objective: " + );
        // System.out.print("A" + i + ".dat" + " result subarray:");
        //
        // System.out.println();
    }

    public static void main(String[] args) {
        // read file names A1.dat, create variables
        Project4Greedy p4 = new Project4Greedy();
        GreedySolver gs = new GreedySolver();
        for (int i = 1; i <= 110; i++) {
            // startTime
            long st = System.currentTimeMillis();
            // int i = 2;
            Data d = p4.readFiles(i);
            long[] A = d.A;
            long T = d.T;
            ArrayList<Long> result = gs.greedy(A, T);
            long et = System.currentTimeMillis();
            long duration = et - st;
            // p4.recordResult(i, d, result)
            System.out.println(duration);
        }
    }
}
