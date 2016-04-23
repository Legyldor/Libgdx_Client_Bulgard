package com.mygdx.bulgar.modele;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Link2 on 07/03/2016.
 */
public class Carte extends Actor {

    Sprite spriteCarte;
    public Carte() {
        Texture texture = new Texture("CarteBulgard/dos.png");
        spriteCarte = new Sprite(texture, 0,0, texture.getWidth(), texture.getHeight());
        this.setWidth(spriteCarte.getWidth()/2);
        this.setHeight(spriteCarte.getHeight()/2);
    }

    public Sprite getSpriteCarte() {
        return spriteCarte;
    }

    public void setSpriteCarte(Sprite spriteCarte) {

        this.spriteCarte = spriteCarte;
        this.setWidth(spriteCarte.getWidth()/2);
        this.setHeight(spriteCarte.getHeight()/2);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(spriteCarte, super.getX(), super.getY(),super.getOriginX(), super.getOriginY(),
                this.getWidth(), this.getHeight(), spriteCarte.getScaleX(), spriteCarte.getScaleY(), spriteCarte.getRotation());
    }
}
