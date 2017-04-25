import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Project5Run {
    // data class to record A and T
    // private class Data {
    //     long[] A;
    //     Long T;
    // }

    SteepestDescent st;

    public Project5Run() {
        super();
        st = new SteepestDescent();
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

    // record result method
    public void recordResult(int i, Data d, boolean[] result) {
        long score = st.getScore(result, d.A, d.T);
        // System.out.println(score);
        System.out.println("A" + i + "score is: " + score);
    }

    public static void main(String[] args) {
        Project5Run p5 = new Project5Run();
        SteepestDescent st = p5.st;
        Initial ini = st.ini;
        for (int i = 1; i <= 110; i++) {
            // startTime
            long start = System.currentTimeMillis();
            // long startTime = System.currentTimeMillis();
            Data d = p5.readFiles(i);
            long[] A = d.A;
            long T = d.T;
            // random init
            boolean[] initial = ini.randomInit(A, T);
            // boolean[] initial = ini.greedyInit(A, T);
            // steepest descent method
            boolean[] result = st.steepestDescentSolver(A, T, initial);
            // Simulated Annealing
            // boolean[] result = st.simuAn(System.currentTimeMillis(), initial, A, T, 60000L);
            // p5.recordResult(i, d, result);
            long end = System.currentTimeMillis();
            long duration = end - start;
            System.out.println(duration);
        }
    }
}
