package kirk.bluetooth;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import kirk.framework.Input.TouchEvent;
import kirk.framework.Game;
import kirk.framework.MultiTouchHandler;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class ImageViewer extends Activity
{
	BluetoothAdapter mBluetoothAdapter;
	private static int RESULT_LOAD_IMAGE = 1;
	private byte[] byteArray;
	public ArrayList<String> images;
	public String[] image;
	String picturePath;
	MainActivity main = new MainActivity();
	Intent data;
	Button button;
	Client client;
	
	Intent intent = new Intent(Intent.ACTION_SEND);
	ContentValues values = new ContentValues();
	View view;
	
	 public void onCreate(Bundle savedInstanceState)
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.images);
	        
	        view = new View(this);
	        
	        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
            startActivityForResult(i, RESULT_LOAD_IMAGE);
	 }
	 
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	 {
	        super.onActivityResult(requestCode, resultCode, data);
	         
	        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) 
	        {
	            this.data = data;
	        	
	            //gets name of data
	            Uri selectedImage = data.getData();
	            
	            //gets path of data
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };
	 
	            //points to the data and allows you to read or write
	            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
	            cursor.moveToFirst();
	 
	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            this.picturePath = cursor.getString(columnIndex);
	            
	            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(selectedImage.toString()));
	            values.put(BluetoothShare.URI, Uri.parse(selectedImage.toString()).toString());
	            
	            byteArray = cursor.getBlob(columnIndex);
	            intent.putExtra("image", byteArray);
	            
	            cursor.close();
	             
	            //Displays image as a button
	            ImageView imageView = (ImageView) findViewById(R.id.imgView);
	            //lets you view photo
	            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	            
	            //ManageConnection.write(byteArray);

	            
	            button = (Button) findViewById(R.id.Connect);
	    		button.setOnClickListener(new OnClickListener()
	    		{
	    			public void onClick(View arg0)
	    			{	    				
	    				main.enableBluetooth();
	    				
	    				Intent j = new Intent("kirk.PairedDevices");
	    				startActivity(j);
						
						
						
						//if(BluetoothAdapter.STATE_CONNECTED == mBluetoothAdapter.getState())
						//{
						//	manage.write(byteArray);
						//}
	    			}
	    		});
	    		
	    		button = (Button) findViewById(R.id.SendPicture);
	    		button.setOnClickListener(new OnClickListener()
	    		{
	    			public void onClick(View arg0)
	    			{	 
	    				
	    				//This Intent works but makes the user choose who to send file to
		    		        //intent.setAction(Intent.ACTION_SEND_MULTIPLE);
		    		        //intent.putStringArrayListExtra(Intent.EXTRA_STREAM, images);
		    		        //intent.putExtra(Intent.EXTRA_STREAM, images);
		    		        //intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(picturePath));
	    				
	    				/*
	    				Swipe swipe = new Swipe(view);
	    				
	    				if(swipe.isAlive())
	    					swipe.stop();
	    				
	    				else
	    					swipe.start();
			        	
			        	if(swipe.isAlive() == false)
			        	{
			        		Log.v("N", "send button hit");
		    		        intent.setType("image/*");
		    		        intent.setPackage("com.android.bluetooth");
		    		        startActivity(Intent.createChooser(intent, "select some pic"));
			        	}
			        	*/
		    		        
		    		        
		    		        
	    				//This Intent sucks ball and does not work
	    				//"14:10:9F:D7:45:0A" Mac Address for my Mac
	    				/*
		    		        //ContentValues values = new ContentValues();
		    		        String address = "14:10:9F:D7:45:0A";
		    		        //values.get(BluetoothShare.PERMISSION_ACCESS);
		    		        //values.put(BluetoothShare.URI, Uri.fromFile(new File(picturePath)).toString());
		    		        //values.put(BluetoothShare.URI, Uri.parse(picturePath).toString());
		    		        //values.put("permission", BluetoothShare.PERMISSION_ACCESS);
		    		        values.put(BluetoothShare.DESTINATION, address);
		    		        values.put(BluetoothShare.DIRECTION, BluetoothShare.DIRECTION_OUTBOUND);
		    		        Long ts = System.currentTimeMillis();
		    		        values.put(BluetoothShare.TIMESTAMP, ts);
		    		        Uri contentUri = getContentResolver().insert(BluetoothShare.CONTENT_URI, values);
		    		        getContentResolver().insert(BluetoothShare.CONTENT_URI, values);
		    		    */
		    		        
		    		        
		    		        
	    				
	    				
	    				//PairedDevices paired = new PairedDevices();
	    				//Client client = new Client(paired.getDevice());
	    				
	    				ManageConnection.write(picturePath.getBytes());
	    				
	    				/*
	    				ContentValues values = new ContentValues();
	    	        	values.put("uri", "content://" + selectedImage);
	    	        	values.put("destination", deviceAddress);
	    	        	values.put("direction", 0);
	    	        	Long ts = System.currentTimeMillis();
	    	        	values.put("timestamp", ts);
	    	        	*/
	    				
	    				//ContentValues values = new ContentValues();
	    				//values.put(BluetoothShare.URI, "content://" + uritoSend);
	    				
	    				//main.enableBluetooth();
						
						//if(BluetoothAdapter.STATE_CONNECTED == mBluetoothAdapter.getState())
						//{
	    				/*
	    				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    				BluetoothSocket socket = null;
	    				OutputStream output = null;
	    				try {
							output = socket.getOutputStream();
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    				try 
	    				{
	    					Log.v("N", "Writing Bytes");
	    					output.write(byteArray);
	    				}
	    				
	    				catch (IOException e) 
	    				{
	    					Log.v("N", "Not Writing, has IOException");
	    					e.printStackTrace();
	    				}
	    					*/
							//ManageConnection.write(byteArray);
	    					//ManageConnection.write(b);
	    				
	    					//WriteData write = new WriteData();
						//}
						
	    				//Server server = new Server();
	    				//server.start();
	    			}
	    			/*
	    			class SendData extends Thread {
	    				 private BluetoothDevice device = null;
	    				 private BluetoothSocket btSocket = null;
	    				 private OutputStream outStream = null;
	    				 
	    				 public SendData(){
	    				 device = mBluetoothAdapter.getRemoteDevice(address);
	    				 try
	    				 {
	    				 btSocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
	    				 }
	    				 catch (Exception e) {
	    				 // TODO: handle exception
	    				 }
	    				 mBluetoothAdapter.cancelDiscovery();
	    				 try {
	    				 btSocket.connect();
	    				 } catch (IOException e) {
	    				 try {
	    				 btSocket.close();
	    				 } catch (IOException e2) {
	    				 }
	    				 }
	    				// Toast.makeText(getBaseContext(), "Connected to " + device.getName(), Toast.LENGTH_SHORT).show();
	    				 try {
	    				 outStream = btSocket.getOutputStream();
	    				 } catch (IOException e) {
	    				 }
	    				 }
	    				 
	    				 public void sendMessage()
	    				 {
	    				 try {
	    				 mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	    				// Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.white);
	    				// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    				// bm.compress(Bitmap.CompressFormat.JPEG, 100,baos); //bm is the bitmap object
	    				// byte[] b = baos.toByteArray();
	    				// Toast.makeText(getBaseContext(), String.valueOf(b.length), Toast.LENGTH_SHORT).show();
	    				 outStream.write(byteArray);
	    				 outStream.flush();
	    				 } catch (IOException e) {
	    				 }
	    				 }
	    				 }
	    				 */
	    		});
	        }
	 }
	 
	 /*
	 public void onBackPressed()
	 {
		 File file = new File(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
		 file.getParent();
	 }
	 */
	 
	 public ArrayList<String> getByteArray()
	 {
		 return images;
	 }
}
