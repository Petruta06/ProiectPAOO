package PaooGame.Items.Prizes;

import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PrizeManager {

    private RefLinks refLink;
    private ArrayList<Prize> items;

    public PrizeManager(RefLinks refLink){
        this.refLink = refLink;
        items = new ArrayList<Prize>();
    }

    public void Update(){
        Iterator<Prize> it = items.iterator();
        while(it.hasNext()){
            Prize i = it.next();
            i.Update();
            if(i.isPickedUp()) {
                refLink.GetMap().getScore().addPoints(200);
                it.remove();
            }
        }
    }

    public void Draw(Graphics g){
        for(Prize i : items)
            i.Draw(g);
    }

    public void addItem(Prize i){
        i.setRefLink(refLink);
        items.add(i);
    }

    // Getters and Setters


    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) {
        this.refLink = refLink;
    }
}
