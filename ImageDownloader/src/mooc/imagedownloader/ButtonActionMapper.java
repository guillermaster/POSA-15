package mooc.imagedownloader;

import android.util.SparseArray;

public class ButtonActionMapper {
	private SparseArray<ButtonAction> mButtonActionArray = new SparseArray<ButtonAction>();
	            
	    public ButtonActionMapper(int[] buttonIds,
	                                ButtonAction[] buttonStrategys) {
	        // Map buttons pushed by the user to the requested type of
	        // ButtonAction.
	        for (int i = 0; i < buttonIds.length; ++i)
	            mButtonActionArray.put(buttonIds[i], buttonStrategys[i]);
	    }

	    /**
	     * Factory method that returns the request ButtonAction
	     * implementation.
	     */
	    public ButtonAction getButtonAction(int buttonId) {
	        // Return the designated ButtonStrategy.
	        return mButtonActionArray.get(buttonId);
	    }
}
