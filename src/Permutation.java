import edu.princeton.cs.algs4.StdIn;

/**
 * Created by java on 2/4/17.
 */
public class Permutation {

    public static void main(String[] args){

        int k = StdIn.readInt();
        String ip = StdIn.readString();
        String[] inputs = ip.split(" ");
        RandomizedQueue<String> rQu = new RandomizedQueue<>();
        for(String s: inputs) {
            rQu.enqueue(s);
        }
        while (k > 0) {
            System.out.println(rQu.sample());
            k--;
        }
    }
}
