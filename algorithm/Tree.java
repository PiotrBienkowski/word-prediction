import java.util.ArrayList;

public class Tree {
    public ArrayList<Node> tree;
    public int uniqueId;
    public Tree() {
        this.tree = new ArrayList<>();
        this.uniqueId = 2;
    }

    public void addNode(int parent, char sign) {
        Node tmp = new Node(this.uniqueId, parent, sign);
        tree.add(tmp);
        this.uniqueId += 1;
    }

    public void showTree() {
        System.out.println("Tree:");
        for (Node v : tree) {
            System.out.println(v.id + " " + v.parent);
        }
    }
}