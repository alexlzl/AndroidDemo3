package fingertip.creditease.com.a50;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;

public class CollapsingToolbarLayout1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout1);
        final CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.ctl); ctl.setTitle("Cool UI");
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.banner);
        Palette.generateAsync(bmp, new Palette.PaletteAsyncListener() {
        public void onGenerated(Palette palette) {
            Palette.Swatch swatch = palette.getDarkMutedSwatch();
            ctl.setContentScrimColor(swatch.getRgb()); }
        });
    }
}
