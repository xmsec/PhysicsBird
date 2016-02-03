package com.stranger.physicsbird;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;



import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;

import android.support.v4.view.PagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	//�˳�
	private long mExitTime;
	//ҳ������
	private ViewPager viewPager; //ҳ������
	private List<View>views;// Tabҳ���б�
	private List<String> titleList;//viewpager�ı���
	private TextView textView1,textView2,textView3,textView4;
	private ImageView imageView;
	private int offset = 0;// ����ͼƬƫ����
    private int currIndex = 0;// ��ǰҳ�����
    private int bmpW;// ����ͼƬ���
    private View view1,view2,view3,view4;//����ҳ��
    //view1 components
    private Button btnnext;
    private Button btnenter;
    private Button btnreset;
    private EditText etnum;
    private EditText etdata;
    private TextView tvdis;
    private double []data_ave=new double[30];
    private int data_num=-1;
    private  int i=0;
    // view2
    private Button btngo;
    private double ave_exp;
    private TextView tvdis_v2;
    private boolean v1equipped;
    // view3
    private double ave_exps;
    private double ave_n;
    private Button btnokv3;
    private EditText etdmv3;
    private TextView tvdisv3;
    
    //view4
    private Button btnnextv4;
    private Button btnresetv4;
    private Button btncalv4;
    private EditText etx;
    private EditText ety;
    private TextView tvdisv4;
    private TextView tvbv4;
    int  nv4=0;
    double [][]data4=new double[60][2];
    
    //private EditText test;
    
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        update();
     
       // System.out.println( getDeviceInfo(MainActivity.this) );
       
        setContentView(R.layout.paper);
     
        InitImageView();
        InitTextView();
        InitViewPager();
        
        Initview1();
        Initview2();
        Initview3();
        Initview4();
        
        
    }
    /*
     * ͳ��,updateʹ��
     */
    
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
    // end !
    
    
    // update 
    private void update() {
		// TODO Auto-generated method stub
    	 UmengUpdateAgent.update(this);
    	 UmengUpdateAgent.setUpdateCheckConfig(false);//���ǽ������Զ�����������ɹ�����2��3���������Ƿ���ȷ��ɡ�
    //	 MobclickAgent.setDebugMode( true ); //Debug ģʽ
    	 AnalyticsConfig.enableEncrypt(true);
    }

    //end !
    
	private void Initview4() {
    	
		// TODO Auto-generated method stub
    	  btnnextv4=(Button) view4.findViewById(R.id.btnnextv4);
    	  btncalv4=(Button) view4.findViewById(R.id.btncalv4);
    	  btnresetv4=(Button) view4.findViewById(R.id.btnresetv4);
    	  etx=(EditText) view4.findViewById(R.id.etxv4);
    	  ety=(EditText) view4.findViewById(R.id.etyv4);
    	  tvdisv4=(TextView) view4.findViewById(R.id.tvdisv4);
    	  tvbv4=(TextView) view4.findViewById(R.id.tvbv4);
    	  
    	  btnnextv4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double x=0,y=0;
			 	
				//double [][]data=new double[60][2];
				try{
				x=Double.valueOf(etx.getText().toString().trim());
				y=Double.valueOf(ety.getText().toString().trim());
				}
				catch(Exception e)
				{
					Toast.makeText(MainActivity.this, "�����������", Toast.LENGTH_SHORT).show();
					return;
				}
				tvdisv4.setText(tvdisv4.getText().toString().trim()+" ("+x+","+y+") ,");
				data4[nv4][0]=x;
				data4[nv4][1]=y;
				nv4++;
				etx.setText("");
				ety.setText("");
				
			}
		});
    	  btncalv4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i;
				double avex=0,avey=0,x2=0,y2=0,xy=0;
				double b,a;
				for(i=0;i<nv4;i++)
				{
					avex=data4[i][0]+avex;
					avey=data4[i][1]+avey;
					x2=data4[i][0]*data4[i][0]+x2;
					y2=data4[i][1]*data4[i][1]+y2;
					xy=data4[i][0]*data4[i][1]+xy;										
				}
				avey=avey/nv4;
				avex=avex/nv4;
				x2=x2/nv4;
				y2=y2/nv4;
				xy=xy/nv4;
				
				b=(avex*avey-xy)/(avex*avex-x2);
				a=avey-b*avex;
				
				tvbv4.setText("b="+new Num(b).show()+"\na="+new Num(a).show());
			}
		});
    	  btnresetv4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tvdisv4.setText("����:");
				tvbv4.setText("���");
				nv4=0;
			}
		});
    }

	private void Initview3() {
		// TODO Auto-generated method stub
		
		btnokv3=(Button) view3.findViewById(R.id.btnokv3);
		etdmv3=(EditText) view3.findViewById(R.id.etdmv3);
		tvdisv3=(TextView) view3.findViewById(R.id.tvdisv3);
		btnokv3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double t=0;
				double ua=ave_exps,ub,ux,ex;
				try{
				t=Double.valueOf(etdmv3.getText().toString().trim());
				}
				catch(Exception e)
				{
					Toast.makeText(MainActivity.this, "�����������", Toast.LENGTH_SHORT).show();
					return;
				}
				ub=t/Math.sqrt(3);
				ux=Math.sqrt(ua*ua+ub*ub);
				ex=ux/ave_n;
				tvdisv3.setText("u(x)="+new Num(ux).show()+"\nE(x)="+new Num(ex).show()+"�����б����ʵ�λ��");
				
			}
		});
		
	
		
	}

	private void Initview2() {
		// TODO Auto-generated method stub
		 btngo=(Button) view2.findViewById(R.id.btngo);
		// test=(EditText) view2.findViewById(R.id.editText1);
		 tvdis_v2=(TextView) view2.findViewById(R.id.tvdis_v2);
		
		 
		 btngo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
			boolean flag=true;
			double []data_temp=new double[30];
			int k=0, j;
			
			if(!v1equipped)
				return;
				//��һ��
				for(j=0;j<data_num;j++) 
				{
					if(Math.abs(data_ave[j]-ave_n)>3*ave_exp)
					{
						tvdis_v2.setText(tvdis_v2.getText().toString().trim()+data_ave[j]+", ");
						flag=false;						
					}
					else{
						data_temp[k]=data_ave[j];
						k++;
					}
				}
				if(k==data_num&&flag==true)
				{
					tvdis_v2.setText(tvdis_v2.getText().toString().trim()+"��");
					return;
				}
				
				//�ڶ���
				flag=true;
				double result;
				int jc=2;
				
				while(flag)
				{
				result=aveex(data_temp,k);
										
				int t=0;
				for(j=0;j<k;j++)
				{
					if(Math.abs(data_temp[j]-ave_n)>3*result)
					{
						tvdis_v2.setText(tvdis_v2.getText().toString().trim()+"\n��"+jc+"�Σ�"+data_ave[j]+", ");
						flag=false;
					}
					else{
						data_temp[t]=data_temp[j];
						t++;
					}
				}
				if(t==k&&flag==true)
				{
					tvdis_v2.setText(tvdis_v2.getText().toString().trim()+"\n��"+jc+"�μ���޻�ֵ");
					ave_exps=result;
					v1equipped=false;
					return;
				}
				k=t;
				j++;
				
				}
				
				
			}

			private double aveex(double[] data,int cou) {
				// TODO Auto-generated method stub
				double result=0;
				double ave_t=0;
				int j;
				for(j=0;j<cou;j++)
					ave_t=ave_t+data[j];
				ave_t=ave_t/(double)cou;
				ave_n=ave_t;
				for (j=0;j<cou;j++)
				{
					result=result+(data[j]-ave_t)*(data[j]-ave_t);
				}
				result=result/(double)(cou-1);
				result=Math.sqrt(result);									
				return result;
			}
		});
	}

	private void Initview1() {
		// TODO Auto-generated method stub
    	btnnext=(Button) view1.findViewById(R.id.btnnext);
        btnreset=(Button) view1.findViewById(R.id.btnreset);
        btnenter=(Button) view1.findViewById(R.id.btnenter);
        etnum= (EditText) view1.findViewById(R.id.etnum);
        etdata=(EditText) view1.findViewById(R.id.etdata);
        tvdis=(TextView) view1.findViewById(R.id.tvdis);
        btnnext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 
				// TODO Auto-generated method stub
				if(etnum.getText().toString().trim()==null||etnum.getText().toString().trim().equals(""))
					{
					Toast.makeText(MainActivity.this, "δ�������ݸ���",Toast.LENGTH_SHORT).show();
						return;
					}
				if(data_num==-1)
					{
					int t;
					try{
					t=Integer.parseInt(etnum.getText().toString().trim());
					}
					catch(Exception e){
						Toast.makeText(MainActivity.this, "���ݸ����������",Toast.LENGTH_SHORT).show();	
						return;
						}
					
					if (t<=0)
						{
						Toast.makeText(MainActivity.this, "���ݸ����������",Toast.LENGTH_SHORT).show();			
						return;
					}
					else
						data_num=t;
					//etnum.setEnabled(false);
				
					}
				if (etdata.getText().toString().trim().equals(""))
					{
					Toast.makeText(MainActivity.this, "���ݲ���Ϊ��",Toast.LENGTH_SHORT).show();
					return;
					}
				try{
					double data_t=Double.valueOf(etdata.getText().toString().trim());
				}catch(Exception e)
				{
					Toast.makeText(MainActivity.this, "�����������",Toast.LENGTH_SHORT).show();
					return;
				}
				
				if (i<data_num){
					tvdis.setText(tvdis.getText()+etdata.getText().toString().trim()+", ");
					data_ave[i]=Double.valueOf(etdata.getText().toString().trim());
					etdata.setText("");
					i++;
				}
				else
					Toast.makeText(MainActivity.this, "�������ݸ���",Toast.LENGTH_SHORT).show();
				if(i==data_num)
				{					
					v1equipped=true;
					btnenter.setEnabled(true);
				}
			}
		});
        
        btnenter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double ave,aveexp,aveexp_s;
				ave=cal_ave();
				//StringBuffer str=new StringBuffer();
				aveexp=cal_exp_ave();
				aveexp_s=aveexp/Math.sqrt(data_num);
				//str.append(aveexp);
				tvdis.setText(tvdis.getText().toString().trim()+"\n"
						+"ƽ����="+new Num(ave).show()+"\n"
						+"ʵ���׼��="+new Num(aveexp).show()+"\n"
						+"ƽ��ֵ��ʵ���׼��="+new Num(aveexp_s).show());
				ave_exp=aveexp;
				ave_exps=aveexp_s;
				//3
				ave_n=ave;
				
				btnenter.setEnabled(false);
										
			}
			private double cal_exp_ave() {
				// TODO Auto-generated method stub
				double result=0,ave=cal_ave();
				int j;
				for (j=0;j<data_num;j++)
				{
					result=result+(data_ave[j]-ave)*(data_ave[j]-ave);
				}
				result=result/(double)(data_num-1);
				result=Math.sqrt(result);																
				return result;
			}
			private double cal_ave() {
				// TODO Auto-generated method stub
				double result=0;
				int j;
				for(j=0;j<data_num;j++)
					result=result+data_ave[j];
				result=result/data_num;
				return result;
			}
		});
        
        btnreset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=0;
				data_num=-1;
				etnum.setText("");
				btnenter.setEnabled(false);
				tvdis.setText("����:");
			}
		});
    }
	

	
	private void InitViewPager() {
		// TODO Auto-generated method stub
    	viewPager=(ViewPager) findViewById(R.id.vPager);
    	views=new ArrayList<View>();
    	LayoutInflater lf = getLayoutInflater().from(this);
    	
       	//infalater view 
       	 
       	view1 = lf.inflate( R.layout.view1, null);
        view2 = lf.inflate( R.layout.view2, null);
        view3 = lf.inflate( R.layout.view3, null);
        view4 = lf.inflate( R.layout.view4, null);
        
        views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
    	
		viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        
    	//view1 = findViewById(R.layout.view1);
//        view2 = findViewById(R.layout.view2);
//        view3 = findViewById(R.layout.view3);
//        view4 = findViewById(R.layout.view4);
                     	    	       	               
	}
    /**
     *  ��ʼ���ױ�
     */

	private void InitTextView() {
		// TODO Auto-generated method stub
		textView1 = (TextView) findViewById(R.id.text1);
        textView2 = (TextView) findViewById(R.id.text2);
        textView3 = (TextView) findViewById(R.id.text3);
        textView4 = (TextView) findViewById(R.id.text4);
 
        textView1.setOnClickListener(new MyOnClickListener(0));
        textView2.setOnClickListener(new MyOnClickListener(1));
        textView3.setOnClickListener(new MyOnClickListener(2));
        textView4.setOnClickListener(new MyOnClickListener(3));
	}
	/**
    2      * ��ʼ��������ҳ������ʱ������ĺ���Ҳ����Ч��
    3 */
	private void InitImageView() {
		// TODO Auto-generated method stub
		imageView= (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();// ��ȡͼƬ���
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��
        offset = (screenW / 4 - (int)(bmpW*0.75)) / 3;// ����ƫ����
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// ���ö�����ʼλ��
    }
	/**
	 * 
	 * @author stranger
	 * Num����˼·�Ż����������
	 * �����������������߾��Ƚ�ϣ�ѭ���ж�С��λ���Ӷ�������Ч����
	 * 
	 */
	private class Num{
		private double data;
		private  String result; 
		private int flag=0;
		Num(double dou)
		{
			if (dou>=0)
			data=dou;
			else
			{
				flag=1;
				data=-1*dou;
			}
			/**
			 * �ָ� .(δ���÷���)
			 * �ж���������0��С��������λ ȡ��λС�� 
			 * ����С��0��С��������λ �жϷ�λ ������ָ�� for ת����ѧ������5λС��   || �߾���ת�� ||��λֱ��
			 */
			//ԭʼ�ж�
//			StringBuffer str1=new StringBuffer();
//			BigDecimal bigDecimal = new BigDecimal(data);
//			str1.append(bigDecimal.toString());
//			String[] str=str1.toString().split("//.");
//			if (str[1].equals("0")||str[1].equals(null))
//				return;
//			int t;
//			try{
//			t=Integer.parseInt(str[0].trim());
//			}catch(Exception e)
//			{
//				return;	
//			}
			if (data>=1)
				//(t>0&&str[1].length()>=5)
			{
				data=(((double)Math.round(data*100000000))/100000000);	
				result=String.valueOf(data);
				return;
			}
			if(data<1)//(t<0)
			{//ԭʼ�ж�
				//if (str[1].length()<=5)
			//	return;
//				int p,j,flag=1;
//				for(j=0;flag==1&&j<str[1].length();)
//				{
//					if (str[1].charAt(j)!='0')
//						flag=0;
//					j++;
//				}
				//�߾���
//				BigDecimal b = new BigDecimal(String.valueOf(data));  
//				BigDecimal divisor = BigDecimal.ONE;  
//				MathContext mc = new MathContext(2);  
//				//result=b.divide(divisor, mc).toString(); 
//				result=b.toString();
				
				
				int j=0,k;
				double temp=data;
				while(temp<1)
				{
					temp=temp*10;
					j++;
				}
				temp=(((double)Math.round(temp*1000000))/1000000);	
				BigDecimal a=new BigDecimal(String.valueOf(temp));
				for(k=0;k<j;k++)
					a=a.divide(new BigDecimal(10));
				result=a.toString();
				
				
			}
		}
		public String show()
		{
			if (flag==1)
				return "-"+result;
			else
				return result;
		}
	};

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menu_about) {
            Intent intent =new Intent(MainActivity.this, About_menu.class);
            startActivity(intent);
           // intent.
        }
        return super.onOptionsItemSelected(item);
    }
   
    private class MyOnClickListener implements OnClickListener{
        private int index=0;
        public MyOnClickListener(int i){
            index=i;
        }
        public void onClick(View v) {
            viewPager.setCurrentItem(index);           
        }
         
    }
     
    public class MyViewPagerAdapter extends PagerAdapter{
        private List<View> mListViews;
         
        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }
 
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)   {  
            container.removeView(mListViews.get(position));
        }
 
 
        @Override
        public Object instantiateItem(ViewGroup container, int position) {         
             container.addView(mListViews.get(position));
             return mListViews.get(position);
        }
 
        @Override
        public int getCount() {        
            return  mListViews.size();
        }
         
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {          
            return arg0==arg1;
        }
    }
 
    public class MyOnPageChangeListener implements OnPageChangeListener{
 
        int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����
        int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����
        public void onPageScrollStateChanged(int arg0) {
             
             
        }
 
        public void onPageScrolled(int arg0, float arg1, int arg2) {
             
             
        }
 
        public void onPageSelected(int arg0) {
            /*���ַ����������һ�֣����滹��һ�֣���Ȼ����Ƚ��鷳
            Animation animation = null;
            switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
                 
            }
            */
            Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);//��Ȼ����Ƚϼ�ֻ࣬��һ�д��롣
            currIndex = arg0;
            animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
            animation.setDuration(300);
            imageView.startAnimation(animation);
           // Toast.makeText(MainActivity.this, "��ѡ����"+ viewPager.getCurrentItem()+"ҳ��", Toast.LENGTH_SHORT).show();
        }
         
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                        Object mHelperUtils;
                        Toast.makeText(this, "�ٰ�һ���˳�����ʵ��", Toast.LENGTH_SHORT).show();
                        mExitTime = System.currentTimeMillis();

                } else {
                        finish();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
}
// //�豸ID��ȡ
//public static String getDeviceInfo(Context context) {
//    try{
//      org.json.JSONObject json = new org.json.JSONObject();
//      android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
//          .getSystemService(Context.TELEPHONY_SERVICE);
//
//      String device_id = tm.getDeviceId();
//
//      android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//
//      String mac = wifi.getConnectionInfo().getMacAddress();
//      json.put("mac", mac);
//
//      if( TextUtils.isEmpty(device_id) ){
//        device_id = mac;
//      }
//
//      if( TextUtils.isEmpty(device_id) ){
//        device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
//      }
//
//      json.put("device_id", device_id);
//
//      return json.toString();
//    }catch(Exception e){
//      e.printStackTrace();
//    }
//  return null;
//}
//                  
    
    
}
  

