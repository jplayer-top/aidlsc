// PersonManager.aidl
package com.ilanchuang.xiaoi.aidlc;
import com.ilanchuang.xiaoi.aidlc.PersonListener;
import com.ilanchuang.xiaoi.aidlc.PersonBean;
// Declare any non-default types here with import statements

interface PersonManager {
  List<PersonBean> getPersons();
  void addPerson(in PersonBean person);
  void onAddListener(PersonListener listener);
}
