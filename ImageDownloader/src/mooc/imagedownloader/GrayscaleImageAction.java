package mooc.imagedownloader;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

public class GrayscaleImageAction implements ButtonAction{
	private final String TAG =
			GrayscaleImageAction.class.getSimpleName();
	private ImageDownloaderContext mImageDownloadContext;
	
	@Override
	public void execute(ImageDownloaderContext context) {
		mImageDownloadContext = context;
		Toast.makeText(context.getActivity(), TAG, Toast.LENGTH_SHORT).show();
		
		new GrayscaleImageTask().execute(mImageDownloadContext.getDownloadedImageUri());
	}

	
	private class GrayscaleImageTask extends AsyncTask<Uri, Integer, Uri> {
	     protected Uri doInBackground(Uri... urls) {
	    	 if (isCancelled()) return null;
	    	 return Utils.grayScaleFilter(mImageDownloadContext.getActivity().getBaseContext(), urls[0]);
	     }

	     /*protected void onProgressUpdate(Integer... progress) {
	         setProgressPercent(progress[0]);
	     }*/

	     protected void onPostExecute(Uri result) {
	    	 mImageDownloadContext.setGrayscaleImage(result);
	     }
	 }
}
