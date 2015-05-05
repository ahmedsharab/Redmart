package co.nz.kastana.android.redmart.assignment.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import co.nz.kastana.android.redmart.assignment.R;

import static co.nz.kastana.android.redmart.assignment.R.string.uri_images_parent;
import static co.nz.kastana.android.redmart.assignment.util.Arguments.ARG_IMAGE;
import static co.nz.kastana.android.redmart.assignment.util.Arguments.ARG_URL;
/**
 * Created by ahmed on 4/05/15.
 */
public class ImageFragment extends Fragment {

    private final static int LAYOUT_RES_ID = R.layout.fragment_slide;
    private View root;
    public static ImageFragment instantiate(Bundle args) {
        ImageFragment fragment = new ImageFragment();
        if (args != null) {
            fragment.setArguments(args);
        }
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(LAYOUT_RES_ID, container, false);

        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments().getString(ARG_URL) != null) {
            Picasso.with(getActivity().getApplicationContext()).
                    load(new StringBuilder().append(getString(uri_images_parent)).append(getArguments().getString(ARG_URL)).toString()).
                    into((ImageView)root.findViewById(R.id.appIcon));
        }
        else if(getArguments().getByteArray(ARG_IMAGE) != null) {
            byte[] byteArray = getArguments().getByteArray(ARG_IMAGE);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            ((ImageView) root.findViewById(R.id.appIcon)).setImageBitmap(bitmap);
        }


    }
}
