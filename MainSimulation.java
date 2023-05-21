import java.util.Scanner;
import java.util.Random;

public class MainSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int[][] costMatrix = new int[16][16];

        System.out.println("Enter Rider A position (you): ");
        System.out.println("X: ");
        int firstRiderX = scanner.nextInt();
        System.out.println("Y: ");
        int firstRiderY = scanner.nextInt();
        Position posFirstRider = new Position(firstRiderX, firstRiderY);
        costMatrix[0][0] = posFirstRider.manhattanDist(new Position());
        
        System.out.println("Enter batching time (max 5s): ");
        int batchTime = scanner.nextInt();
        while(batchTime > 5){
            System.out.println("Batching time cannot be over 5s: ");
            System.out.println("Enter batching time (max 5s): ");
            batchTime = scanner.nextInt();
        }
        int lastAdded = 1;
        for(int i = 0; i < batchTime; i++){
            try {
                System.out.println("Batching..(" + (i + 1) + "s)");
                int thisAdded = rand.nextInt(4);
                for(int j = lastAdded ; j < lastAdded + thisAdded; j++){
                    for(int k = 0 ; k < lastAdded + thisAdded; k++){
                        Position riderPos = new Position();
                        costMatrix[j][k] = riderPos.manhattanDist(new Position());
                    }
                }
                for(int j = 0 ; j < lastAdded; j++){
                    for(int k = lastAdded ; k < lastAdded + thisAdded; k++){
                        Position riderPos = new Position();
                        costMatrix[j][k] = riderPos.manhattanDist(new Position());
                    }
                }
                lastAdded += thisAdded;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-");
        System.out.println("Cost Matrix (waiting time in minute(s)):");
        MatrixPrinter.printMatrix(costMatrix, lastAdded);
        System.out.println("-");
        System.out.println("Using Branch & Bound:");
        System.out.println("Matching:");
        long startTime = System.nanoTime();
        BatchedMatchingBnB bMatchingBnB = new BatchedMatchingBnB(lastAdded, costMatrix);
        System.out.println("Average waiting time is " + bMatchingBnB.findMinCost()/lastAdded + " min");
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("Execution Time: " + executionTime/1_000_000.0 + " ms");
        System.out.println("-");
        System.out.println("Using Exhaustive Search (Brute Force):");
        startTime = System.nanoTime();
        BatchedMatchingBF bMatchingBF = new BatchedMatchingBF(lastAdded, costMatrix);
        System.out.println("Average waiting time is " + bMatchingBF.findMinCost()/lastAdded + " min");
        endTime = System.nanoTime();
        executionTime = endTime - startTime;
        System.out.println("Execution Time: " + executionTime/1_000_000.0 + " ms");
        scanner.close();
    }
}
