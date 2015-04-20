package mooc.imagedownloader;

public interface ButtonAction {
	void execute(ImageDownloaderContext context);
	void cancel();
	boolean isRunning();
	void updateContext(ImageDownloaderContext context);
}
