package mooc.imagedownloader;

import java.lang.ref.WeakReference;
import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageDownloaderContext {
	private final String TAG =
			ImageDownloaderContext.class.getSimpleName();
	
	private final WeakReference<EditText> mUrlEditText;
	
	private final WeakReference<ImageView> mImageView;
	
	private final WeakReference<Activity> mActivity;
	
	public ImageDownloaderContext(EditText editText,
            ImageView imageView,
            Activity activity) {
		mUrlEditText = new WeakReference<EditText>(editText);
		mImageView = new WeakReference<ImageView>(imageView);
		mActivity = new WeakReference<Activity>(activity);
	}
	
	public Activity getActivity(){
		return mActivity.get();
	}
}
