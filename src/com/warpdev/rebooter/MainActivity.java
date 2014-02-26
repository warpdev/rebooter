package com.warpdev.rebooter;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast.*;
import android.content.*;
import android.widget.Button.*;
import android.view.View.*;
import android.net.*;
import java.io.File;
import java.io.*;
import java.io.Writer;
import android.os.PowerManager;
import java.lang.Object.*;
import android.os.Vibrator;
import android.os.SystemClock.*;
import java.lang.System.*;
import android.media.AudioManager.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import android.widget.Button.*;
import java.lang.Runtime.*;
import android.util.*;
//import android.os.Process;
import java.lang.Process;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity
{
    private AdView adView;
	private Button rbt;
	private Button rb;
	private Button prb;
	private static final String MY_AD_UNIT_ID="a150f55d425b36b";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		adView =new AdView(this,AdSize.BANNER,MY_AD_UNIT_ID);
		LinearLayout layout=(LinearLayout)findViewById(R.id.mainLayout);
		layout.addView(adView);
		adView.loadAd(new AdRequest());
	rbt=(Button) findViewById(R.id.rbt);
	rb=(Button) findViewById(R.id.rb);
	prb=(Button) findViewById(R.id.prb);
	rbt.setOnClickListener(new OnClickListener(){
	public void onClick(View v){
		//Vibrator vib = (Vibrator)MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		runRootCommand("reboot recovery");
	}});
		rb.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					//Vibrator vib = (Vibrator)MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
					runRootCommand("reboot");
				}});
		prb.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					//Vibrator vib = (Vibrator)MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
					runRootCommand("reboot -p");
				}});
	}
	public static boolean runRootCommand(String command) {
		Process process = null;
		DataOutputStream os = null;
		try {
			process=Runtime.getRuntime().exec("su");
			os = new DataOutputStream(process.getOutputStream());
			os.writeBytes(command+"\n");
			os.writeBytes("exit\n");
			os.flush();
			process.waitFor();
		} catch (Exception e) {
			Log.d("*** DEBUG ***", "Error - "+e.getMessage());
			return false;
		}
		finally {
			try {
				if (os != null) {
					os.close();
				}
				process.destroy();
			} catch (Exception e) {

			}
		}
		return true;
	}
		@Override public void onDestroy() {
			adView.destroy();
			super.onDestroy();
			}

	}



/*public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
   /* @Override
	private Button rbt;
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		rbt=(Button) findViewById(R.id.rbt);
		rbt.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					Vibrator vib = (Vibrator)MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
					vib.vibrate(5000);
					while(true){
						vib.vibrate(5000);
						Intent i=new Intent(MainActivity.this,MainActivity.class);
						startActivity(i);
					}
				}
			});
    }
}*/
