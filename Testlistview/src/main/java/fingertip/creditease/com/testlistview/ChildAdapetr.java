package fingertip.creditease.com.testlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/12 下午6:00
 */

public class ChildAdapetr extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater layoutInflater;
    public ChildAdapetr(Context context) {
        mcontext=context;
        layoutInflater=LayoutInflater.from(mcontext);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView= layoutInflater.inflate(R.layout.child_item,parent,false);
        return itemView;
    }
}
