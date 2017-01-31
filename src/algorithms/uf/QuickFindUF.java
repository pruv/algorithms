package algorithms.uf;

/**
 * Quick find algorithm:
 *  initialize - N, union - N, find - 1
 *  Takes N^2 array accesses to process sequence of N union commands on N objects
 *  Quadratic times is not scalable
 *
 *  DEFECT - union is too expensive always N array accesses
 *
 *  M union find operations on set of N objects
 *  Worst Case - (M * N)
 */
public class QuickFindUF {

    private int[] id;

    // N array access
    public QuickFindUF(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    // 2 array access
    public boolean connected(int pid, int qid) {
        return id[pid] == id[qid];
    }

    // maximum of 2N + 2 array access
    public void union(int pid, int qid) {
        if (!connected(pid, qid)) {
            int qValue = id[qid];
            int pValue = id[pid];
            for (int i = 0; i < id.length; i++) {
                if (id[i] == pValue) {
                    id[i] = qValue;
                }
            }
        }
    }

}
