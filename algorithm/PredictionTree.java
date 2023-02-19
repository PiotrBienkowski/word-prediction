import java.util.ArrayList;
import java.util.Collections;

public class PredictionTree {
    public ArrayList<Node> tree;
    private int uniqueId;
    private static int limitOfPrediction = 5;

    public PredictionTree() {
        this.tree = new ArrayList<>();
        this.uniqueId = 1;
        this.tree.add(new Node(0, -1, ' '));
    }

    public int addNode(int parent, char sign) {
        Node tmp = new Node(this.uniqueId, parent, sign);
        this.tree.add(tmp);
        this.tree.get(parent).neighbours.add(uniqueId);
        this.uniqueId += 1;
        return this.uniqueId - 1;
    }

    public void showTree() {
        System.out.println("Tree:");
        for (Node v : tree) {
            System.out.println(v.id + "\t" + v.parent + "\t" + v.sign + "\t" + v.isEndOfWord + "\t" + v.numberOfUses);
            System.out.print("--> ");
            for (PairP tmp: v.maxSubtree) {
                System.out.print("(" + tmp.indexOfMax + "; " + tmp.value + "), ");
            }
            System.out.println();
            System.out.println("---------------------");
        }
    }

    public void addWord(String word) {
        int vertex = 0;
        int signIndex = 0;
        while (signIndex < word.length()) {
            int tmpVertex = -1;
            for (int v: this.tree.get(vertex).neighbours) {
                if (this.tree.get(v).sign == word.charAt(signIndex)) {
                    tmpVertex = v;
                    break;
                }
            }
            if (tmpVertex == -1) {
                tmpVertex = this.addNode(vertex, word.charAt(signIndex));
            }
            vertex = tmpVertex;
            signIndex += 1;
        }
        tree.get(vertex).isEndOfWord = true;
    }

    public int searchWord(String word, boolean check) {
        int vertex = 0;
        int signIndex = 0;
        while (signIndex < word.length()) {
            int tmpVertex = -1;
            for (int v: this.tree.get(vertex).neighbours) {
                if (this.tree.get(v).sign == word.charAt(signIndex)) {
                    tmpVertex = v;
                    break;
                }
            }
            if (tmpVertex == -1) {
                return -1;
            }
            vertex = tmpVertex;
            signIndex += 1;
        }
        if (check && !this.tree.get(vertex).isEndOfWord) {
            return -1;
        }
        return vertex;
    }

    public String printWord(int vertex) {
        String ret = "";
        while (vertex != 0) {
            ret = this.tree.get(vertex).sign + ret;
            vertex = this.tree.get(vertex).parent;
        }
        return ret;
    }

    public void increaseWord(String word, int number) {
        int vertex = searchWord(word, true);
        if (vertex == -1) {
            return;
        }
        int signIndex = 0;
        this.tree.get(vertex).numberOfUses += number;
        while(signIndex < word.length()) {
            ArrayList<PairP> tmp = new ArrayList<>();
            for (int v: this.tree.get(vertex).neighbours) {
                tmp.addAll(this.tree.get(v).maxSubtree);
            }
            if(this.tree.get(vertex).isEndOfWord)
                tmp.add(new PairP(vertex, this.tree.get(vertex).numberOfUses));
            Collections.sort(tmp);
            Collections.reverse(tmp);
            this.tree.get(vertex).maxSubtree = new ArrayList<>();
            int cnt = 0;
            for (PairP i: tmp) {
                this.tree.get(vertex).maxSubtree.add(i);
                cnt += 1;
                if (cnt >= limitOfPrediction)
                    break;
            }
            signIndex += 1;
            vertex = this.tree.get(vertex).parent;
        }
    }

    public ArrayList<String> predict(String pref) {
        int vertex = searchWord(pref, false);
        ArrayList<String> ret = new ArrayList<>();
        if (vertex == -1) 
            return ret;
        for (PairP i: this.tree.get(vertex).maxSubtree) {
            ret.add(printWord(i.indexOfMax));
        }        
        return ret;
    }
}   