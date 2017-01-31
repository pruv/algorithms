package algorithms.uf;

/**
 * Weighted Quick Union With Path Compression algorithm (WQUPC)
 * when finding root of a node x, point all the nodes in the way to its root.
 * initialize - N, union - lgN, find - lgN
 *
 * Proposition: [Hopcraft-Ulman, Tarjan]
 *
 * M union find operations on set of N objects
 * Worst Case - ( N + Mlg^*N )
 */
public class WeightedQuickUnionUFWithPC {

    private int[] id;
    private int[] size;

    public WeightedQuickUnionUFWithPC(int[] id) {
        this.id = id;
    }

    // N array access
    public WeightedQuickUnionUFWithPC(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    // array access - depth of pid and qid
    public boolean connected(int pid, int qid) {
        return find(pid) == find(qid);
    }

    // array access - depth of pid and qid
    public void union(int pid, int qid) {
        if (!connected(pid, qid)) {
            int pRoot = find(pid);
            int qRoot = find(qid);
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
    private int find(int x){
        while(x != id[x]) {
            // point each node to its grand parent on the way up. (this is not exactly flattening the tree,
            // but in practicality it is as good as flattening the tree)
            id[x] = id[id[x]]; // only one extra line of code:
            x = id[x];
        }
        return x;
    }
}
