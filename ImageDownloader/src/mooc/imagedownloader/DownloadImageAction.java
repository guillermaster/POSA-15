package mooc.imagedownloader;

import android.widget.Toast;
import android.os.AsyncTask;
import android.net.Uri;

public class DownloadImageAction implements ButtonAction {
	private final String TAG =
			DownloadImageAction.class.getSimpleName();
	private ImageDownloaderContext mImageDownloadContext;
	private DownloadImageTask mTask;
	private boolean mTaskRunning;
	
	@Override
	public void execute(ImageDownloaderContext context) {
		mImageDownloadContext = context;
		Toast.makeText(context.getActivity(), TAG, Toast.LENGTH_SHORT).show();
		
		mTask = new DownloadImageTask();
		mTaskRunning = true;
		mTask.execute(mImageDownloadContext.getImageUrl());
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
	
	private class DownloadImageTask extends AsyncTask<Uri, Integer, Uri> {
	     protected Uri doInBackground(Uri... urls) {
	    	 if (isCancelled()) return null;
	    	 try{Thread.sleep(12000);}catch(InterruptedException e){System.out.println(e);}  
	    	 return Utils.downloadImage(mImageDownloadContext.getActivity().getBaseContext(), urls[0]);
	     }

	     /*protected void onProgressUpdate(Integer... progress) {
	         setProgressPercent(progress[0]);
	     }*/

	     protected void onPostExecute(Uri result) {
	    	 mImageDownloadContext.setDownloadedImage(result);
	    	 mTaskRunning = false;
	     }
	 }


}
