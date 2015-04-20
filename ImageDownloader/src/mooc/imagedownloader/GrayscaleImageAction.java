package mooc.imagedownloader;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

public class GrayscaleImageAction implements ButtonAction{
	private final String TAG =
			GrayscaleImageAction.class.getSimpleName();
	private ImageDownloaderContext mImageDownloadContext;
	private GrayscaleImageTask mTask;
	private boolean mTaskRunning;
	
	@Override
	public void execute(ImageDownloaderContext context) {
		mImageDownloadContext = context;
		Toast.makeText(context.getActivity(), TAG, Toast.LENGTH_SHORT).show();
		
		mTask = new GrayscaleImageTask();
		mTaskRunning = true;
		mTask.execute(mImageDownloadContext.getDownloadedImageUri());
	}

	@Override
	public void cancel() {
		if(mTask != null){
			mTask.cancel(false);
			mTaskRunning = false;
		}
	}
	
	@Override
	public boolean isRunning() {
	    return mTaskRunning;
	  }
	
	@Override
	public void updateContext(ImageDownloaderContext context) {
		mImageDownloadContext = context;
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
	    	 mTaskRunning = false;
	     }
	 }
}
