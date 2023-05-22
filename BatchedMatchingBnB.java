import java.util.PriorityQueue;

public class BatchedMatchingBnB {
    private int N;
    private int[][] costMatrix;

    BatchedMatchingBnB(int N, int[][] costMatrix){
        this.N = N;
        this.costMatrix = costMatrix;
    }

    public int calculateCost(int riderIdx, boolean[] assigned) {
        int cost = 0;
        for (int i = riderIdx + 1; i < N; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (!assigned[j] && costMatrix[i][j] < min) {
                    min = this.costMatrix[i][j];
                }
            }
            cost += min;
        }
        return cost;
    }

    public void printAssignments(Node min) {
        if (min.getParent() == null) {
            return;
        }
        printAssignments(min.getParent());
        System.out.println("Rider " + (char) (min.getRiderID() + 'A') + " -> Driver " + (min.getDriverID() + 1) + " (" + costMatrix[min.getRiderID()][min.getDriverID()] + " min)");
    }

    public int findMinCost() {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        Node root = new Node(-1, -1, new boolean[N], null);
        root.setPathCost(0);
        root.setCost(0);
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
                    Node child = new Node(i, j, min.getAssigned(), min);
                    child.setPathCost(min.getPathCost() + this.costMatrix[i][j]);
                    child.setCost(child.getPathCost() + calculateCost(i, child.getAssigned()));
                    pq.add(child);
                }
            }
        }

        return -1;
    }
}