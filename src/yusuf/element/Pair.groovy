package yusuf.element

/**
 * Created by YUSUF on 4/7/15.
 */
class Pair {
    public int x;
    public int y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        return this.x == o.x && this.y == o.y;
    }

    public int hashCode() {

    }
}
