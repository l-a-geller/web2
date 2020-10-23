import java.time.LocalDateTime;

public class Point {
    private final float x;
    private final float y;
    private final float r;
    private boolean res;
    private final LocalDateTime requestTime;
    private long executionTime;

    Point(float x, float y, float r){
        this.x = x;
        this.y = y;
        this.r = r;
        this.requestTime = LocalDateTime.now();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public boolean getRes() {
        return res;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public long getExecutionTime() {
        return executionTime;
    }
}
