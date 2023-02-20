import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
                if (tmp.length() >= 4 && !findInArray(tab, tmp)) {
                    tab.add(tmp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }    

    public static void buildTree(Database database, PredictionTree tree) {
        if (!database.checkDatabaseExist()) {
            ArrayList<String> tab = clearData();
            database.buildDatabase(tab);
        }

        ArrayList<PairS> d = new ArrayList<>(database.getData());
        for (PairS i: d) {
            tree.addWord(i.word);
            tree.increaseWord(i.word, i.number);
        }
    }

    public static void addFinalSentence(String sentence, Database database, PredictionTree tree) {
        for (String word: sentence.split(" ")) {
            word = word.toLowerCase();
            tree.increaseWord(word, 1);
            database.inceaseWord(word);
        }
    }

    public static void main(String[] args) {
        System.out.println("initializing...");
        Database database = new Database();
        PredictionTree tree = new PredictionTree();
        buildTree(database, tree);

        System.out.println("Commands:");
        System.out.println("P - word prediction command");
        System.out.println("A - the final version of the sentence, model training");
        System.out.println("EXIT - close the program");

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line.equals("P")) {
                System.out.print("Type prefix: ");
                Scanner scanner2 = new Scanner(System.in);
                String prefix = scanner2.nextLine();
                System.out.println(tree.predict(prefix));
            } else if (line.equals("A")) {
                System.out.print("Type sentence: ");
                Scanner scanner2 = new Scanner(System.in);
                String sentence = scanner2.nextLine();
                addFinalSentence(sentence, database, tree);
            } else if (line.toLowerCase().equals("exit")) {
                break;
            } else {
                System.out.println("Command not found.");
            }
            System.out.println();
        }
    }
}