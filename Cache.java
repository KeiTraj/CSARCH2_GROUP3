// Cache.java eveerything
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

    public double getAverageAccessTime() {
        int totalAccesses = hits + misses;
        return totalAccesses > 0 ? (double) totalAccessTime / totalAccesses : 0;
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
