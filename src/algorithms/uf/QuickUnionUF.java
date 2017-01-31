package algorithms.uf;

/**
 * Quick Union algorithm: (Lazy approach)
 * initialize - N, union - N, find - N (worst case scenario)
 *
 * DEFECT - Trees can get tall, Find too expensive(worst case N array accesses)
 *
 * M union find operations on N objects
 * Worst Case - (M * N)
 */
public class QuickUnionUF {

    private int[] id;

    // N array access
    public QuickUnionUF(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    // array access - depth of pid and qid
    public boolean connected(int pid, int qid) {
        return getRoot(pid) == getRoot(qid);
    }

    // array access - depth of pid and qid
    public void union(int pid, int qid) {
        if (!connected(pid, qid)) {
            int pRoot = getRoot(pid);
            int qRoot = getRoot(qid);
            id[pRoot] = qRoot;
        }
    }

    // array access - depth of pid
    private int getRoot(int pid){
        int x = pid;
        while(x != id[x]) {
            x = id[x];
        }
        return x;
    }
}
