package com.dkotov.positionsender.ws;


import android.net.Uri;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by dkotov on 25-May-16.
 */
public class SocketHelper {
    public SocketHelper() {
    }

    public WebSocketClient getmWebSocketClient() {
        return mWebSocketClient;
    }

    private WebSocketClient mWebSocketClient;

    private void connectWebSocket(String username, String password) {
        URI uri;
        Uri.Builder builder = Uri.parse("ws://mini-mdt.wheely.com").buildUpon();
        builder.appendQueryParameter("Username", username);
        builder.appendQueryParameter("Password", password);

        try {
            uri = new URI(builder.build().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.i("Websocket", " Connection opened");
            }

            @Override
            public void onMessage(String message) {
                Log.i("Message", " Received " + message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i("Websocket", " Connection closed " + reason);

            }

            @Override
            public void onError(Exception ex) {
                Log.i("Websocket", " Error " + ex.getMessage());
            }
        };

        mWebSocketClient.connect();
    }
}
