package PaooGame.Graphics;

import PaooGame.GameWindow.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    public Background(BufferedImage buffimg) {
        image=buffimg;

    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {

        g.drawImage(image, (int)x, (int)y, null);

        if(x < 0) {
            g.drawImage(image, (int)x, (int)y, null);
        }
        if(x > 0) {
            g.drawImage(image, (int)x, (int)y, null);
        }
    }



}
