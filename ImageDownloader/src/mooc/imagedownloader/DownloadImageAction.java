package mooc.imagedownloader;

import android.widget.Toast;

public class DownloadImageAction implements ButtonAction {
	private final String TAG =
			DownloadImageAction.class.getSimpleName();

	@Override
	public void execute(ImageDownloaderContext context) {
		Toast.makeText(context.getActivity(), TAG, Toast.LENGTH_SHORT).show();
	}

}
