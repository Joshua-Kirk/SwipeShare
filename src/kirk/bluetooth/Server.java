package kirk.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class Server extends Thread
{
	private final BluetoothServerSocket mmServerSocket;
	private final String bluetooth = "Bluetooth";
	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	 
    public Server() 
    {
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSocket is final
        BluetoothServerSocket tmp = null;
        
        try 
        {
            // MY_UUID is the app's UUID string, also used by the client code
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(bluetooth, UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
            //mBluetoothAdapter.
        } 
        
        catch (IOException e)
        { 
        	
        }
        mmServerSocket = tmp;
    }
 
    public void run() 
    {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned
        
        while (true)
        {
            try 
            {
            	Log.v("N", "Server Listening");
                socket = mmServerSocket.accept();
            } 
            
            catch (NullPointerException e) 
            {
            	Log.v("N", "Server Connection Failed on NullPointerException");
                break;
            } 
            
            catch (IOException e) 
            {
				// TODO Auto-generated catch block
            	Log.v("N", "Server Connection Failed on IOException");
				e.printStackTrace();
				break;
			}
            
            // If a connection was accepted
            if (socket != null)
            {
            	Log.v("N", "Server Connection Successful");
            	
                // Close ServerSocket and do work to manage the connection (in a separate thread)
            	cancel();
            	
                ManageConnection manage = new ManageConnection(socket);
                manage.start();
                
                break;
            }
        }
    }
 
    /** Will cancel the listening socket, and cause the thread to finish */
    public void cancel() 
    {
        try 
        {
            mmServerSocket.close();
        }
        
        catch (IOException e) 
        {
        	
        }
    }
}
