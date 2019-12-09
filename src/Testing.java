import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Testing {

    /* define constants */
    static int numberOfTrials = 20;
    private static final int MAXSTRINGSIZE = 17000;
    static String ResultsFolderPath = "/home/elizabeth/Results/LCS/"; // pathname to results folder
    static FileWriter resultsFile;
    static PrintWriter resultsWriter;

    public static void main(String[] args) {

        ArrayList<Integer> set = new ArrayList<Integer>(Arrays.asList(2,5,8,9,10));
        Integer num = Dynamic_Programming.setToInteger(set);
        System.out.println(num);
        System.out.println(Dynamic_Programming.binaryIntegerToSet(num));

        // run the whole experiment at least twice, and expect to throw away the data from the earlier runs, before java has fully optimized
        //runFullExperiment("BruteForceLCS-Exp1-ThrowAway.txt");
        //runFullExperiment("BruteForceLCS-Exp2.txt");
        //runFullExperiment("BruteForceLCS-Exp3.txt");

    }

    private static void runFullExperiment(String resultsFileName) {
        try {
            resultsFile = new FileWriter(ResultsFolderPath + resultsFileName);
            resultsWriter = new PrintWriter(resultsFile);
        } catch (Exception e) {
            System.out.println("*****!!!!!  Had a problem opening the results file " + ResultsFolderPath + resultsFileName);
            return;
        }

        ThreadCpuStopWatch TrialStopwatch = new ThreadCpuStopWatch(); // for timing an individual trial
        double lastAverageTime = -1;
        double doublingRatio = 0;

        resultsWriter.println("#String size)  AverageTime"); // # marks a comment in gnuplot data
        resultsWriter.flush();

        for (int inputSize = 1; inputSize <= MAXSTRINGSIZE; inputSize*=2) {
            System.out.println("Running test for string size " + inputSize + " ... ");
            System.out.print("    Running trial batch...");
            System.gc();
            long batchElapsedTime = 0;
            for (long trial = 0; trial < numberOfTrials; trial++) {
                System.out.print("    Generating test data...");

                System.gc();
                System.out.println("...done.");
                TrialStopwatch.start();
                //Testing
                batchElapsedTime = batchElapsedTime + TrialStopwatch.elapsedTime();

            }
            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double) numberOfTrials; // calculate the average time per trial in this batch
            if (lastAverageTime != -1) {
                doublingRatio = averageTimePerTrialInBatch / lastAverageTime;
            }
            lastAverageTime = averageTimePerTrialInBatch;

            /* print data for this size of input */
            resultsWriter.printf("%12d  %15.2f %10.2f\n", inputSize, averageTimePerTrialInBatch, doublingRatio);
            resultsWriter.flush();
            System.out.println(" ....done.");
        }
    }

    public static CostMatrix GenerateRandomCostMatrix(int vertices, int maxEdgeCost )
    {
        CostMatrix matrix = new CostMatrix(vertices);
        matrix.fillRandomMatrix(maxEdgeCost);
        return matrix;
    }

    public static CostMatrix GenerateRandomEuclidianCostMatrix(int vertices, int maxCoordinate)
    {
        CostMatrix matrix = new CostMatrix(vertices);
        matrix.fillEuclideanMatrix(maxCoordinate);
        return matrix;
    }

    public static CostMatrix GenerateCircularGraphCostMatrix(int vertices, int radius )
    {
        CostMatrix matrix = new CostMatrix(vertices);
        matrix.fillCircularMatrix(radius);
        return matrix;
    }


}
