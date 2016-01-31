package kirk.bluetooth;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class Client extends Thread
{
	private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    byte[] bytes;
    ImageViewer imageView = new ImageViewer();
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
 
    public Client(BluetoothDevice device) 
    {
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
        BluetoothSocket tmp = null;
        mmDevice = device;
        
        this.bytes = bytes;
 
        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try 
        {
            tmp = mmDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
            //"82f77cf2-5018-11e3-9618-ce3f5508acd9"
        } 
        
        catch (IOException e) 
        { 
        	
        }
        mmSocket = tmp;
    }
 
    public void run() 
    {
        // Cancel discovery because it will slow down the connection
        mBluetoothAdapter.cancelDiscovery();
 
        try
        {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            mmSocket.connect();
        }
        
        catch (IOException connectException) 
        {
            // Unable to connect; close the socket and get out
        	Log.v("N", "Client Connection Failed");
        	
            cancel();
        }
 
        // Do work to manage the connection (in a separate thread)
        Log.v("N", "Client Connection Successful");
        ManageConnection manage = new ManageConnection(mmSocket);
        manage.start();
        //manage.write(bytes);
    }
 
    /** Will cancel an in-progress connection, and close the socket */
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
    
    public BluetoothSocket getBluetoothSocket()
    {
    	return mmSocket;
    }
}
