package yusuf.game

import yusuf.ai.AiBoard

import javax.swing.JFrame
import javax.swing.JPanel
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.geom.Ellipse2D

/**
 * Created by YUSUF on 9/15/15.
 */
class GameBoard extends JPanel {

    private int x;
    private int y;
    private int width;
    private int height;
    private MouseDrag mouseDrag;
    private AiBoard aiBoard;
    private int diameter = 10;
    private int rectX = 50;
    private int rectY = 50;
    private int rectHeight = 500;
    private int rectWidth = 500;

    private final class MouseDrag extends MouseAdapter {
        private boolean dragging = false;
        private Point last;


        @Override
        public void mousePressed(MouseEvent m) {
            this.last = m.getPoint();
            this.dragging = isInsideEllipse(last);
            if (!dragging) {
                x = last.x;
                y = last.y;
                width = 0;
                height = 0;
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent m) {
            last = null;
            dragging = false;
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent m) {
            int dx = m.getX() - last.x;
            int dy = m.getY() - last.y;
            if (dragging) {
                x += dx;
                y += dy;
            } else {
                width += dx;
                height += dy;
            }
            last = m.getPoint();
            repaint();
        }
    }

    public GameBoard() {
        setBackground(Color.WHITE);
        mouseDrag = new MouseDrag();
        addMouseListener(mouseDrag);
        addMouseMotionListener(mouseDrag);
        repaint();
    }

    public boolean isInsideEllipse(Point point) {
        return new Ellipse2D.Float(x, y, width, height).contains(point);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.fillOval(x, y, this.diameter, this.diameter);
        g.drawRect(this.rectX, this.rectY, this.rectHeight, this.rectWidth);
        g.drawLine(this.rectX, this.rectY, this.rectX + this.rectWidth, this.rectY + this.rectHeight);
        g.drawLine((int)(this.rectX + this.rectWidth)/ 2 + this.rectX / 2, this.rectY, (int)(this.rectX + this.rectWidth)/ 2 + this.rectX / 2, this.rectY + this.rectHeight);
        g.drawLine(this.rectX + this.rectWidth, this.rectY, this.rectX , this.rectY + this.rectHeight);
        g.drawLine(this.rectX, (int)(this.rectY + this.rectHeight)/ 2 + this.rectY / 2, this.rectX + this.rectWidth, (int)(this.rectY + this.rectHeight)/ 2 + this.rectY / 2);
    }
}

