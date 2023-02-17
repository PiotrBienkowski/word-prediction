import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class Database {
    private static String dbName;
    private static String infoName;
    private static String trainginFilePath;

    public Database() {
        this.dbName = "database.db";
        this.infoName = ".info_" + dbName;
        this.trainginFilePath = "all_transcripts_1676637361.txt";
        // this.trainginFilePath = "all_transcripts_learn.txt";
    }

    public static boolean checkDatabaseExist() {
        File file = new File(dbName);
        File infoFile = new File(infoName);
        return file.exists() && infoFile.exists();
    }

    public static boolean createFile(String fileName) throws IOException {
        File file = new File(fileName);
        File infoFile = new File(infoName);

        if (file.createNewFile() && infoFile.createNewFile()) {
            return true;
        } else {
            return false;
        }
    }

    public static void buildDatabase(ArrayList<String> dictionary) {
        Collections.sort(dictionary);
        try (BufferedReader br = new BufferedReader(new FileReader(trainginFilePath))) {
            try {
                if (!createFile(dbName)) {
                    System.out.println("Database already exists.");
                } else {
                    String line = br.readLine();
                    String[] trainingTab = line.split(" ");

                    Arrays.sort(trainingTab);
                
                    ArrayList<Integer> number = new ArrayList<>();
                    int idx = 0;
                    for (String tmp: dictionary) {
                        int cnt = 0;
                        while (idx < trainingTab.length && (trainingTab[idx].compareTo(tmp) < 0 && !trainingTab[idx].equals(tmp))) {
                            idx += 1;
                        }
                        while(idx < trainingTab.length && trainingTab[idx].equals(tmp)) {
                            idx += 1;
                            cnt += 1;
                        }
                        number.add(cnt);
                    }

                    int tmpCnt = 0;
                    File file = new File(dbName);
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    int cnt = 0;
                    for (String tmp: dictionary) {
                        bw.write(tmp + "\t" + Integer.toString(number.get(cnt)));
                        bw.newLine();
                        tmpCnt += 1;
                        cnt += 1;
                    }
                    bw.close();

                    File infoFile = new File(infoName);
                    FileWriter fw2 = new FileWriter(infoFile, true);
                    BufferedWriter bw2 = new BufferedWriter(fw2);
                    bw2.write(Integer.toString(tmpCnt));
                    bw2.close();
                }
                
            } catch (IOException e) {
                System.out.println("Error.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public ArrayList<PairS> getData() {
        ArrayList<PairS> ret = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dbName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    PairS tmp = new PairS(line.split("\t")[0], Integer.parseInt(line.split("\t")[1]));
                    ret.add(new PairS(line.split("\t")[0], Integer.parseInt(line.split("\t")[1])));
                }
            }
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void changeLine(int i, String nowaWartosc) throws IOException {
        RandomAccessFile plik = new RandomAccessFile(dbName, "rw");
        long tmp = 0;
        for (int j = 0; j < i; j++) {
            tmp = plik.readLine().length() + 1; // +1 dla znaku końca linii
        }
        plik.seek(tmp);
        plik.writeBytes(nowaWartosc);
        plik.close();
    }

    public static void inceaseWord(String word) {
        word = word.toLowerCase();
        System.out.println(word);
        try (BufferedReader br = new BufferedReader(new FileReader(dbName))) {
            String line;
            int cnt = 0;
            while ((line = br.readLine()) != null) {
                cnt += 1;
                String tmp = line.split("\t")[0];
                int tmpNumber = Integer.parseInt(line.split("\t")[1]) + 1;
                if (tmp.equals(word)) {
                    System.out.println("dupa");
                    try {
                        File file = new File(dbName);
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        StringBuilder text = new StringBuilder();
                        line = reader.readLine();

                        while (line != null) {
                            text.append(line).append(System.lineSeparator());
                            line = reader.readLine();
                        }
                        reader.close();

                        String[] lines = text.toString().split(System.lineSeparator());
                        String newLine = word + "\t" + Integer.toString(tmpNumber);
                        lines[cnt-1] = newLine;

                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        for (String l : lines) {
                            writer.write(l);
                            writer.newLine();
                        }
                        writer.close();
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}