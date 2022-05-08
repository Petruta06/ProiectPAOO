package PaooGame.Items.StaticItems;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

public class Potion extends StaticItem {
    public Potion(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH-16, Tile.TILE_HEIGHT-16);
        life =2;
    }

    @Override
    protected void die() {
        refLink.GetMap().getScore().addPoints(150);
        refLink.GetMap().getItemManager().getHero().setLife(refLink.GetMap().getItemManager().getHero().getLife() +2);
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.potion, (int) (x - refLink.getCamera().getxOffset()), (int) (y - refLink.getCamera().getyOffset()), width, height, null);

    }
}
