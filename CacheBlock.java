// CacheBlock.java
public class CacheBlock {
    private Integer tag;
    private boolean valid;
    
    public CacheBlock() {
        this.tag = null;
        this.valid = false;
    }
    
    public boolean isValid() {return valid;}
    
    public Integer getTag() {return tag;}
    
    public void setTag(int tag) {
        this.tag = tag;
        this.valid = true;
    }
    public void setValid(boolean isValid) {
        this.valid = isValid;
    }

    public void invalidate() {
        this.tag = null;
        this.valid = false;
    }
}