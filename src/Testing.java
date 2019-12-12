import java.io.FileWriter;
import java.io.PrintWriter;


public class Testing {

    /* define constants */
    static int numberOfTrials = 15;
    private static final int MAXINPUTSIZE = 20;
    static String ResultsFolderPath = "/Users/elizabethwersal/IdeaProjects/Results/TSP/"; // pathname to results folder
    static FileWriter resultsFile;
    static PrintWriter resultsWriter;

    public static void main(String[] args) {

        // run the whole experiment at least twice, and expect to throw away the data from the earlier runs, before java has fully optimized
        runFullExperiment("RandomQualityGreedyTSP-Exp1-ThrowAway.txt");
        runFullExperiment("RandomQualityGreedyTSP-Exp2.txt");
        runFullExperiment("RandomQualityGreedyTSP-Exp3.txt");

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

        resultsWriter.println("#Number of Vertices  AverageTime  Solution Quality Ratio"); // # marks a comment in gnuplot data
        resultsWriter.flush();

        for (int inputSize = 4; inputSize <= MAXINPUTSIZE; inputSize++) {
            System.out.println("Running test for string size " + inputSize + " ... ");
            System.out.print("    Running trial batch...");
            System.gc();
            long batchElapsedTime = 0;
            double qualityRatio = 0;
            for (long trial = 0; trial < numberOfTrials; trial++) {
                System.out.print("    Generating test data...");
                CostMatrix testMatrix = GenerateRandomCostMatrix(inputSize, 100);
                Dynamic_Programming dp = new Dynamic_Programming();
                System.gc();
                System.out.println("...done.");
                TrialStopwatch.start();
                Path testPath = Greedy.TSP(testMatrix);
                Path exactPath = dp.TSP(testMatrix);
                qualityRatio += testPath.cost / exactPath.cost;
                batchElapsedTime = batchElapsedTime + TrialStopwatch.elapsedTime();
                testPath.printPath();
                exactPath.printPath();
                System.gc();
            }
            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double) numberOfTrials; // calculate the average time per trial in this batch
            double SQR = qualityRatio/(double)numberOfTrials;
            if (lastAverageTime != -1) {
                doublingRatio = averageTimePerTrialInBatch / lastAverageTime;
            }
            lastAverageTime = averageTimePerTrialInBatch;

            /* print data for this size of input */
            resultsWriter.printf("%12d  %5.2f %15.2f\n", inputSize, SQR, averageTimePerTrialInBatch);
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
