import java.util.ArrayList;

public class Node {
    public int id;
    public int parent;
    public char sign;
    public ArrayList<Integer> neighbours;
    public boolean isEndOfWord;
    public int numberOfUses;
    public ArrayList<PairP> maxSubtree;

    public Node(int id, int parent, char sign) {
        this.id = id;
        this.parent = parent;
        this.neighbours = new ArrayList<>();       
        this.sign = sign;
        this.isEndOfWord = false;
        this.numberOfUses = 0;
        this.maxSubtree = new ArrayList<>();
    }
}