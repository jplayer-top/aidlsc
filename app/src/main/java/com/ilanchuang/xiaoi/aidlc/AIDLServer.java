package com.ilanchuang.xiaoi.aidlc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Obl on 2018/12/21.
 * com.ilanchuang.xiaoi.com.ilanchuang.xiaoi.aidlc
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class AIDLServer extends Service {
    private ArrayList<PersonBean> mPersons;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPersons = new ArrayList<>();
        return mIBinder;
    }

    private IBinder mIBinder = new PersonManager.Stub() {

        @Override
        public List<PersonBean> getPersons() throws RemoteException {
            System.out.println(mPersons);
            return mPersons;
        }

        @Override
        public void addPerson(PersonBean person) throws RemoteException {
            mPersons.add(person);
        }

        @Override
        public void onAddListener(final PersonListener listener) throws RemoteException {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(3000);
                    try {
                        listener.onSuccess("OJBK");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(5000);
                    try {
                        listener.onError("ERROR");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    };
}
