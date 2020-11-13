package second;

public class Interval{

    private int start;
    private int end;

    Interval(int s, int e) {
        this.start = s;
        this.end = e;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean overlap(Interval two)
    {
        if(this.start>=two.start && this.start<=two.end)
            return true;
        else if(two.start>=this.start && two.start<=this.end)
            return true;
        return false;
    }

}
