package mooc.imagedownloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;

public class ImageDownloaderContext {
	private final String TAG =
			ImageDownloaderContext.class.getSimpleName();
	//UI references
	private final WeakReference<EditText> mUrlEditText;	
	private final WeakReference<ImageView> mImageView;
	private final WeakReference<Button> mGrayscaleButton;
	private final WeakReference<Button> mResetButton;
	private final WeakReference<Activity> mActivity;
	//other variables
	private Uri mDownloadedImageUri;
	
	public ImageDownloaderContext(EditText editText,
            ImageView imageView,
            Button grayscaleButton,
            Button resetButton,
            Activity activity) {
		mUrlEditText = new WeakReference<EditText>(editText);
		mImageView = new WeakReference<ImageView>(imageView);
		mGrayscaleButton = new WeakReference<Button>(grayscaleButton);
		mResetButton = new WeakReference<Button>(resetButton);
		mActivity = new WeakReference<Activity>(activity);
	}
	
	public Activity getActivity(){
		return mActivity.get();
	}
	
	public Uri getImageUrl(){
		return Uri.parse(mUrlEditText.get().getText().toString());
	}
	
	public void resetImage(){
		setEnableSecondaryActionButtons(false);
		mImageView.get().setImageBitmap(null);
	}
	
	public void setDownloadedImage(Uri imageUri){
		if(imageUri == null) {
			Toast.makeText(mActivity.get(), R.string.msg_invalid_image, Toast.LENGTH_LONG).show();
			return;
		}
		mDownloadedImageUri = imageUri;
		LoadImage(mDownloadedImageUri);
	}
	
	public void setGrayscaleImage(Uri imageUri){
		if(imageUri == null) {
			Toast.makeText(mActivity.get(), R.string.msg_invalid_image, Toast.LENGTH_LONG).show();
			return;
		}
		LoadImage(imageUri);
	}
	
	public Uri getDownloadedImageUri(){
		return mDownloadedImageUri;
	}
	
	private void LoadImage(Uri filepath){
		try {
			displayImage(readImageFromFile(filepath));
			setEnableSecondaryActionButtons(true);
		} catch (FileNotFoundException e) {
			mDownloadedImageUri = null;
			e.printStackTrace();
		}		
	}
	
	private Bitmap readImageFromFile(Uri filepath) throws FileNotFoundException{
		File imgFile = new File(filepath.toString());
		if(!imgFile.exists()) return null;
		Bitmap bmp = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		return bmp;
	}
	
	private void displayImage(Bitmap image){
		if (image == null)
            Toast.makeText(mActivity.get(), R.string.msg_invalid_image, Toast.LENGTH_LONG).show();
        else {
            // Display the image on the user's screen.
            mImageView.get().setImageBitmap(image);
        }
	}
	
	private void setEnableSecondaryActionButtons(boolean enable){
		mGrayscaleButton.get().setEnabled(true);
		mResetButton.get().setEnabled(true);
	}
}
