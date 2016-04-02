package com.mygdx.bulgar.client;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.bulgar.controleur.ClientImpl;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Link2 on 04/02/2016.
 */
public class GameScreen extends BaseScreen implements Observer{

    Background background;

    public GameScreen(UiApp app, ClientImpl client) {
        super(app);

        mainTable.setColor(app.skin.getColor("lt-green"));
        background = new Background("backgroundGame.png");
        app.stage.addActor(background);
        for(int i =0;i<30; i++){
            com.mygdx.bulgar.modele.Carte carte = new com.mygdx.bulgar.modele.Carte();
            Sprite sprite = carte.getSpriteCarte();
            sprite.setPosition((float)i*10 , 0);
            carte.setSpriteCarte(sprite);
            app.stage.addActor(carte);
        }

    }

    @Override
    public void onBackPress() {
        //app.switchScreens(new MainScreen(app));
    }

    @Override
    public void update(Observable o, Object arg) {

    }

   /* public void gameOver(Stats stats) {
        app.switchScreens(new GameOverScreen(app, stats));

    }*/
}
