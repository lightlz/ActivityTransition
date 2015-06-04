package com.light.activitytransition;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by light on 15/5/22.
 */
public class DetialActivity extends Activity{

    public static final String COLOR_INDEX = "detial_index";

    public static final String DETIAL_IMAGE = "detial_image";

    public static final String DETIAL_NAME = "detial_name";

    public static final int CHANGE_COLOR = 0;

    private ImageView mHeaderImageView;

    private TextView mHeaderTitle;

    private ColorBean bean;

    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.Adapter adapter;

    private List<ColorBean> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        bean = ColorBean.getItem(getIntent().getIntExtra(COLOR_INDEX,-1));

        //隐藏标题栏
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        initView();
    }

    private void initView(){

        mHeaderImageView = (ImageView) findViewById(R.id.imageview_header);
        mHeaderTitle = (TextView) findViewById(R.id.textview_title);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        ViewCompat.setTransitionName(mHeaderImageView, DETIAL_IMAGE);
        ViewCompat.setTransitionName(mHeaderTitle, DETIAL_NAME);

        addTransitionListener();


        dataSet = new ArrayList<ColorBean>();
        adapter = new MyAdapter(this,dataSet,handler);

        recyclerView.setHasFixedSize(true);
        layoutManager = new FullyLinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    /**
    * show image & get palette
    * */
    private void show(){

        mHeaderTitle.setText(bean.getColorName());
       // mHeaderImageView.setImageResource(bean.getColorRes());
        Bitmap bitmap = ImageUtil.compressPixel(this, bean.getColorRes(), 480, 480);

        if(bitmap != null ){
            mHeaderImageView.setImageBitmap(bitmap);

            Palette.generateAsync(bitmap,new Palette.PaletteAsyncListener(){

                @Override
                public void onGenerated(Palette palette) {

                    //充满活力
                    Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                    Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
                    Palette.Swatch paletteDarkVibrantSwatch = palette.getDarkVibrantSwatch();

                    //柔和
                    Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                    Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
                    Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();

                    //设置状态栏和导航栏颜色
                    if (android.os.Build.VERSION.SDK_INT >= 21) {
                        changeColor(darkMutedSwatch.getRgb());
                    }
                    //获取列表数据

                    ColorBean colorBean = new ColorBean("Vibrant",vibrantSwatch.getRgb());
                    ColorBean colorBean1 = new ColorBean("Vibrant Light",lightVibrantSwatch.getRgb());
                    ColorBean colorBean2 = new ColorBean("Vibrant Dark",paletteDarkVibrantSwatch.getRgb());
                    ColorBean colorBean3 = new ColorBean("Muted",mutedSwatch.getRgb());
                    ColorBean colorBean4 = new ColorBean("Muted Light",lightMutedSwatch.getRgb());
                    ColorBean colorBean5 = new ColorBean("Muted ",darkMutedSwatch.getRgb());

                    dataSet.add(colorBean);
                    dataSet.add(colorBean1);
                    dataSet.add(colorBean2);
                    dataSet.add(colorBean3);
                    dataSet.add(colorBean4);
                    dataSet.add(colorBean5);

                    adapter.notifyDataSetChanged();

                }
            });

        }else{
            mHeaderImageView.setImageResource(R.color.blue);
        }

    }


    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int color = msg.arg1;

            switch (msg.what){
                case CHANGE_COLOR:
                    changeColor(color);
                    break;
            }
        }
    };

    /**
     * TransitionListener
     * @return
     */
    private boolean addTransitionListener() {
        final Transition transition = getWindow().getSharedElementEnterTransition();

        if (transition != null) {
            // There is an entering shared element transition so add a listener to it
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionEnd(Transition transition) {
                    // As the transition has ended, we can now load the full-size image
                    show();
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionStart(Transition transition) {
                    // No-op
                }

                @Override
                public void onTransitionCancel(Transition transition) {
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionPause(Transition transition) {
                    // No-op
                }

                @Override
                public void onTransitionResume(Transition transition) {
                    // No-op
                }
            });
            return true;
        }
        // If we reach here then we have not added a listener
        return false;
    }


    private void changeColor(int color){

        Window window = getWindow();
        window.setStatusBarColor(color);
        window.setNavigationBarColor(color);
    }

}
