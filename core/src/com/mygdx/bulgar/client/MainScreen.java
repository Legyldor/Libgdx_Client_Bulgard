package com.mygdx.bulgar.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.bulgar.controleur.ClientImpl;

/**
 * Created by Link2 on 04/02/2016.
 */
public class MainScreen extends BaseScreen {
    public MainScreen(final UiApp app) {
        super(app);

        final TextButton buttonConnect = new TextButton("Connect", app.skin);
        Label labelPseudo = new Label("Pseudo : ", app.skin);
        final TextField textFieldPseudo = new TextField("unnamed", app.skin);

        buttonConnect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.switchScreens(new GameScreen(app, new ClientImpl("",2, "jean") ));
                /*ClientImpl client = new ClientImpl("192.168.0.103",2000,textFieldPseudo.getText());
                RoomScreen roomScreen = new RoomScreen(app, client);
                client.getGestionMess().addObserver(roomScreen);
                client.connect();
                app.switchScreens(roomScreen);
                buttonConnect.setChecked(false);*/
            }
        });
        mainTable.defaults().pad(6f);
        mainTable.setBackground(app.skin.getDrawable("default-window"));
        mainTable.setColor(app.skin.getColor("lt-blue"));
        mainTable.add(label("Bulgard", Color.GREEN));
        mainTable.row();
        mainTable.add(labelPseudo);
        mainTable.add(textFieldPseudo);
        mainTable.row();
        mainTable.add(buttonConnect);
    }

    /** used to tidy up the label adding a bit for the how to play description */
    private Label label(String text, Color color) {
        Label label = new Label(text, app.skin);
        label.setAlignment(Align.center, Align.center);
        label.setColor(color);
        return label;
    }

    @Override
    public void onBackPress() {
        Gdx.app.exit();
    }

}
