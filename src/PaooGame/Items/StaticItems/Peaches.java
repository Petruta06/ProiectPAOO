package PaooGame.Items.StaticItems;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Peaches extends StaticItem {
    public Peaches(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        life =2;
    }

    @Override
    protected void die() {
        refLink.GetMap().getScore().addPoints(200);
        refLink.GetMap().getItemManager().getHero().setLife(refLink.GetMap().getItemManager().getHero().getLife() +1);
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.peaches, (int) (x - refLink.getCamera().getxOffset()), (int) (y - refLink.getCamera().getyOffset()), width, height, null);

    }
}
