package com.mygdx.bulgar.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Link2 on 07/03/2016.
 */
public class Background extends Actor{

    Sprite spriteBackground;

    public Background(String urlBackground) {
        Texture texture = new Texture(urlBackground);
        spriteBackground = new Sprite(texture,0,0,texture.getWidth(), texture.getHeight());
    }

    public Sprite getSpriteBackground() {
        return spriteBackground;
    }

    public void setSpriteBackground(Sprite spriteBackground) {
        this.spriteBackground = spriteBackground;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(spriteBackground, 0, 0, spriteBackground.getOriginX(), spriteBackground.getOriginY(),
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), spriteBackground.getScaleX(), spriteBackground.getScaleY(), spriteBackground.getRotation());
    }
}
