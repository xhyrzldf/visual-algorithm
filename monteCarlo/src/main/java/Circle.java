import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.awt.*;

/**
 * visual-algorithm.
 * Des :
 *
 * @author : M@tr!x [xhyrzldf@gmail.com]
 * @date : 2018/4/23 16:43
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Circle {

    private int x;
    private int y;
    private int r;

    /**
     * Determine if a point is within a circle
     *
     * @param point
     * @return
     */
    public boolean contain(Point point) {
        return Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2) <= r * r;
    }
}
