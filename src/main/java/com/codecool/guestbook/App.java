package com.codecool.guestbook;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        // create a server on port 8000
        System.out.println("test1");
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        System.out.println("test2");

        // set routes
        server.createContext("/guestbook", new GuestBook());
        server.createContext("/static", new Static());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
    }

}
