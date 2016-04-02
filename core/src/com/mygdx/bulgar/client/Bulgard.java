package com.mygdx.bulgar.client;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.bulgar.controleur.ClientImpl;

public class Bulgard extends UiApp {
	@Override
	protected String atlasPath() {
		return "uiskin.atlas";
	}

	@Override
	protected String skinPath() {
		return "uiskin.json";
	}
	@Override
	protected void styleSkin(Skin skin, TextureAtlas atlas) {
		new Styles().styleSkin(skin, atlas);
	}

	@Override
	protected BaseScreen getFirstScreen() {
		return new MainScreen(this);
	}


}
