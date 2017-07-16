package fingertip.creditease.com.aidlc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/2/17 下午2:53
 */

public class Student implements Parcelable{
    private String name;
    private  int age;

    protected Student(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
