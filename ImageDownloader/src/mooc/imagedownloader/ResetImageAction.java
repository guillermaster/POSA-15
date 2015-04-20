package mooc.imagedownloader;

import android.widget.Toast;

public class ResetImageAction implements ButtonAction {
	private final String TAG =
			ResetImageAction.class.getSimpleName();
	private boolean mTaskRunning = false;
	
	@Override
	public void execute(ImageDownloaderContext context) {
		Toast.makeText(context.getActivity(), TAG, Toast.LENGTH_SHORT).show();
		mTaskRunning = false;
		context.resetImage();
	}

	@Override
	public void cancel() {
		mTaskRunning = false;
	}
	
	@Override
	public void updateContext(ImageDownloaderContext context) {
		
	}
	
	public boolean isRunning() {
	    return mTaskRunning;
	  }

}
