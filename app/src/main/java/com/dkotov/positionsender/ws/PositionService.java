package com.dkotov.positionsender.ws;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.dkotov.positionsender.model.Request;
import com.dkotov.positionsender.model.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dkotov on 25-May-16.
 */
public class PositionService extends Service {

    SocketHelper mSocketHelper;
    Thread mPositionSender;
    Request mRequest;
    Response mResponse;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            mSocketHelper = new SocketHelper();
            Method connectWebSocketMethod = SocketHelper.class.getDeclaredMethod("connectWebSocket", String.class, String.class);
            connectWebSocketMethod.setAccessible(true);
            connectWebSocketMethod.invoke(mSocketHelper, intent.getStringExtra("login"), intent.getStringExtra("password"));
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mPositionSender.start();
        Toast.makeText(getBaseContext(), "Service started", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    public void createThread() {
        mPositionSender = new Thread(){
            public void run(){
                LocationManager locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
                mRequest = new Request(0,0);
                mSocketHelper.getmWebSocketClient().send(mRequest.toString());
            }

        };

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPositionSender.interrupt();
        Toast.makeText(getBaseContext(), "Service stopped", Toast.LENGTH_SHORT).show();
    }
}
