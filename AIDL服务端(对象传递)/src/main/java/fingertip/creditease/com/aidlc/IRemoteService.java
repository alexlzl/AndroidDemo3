package fingertip.creditease.com.aidlc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/9 下午1:45
 */

public class IRemoteService extends Service {
    public IRemoteService() {
    }

    private ArrayList<Person> persons;

    /**
     * 当客户端绑定到该服务的时候，会执行 * * @param intent * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        return iBinder;
//        return null;
    }

    private IBinder iBinder = new IImoocAidl.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.d("TAG", "收到了远程的请求，输入的参数是 " + num1 + " 和 " + num2);
            return num1 + num2;
        }



        @Override
        public List<Person> addPerson(Person person) throws RemoteException {
            persons.add(person);
            return persons;
        }
    };

}
