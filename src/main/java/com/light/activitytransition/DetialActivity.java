package com.light.activitytransition;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by light on 15/5/22.
 */
public class DetialActivity extends Activity{

    public static final String COLOR_INDEX = "detial_index";

    public static final String DETIAL_IMAGE = "detial_image";

    public static final String DETIAL_NAME = "detial_name";

    private ImageView mHeaderImageView;

    private TextView mHeaderTitle;

    private ColorBean bean;

    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        bean = ColorBean.getItem(getIntent().getIntExtra(COLOR_INDEX,-1));

        initView();
    }

    private void initView(){

        mHeaderImageView = (ImageView) findViewById(R.id.imageview_header);
        mHeaderTitle = (TextView) findViewById(R.id.textview_title);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        ViewCompat.setTransitionName(mHeaderImageView, DETIAL_IMAGE);
        ViewCompat.setTransitionName(mHeaderTitle, DETIAL_NAME);

        addTransitionListener();

        adapter = new MyAdapter(this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new FullyLinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    private void show(){

        mHeaderTitle.setText(bean.getColorName());
       // mHeaderImageView.setImageResource(bean.getColorRes());
        mHeaderImageView.setImageBitmap(ImageUtil.compressPixel(this,
                bean.getColorRes(),480,480));
    }

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

}
