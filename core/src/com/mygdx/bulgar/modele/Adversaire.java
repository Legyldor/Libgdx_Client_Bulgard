package com.mygdx.bulgar.modele;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Created by Link2 on 18/04/2016.
 */
public class Adversaire{

    private int nbCarte;
    private String nom;
    private ArrayList<Carte> cartes;

    public int getNbCarte() {
        return nbCarte;
    }

    public String getNom() {
        return nom;
    }

    public void setNbCarte(int nbCarte) {
        this.nbCarte = nbCarte;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }

    public Adversaire() {

    }
}
