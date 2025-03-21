// CacheSet.java
import java.util.Stack;

public class CacheSet {
    private CacheBlock[] blocks;
    private Stack<Integer> mruStack;

    public CacheSet(int ways) {
        blocks = new CacheBlock[ways];
        for (int i = 0; i < ways; i++) {
            blocks[i] = new CacheBlock();
        }
            mruStack = new Stack<>();
    }

    public boolean access(int tag) {
        // For Debug: Print the set before access
       System.out.println("\nAccessing tag: " + tag);
        printSetState();

        for (int i = 0; i < blocks.length; i++) {
            CacheBlock block = blocks[i];
            if (block.isValid() && block.getTag() == tag) {
                mruStack.remove(Integer.valueOf(i));
                mruStack.push(i);
               System.out.println("Cache Hit at block index " + i);
                return true;
            }
        }

        for (int i = 0; i < blocks.length; i++) {
            CacheBlock block = blocks[i];
            if (!block.isValid()) {
                block.setTag(tag);
                block.setValid(true);
                mruStack.push(i);
               System.out.println("Cache Miss (filled empty block at index " + i + ")");
                printSetState();
                return false;
            }
        }

        int mruIndex = mruStack.pop();
        blocks[mruIndex].setTag(tag); // Replace tag
        mruStack.push(mruIndex);
       System.out.println("Cache Miss (evicted block at index " + mruIndex + ")");
        printSetState();
        return false;
    }

    // For Debug: Print state of this set
    public void printSetState() {
       System.out.print("Cache Blocks: ");
        for (int i = 0; i < blocks.length; i++) {
           System.out.print("[Index " + i + ": Tag=" + blocks[i].getTag() + ", Valid=" + blocks[i].isValid() + "] ");
        }
       System.out.println(" | MRU Stack Order (Top = Most Recent): " + mruStack + "\n");
    }

    // public CacheBlock getBlock(int index) {
    //     return blocks[index];
    // }
}
