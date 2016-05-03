package com.mygdx.bulgar.client;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.bulgar.controleur.ClientImpl;
import com.mygdx.bulgar.controleur.GestionMessage;
import com.mygdx.bulgar.modele.Message;
import com.mygdx.bulgar.modele.Room;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Link2 on 04/02/2016.
 */
public class RoomScreen extends BaseScreen implements Observer{

    private ScrollPane scroll;
    private ScrollPane scrollInfo;
    private Table tableTchat;
    private Table tableInfo;
    private TextButton textButtonJouer;
    private TextField textFieldMessage;
    private TextButton textButtonMessage;
    private ClientImpl client;

    public RoomScreen(final UiApp app, final ClientImpl client) {
        super(app);
        configUI(50);
        this.client = client;

        textFieldMessage = new TextField("",app.skin);
        textButtonMessage= new TextButton("Envoyer", app.skin);
        textButtonMessage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!"".equals(textFieldMessage.getText())) {
                    client.send("<tchat>" + textFieldMessage.getText() + "</tchat>");
                    Label label = new Label("["+client.getName()+"] : "+textFieldMessage.getText(),app.skin);
                    label.setColor(Color.GREEN);
                    tableTchat.add(label).left();
                    tableTchat.row();
                    textFieldMessage.setText("");
                }
            }
        });

        tableTchat = new Table();
        scroll = new ScrollPane(tableTchat, app.skin);
        scroll.setWidth((float) (mainTable.getWidth() * (0.8)));
        scroll.setHeight((float) (mainTable.getHeight() * (0.8)));
        tableTchat.pad(10).defaults().expandX().space(4);
        tableInfo = new Table();
        scrollInfo = new ScrollPane(tableInfo, app.skin);

        textButtonJouer = new TextButton("Jouer", app.skin);
        textButtonJouer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final Dialog dialog = new Dialog("Choix du mode de jeu", app.skin);
                for(int i=2;i<7; i++){
                    final int indice = i;
                    TextButton textButton = new TextButton(i+" Joueurs", app.skin);
                    textButton.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            client.send("<ready nbJoueur=\""+indice+"\"></ready>");
                            dialog.hide();
                        }
                    });
                    dialog.add(textButton).height(100);
                }
                dialog.row();
                TextButton textButtonClose = new TextButton("Close", app.skin);
                textButtonClose.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        dialog.hide();
                    }
                });
                dialog.add(textButtonClose).expandX().center().colspan(dialog.getColumns()).height(100);
                dialog.show(app.stage);
            }
        });

        mainTable.add(scroll).expand().fill().colspan(4);
        mainTable.add(scrollInfo).expand().fill();
        mainTable.row().space(10).padBottom(10);
        mainTable.add(textFieldMessage).expandX().fillX().colspan(4).height(100);
        mainTable.add(textButtonMessage).expandX().fillX().height(100);
        mainTable.row();
        mainTable.add(textButtonJouer).expandX().fillX().colspan(mainTable.getColumns());
    }

    private void configUI(int size){
        Label label = new Label("",app.skin);
        TextField textField = new TextField("", app.skin);
        TextButton textButton = new TextButton("", app.skin);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("cardigan.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        textField.getStyle().font = generator.generateFont(parameter);
        label.getStyle().font = generator.generateFont(parameter);
        textButton.getStyle().font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void onBackPress() {
        app.switchScreens(new MainScreen(app));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof GestionMessage){
            if(arg instanceof Message){
                Label label = new Label(((Message) arg).getMessage() ,app.skin);
                this.tableTchat.add(label).left();
                this.tableTchat.row();
            }else if(arg instanceof Array){
                this.tableInfo.clearChildren();
                for(int i = 0; i < ((Array) arg).size; i++){
                    Table table = new Table(app.skin);
                    Label labelRoomTitre = new Label(("Room " + ((Room)((Array) arg).get(i)).getNumRoom()), app.skin);
                    Label labelNbJoueur = new Label(((Room)((Array) arg).get(i)).getNbJoueur()+" Joueurs", app.skin);
                    table.add(labelRoomTitre).left();
                    table.row();
                    table.add(labelNbJoueur).right();
                    this.tableInfo.add(table).top();
                    this.tableInfo.row();
                }
            }else if(arg instanceof String){
                app.switchScreens(new GameScreen(app, client));
            }
        }
    }
}