package PaooGame.Items;

import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class ItemManager {

    private RefLinks reflink;
    private Hero hero;
    private ArrayList<Item> items;
    private Comparator<Item> renderSorter = new Comparator<Item>(){
        @Override
        public int compare(Item a, Item b) {
            if(a.GetY() + a.GetHeight() < b.GetY() + b.GetHeight())
                return -1;
            return 1;
        }
    };


    public ItemManager(RefLinks reflink, Hero hero) {
        this.reflink = reflink;
        this.hero = hero;
        items = new ArrayList<>();
        addItem(hero);
    }

    public void Update(){
        ///Loops through the array
        for(int i = 0;i <items.size();++i){
            ///Updates every entity in the array
            items.get(i).Update();
            ///Checks if the entity's isActive flag is false
            if(!items.get(i).isActive()){
                ///Removes the inactive entity from the array
                items.remove(i);
            }
        }
        items.sort(renderSorter);
    }

    public void Draw(Graphics g){
        for(Item e : items){
            e.Draw(g);
        }

    }

    public void addItem(Item e){ items.add(e);}


    //Getters si Setters
    public RefLinks getReflink() {
        return reflink;
    }

    public void setReflink(RefLinks reflink) {
        this.reflink = reflink;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
