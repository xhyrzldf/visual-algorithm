import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * visual-algorithm.
 *
 * @author : M@tr!x [xhyrzldf@gmail.com]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Circle {
    public int x;
    public int y;
    private int r;
    public int vx;
    public int vy;

    /**
     * 移动坐标,根据vx,vy进行移动
     */
    public void move(int minX, int minY, int maxX, int maxY) {
        x += vx;
        y += vy;
        //每次移动完进行碰撞检测
        checkCollision(minX, minY, maxX, maxY);
    }

    /**
     * 边缘碰撞检测，如果碰到到了指定参数的边缘，那么就原地反弹 :)
     *
     * @param minX x 坐标最小边缘值
     * @param minY y 坐标最小边缘值
     * @param maxX x 坐标最大边缘值
     * @param maxY y 坐标最大边缘值
     */
    private void checkCollision(int minX, int minY, int maxX, int maxY) {
        if (x - r <= minX) {
            x = r;
            vx = -vx;
        }
        if (x + r >= maxX) {
            x = maxX - r;
            vx = -vx;
        }
        if (y - r <= minY) {
            y = r;
            vy = -vy;
        }
        if (y + r >= maxY) {
            y = maxY - r;
            vy = -vy;
        }
    }
}
