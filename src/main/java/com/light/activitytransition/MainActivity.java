package com.light.activitytransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends Activity {

    private GridView gridView;

    private GridViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        gridView = (GridView)findViewById(R.id.grid_mains);

        adapter = new GridViewAdapter();

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(MainActivity.this,DetialActivity.class);
                intent.putExtra(DetialActivity.COLOR_INDEX,position);

                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this,

                        new Pair<View, String>(view.findViewById(R.id.imageview_item),
                                DetialActivity.DETIAL_IMAGE),
                        new Pair<View, String>(view.findViewById(R.id.textview_name),
                                DetialActivity.DETIAL_NAME));

                // Now we can start the Activity, providing the activity options as a bundle
                ActivityCompat.startActivity(MainActivity.this, intent, activityOptions.toBundle());


            }
        });

    }



    class GridViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return ColorBean.COLOR_BEAN.length;
        }

        @Override
        public Object getItem(int position) {
            return ColorBean.getItem(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null){
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.grid_item,null);
            }

            ColorBean bean = ColorBean.getItem(position);

            ImageView image = (ImageView) convertView.findViewById(R.id.imageview_item);
            //image.setImageResource(bean.getColorRes());
            image.setImageBitmap(ImageUtil.compressPixel(MainActivity.this,
                    bean.getColorRes(),240,240));

            TextView name = (TextView) convertView.findViewById(R.id.textview_name);
            name.setText(bean.getColorName());

            return convertView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
