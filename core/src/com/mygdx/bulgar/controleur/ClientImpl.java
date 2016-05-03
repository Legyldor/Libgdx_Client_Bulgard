package com.mygdx.bulgar.controleur;

/**
 * Created by Alexandre on 01/02/2016.
 */



import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;


public class ClientImpl implements Client {


    private String host;
    private int port;
    private String name;
    private GestionMessage gestionMess;
    private Socket socket;
    private Thread ecriture;

    public GestionMessage getGestionMess() {
        return gestionMess;
    }

    public void setGestionMess(GestionMessage gestionMess) {
        this.gestionMess = gestionMess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private volatile AtomicBoolean isFinished = new AtomicBoolean();

    public ClientImpl(final String host, final int port, final String name) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.gestionMess = new GestionMessage();
    }

    @Override
    public void connect() {

        SocketHints hints = new SocketHints();
        socket = Gdx.net.newClientSocket(Net.Protocol.TCP,host,port,hints);

        System.out.println("CONNECT !!!!");
        ecriture = new Thread(){
            public void run() {
                PrintWriter  out = null;
                Scanner sysIn = new Scanner(System.in);
                try {
                    out = new PrintWriter(socket.getOutputStream());
                    out.println(name);
                    out.flush();

                    while (sysIn.hasNext() && !isFinished.get()) {
                        String line = sysIn.nextLine();
                        if ("exit".equals(line)) {
                            synchronized (isFinished) {
                                isFinished.set(true);
                            }
                        }
                        out.println(line);
                        out.flush();
                        //disconnect();
                    }
                }finally {
                    if (out != null) {
                        out.close();
                    }
                }
            }
        };
        ecriture.start();

        final Thread lecture = new Thread() {
            @Override
            public void run() {
                // Use a Scanner to read from the remote server

                Scanner in = null;
                try {
                    in = new Scanner(socket.getInputStream());
                    String line = in.nextLine();
                    while (!isFinished.get()) {
                        //System.out.println(line);
                        gestionMess.handleMessage(line);
                        line = in.nextLine();
                    }
                }finally {
                    if (in != null) {
                        in.close();
                    }
                }
            };
        };
        lecture.start();


    }

    @Override
    public void disconnect() {
       /* try {
            socket.close();
        } catch (IOException e) {

			e.printStackTrace();
        }*/
    }

    @Override
    public boolean isConnected() {
        return socket.isConnected();
    }

    @Override
    public void send(String message) {
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println(message);
        out.flush();
    }





}