package mooc.imagedownloader;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity implements DownloaderTaskFragment.DownloaderTaskCallbacks {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String TAG_TASK_FRAGMENT = "task_fragment";
	
	private DownloaderTaskFragment mTaskFragment;
	
	private EditText mUrlEditText;
	private ButtonActionMapper mButtonActionMapper;
	protected ButtonAction mActiveButtonAction = null;
	private ImageDownloaderContext mDownloaderContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mUrlEditText = (EditText)findViewById(R.id.mUrlEditText);
		setDefaultImageURL();
		mDownloaderContext = getThisContextReference();
		initButtonActions();
		
		FragmentManager fm = getFragmentManager();
	    mTaskFragment = (DownloaderTaskFragment) fm.findFragmentByTag(TAG_TASK_FRAGMENT);

	    // If the Fragment is non-null, then it is being retained
	    // over a configuration change.
	    if (mTaskFragment == null) {
	      mTaskFragment = new DownloaderTaskFragment();
	      fm.beginTransaction().add(mTaskFragment, TAG_TASK_FRAGMENT).commit();
	    }
	}

	public void handleButtonClick(View view) {
		mActiveButtonAction = mButtonActionMapper.getButtonAction(view.getId());
		//mActiveButtonAction.execute(mDownloaderContext);
		if(mTaskFragment != null){
			mTaskFragment.setTaskAction(mActiveButtonAction);
			mTaskFragment.startDownload();
		}
	}
	
	private void initButtonActions(){
		mButtonActionMapper = new ButtonActionMapper(
				new int[] { R.id.download_button, R.id.grayscale_button, R.id.resetimage_button },
				new ButtonAction[] {new DownloadImageAction(), new GrayscaleImageAction(), new ResetImageAction()});
	}
	
	public ImageDownloaderContext getThisContextReference(){
		ImageDownloaderContext context = new ImageDownloaderContext(
				mUrlEditText,
				(ImageView)findViewById(R.id.mImageView),				
				(Button)findViewById(R.id.grayscale_button),
				(Button)findViewById(R.id.resetimage_button),
				this);
		return context;
	}
	
	private void setDefaultImageURL(){
		mUrlEditText.setText(R.string.image_url);
	}
	
	/*********************************/
	  /***** TASK CALLBACK METHODS *****/
	  /*********************************/

	  @Override
	  public void onPreExecute() {
	    Log.i(TAG, "onPreExecute()");
	    //Toast.makeText(this, R.string.task_started_msg, Toast.LENGTH_SHORT).show();
	  }

	  @Override
	  public void onProgressUpdate(int percent) {
	    Log.i(TAG, "onProgressUpdate(" + percent + "%)");
	    //mProgressBar.setProgress(percent * mProgressBar.getMax() / 100);
	    //mPercent.setText(percent + "%");
	  }

	  @Override
	  public void onCancelled() {
	    Log.i(TAG, "onCancelled()");
	    /*mButton.setText(getString(R.string.start));
	    mProgressBar.setProgress(0);
	    mPercent.setText(getString(R.string.zero_percent));
	    Toast.makeText(this, R.string.task_cancelled_msg, Toast.LENGTH_SHORT).show();*/
	  }

	  @Override
	  public void onPostExecute() {
	    Log.i(TAG, "onPostExecute()");
	    /*mButton.setText(getString(R.string.start));
	    mProgressBar.setProgress(mProgressBar.getMax());
	    mPercent.setText(getString(R.string.one_hundred_percent));
	    Toast.makeText(this, R.string.task_complete_msg, Toast.LENGTH_SHORT).show();*/
	  }
}
