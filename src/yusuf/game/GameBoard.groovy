package yusuf.game

import yusuf.ai.AiBoard
import yusuf.element.PointPosition

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
    private PointPosition[] pointPositions;

    public GameBoard() {
        this.pointPositions = new PointPosition[9];
        setBackground(Color.WHITE);
        this.mouseDrag = new MouseDrag();
        addMouseListener(this.mouseDrag);
        addMouseMotionListener(this.mouseDrag);
        this.setPointPosition();
        repaint();
    }

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

    public void setPointPosition() {
        this.pointPositions[0].row = 0;
        this.pointPositions[0].col = 0;
        this.pointPositions[0].posX = this.rectX;
        this.pointPositions[0].posY = this.rectY;
        this.pointPositions[0].isOccupied = true;

        this.pointPositions[1].row = 0;
        this.pointPositions[1].col = 1;
        this.pointPositions[1].posX = this.rectY + (int) (this.rectWidth/2);
        this.pointPositions[1].posY = this.rectY;
        this.pointPositions[1].isOccupied = true;

        this.pointPositions[2].row = 0;
        this.pointPositions[2].col = 2;
        this.pointPositions[2].posX = this.rectX + this.rectWidth;
        this.pointPositions[2].posY = this.rectY;
        this.pointPositions[2].isOccupied = true;

        this.pointPositions[3].row = 1;
        this.pointPositions[3].col = 0;
        this.pointPositions[3].posX = this.rectX;
        this.pointPositions[3].posY = this.rectY + (int) (this.rectHeight/2);
        this.pointPositions[3].isOccupied = false;

        this.pointPositions[4].row = 1;
        this.pointPositions[4].col = 1;
        this.pointPositions[4].posX = this.rectY + (int) (this.rectWidth/2);
        this.pointPositions[4].posY = this.rectY + (int) (this.rectHeight/2);
        this.pointPositions[4].isOccupied = false;

        this.pointPositions[5].row = 1;
        this.pointPositions[5].col = 2;
        this.pointPositions[5].posX = this.rectX + this.rectWidth;
        this.pointPositions[5].posY = this.rectY + (int) (this.rectHeight/2);
        this.pointPositions[5].isOccupied = false;

        this.pointPositions[6].row = 2;
        this.pointPositions[6].col = 0;
        this.pointPositions[6].posX = this.rectX;
        this.pointPositions[6].posY = this.rectY + this.rectHeight;
        this.pointPositions[6].isOccupied = true;

        this.pointPositions[7].row = 2;
        this.pointPositions[7].col = 1;
        this.pointPositions[7].posX = this.rectY + (int) (this.rectWidth/2);
        this.pointPositions[7].posY =  this.rectY + this.rectHeight;
        this.pointPositions[7].isOccupied = true;

        this.pointPositions[8].row = 2;
        this.pointPositions[8].col = 2;
        this.pointPositions[8].posX = this.rectX + this.rectWidth;
        this.pointPositions[8].posY =  this.rectY + this.rectHeight;
        this.pointPositions[8].isOccupied = true;

    }

}

