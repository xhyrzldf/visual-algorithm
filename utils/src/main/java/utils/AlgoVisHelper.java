package utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * visual-algorithm.
 *
 * @author : M@tr!x [xhyrzldf@gmail.com]
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AlgoVisHelper {

    /**
     * 该函数为设置笔画线条宽度
     *
     * @param g2d
     * @param strokeWidth
     */
    public static void setStrokeWitdh(Graphics2D g2d, int strokeWidth) {
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void setColor(Graphics2D g2d, Color color) {
        g2d.setColor(color);
    }

    /**
     * 暂停一哈，动画刷新的帧率
     *
     * @param millisecond
     */
    public static void pause(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制一个空心圆
     *
     * @param g2d
     * @param x   圆心横坐标
     * @param y   圆心纵坐标
     * @param r   半径
     */
    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circular = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circular);
    }

    /**
     * 绘制一个实心圆
     *
     * @param g2d
     * @param x   圆心横坐标
     * @param y   圆心纵坐标
     * @param r   半径
     */
    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circular = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circular);
    }
}
