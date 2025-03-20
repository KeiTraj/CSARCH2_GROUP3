// CacheSet.java
import java.util.LinkedList;
import java.util.Queue;

public class CacheSet {
    private CacheBlock[] blocks;
    private Queue<Integer> lruQueue; // Store indices (0 to ways-1)

    public CacheSet(int ways) {
        blocks = new CacheBlock[ways];
        for (int i = 0; i < ways; i++) {
            blocks[i] = new CacheBlock();
        }
        lruQueue = new LinkedList<>();
    }

    public boolean access(int tag) {
        // For Debug: Print the set before access
       // System.out.println("\nAccessing tag: " + tag);
        printSetState();

        for (int i = 0; i < blocks.length; i++) {
            CacheBlock block = blocks[i];
            if (block.isValid() && block.getTag() == tag) {
                lruQueue.remove(i); 
                lruQueue.add(i);
            //    System.out.println("Cache Hit at block index " + i);
                return true;
            }
        }

        for (int i = 0; i < blocks.length; i++) {
            CacheBlock block = blocks[i];
            if (!block.isValid()) {
                block.setTag(tag);
                block.setValid(true);
                lruQueue.add(i);
               // System.out.println("Cache Miss (filled empty block at index " + i + ")");
                printSetState();
                return false;
            }
        }

        int lruIndex = lruQueue.poll();
        blocks[lruIndex].setTag(tag);
        lruQueue.add(lruIndex);
    //    System.out.println("Cache Miss (evicted block at index " + lruIndex + ")");
        printSetState();
        return false;
    }

    // For Debug: Print state of this set
    public void printSetState() {
    //    System.out.print("Cache Blocks: ");
        for (int i = 0; i < blocks.length; i++) {
    //        System.out.print("[Index " + i + ": Tag=" + blocks[i].getTag() + ", Valid=" + blocks[i].isValid() + "] ");
        }
    //    System.out.println("LRU Queue (index order): " + lruQueue);
    }

    // public CacheBlock getBlock(int index) {
    //     return blocks[index];
    // }
}
