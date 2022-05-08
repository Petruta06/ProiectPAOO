package PaooGame.Items.Prizes;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dart extends Item {

    private BufferedImage image;

    public Dart(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        image = Assets.dart;
    }


    public void die(){
        refLink.GetMap().getScore().addPoints(50);
        refLink.GetMap().getItemManager().getHero().setLife(refLink.GetMap().getItemManager().getHero().getLife() +1);
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int) (x - refLink.getCamera().getxOffset()), (int) (y - refLink.getCamera().getyOffset()), width, height, null);
    }
}
