import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static util.AlgoVisHelper.pause;

/**
 * visual-algorithm.
 * data controller
 *
 * @author : M@tr!x [xhyrzldf@gmail.com]
 */
public final class AlgoVisualizer {

    //画布里的圆形数组
    private Circle[] circles;
    //画布
    private AlgoFrame frame;
    //动画是否执行
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        //initial circles , N = the number of circles , R = radius
        int R = 50;
        circles = new Circle[N];

        Random rand = new Random();
        for (int i = 0; i < circles.length; i++) {
            //随机生成圆心坐标 rand x = random(0,width-2r)+r , y is same
            int x = rand.nextInt(sceneWidth - 2 * R) + R;
            int y = rand.nextInt(sceneHeight - 2 * R) + R;
            // vx,vy = [-5,5)
            int vx = rand.nextInt(10) - 5;
            int vy = rand.nextInt(10) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        // start paint
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            // add keyboardListener
            frame.addKeyListener(new AlgoKeyListener());
            // run animation
            CompletableFuture.runAsync(this::run);
        });
    }

    /**
     * Animation logic. 动画逻辑
     */
    private void run() {
        while (true) {
            // draw circle
            frame.render(circles);
            // wait 20 ms
            pause(20);
            // update circle position

            if (isAnimated) {
                for (Circle circle : circles) {
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
                }
            }
        }
    }

    /**
     * 自定义键盘响应事件
     */
    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            // 如果用户按了空格,那么就切换动画绘制的状态 动画执行/暂停
            if (e.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }


    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}