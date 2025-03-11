// CacheSet.java
import java.util.LinkedList;
import java.util.Queue;

public class CacheSet {
    private CacheBlock[] blocks;
    private Queue<Integer> lruQueue;
    
    public CacheSet(int ways) {
        blocks = new CacheBlock[ways];
        for (int i = 0; i < ways; i++) {
            blocks[i] = new CacheBlock();
        }
        lruQueue = new LinkedList<>();
    }
    
    // public boolean access(int tag) {
    //     for (CacheBlock block : blocks) {
    //         if (block.isValid() && block.getTag() == tag) {
    //             lruQueue.remove(tag);
    //             lruQueue.add(tag);
    //             return true; // Cache hit
    //         }
    //     }
        
    //     if (lruQueue.size() < blocks.length) {
    //         for (CacheBlock block : blocks) {
    //             if (!block.isValid()) {
    //                 block.setTag(tag);
    //                 lruQueue.add(tag);
    //                 return false; // Cache miss
    //             }
    //         }
    //     }
        
    //     int lruTag = lruQueue.poll();
    //     for (CacheBlock block : blocks) {
    //         if (block.getTag() == lruTag) {
    //             block.setTag(tag);
    //             break;
    //         }
    //     }
    //     lruQueue.add(tag);
    //     return false;
    // }

    public boolean access(int tag) {
    System.out.println("Accessing tag: " + tag);
    System.out.println("Current cache contents before access: " + lruQueue);

    // Check if tag is already in cache (Cache Hit)
    for (CacheBlock block : blocks) {
        if (block.isValid() && block.getTag() == tag) {
            // Move accessed block to most recently used
            lruQueue.remove(tag);
            lruQueue.add(tag);
            System.out.println("Cache Hit!");
            return true;
        }
    }

    // If there's space in the set, use an empty block (NO eviction needed)
    for (CacheBlock block : blocks) {
        if (!block.isValid()) {
            block.setTag(tag);
            block.setValid(true);
            lruQueue.add(tag);
            System.out.println("Cache Miss (filled empty block).");
            return false;
        }
    }

    //Only evict if ALL 4 blocks are filled
    if (lruQueue.size() == 4) {
        int lruTag = lruQueue.poll(); // Remove LRU tag
        for (CacheBlock block : blocks) {
            if (block.getTag() == lruTag) {
                block.setTag(tag);
                break;
            }
        }
        lruQueue.add(tag);
        System.out.println("Cache Miss (evicted " + lruTag + ").");
        return false;
    }

    return false;
}

}