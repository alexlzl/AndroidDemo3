package fingertip.creditease.com.testlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Describe：
 * <p>
 * Author：LZL
 * <p>
 * Date：2017/1/12 下午5:51
 */

public class ParentAdapter extends BaseAdapter {
    private  Context mcontext;
    private LayoutInflater layoutInflater;
    public ParentAdapter(Context context) {
        mcontext=context;
        layoutInflater=LayoutInflater.from(mcontext);
    }

    @Override
    public int getCount() {
        return 10;
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
        View itemView= layoutInflater.inflate(R.layout.parent_item,parent,false);
        TextView textView= (TextView) itemView.findViewById(R.id.parent_tv);

        final ScrollViewListView listView= (ScrollViewListView) itemView.findViewById(R.id.child_lv);
        listView.setAdapter(new ChildAdapetr(mcontext));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listView.getVisibility()==View.VISIBLE){
                    listView.setVisibility(View.GONE);
                }else {
                    listView.setVisibility(View.VISIBLE);
                }

            }
        });
        return  itemView;
    }
}
