// Cache.java
public class Cache {
    private CacheSet[] sets;
    private int numSets;
    private int hits, misses;
    private long totalAccessTime;
    private final int CACHE_LINE_SIZE = 16; // 16 words per line
    
    public Cache(int numSets, int ways) {
        this.numSets = numSets;
        this.sets = new CacheSet[numSets];
        for (int i = 0; i < numSets; i++) {
            sets[i] = new CacheSet(ways);
        }
        this.hits = 0;
        this.misses = 0;
        this.totalAccessTime = 0;
    }
    
    public boolean accessMemory(int address) {
        long startTime = System.nanoTime();
        int index = (address / CACHE_LINE_SIZE) % numSets;
        int tag = address / (CACHE_LINE_SIZE * numSets);
        boolean hit = sets[index].access(tag);
        long endTime = System.nanoTime();
        
        totalAccessTime += (endTime - startTime);
        
        if (hit) {
            hits++;
        } else {
            misses++;
        }
        return hit;
    }
    
    public int getHits() {return hits;}

    public int getMisses() {return misses;}
    
    public long getTotalAccessTime() { return totalAccessTime; }
    
    public double getAverageAccessTime() {
        int totalAccesses = hits + misses;
        return totalAccesses > 0 ? (double) totalAccessTime / totalAccesses : 0;
    }
}