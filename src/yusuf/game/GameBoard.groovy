package yusuf.game

import javax.swing.JPanel
import java.awt.Color
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter

/**
 * Created by YUSUF on 9/15/15.
 */
class GameBoard extends JPanel {
    public int x1, x2, y1, y2, r, w, h,xDist,yDist;
    public static boolean flag = false, pressFlag = false;

    public r() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent m) {
//                pressFlag = true;
                if (r > (int) Math.sqrt(Math.abs(m.getX() - x1) * Math.abs(m.getX() - x1) + Math.abs(m.getY() - y1) * Math.abs(m.getY() - y1))) {
                    flag = true;
                    yDist=xDist=x2 = y2 = 0;
                } else {
                    x1 = y1 = 0;
                    r=x2 = y2 = 0;
                    x1 = m.getX();
                    y1 = m.getY();
                }
                repaint();
            }

            public void mouseReleased(MouseEvent m) {
                w = x2 - x1;
                h = y2 - y1;
                r = (int) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                flag = false;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent m) {
                if (flag && (x2!=0 && y2!=0)) {
                    xDist=(m.getX()-x2);
                    yDist=(m.getY()-y2);
                }
                x2 = m.getX();
                y2 = m.getY();

                repaint();
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (flag) {
            x1=x1+xDist;
            y1=y1+yDist;
            g.drawOval(x1, y1, w, h);
        } else {
            g.drawOval(x1, y1, x2 - x1, y2 - y1);
        }
    }
}

