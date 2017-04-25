import java.util.*;
import java.io.*;

public class BenchmarkGenerator {

    // group 1 data
    public long[][] groupOneSets;
    public long[] groupOneTargets;
    // group 2 data
    public long[][] groupTwoSets;
    public long[] groupTwoTargets;
    // 3
    public long[][] groupThreeSets;
    public long[] groupThreeTargets;
    // 4
    public long[][] groupFourSets;
    public long[] groupFourTargets;
    // 5
    public long[][] groupFiveSets;
    public long[] groupFiveTargets;

    // method to generate benchmark instance data files, for ILP. such as A1.dat, A2.dat ...
    // input is 1. an array of numbers, 2. Target, 3. filename
    public void generateDataFile(long[] A, long T, String filename) {
        // create a bufferedwriter around a filewriter
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            // generate a string for A
            StringBuilder sb = new StringBuilder("set A :=");
            for (long a : A) {
                sb.append(" " + a);
            }
            sb.append(";");

            writer.write("data;");
            writer.newLine();
            writer.newLine();
            writer.write(sb.toString());
            writer.newLine();
            writer.write("param T := " + T + ";");
            writer.newLine();
            writer.newLine();
            writer.write("end;");
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
            System.out.println("ERROR: could not write data out.");
            ex.printStackTrace();
        }
    }

    // generate group one instance data files
    public void groupOneDataFiles() {
        // number of instances
        int numIns = groupOneSets.length;
        // iterate through each instance to create a new file
        for (int i = 0; i < numIns; i++) {
            long[] A = groupOneSets[i];
            long T = groupOneTargets[i];
            StringBuilder filename = new StringBuilder();
            filename.append("A");
            filename.append(i+1);
            filename.append(".dat");
            String f = filename.toString();
            generateDataFile(A, T, f);
        }
    }

    // generate group two instance data files
    public void groupTwoDataFiles() {
        // number of instances
        int numIns = groupTwoSets.length;
        // iterate through each instance to create a new file
        for (int i = 0; i < numIns; i++) {
            long[] A = groupTwoSets[i];
            long T = groupTwoTargets[i];
            StringBuilder filename = new StringBuilder();
            filename.append("A");
            filename.append(i+10+1);
            filename.append(".dat");
            String f = filename.toString();
            generateDataFile(A, T, f);
        }
    }

    // generate group three instance data files
    public void groupThreeDataFiles() {
        // number of instances
        int numIns = groupThreeSets.length;
        // iterate through each instance to create a new file
        for (int i = 0; i < numIns; i++) {
            long[] A = groupThreeSets[i];
            long T = groupThreeTargets[i];
            StringBuilder filename = new StringBuilder();
            filename.append("A");
            filename.append(i+35+1);
            filename.append(".dat");
            String f = filename.toString();
            generateDataFile(A, T, f);
        }
    }

    // generate group four instance data files
    public void groupFourDataFiles() {
        // number of instances
        int numIns = groupFourSets.length;
        // iterate through each instance to create a new file
        for (int i = 0; i < numIns; i++) {
            long[] A = groupFourSets[i];
            long T = groupFourTargets[i];
            StringBuilder filename = new StringBuilder();
            filename.append("A");
            filename.append(i+60+1);
            filename.append(".dat");
            String f = filename.toString();
            generateDataFile(A, T, f);
        }
    }

    // generate group five instance data files
    public void groupFiveDataFiles() {
        // number of instances
        int numIns = groupFiveSets.length;
        // iterate through each instance to create a new file
        for (int i = 0; i < numIns; i++) {
            long[] A = groupFiveSets[i];
            long T = groupFiveTargets[i];
            StringBuilder filename = new StringBuilder();
            filename.append("A");
            filename.append(i+85+1);
            filename.append(".dat");
            String f = filename.toString();
            generateDataFile(A, T, f);
        }
    }

    // method to generate all data
    public void generateAllData() {
        groupOne();
        groupTwo();
        groupThree();
        groupFour();
        groupFive();
    }

    // Group 1 data generation
    public void groupOne() {
        this.groupOneSets = new long[10][];
        this.groupOneTargets = new long[10];
        // for loop to generate the 10 arrays
        for (int i = 0; i < 10; i++) {
            // A[i][] length is 10, 20, 30 ...
            groupOneSets[i] = new long[10 * (i + 1)];
            for (int j = 0; j < 10 * (i + 1); j++) {
                this.groupOneSets[i][j] = j + 1;
            }
            groupOneTargets[i] = 10 * (i + 1);
        }
        return;
    }

    // Group data 2 generation
    public void groupTwo() {
        // group two set contains 25 sets, each have length 4, 8, 12, ... 100
        this.groupTwoSets = new long[25][];
        // group two targets have 25 numbers
        this.groupTwoTargets = new long[25];
        for (int i = 0; i < 25; i++) {
            groupTwoSets[i] = new Random().longs(1, 1001).distinct().limit(4 * (i+1)).toArray();
            groupTwoTargets[i] = ((4 * (i+1)) * 1000) / 4;
        }
        return;
    }

    // Group data 3 generation
    public void groupThree() {
        // group two set contains 25 sets, each have length 4, 8, 12, ... 100
        this.groupThreeSets = new long[25][];
        // group two targets have 25 numbers
        this.groupThreeTargets = new long[25];
        for (int i = 0; i < 25; i++) {
            groupThreeSets[i] = new Random().longs(1, 1000001L).distinct().limit(4 * (i+1)).toArray();
            groupThreeTargets[i] = ((4 * (i+1)) * 1000000L) / 4;
        }
        return;
    }

    // Group data 4 generation
    public void groupFour() {
        this.groupFourSets = new long[25][];
        this.groupFourTargets = new long[25];
        for (int i = 0; i < 25; i++) {
            groupFourSets[i] = new Random().longs(1, 1000000001L).distinct().limit(4 * (i+1)).toArray();
            groupFourTargets[i] = (4 * (i+1)) * (1000000000L / 4);
        }
        return;
    }

    // Group data 5 generation
    public void groupFive() {
        this.groupFiveSets = new long[25][];
        this.groupFiveTargets = new long[25];
        for (int i = 0; i < 25; i++) {
            groupFiveSets[i] = new Random().longs(1, 501L).distinct().limit(4 * (i+1)).toArray();
            for (int j = 0; j < (4 * (i + 1)); j++) {
                groupFiveSets[i][j] = 2 * groupFiveSets[i][j];
            }
            groupFiveTargets[i] = (4 * (i+1)) * (1000L / 4);
        }
        return;
    }

    // Test
    public static void main(String[] args) {
        BenchmarkGenerator bg = new BenchmarkGenerator();
        // generate group one data files
        bg.groupFive();
        bg.groupFiveDataFiles();

        // test the generate data file method
        // bg.groupOne();
        // long[] A = bg.groupOneSets[0];
        // long T = bg.groupOneTargets[0];
        // generateDataFile(A, T, "test1.dat");


        // for (long a : A) {
        //     System.out.println(a);
        // }
        // System.out.println(T);

        // group 1 data tests
        // bg.groupOne();
        // // print sets
        // for (int i = 0; i < 10; i++) {
        //     System.out.println();
        //     for (int j = 0; j < 10 * (i + 1); j++) {
        //         System.out.print(bg.groupOneSets[i][j] + " ");
        //     }
        //     System.out.println("Target is: " + bg.groupOneTargets[i]);
        // }

        // group 2 data Test
        // bg.groupTwo();
        // for (int i = 0; i < 25; i++) {
        //     System.out.println((i + 1) + "th" + " set:");
        //     for (int j = 0; j < (4 * (i+1)); j++) {
        //         System.out.print(bg.groupTwoSets[i][j] + " ");
        //     }
        //     System.out.println("Target is: " + bg.groupTwoTargets[i]);
        // }

        // group 3 data Test
        // bg.groupThree();
        // for (int i = 0; i < 25; i++) {
        //     System.out.println((i + 1) + "th" + " set:");
        //     for (int j = 0; j < (4 * (i+1)); j++) {
        //         System.out.print(bg.groupThreeSets[i][j] + " ");
        //     }
        //     System.out.println("Target is: " + bg.groupThreeTargets[i]);
        // }


        // group 4 data Test
        // bg.groupFour();
        // for (int i = 0; i < 25; i++) {
        //     System.out.println((i + 1) + "th" + " set:");
        //     for (int j = 0; j < (4 * (i+1)); j++) {
        //         System.out.print(bg.groupFourSets[i][j] + " ");
        //     }
        //     System.out.println("Target is: " + bg.groupFourTargets[i]);
        // }

        //group 5 data Test
        // bg.groupFive();
        // for (int i = 0; i < 25; i++) {
        //     System.out.println((i + 1) + "th" + " set:");
        //     for (int j = 0; j < (4 * (i+1)); j++) {
        //         System.out.print(bg.groupFiveSets[i][j] + " ");
        //     }
        //     System.out.println("Target is: " + bg.groupFiveTargets[i]);
        // }
    }

}
