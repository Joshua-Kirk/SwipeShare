package kirk.bluetooth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PairedDevices extends ListActivity
{
	ImageViewer image = new ImageViewer();
	
	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	BluetoothDevice selectedDevice;
	String[] pairedList;
	String[] pairedAddress;
	static String[] pairedAddressList;
	int i = 0;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		while(true)
		{
			if(BluetoothAdapter.STATE_ON == mBluetoothAdapter.getState())
			{
				Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
				pairedList = new String[pairedDevices.size()];
				pairedAddress = new String[pairedDevices.size()];
				
				// If there are paired devices
				if (pairedDevices.size() > 0)
				{
				    // Loop through paired devices
				    for (BluetoothDevice device : pairedDevices) 
				    {
				        // Add the name and address to an array adapter to show in a ListView
				    	pairedList[i] = (device.getName() + "\n" + device.getAddress());
				    	pairedAddress[i] = (device.getAddress());
				    	i++;
				    }
				}
				break;
			}
		}
		
		ListView awesome = getListView();
		awesome.setChoiceMode(0);
		//awesome.setChoiceMode(1);
		//awesome.setChoiceMode(2);
		awesome.setTextFilterEnabled(true);

		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, pairedList));
	}
	
	public void onListItemClick(ListView parent, View v, int position, long id)
	{
		parent.setItemChecked(position, parent.isItemChecked(position));
		
		Toast.makeText(this, "You have selected " + pairedAddress[position], Toast.LENGTH_SHORT).show();
		
		this.selectedDevice = mBluetoothAdapter.getRemoteDevice(pairedAddress[position]);
		
		//pairedAddressList[0] = mBluetoothAdapter.getRemoteDevice(pairedAddress[position]).getAddress();
		
		Client client = new Client(mBluetoothAdapter.getRemoteDevice(pairedAddress[position]));
	    client.start();
	    
	    Server server = new Server();
	    server.start();
	    
	    finish();
	}
	
	public BluetoothDevice getDevice()
	{
		return mBluetoothAdapter.getRemoteDevice(pairedAddress[3]);
	}
	
	public static String getSelectedAddress()
	{
		return pairedAddressList[0];
	}
}
