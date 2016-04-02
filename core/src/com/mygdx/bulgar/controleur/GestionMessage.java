package com.mygdx.bulgar.controleur;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.*;
import com.mygdx.bulgar.modele.Message;
import com.mygdx.bulgar.modele.Room;

import java.util.Observable;

/**
 * Created by Alexandre on 03/02/2016.
 */
public class GestionMessage extends Observable {

    private XmlReader xmlReader = new XmlReader() ;


    public GestionMessage(){

    }

    public void handleMessage(String message){
        gestionMessage(getTypeMessage(message), message);
    }

    public String getTypeMessage(String message){
        Element element = xmlReader.parse(message);
        if(element != null){
            return element.getName();
        }
        return "";
    }

    public void gestionMessage(String typeMessage, String message){
        if("tchat".equals(typeMessage)){
            setChanged();
            notifyObservers(getTchatMessage(message));
        }else if("allRoom".equals(typeMessage)){
            setChanged();
            notifyObservers(getRoom(message));
        }else if("go".equals(typeMessage)){
            setChanged();
            notifyObservers(typeMessage);
        }
    }

    public Message getTchatMessage(String message){
        Element elementBase = xmlReader.parse(message);
        String messageTchat = elementBase.getText();
        Message messageObject = new Message(messageTchat);
        return messageObject;
    }

    public Array<Room> getRoom(String message){
        Element elementBase = xmlReader.parse(message);
        Array<Element> elementsRoom = elementBase.getChildrenByName("room");
        Array<Room> listeRooms = new Array<Room>();
        for(int i = 0; i<elementsRoom.size;i++){
            int numRoom = elementsRoom.get(i).getInt("numRoom");
            int nbJoueur = elementsRoom.get(i).getInt("nbJoueur");
            Room room = new Room(numRoom, nbJoueur);
            listeRooms.add(room);
        }
        return listeRooms;
    }
}