package com.mygdx.bulgar.client;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.bulgar.modele.Carte;

/**
 * Created by Link2 on 24/04/2016.
 */
public class Pioche extends Carte {

    private int nbPioche;
    private Skin skin;

    public Pioche(int nbPioche, Skin skin) {
        this.nbPioche = nbPioche;
        this.skin = skin;
    }


    public int getNbPioche() {
        return nbPioche;
    }

    public void setNbPioche(int nbPioche) {
        this.nbPioche = nbPioche;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Label label = new Label(Integer.toString(nbPioche),skin);
        label.setPosition(super.getX(),super.getY());
        label.setWidth(this.getWidth());
        label.setHeight(this.getHeight());
        label.setColor(Color.RED);
        label.draw(batch, parentAlpha);
    }
}
