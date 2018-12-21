package com.ilanchuang.xiaoi.aidlc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Obl on 2018/12/21.
 * com.ilanchuang.xiaoi.com.ilanchuang.xiaoi.aidlc
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class PersonBean implements Parcelable{
    public String name;
    public String age;
    public String sex;

    public PersonBean(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    protected PersonBean(Parcel in) {
        name = in.readString();
        age = in.readString();
        sex = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(sex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public static final Creator<PersonBean> CREATOR = new Creator<PersonBean>() {
        @Override
        public PersonBean createFromParcel(Parcel in) {
            return new PersonBean(in);
        }

        @Override
        public PersonBean[] newArray(int size) {
            return new PersonBean[size];
        }
    };
}
