import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static utils.AlgoVisHelper.*;

/**
 * visual-algorithm.
 *
 * @author : M@tr!x [xhyrzldf@gmail.com]
 */
@Getter
public final class AlgoFrame extends JFrame {

    private final int canvasWidth;
    private final int canvasHeight;

    /**
     * 构造函数.
     *
     * @param title
     * @param canvasWidth  自定义宽度
     * @param canvasHeight 自定义高度
     */
    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        //initial private AlgoCanvas
        AlgoCanvas canvas = new AlgoCanvas();

        //initial sample.AlgoFrame
        this.setContentPane(canvas);
        // sync layout
        pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    private Circle circle;
    private LinkedList<Point> points;

    // FIXME: 2018/4/22 在这里可以设置自己的数据
    public void render(Circle circle, LinkedList<Point> points) {
        this.circle = circle;
        this.points = points;
        //调用paintComponent 重新绘制图形，以达到动画的效果
        this.repaint();
    }

    private final class AlgoCanvas extends JPanel {

        /**
         * 打开双缓存
         */
        public AlgoCanvas() {
            super(true);
        }

        /**
         * 刚方法用于绘制jPanel中的图形
         *
         * @param g 绘制上下文对象
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            //抗锯齿设置
            RenderingHints hints = new RenderingHints(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // FIXME: 2018/4/22 在这里定义自己的绘制
            // 绘制图形
            setStrokeWitdh(g2d, 3);
            setColor(g2d, Color.cyan);
            strokeCircle(g2d, circle.getX(), circle.getY(), circle.getR());

            for (int i = 0; i < points.size(); i++) {
                Point point = points.get(i);
                if (circle.contain(point)) {
                    setColor(g2d, Color.GREEN);
                } else {
                    setColor(g2d, Color.RED);
                }
                fillCircle(g2d, point.x, point.y, 3);
            }
        }

        /**
         * 设置画布大小
         *
         * @return
         */
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
