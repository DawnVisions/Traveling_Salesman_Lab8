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
        CostMatrix matrix = new CostMatrix(6);
        matrix.matrix[0][1] = 2;
        matrix.matrix[0][2] = 8;
        matrix.matrix[0][3] = 12;
        matrix.matrix[0][4] = 6;
        matrix.matrix[0][5] = 1;
        matrix.matrix[1][0] = 2;
        matrix.matrix[1][2] = 15;
        matrix.matrix[1][3] = 6;
        matrix.matrix[1][4] = 4;
        matrix.matrix[1][5] = 2;
        matrix.matrix[2][0] = 8;
        matrix.matrix[2][1] = 15;
        matrix.matrix[2][3] = 6;
        matrix.matrix[2][4] = 20;
        matrix.matrix[2][5] = 3;
        matrix.matrix[3][0] = 9;
        matrix.matrix[3][1] = 6;
        matrix.matrix[3][2] = 6;
        matrix.matrix[3][4] = 4;
        matrix.matrix[3][5] = 3;
        matrix.matrix[4][0] = 6;
        matrix.matrix[4][1] = 4;
        matrix.matrix[4][2] = 20;
        matrix.matrix[4][3] = 4;
        matrix.matrix[4][5] = 7;
        matrix.matrix[5][0] = 1;
        matrix.matrix[5][1] = 2;
        matrix.matrix[5][2] = 3;
        matrix.matrix[5][3] = 3;
        matrix.matrix[5][4] = 7;

        Dynamic_Programming dp = new Dynamic_Programming();
        dp.TSP(matrix);


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
