package AI;

import entity.Entity;
import main.GamePanel;

import java.util.ArrayList;

public class PathFinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();

    }
    public void instantiateNodes() {
        node = new Node[gp.getMaxWorldRow()][gp.getMaxWorldCol()];
        int col = 0;
        int row = 0;

        while(col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
            node[row][col] = new Node(col, row);

            col++;
            if(col == gp.getMaxWorldCol()) {
                col = 0;
                row++;
            }
        }
    }
     public void resetNodes() {
        int col = 0;
        int row = 0;
        while(col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
            node[row][col].open = false;
            node[row][col].checked = false;
            node[row][col].solid = false;
            col++;
            if(col == gp.getMaxWorldCol()) {
                col = 0;
                row++;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity) {
        resetNodes();
        startNode = node[startRow][startCol];
        currentNode = startNode;
        goalNode = node[goalRow][goalCol];
        openList.add(currentNode);

        int col = 0;
        int row = 0;
        while(col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
            int tileNum = gp.getTileM().mapFromFile[row][col];
            if(gp.getTileM().tile[tileNum].collision == true) {
                node[row][col].solid = true;

            }
            getCost(node[row][col]);

            col++;
            if(col == gp.getMaxWorldCol()) {
                col = 0;
                row++;
            }
        }
    }
    public void getCost(Node node) {
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance; // Tu node bat dau toi node hien tai
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance; // Tu node hien tai toi node ket thuc
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while(goalReached == false && step < 500) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.checked = true;
            openList.remove(currentNode);

            if (row - 1 >= 0) {
                openNode(node[row - 1][col]);
            }
            if (col - 1 >= 0) {
                openNode(node[row][col - 1]);
            }
            if (row + 1 < gp.getMaxWorldRow()) {
                openNode(node[row + 1][col]);
            }
            if (col + 1 < gp.getMaxWorldCol()) {
                openNode(node[row][col + 1]);
            }
            // Tim node tot nhat
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            // Neu khong co node nao thi end
            if (openList.size() == 0) {
                break;
            }

            // Cap nhat currentNode sau khi tim ra bestNode
            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) { // Den goal thi cap nhat trang thai da den
                goalReached = true;
                trackThePath();
            }
            ++step;

        }
        return goalReached;
    }
    public void trackThePath() {
        Node current = goalNode;
        while(current != startNode) {
            pathList.add(0, current); // Them vao slot dau tien
            current = current.parent;
        }
    }
    public void openNode(Node node) {
        if(node.open == false && node.checked == false && node.solid == false) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
}
