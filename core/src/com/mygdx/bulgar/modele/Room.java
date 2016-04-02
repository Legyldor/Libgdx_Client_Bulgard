package com.mygdx.bulgar.modele;

/**
 * Created by Link2 on 13/02/2016.
 */
public class Room {

    private int numRoom;
    private int nbJoueur;

    public Room(int numRoom, int nbJoueur) {
        this.numRoom = numRoom;
        this.nbJoueur = nbJoueur;
    }

    public int getNumRoom() {
        return numRoom;
    }

    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }

    public int getNbJoueur() {
        return nbJoueur;
    }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }
}
