package mooc.imagedownloader;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
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
	}

	public void handleButtonClick(View view) {
		mActiveButtonAction = mButtonActionMapper.getButtonAction(view.getId());
		mActiveButtonAction.execute(mDownloaderContext);
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
				(Button)findViewById(R.id.grayscale_button),
				(Button)findViewById(R.id.resetimage_button),
				this);
		return context;
	}
	
	private void setDefaultImageURL(){
		mUrlEditText.setText(R.string.image_url);
	}
}
