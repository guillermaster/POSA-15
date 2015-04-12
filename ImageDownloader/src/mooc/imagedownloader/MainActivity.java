package mooc.imagedownloader;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private EditText mUrlEditText;
	private ButtonActionMapper mButtonActionMapper;
	protected ButtonAction mActiveButtonAction = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mUrlEditText = (EditText)findViewById(R.id.mUrlEditText);
		setDefaultImageURL();
		initButtonActions();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void handleButtonClick(View view) {
		ImageDownloaderContext context = getThisContextReference();
		mActiveButtonAction = mButtonActionMapper.getButtonAction(view.getId());
		mActiveButtonAction.execute(context);
	}
	
	private void initButtonActions(){
		mButtonActionMapper = new ButtonActionMapper(
				new int[] { R.id.download_button, R.id.grayscale_button, R.id.resetimage_button },
				new ButtonAction[] {new DownloadImageAction(), new GrayscaleImageAction(), new ResetImageAction()});
	}
	
	private ImageDownloaderContext getThisContextReference(){
		ImageDownloaderContext context = new ImageDownloaderContext(
				mUrlEditText,
				(ImageView)findViewById(R.id.mImageView),
				this);
		return context;
	}
	
	private void setDefaultImageURL(){
		mUrlEditText.setText(R.string.image_url);
	}
}
