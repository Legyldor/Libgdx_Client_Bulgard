package com.mygdx.bulgar.controleur;

import java.io.IOException;

/**
 * Created by Alexandre on 01/02/2016.
 */
public interface Client {

    void connect();

    void disconnect();

    boolean isConnected();

    void send(final String message) throws IOException;
}
