import java.util.Comparator;

class Node {
    private Node parent;
    private int pathCost;
    private int cost;
    private int riderID;
    private int driverID;
    private boolean[] assigned;

    Node(int riderID, int driverID, boolean[] assigned, Node parent, int pathCost, int cost) {
        this.assigned = assigned.clone();
        if(driverID != -1) this.assigned[driverID] = true;
        this.parent = parent;
        this.riderID = riderID;
        this.driverID = driverID;
        this.pathCost = pathCost;
        this.cost = cost;
    }
    public Node getParent() {
        return parent;
    }
    public int getPathCost() {
        return pathCost;
    }
    public int getRiderID() {
        return riderID;
    }
    public int getDriverID() {
        return driverID;
    }
    public boolean[] getAssigned() {
        return assigned;
    }
    public int getCost() {
        return cost;
    }
}

class NodeComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        return n1.getCost() - n2.getCost();
    }
}