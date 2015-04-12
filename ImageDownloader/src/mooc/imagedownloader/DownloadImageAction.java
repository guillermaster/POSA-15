package mooc.imagedownloader;

import android.widget.Toast;
import android.os.AsyncTask;
import android.net.Uri;

public class DownloadImageAction implements ButtonAction {
	private final String TAG =
			DownloadImageAction.class.getSimpleName();
	private ImageDownloaderContext mImageDownloadContext;
	
	@Override
	public void execute(ImageDownloaderContext context) {
		mImageDownloadContext = context;
		Toast.makeText(context.getActivity(), TAG, Toast.LENGTH_SHORT).show();
		
		new DownloadImageTask().execute(mImageDownloadContext.getImageUrl());
	}
	
	
	private class DownloadImageTask extends AsyncTask<Uri, Integer, Uri> {
	     protected Uri doInBackground(Uri... urls) {
	    	 if (isCancelled()) return null;
	    	 return Utils.downloadImage(mImageDownloadContext.getActivity().getBaseContext(), urls[0]);
	     }

	     /*protected void onProgressUpdate(Integer... progress) {
	         setProgressPercent(progress[0]);
	     }*/

	     protected void onPostExecute(Uri result) {
	    	 mImageDownloadContext.setDownloadedImage(result);
	     }
	 }
}
