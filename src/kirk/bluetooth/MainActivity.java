package kirk.bluetooth;

import kirk.bluetooth.R;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

public class MainActivity extends Activity
{
	public final int REQUEST_ENABLE_BT = 1;
	Button b1, b2, b3, b4, b5;
	
	public void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		b1 = (Button) findViewById(R.id.btn_photo);
		b1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				Intent i = new Intent("kirk.ImageViewer");
				startActivity(i);	
			}
		});
		
		b2 = (Button) findViewById(R.id.btn_music);
		b2.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				@SuppressWarnings("deprecation")
				Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
				startActivity(intent);
			}
		});
		
		b3 = (Button) findViewById(R.id.btn_video);
		b3.setOnClickListener(new OnClickListener()
			{
				public void onClick(View arg0)
				{
					
				}
			});
		
		b4 = (Button) findViewById(R.id.btn_data);
		b4.setOnClickListener(new OnClickListener()
			{
				public void onClick(View arg0)
				{
					Intent i = new Intent("kirk.SdCardFolders");
					startActivity(i);
				}
			});
		
		b5 = (Button) findViewById(R.id.btn_options);
		b5.setOnClickListener(new OnClickListener()
			{
				public void onClick(View arg0)
				{
					
				}
			});
	}
		
	
	
	
	
	
	
	
	
	
	//@Override
	//public boolean onTouch(View v, MotionEvent event) 
	public void enableBluetooth()
	{
			BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			if (mBluetoothAdapter == null) 
			{
				    
			}
			
			else
			{
				//Asking/Forcing BlueTooth to turn on
				if(!mBluetoothAdapter.isEnabled())
				{
					mBluetoothAdapter.enable();
						
					//server.start();
					//Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
					//startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
				}	
				
			}
			//break;
		//}
		//return true;
	}
	
	public void disableBluetooth()
	{
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) 
		{
					    
		}
		
		else
		{
			if(mBluetoothAdapter.isEnabled())
			{
				mBluetoothAdapter.disable();
			}
		}
	}
	
}
