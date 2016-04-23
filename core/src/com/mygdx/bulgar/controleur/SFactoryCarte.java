package com.mygdx.bulgar.controleur;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.bulgar.modele.Carte;

/**
 * Created by Link2 on 22/04/2016.
 */
public class SFactoryCarte {

    private static SFactoryCarte instance;
    public final static String CARREAU = "carreau";
    public final static String COEUR = "coeur";
    public final static String PIQUE = "pique";
    public final static String TREFLE = "trefle";

    private SFactoryCarte() {
    }

    public static SFactoryCarte getInstance() {
        if(instance == null){
            instance = new SFactoryCarte();
        }
        return instance;
    }

    public Carte getCarte(String type, int nbCarte){
        Carte carte = new Carte();
        Texture texture = new Texture("CarteBulgard/"+type+"/"+nbCarte+".png");
        Sprite spriteCarte = new Sprite(texture, 0,0, texture.getWidth(), texture.getHeight());
        carte.setSpriteCarte(spriteCarte);
        return carte;
    }
}
