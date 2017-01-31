package algorithms.uf;
/**
 * Weighted Quick Union algorithm: Always make smaller tree as child of bigger tree
 * initialize - N, union - lgN, find - lgN
 *
 * Proposition: Depth of any node x is at most lgN (lg is log base 2)
 *
 * M union find operations on N objects
 * Worst Case - ( N + MlogN )
 */
public class WeightedQuickUnionUF {

    private int[] id;
    private int[] size;

    public WeightedQuickUnionUF(int[] id) {
        this.id = id;
    }

    // N array access
    public WeightedQuickUnionUF(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            size[i] = 1;
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
            int pRootSize = size[pRoot];
            int qRootSize = size[qRoot];
            if(pRootSize > qRootSize){
                id[qRoot] = pRoot;
                size[pRoot] += qRootSize;
            } else{
                id[pRoot] = qRoot;
                size[qRoot] += pRootSize;
            }
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
