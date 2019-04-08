package com.stranger.physicsbird;

import com.umeng.analytics.MobclickAgent;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class About_menu extends Activity{
	private TextView btnup;
	private Button btnre;
@Override

protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	setContentView(R.layout.menu_about);
	btnup=(TextView) findViewById(R.id.ab_tv3);
	btnre=(Button) findViewById(R.id.ab_re);
	btnup.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Uri uri= Uri.parse("http://xmsec.github.io/blog/2016/02/04/Physics-Bird/");
			Intent intent =new Intent(Intent.ACTION_VIEW,uri);
			startActivity(intent);
		}
	});
	btnre.setVisibility(View.INVISIBLE);
	btnre.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Uri uri= Uri.parse("http://xmsec.github.io/blog/2016/02/04/Physics-Bird/");
			Intent intent =new Intent(Intent.ACTION_VIEW,uri);
			startActivity(intent);

		}
	});
	super.onCreate(savedInstanceState);
}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	MobclickAgent.onResume(this);
}
@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	MobclickAgent.onPause(this);
}
}
