package com.mygdx.bulgar.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * Created by Link2 on 04/02/2016.
 */
public class Styles {
    public void styleSkin(Skin skin, TextureAtlas atlas) {
        BitmapFont font = new BitmapFont(Gdx.files.internal("utf-font.fnt"), false);
        skin.add("default", font);

        skin.add("lt-blue", new Color(.6f, .8f, 1f, 1f));
        skin.add("lt-green", new Color(.6f, .9f, .6f, 1f));
        skin.add("dark-blue", new Color(.1f, .3f, 1f, 1f));

        //NinePatchDrawable btn1up = new NinePatchDrawable(atlas.createPatch("patchThick"));
        //NinePatchDrawable btn1down = new NinePatchDrawable(atlas.createPatch("patchThickDown"));
        //NinePatch window1patch = atlas.createPatch("window1");
        //skin.add("btn1up", btn1up);
        //skin.add("btn1down", btn1down);
        //skin.add("window1", window1patch);
        //skin.add("white-pixel", atlas.findRegion("white-pixel"), TextureRegion.class);

        //Label.LabelStyle lbs = new Label.LabelStyle();
        //lbs.font = font;
        //lbs.fontColor = Color.WHITE;
        //skin.add("default", lbs);

        //TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle(btn1up, btn1down, btn1down, font);
        //tbs.fontColor = skin.getColor("dark-blue");
        //tbs.pressedOffsetX = Math.round(1f * Gdx.graphics.getDensity());
        //tbs.pressedOffsetY = tbs.pressedOffsetX * -1f;
        //skin.add("default", tbs);
    }
}
