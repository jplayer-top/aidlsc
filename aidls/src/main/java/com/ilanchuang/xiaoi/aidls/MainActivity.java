package com.ilanchuang.xiaoi.aidls;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ilanchuang.xiaoi.aidlc.PersonBean;
import com.ilanchuang.xiaoi.aidlc.PersonListener;
import com.ilanchuang.xiaoi.aidlc.PersonManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind();
            }
        });
        findViewById(R.id.btnClick2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mPersonManager.addPerson(new PersonBean("1", "2", "3"));
                    mPersonManager.getPersons();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btnClick3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mPersonManager.onAddListener(mPersonListener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private PersonListener mPersonListener = new PersonListener.Stub() {
        @Override
        public void onSuccess(String ok) throws RemoteException {
            System.out.println(ok);
        }

        @Override
        public void onError(String error) throws RemoteException {
            System.out.println(error);
        }
    };

    private void bind() {
        Intent intentService = new Intent("com.ilanchuang.xiaoi.aidlc.AIDLServer");
        intentService.setPackage("com.ilanchuang.xiaoi.aidlc");
        intentService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.bindService(intentService, mConnection, Context.BIND_AUTO_CREATE);
    }

    private PersonManager mPersonManager;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPersonManager = PersonManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mPersonManager = null;
        }
    };

}
