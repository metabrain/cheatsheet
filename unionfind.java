class DisjointSetForest {
    int[] partition;
    int[] compression;
    
    DisjointSetForest(int domain) {
        partition = new int[domain];
        compression = new int[domain];
        Arrays.fill(partition, -1);
    }

    //ONLY union if rep1!=rep2 !!
    void union(int rep1, int rep2) {
        if (partition[rep1] <= partition[rep2]){
            partition[rep1] += partition[rep2];
            partition[rep2] = rep1;
        } else {
            partition[rep2] += partition[rep1];
            partition[rep1] = rep2;
        }   
    }

    int find(int element) {
        int node = element;
        int count = 0 ;
        while (partition[node] >= 0) {
            compression[count++] = node ;
            node = partition[node];
        }
        for(int i = 0 ; i<count ; i++)
            partition[compression[i]] = node ;
        return node;    
    }
}