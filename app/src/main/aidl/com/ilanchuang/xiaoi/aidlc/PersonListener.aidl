// PersonListener.aidl
package com.ilanchuang.xiaoi.aidlc;

// Declare any non-default types here with import statements

interface PersonListener {
   void onSuccess(in String ok);
   void onError(in String error);
}
