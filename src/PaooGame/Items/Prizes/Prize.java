package PaooGame.Items.Prizes;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Prize {

    public static Prize[] items = new Prize[256];
    public static Prize dartItem = new Prize(Assets.dart, "Wood", 0);
    public static Prize bookItem = new Prize(Assets.book, "Rock", 1);

    // Class

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected RefLinks refLink;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    protected int x, y, count;
    protected boolean pickedUp = false;

    public Prize(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    public void Update(){
        if(refLink.GetMap().getItemManager().getHero().getCollisionBounds(0f, 0f).intersects(bounds)){
            pickedUp = true;
        //    refLink.GetMap().setScore(refLink.GetMap().get() + 2);
//            refLink.GetMap().getItemManager().getHero().getInventory().addPrize(this);
  //          refLink.GetMap().getItemManager().getHero().getInventory().addItem(this);
        }
    }

    public void Draw(Graphics g){
        if(refLink == null)
            return;
        Draw(g, (int) (x - refLink.getCamera().getxOffset()), (int) (y - refLink.getCamera().getyOffset()));
    }

    public void Draw(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Prize createNew(int x, int y){
        Prize i = new Prize(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }


    //GETTERS AND SETTERS!!

    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
