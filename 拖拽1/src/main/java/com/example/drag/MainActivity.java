package com.example.drag;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String IMAGEVIEW_TAG = "ee";
	private ImageView imageView;
	private LinearLayout container;
	private RelativeLayout topContainer;
	private TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		imageView = (ImageView) findViewById(R.id.img);
		imageView.setTag(IMAGEVIEW_TAG);
		container = (LinearLayout)findViewById(R.id.container);
		topContainer=(RelativeLayout)findViewById(R.id.topContainer);
		title = (TextView)findViewById(R.id.title);
		
		imageView.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				ClipData.Item item = new ClipData.Item((String) v.getTag());
				ClipData data = new ClipData(IMAGEVIEW_TAG,
						new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
						item);
				v.startDrag(data, new View.DragShadowBuilder(v), null, 0);
				return true;
			}
		});

		topContainer.setOnDragListener(new OnDragListener() {
			@Override
			public boolean onDrag(View v, DragEvent event) {
				final int action = event.getAction();
				switch (action) {
				case DragEvent.ACTION_DRAG_STARTED:
					return true;
				case DragEvent.ACTION_DRAG_ENTERED:
					return true;
				case DragEvent.ACTION_DRAG_LOCATION:
					return true;
				case DragEvent.ACTION_DRAG_EXITED:
					return true;
				case DragEvent.ACTION_DROP:
					imageView.setX(event.getX()-imageView.getWidth()/2);
					imageView.setY(event.getY()-imageView.getHeight()/2);
					return true;
				case DragEvent.ACTION_DRAG_ENDED:
					return true;
				default:
					break;
				}
				return false;
			}
		});
		
		container.setOnDragListener(new OnDragListener() {
			@Override
			public boolean onDrag(View v, DragEvent event) {
				final int action = event.getAction();
				switch (action) {
				case DragEvent.ACTION_DRAG_STARTED:
					if (event.getClipDescription().hasMimeType(
							ClipDescription.MIMETYPE_TEXT_PLAIN)) {
						return true;
					}
					return false;
				case DragEvent.ACTION_DRAG_ENTERED:
					container.setBackgroundColor(Color.YELLOW);
					return true;
				case DragEvent.ACTION_DRAG_LOCATION:
					return true;
				case DragEvent.ACTION_DRAG_EXITED:
					container.setBackgroundColor(Color.BLUE);
					title.setText("");
					return true;
				case DragEvent.ACTION_DROP:
					ClipData.Item item = event.getClipData().getItemAt(0);
					String dragData = item.getText().toString();
					title.setText(dragData+event.getY()+":"+event.getX());
					return true;
				case DragEvent.ACTION_DRAG_ENDED:
					return true;
				default:
					break;
				}
				return false;
			}
		});
	}

}
