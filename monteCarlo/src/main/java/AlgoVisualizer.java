import utils.AlgoVisHelper;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;


/**
 * visual-algorithm.
 * data controller
 *
 * @author : M@tr!x [xhyrzldf@gmail.com]
 */
@SuppressWarnings("Duplicates")
public final class AlgoVisualizer {

    // FIXME: 2018/4/22 创建自己的数据
    private Circle circle;
    private LinkedList<Point> points;
    private int N;
    private int insideCircle = 0;


    //画布 视图
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        if (sceneHeight != sceneWidth) {
            throw new IllegalArgumentException("width must equal height!");
        }

        // FIXME: 2018/4/22 初始化数据
        circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneHeight / 2);
        points = new LinkedList<>();
        this.N = N;

        // FIXME: 2018/4/22 初始化视图
        // start paint
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            // FIXME: 2018/4/22 在这里增加监听器
            //add  Listeners
            // run animation
            CompletableFuture.runAsync(this::run);
        });
    }

    /**
     * Animation logic. 动画逻辑
     */
    @SuppressWarnings("InfiniteLoopStatement")
    private void run() {
        // FIXME: 2018/4/22 动画逻辑
        for (int i = 0; i < N; i++) {
            if (i % 100 == 0) {
                frame.render(circle, points);
                AlgoVisHelper.pause(20);

                int circleArea = insideCircle;
                int squareArea = points.size();
                double pi = 4 * (double) circleArea / squareArea;
                System.out.printf("pi's estimated value is %f %n", pi);
            }

            Random rand = new Random();
            int x = rand.nextInt(frame.getCanvasWidth());
            int y = rand.nextInt(frame.getCanvasHeight());

            Point p = new Point(x, y);
            points.add(p);
            if (circle.contain(p)) {
                insideCircle++;
            }

        }
    }

    /* 下面是入口 */
    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100000;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
