package com.mystartup.customizedlistview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

    public Context mContext;
    public LayoutInflater mLayoutInflater;
    private int images[];
    private String animalNames[];
    private String SpeedPower[];
    private ImageView animalImage;
    private TextView animalName,animalSpeedPower;

    interface OnAnimalImageClicked{
        void switchToNextActivity();
    }
    private OnAnimalImageClicked mOnAnimalImageClicked;


    public ListViewAdapter(Context context,OnAnimalImageClicked onAnimalImageClicked) {
        mContext = context;
        mOnAnimalImageClicked = onAnimalImageClicked;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        images = new int[]{R.drawable.tiger, R.drawable.leopard, R.drawable.panda, R.drawable.zebra};
        animalNames= new String[] {"Tiger","Leopard","Panda","Zebra"};
        SpeedPower=new String[]{"150km/hr,150 J","200km/hr,100J","35km/hr,300J","75km/hr,150J"};
    }




    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }





    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mLayoutInflater.inflate(R.layout.customized_list_view,null);
        animalImage = view.findViewById(R.id.animalImage);
        animalName = view.findViewById(R.id.animalName);
        animalSpeedPower = view.findViewById(R.id.animalSpeedPower);
        animalName.setText(animalName.getText().toString()+animalNames[i]);
        animalSpeedPower.setText(animalSpeedPower.getText().toString()+SpeedPower[i]);
        animalImage.setImageBitmap(decodeSampledBitmapFromResource(mContext.getResources(),images[i],120,120));
        animalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAnimalImageClicked.switchToNextActivity();

            }
        });
        return view;
    }
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}

