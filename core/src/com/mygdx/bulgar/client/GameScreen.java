package com.mygdx.bulgar.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.bulgar.controleur.ClientImpl;
import com.mygdx.bulgar.controleur.SFactoryCarte;
import com.mygdx.bulgar.modele.Adversaire;
import com.mygdx.bulgar.modele.Carte;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by Link2 on 04/02/2016.
 */
public class GameScreen extends BaseScreen implements Observer{

    Background background;
    private ArrayList<Adversaire> adversaires;
    private ArrayList<Carte> cartesMain;
    private ArrayList<Carte> cartesPose;
    private int indiceCartes = 0;
    private Table tableCarteJouable = new Table(app.skin);
    private Table tableSelectionCarte = new Table(app.skin);
    private Table tablePiocheTas = new Table(app.skin);
    private int NBCARTESAFFICHE = 10;

    public GameScreen(UiApp app, ClientImpl client) {
        super(app);
        configUI();
        //Toute les cartes en main
        this.cartesMain = new ArrayList<Carte>();
        for(int i =1;i<=13;i++){
            this.cartesMain.add(SFactoryCarte.getInstance().getCarte(SFactoryCarte.CARREAU, i));
            this.cartesMain.add(SFactoryCarte.getInstance().getCarte(SFactoryCarte.COEUR, i));
            this.cartesMain.add(SFactoryCarte.getInstance().getCarte(SFactoryCarte.TREFLE, i));
            this.cartesMain.add(SFactoryCarte.getInstance().getCarte(SFactoryCarte.PIQUE, i));
        }
        //init les adversaires
        adversaires = new ArrayList<Adversaire>();
        for(int i =0; i<5;i++){
            Adversaire add = new Adversaire();
            add.setNom("Unnamed");
            add.setNbCarte(0);
            ArrayList<Carte> cartes = new ArrayList<Carte>();
            cartes.add(SFactoryCarte.getInstance().getCarte(SFactoryCarte.PIQUE, 1));
            cartes.add(SFactoryCarte.getInstance().getCarte(SFactoryCarte.TREFLE, 10));
            cartes.add(SFactoryCarte.getInstance().getCarte(SFactoryCarte.CARREAU, 7));
            add.setCartes(cartes);
            adversaires.add(add);
        }
        //Init carte posé
        cartesPose = new ArrayList<Carte>();
        for(int i =1; i<=3;i++){
            cartesPose.add(SFactoryCarte.getInstance().getCarte(SFactoryCarte.CARREAU,i));
        }
        initUI();
    }

    private void configUI(){
        Label label = new Label("",app.skin);
        TextField textField = new TextField("", app.skin);
        TextButton textButton = new TextButton("", app.skin);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("cardigan.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        textField.getStyle().font = generator.generateFont(parameter);
        label.getStyle().font = generator.generateFont(parameter);
        textButton.getStyle().font = generator.generateFont(parameter);
        generator.dispose();
    }

    public void initUI(){
        //mainTable.setDebug(true);
        background = new Background("backgroundGame.png");
        app.stage.addActor(background);
        afficherAdversaire(this.adversaires);
        mainTable.row();
        //tas et pioche
        poserPioche(0);
        poserTas(SFactoryCarte.getInstance().getCarte(SFactoryCarte.TREFLE,12));
        mainTable.add(tablePiocheTas).center().colspan(mainTable.getColumns());
        mainTable.row();
        Table tableCartePose = new Table(app.skin);
        for(int i =0;i<cartesPose.size();i++){
            tableCartePose.add(cartesPose.get(i));
        }
        mainTable.add(tableCartePose).center().colspan(mainTable.getColumns());
        mainTable.row();
        initButton();
        mainTable.add(tableSelectionCarte).center().colspan(mainTable.getColumns());
    }

    public void initButton(){
        Carte carte = new Carte();
        TextButton buttonPrecedent = new TextButton("<=",app.skin);
        buttonPrecedent.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (indiceCartes != 0) {
                    indiceCartes--;
                    creerUICartesMain();
                }
            }
        });
        tableSelectionCarte.add(buttonPrecedent).width(carte.getWidth()).height(carte.getHeight());
        creerUICartesMain();
        tableSelectionCarte.add(tableCarteJouable);
        TextButton buttonSuivant = new TextButton("=>",app.skin);
        buttonSuivant.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if((indiceCartes+NBCARTESAFFICHE) < cartesMain.size()){
                    indiceCartes++;
                    creerUICartesMain();
                }
            }
        });
        tableSelectionCarte.add(buttonSuivant).width(carte.getWidth()).height(carte.getHeight());
    }

    public void creerUICartesMain(){
        this.tableCarteJouable.clear();
        for(int i = indiceCartes; i<(NBCARTESAFFICHE+indiceCartes);i++){
            tableCarteJouable.add(this.cartesMain.get(i));
        }
    }

    public void poserPioche(int nbRestant){
        Pioche pioche = new Pioche(nbRestant, app.skin);
        tablePiocheTas.add(pioche);
    }

    public void poserTas(Carte carte){
        if(carte != null){
            Sprite sprite = carte.getSpriteCarte();
            carte.setSpriteCarte(sprite);
            tablePiocheTas.add(carte).width(carte.getWidth()).height(carte.getHeight());
        }
    }

    public void afficherAdversaire(ArrayList<Adversaire> adversaires){
        for(int i =0; i<adversaires.size();i++){
            Table tableAdversaire = new Table(app.skin);
            Label labelJ = new Label(adversaires.get(i).getNom(), app.skin);
            tableAdversaire.add(labelJ);
            tableAdversaire.row();
            Label labelNbCarte = new Label("cartes : "+ adversaires.get(i).getNbCarte(), app.skin);
            tableAdversaire.add(labelNbCarte);
            tableAdversaire.row();
            Table tableCarteAdversaire = new Table();
            for(int y =0;y<adversaires.get(i).getCartes().size(); y++){
                if(adversaires.get(i).getCartes().get(y) != null){
                    tableCarteAdversaire.add(adversaires.get(i).getCartes().get(y))
                            .width((float) (adversaires.get(i).getCartes().get(y).getWidth()/(1.5)))
                            .height((float) (adversaires.get(i).getCartes().get(y).getHeight()/(1.5)));
                }
            }
            tableAdversaire.add(tableCarteAdversaire);
            mainTable.add(tableAdversaire).expandX();
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
