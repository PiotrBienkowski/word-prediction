import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static String filePath = "no_repetitions_all_dictionary_1675539110.txt";
    // private static String filePath = "learn.txt";
    
    public static boolean findInArray(ArrayList<String> tab, String x) {
        if (tab.size() == 0)
            return false;

        int l = 0;
        int r = tab.size();
        while (l + 1 < r) {
            int m = (l + r) / 2;
            if (tab.get(m).compareTo(x) < 0 || tab.get(m).equals(x))
                l = m;
            else
                r = m;
        }

        if (tab.get(l) == x)
            return true;
        else
            return false;
    }

    public static ArrayList<String> clearData() {
        ArrayList<String> tab = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String tmp = line.split("\t")[0].split(" ")[0].toLowerCase();
                if (tmp.length() > 4 && !findInArray(tab, tmp)) {
                    tab.add(tmp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }

    public static void main(String[] args) {
        ArrayList<String> tab = clearData();

        PredictionTree tree = new PredictionTree();
        for (String word: tab) {
            tree.addWord(word);
            // mozemy od razu robic incease word 
        }

        tree.increaseWord("sezam");
        tree.increaseWord("sezam");

        System.out.print(tree.predict("se"));
    }
}