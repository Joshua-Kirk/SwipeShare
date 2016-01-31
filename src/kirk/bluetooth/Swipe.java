package kirk.bluetooth;

import java.util.List;

import kirk.framework.Input.TouchEvent;
import kirk.framework.MultiTouchHandler;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class Swipe extends Thread
{
	View view;
	MultiTouchHandler multi;
	Swipe swipe;
	
	public Swipe(View view)
	{
		this.view = view;
		this.multi = new MultiTouchHandler(view);
	}
	
	@SuppressWarnings("deprecation")
	public void run()
	{
			Log.v("N", "Swipe not inside for loop");
        	List<TouchEvent> TouchEvents = multi.getTouchEvents();
        		
        	int len = TouchEvents.size();
        		
        	for(int i = 0; i < len; i++)
        	{
        		TouchEvent event = TouchEvents.get(i);
        			
        		Log.v("N", "Swipe not in if statement");
        		if(event.type == TouchEvent.TOUCH_DRAGGED)
        		{
        			//if(event.x >= view.getWidth())
        			//{
        				Log.v("N", "Swipe");
        				stop();
        			//}
        		}
		}
     }
		
	
}
