import java.util.PriorityQueue;

public class BatchedMatching {
    private int N;
    private int[][] costMatrix;
    private boolean[] assigned;

    BatchedMatching(int N, int[][] costMatrix){
        this.N = N;
        this.costMatrix = costMatrix;
        this.assigned = new boolean[N];
    }

    public int calculateCost(int riderIdx) {
        int cost = 0;
        boolean[] available = new boolean[this.N];
        for (int j = 0; j < N; j++) {
            available[j] = true;
        }
        for (int i = riderIdx + 1; i < N; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int j = 0; j < N; j++) {
                if (!this.assigned[j] && available[j] && costMatrix[i][j] < min) {
                    minIndex = j;
                    min = this.costMatrix[i][j];
                }
            }
            cost += min;
            available[minIndex] = false;
        }
        return cost;
    }

    public void printAssignments(Node min) {
        if (min.getParent() == null) {
            return;
        }
        printAssignments(min.getParent());
        System.out.println("Rider " + (char) (min.getRiderID() + 'A') + " -> Driver " + min.getDriverID() + " (" + costMatrix[min.getRiderID()][min.getDriverID()] + " min)");
    }

    public int findMinCost() {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        Node root = new Node(-1, -1, this.assigned, null, 0, 0);
        pq.add(root);
        while (!pq.isEmpty()) {
            Node min = pq.poll();
            int i = min.getRiderID() + 1;
            if (i == N) {
                printAssignments(min);
                return min.getCost();
            }
            for (int j = 0; j < N; j++) {
                if (!min.getAssigned()[j]) {
                    int childPathCost = min.getPathCost() + this.costMatrix[i][j];
                    int childCost = childPathCost + calculateCost(i);
                    Node child = new Node(i, j, min.getAssigned(), min, childPathCost, childCost);
                    pq.add(child);
                }
            }
        }

        return -1;
    }
}