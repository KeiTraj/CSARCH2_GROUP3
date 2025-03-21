// Cache.java eveerything
public class Cache {
    private CacheSet[] sets;
    private int numSets;
    private int hits, misses;
    private long totalAccessTime;
    private final int CACHE_LINE_SIZE = 16; // 16 words per line

    //Constants for timing
    private final int CACHE_ACCESS_TIME = 1; // ns
    private final int MEMORY_ACCESS_TIME = 10; // ns
    private final int CACHE_BLOCK_SIZE = 16; // 16 words (same as line size)


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
    //    System.out.println("\n==== Accessing address: " + address + " (Set " + index + ", Tag " + tag + ") ====");

        boolean hit = sets[index].access(tag);

        long endTime = System.nanoTime();
        totalAccessTime += (endTime - startTime);

        if (hit) {
            hits++;
        } else {
            misses++;
        }

        // For Debug: Print entire cache after each access
        printEntireCacheState();
        return hit;
    }

    public int getHits() { return hits; }

    public int getMisses() { return misses; }

    public long getTotalAccessTime() { return totalAccessTime; }

    public int getMemoryAccessCount() { return hits + misses;}
    

    public double getHitRate() {
        int totalAccesses = hits + misses;
        return totalAccesses > 0 ? (double) hits / totalAccesses : 0;
    }
    
    public double getMissRate() {
        int totalAccesses = hits + misses;
        return totalAccesses > 0 ? (double) misses / totalAccesses : 0;
    }    

    public int getMissPenalty() {
        return CACHE_ACCESS_TIME + (CACHE_BLOCK_SIZE * MEMORY_ACCESS_TIME) + (CACHE_BLOCK_SIZE * CACHE_ACCESS_TIME);
    }

    public long calculateTotalMemoryAccessTime() {
        long hitTime = CACHE_BLOCK_SIZE * hits * CACHE_ACCESS_TIME;
        long missTime = misses * getMissPenalty();
        return hitTime + missTime;
    }
    public double calculateAverageMemoryAccessTime() {
        return (getHitRate() * CACHE_ACCESS_TIME) + (getMissRate() * getMissPenalty());
    }

    // For Debug: Print full cache (all sets)
    public void printEntireCacheState() {
    //    System.out.println("\n--- Cache Snapshot ---");
        for (int i = 0; i < numSets; i++) {
    //        System.out.print("Set " + i + ": ");
            sets[i].printSetState();
        }
    //    System.out.println("----------------------\n");
    }

    public CacheSet getSet(int index) {
        return sets[index];
    }
}
