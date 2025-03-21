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


    public Cache(int numBlocks) {  // Now only takes total blocks
        numSets = 1;
        sets = new CacheSet[numSets]; // Fully associative: only one set
        sets[0] = new CacheSet(numBlocks); // All blocks in one set
        hits = 0;
        misses = 0;
        totalAccessTime = 0;
        System.out.println("=== Cache Initialized: Fully Associative, " + numBlocks + " Blocks, MRU Replacement ===");
    }

    public boolean accessMemory(int address) {
        long startTime = System.nanoTime();
        
        int tag = address / CACHE_LINE_SIZE;
        System.out.println("\n=== Accessing Memory Address: " + address + " | Tag: " + tag + " ===");

        boolean hit =  sets[0].access(tag);

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
       System.out.println("\n--- Cache Snapshot ---");
        for (int i = 0; i < numSets; i++) {
           System.out.print("Set " + i + ": ");
            sets[i].printSetState();
        }
       System.out.println("----------------------\n");
    }

    public CacheSet getSet(int index) {
        return sets[0];
    }
}
