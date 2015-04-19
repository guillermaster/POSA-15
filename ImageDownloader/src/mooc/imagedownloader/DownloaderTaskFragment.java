package mooc.imagedownloader;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

public class DownloaderTaskFragment extends Fragment {
	private static final String TAG = DownloaderTaskFragment.class.getSimpleName();
	
	static interface DownloaderTaskCallbacks {
	    void onPreExecute();
	    void onProgressUpdate(int percent);
	    void onCancelled();
	    void onPostExecute();	
	}
	
	private DownloaderTaskCallbacks mCallbacks;
	private ImageDownloaderContext mDownloaderContext;
	ButtonAction mTaskAction;
	
	@Override
	  public void onAttach(Activity activity) {
	    Log.i(TAG, "onAttach(Activity)");
	    super.onAttach(activity);
	    if (!(activity instanceof DownloaderTaskCallbacks)) {
	      throw new IllegalStateException("Activity must implement the DownloaderTaskCallbacks interface.");
	    }

	    // Hold a reference to the parent Activity so we can report back the task's
	    // current progress and results.
	    mCallbacks = (DownloaderTaskCallbacks) activity;
	    mDownloaderContext = ((MainActivity)activity).getThisContextReference();
	  }
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    Log.i(TAG, "onCreate(Bundle)");
	    super.onCreate(savedInstanceState);
	    setRetainInstance(true);
	  }
	
	@Override
	  public void onDestroy() {
	    Log.i(TAG, "onDestroy()");
	    super.onDestroy();
	    cancelDownload();
	  }
	
	public boolean isRunning() {
	    return mTaskAction.isRunning();
	  }
	
	public void setTaskAction(ButtonAction action){
		mTaskAction = action;
	}
	
	 /**
	   * Start the background task.
	   */
	  public void startDownload() {
	    if (!mTaskAction.isRunning()) {
	    	mTaskAction.execute(mDownloaderContext);
	    }
	  }

	  /**
	   * Cancel the background task.
	   */
	  public void cancelDownload() {
	    if (mTaskAction.isRunning()) {
	    	mTaskAction.cancel();
	    	mTaskAction = null;
	    }
	  }
}
