class BatchedMatchingBF {
    private int N;
    private int[][] costMatrix;
    private boolean[] assigned;
    private int minCost;

    BatchedMatchingBF(int N, int[][] costMatrix) {
        this.N = N;
        this.costMatrix = costMatrix;
        this.assigned = new boolean[N];
        this.minCost = Integer.MAX_VALUE;
    }

    public int exhaustiveSearch() {
        generatePermutations(0, 0);
        return minCost;
    }

    private void generatePermutations(int riderIdx, int currentCost) {
        if (riderIdx == N) {
            if (currentCost < minCost) {
                minCost = currentCost;
            }
            return;
        }

        for (int driverIdx = 0; driverIdx < N; driverIdx++) {
            if (!assigned[driverIdx]) {
                assigned[driverIdx] = true;
                generatePermutations(riderIdx + 1, currentCost + costMatrix[riderIdx][driverIdx]);
                assigned[driverIdx] = false; // backtrack
            }
        }
    }
}
