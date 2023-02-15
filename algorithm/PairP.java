public class PairP implements Comparable<PairP> {
    public int indexOfMax;
    public int value;

    public PairP(int indexOfMax, int value) {
        this.indexOfMax = indexOfMax;
        this.value = value;
    }

    public int compareTo(PairP other) {
        return Integer.compare(value, other.value);
    }
}