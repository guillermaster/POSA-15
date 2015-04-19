package vandy.mooc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * An Activity that downloads an image, stores it in a local file on
 * the local device, and returns a Uri to the image file.
 */
public class DownloadImageActivity extends Activity {
    /**
     * Debugging tag used by the Android logger.
     */
    private final String TAG = getClass().getSimpleName();
    private final static int DOWNLOAD_COMPLETED = 0;
    private final static int DOWNLOAD_ERROR = 1;

    /**
     * Hook method called when a new instance of Activity is created.
     * One time initialization code goes here, e.g., UI layout and
     * some class scope variable initialization.
     *
     * @param savedInstanceState object that contains saved state information.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Always call super class for necessary
        // initialization/implementation.
        super.onCreate(savedInstanceState);

        // Get the URL associated with the Intent data.
        Uri url = getIntent().getData();

        // Download the image in the background, create an Intent that
        // contains the path to the image file, and set this as the
        // result of the Activity.
        Handler handler = new DownloadImageHandler(this);
        Runnable runnable = new DownloadImage(this, url, handler);

        new Thread(runnable).start();
    }

    private static class DownloadImage implements Runnable {
        private Context context;
        private final Uri url;
        private final Handler handler;

        public DownloadImage(Context context, Uri url, Handler handler) {
            this.context = context;
            this.url = url;
            this.handler = handler;
        }

        @Override
        public void run() {
            Uri result = DownloadUtils.downloadImage(context, url);

            handler.sendMessage(createMessage(result));
        }

        private Message createMessage(Uri result) {
            if (result != null) {
                result = Uri.parse("file://" + result);
                return handler.obtainMessage(DOWNLOAD_COMPLETED, result);
            }

            return handler.obtainMessage(DOWNLOAD_ERROR);
        }
    }

    private static class DownloadImageHandler extends Handler {
        private Activity activity;

        public DownloadImageHandler(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case DOWNLOAD_COMPLETED:
                    Intent intent = new Intent()
                            .setData((Uri) message.obj);
                    activity.setResult(RESULT_OK, intent);
                    break;
                case DOWNLOAD_ERROR:
                    activity.setResult(RESULT_CANCELED);
                    break;
            }

            activity.finish();
        }
    }
}
