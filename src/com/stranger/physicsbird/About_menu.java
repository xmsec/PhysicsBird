package com.stranger.physicsbird;

import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;

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
			 UmengUpdateAgent.forceUpdate(About_menu.this);
		}
	});
	btnre.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			Uri uri= Uri.parse("http://xmnet.webcindario.com/blog/?post=2");
//			Intent intent =new Intent(Intent.ACTION_VIEW,uri);
//			startActivity(intent);
			FeedbackAgent agent = new FeedbackAgent(About_menu.this);
			agent.setWelcomeInfo("你好，请在下方输入您要发送的内容~(作者回复后，再次打开本页面可查看回复内容)");
			agent.closeAudioFeedback();
			agent.startFeedbackActivity();
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
