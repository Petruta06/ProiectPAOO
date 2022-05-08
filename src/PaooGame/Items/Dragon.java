package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dragon extends Item{

    private BufferedImage image;
    private long previousAttackTime;            /*!< Holds the time at which the previous attack occurred.*/
    private long attackWaitTime = 2000;          /*!< Holds the attack cooldown time.*/
    private long attackTimer = attackWaitTime;  /*!< Holds the time that passed between attacks. It is initialized with the attackWaitTime.*/
    private Rectangle attackRect;
    public Dragon(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        normalBounds.x = 14;
        normalBounds.y = 10;
        normalBounds.width = 10;
        normalBounds.height = 25;

        image = Assets.dragon;

        this.life= 12;

    }

    private void checkAttack(){
        /// Updating the attackTimer based on the previous attack time
        attackTimer += System.currentTimeMillis() - previousAttackTime;
        ///Initializing the previousAttackTime with the current time (as right now it it the current attack)
        previousAttackTime = System.currentTimeMillis();
        ///If the timer is less than the wait time, there is no attack
        if(attackTimer < attackWaitTime)
            return;

        ///The default size for width/height
        int arSize = bounds.width +20;
        ///Building the attack bounds; slightly bigger than the collision bounds
        attackRect = new Rectangle(bounds.x - 5,bounds.y - 5, arSize, arSize);
        attackRect.width = arSize;
        attackRect.height = arSize;

        ///Checks if the attack bounds intersect with the Player's bounds
        if(refLink.GetMap().getItemManager().getHero().getCollisionBounds(-x,-y).intersects(attackRect)){
            ///Attacks the player
            refLink.GetMap().getItemManager().getHero().hurt(1);
        }
        ///Sets the attack timer to 0 -> an attack has occurred and the player
        ///must wait to attack again
        attackTimer = 0;
    }

    @Override
    protected void die() {
        refLink.GetMap().getScore().addPoints(2000);
//        State.SetState(refLink.GetGame().getPlayState());
        refLink.GetMap().LoadWorld(2);
    }

    @Override
    public void Update() {
        checkAttack();

    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int) (x - refLink.getCamera().getxOffset()), (int) (y - refLink.getCamera().getyOffset()), width, height, null);
    }
}
