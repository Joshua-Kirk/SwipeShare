package kirk.bluetooth;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class ManageConnection extends Thread
{
	private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private static OutputStream mmOutStream;
 
    public ManageConnection(BluetoothSocket socket) 
    {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
 
        // Get the input and output streams, using temp objects because
        // member streams are final
        try 
        {
            tmpIn = mmSocket.getInputStream();
            tmpOut = mmSocket.getOutputStream();
        }
        
        catch (IOException e) 
        {
        	
        }
 
        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }
 
    public void run() 
    {
        byte[] buffer = new byte[1024]; 
        
        // buffer store for the stream
        int bytes;
        
        // bytes returned from read()
       // Looper.prepare();
       // Handler mHandler = new Handler();
        Log.v("N", "Listening for bytes");
 
        // Keep listening to the InputStream until an exception occurs
        while (true) 
        {
            try 
            {
                // Read from the InputStream
                //bytes = mmInStream.read(buffer);
            	
            	File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                
                FileOutputStream stream = new FileOutputStream(new File(path + "/Bluetooth"));
                
                int len = 0;
                while ( (len = mmInStream.read(buffer)) !=  -1 ) 
                {
                	Log.v("N", "Saving Bytes to SD Card");
                	stream.write(buffer,0, len);
                }
                
                stream.close();
                
                // Send the obtained bytes to the UI activity
               // mHandler.obtainMessage(NORM_PRIORITY, bytes, -1, buffer).sendToTarget();
            }
            
            catch (IOException e) 
            {
            	Log.v("N", "Failing to Write Bytes to SD Card");
                break;
            }
        }
        //Looper.loop();
    }
 
    /* Call this from the main activity to send data to the remote device */
    public static void write(byte[] bytes) 
    { 	
        try 
        {
        	Log.v("N", "Writing Bytes");
        	//mmOutStream.write(bytes, NORM_PRIORITY, NORM_PRIORITY);
        	//while((send = bytes.length) > 0)
        	//{
        		mmOutStream.write(bytes);
        	//	send -= bytes;
        	//}
            mmOutStream.flush();
            mmOutStream.close();
        	
        	/*
        	OutputStream mmOutStream = mmSocket.getOutputStream();
        	 mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        	 Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	 bm.compress(Bitmap.CompressFormat.JPEG, 100,baos); //bm is the bitmap object
        	 byte[] b = baos.toByteArray();
        	 Toast.makeText(getBaseContext(), String.valueOf(b.length), Toast.LENGTH_SHORT).show();
        	 mmOutStream.write(b);
        	 mmOutStream.flush();
        	 */
        }
        
        catch (IOException e) 
        { 
        	Log.v("N", "IOException when writing");
        }
        
        catch(NullPointerException e)
        {
        	Log.v("N", "NullPointerException when writing");
        }
    }
 
    /* Call this from the main activity to shutdown the connection */
    public void cancel() 
    {
        try 
        {
            mmSocket.close();
        } 
        
        catch (IOException e) 
        { 
        	
        }
    }
}
