package com.stranger.physicsbird;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.qihoo.updatesdk.lib.UpdateHelper;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;



import android.content.Intent;
import android.support.v4.view.ViewPager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {


        //退出
        private long mExitTime;
        //页卡设置
        private MaterialViewPager mViewPager;
        private ViewPager viewPager; //页卡内容
        private List<View> views;// Tab页面列表
        // private List<String> titleList;//viewpager的标题
        // private TextView textView1, textView2, textView3, textView4;
        // private ImageView imageView;

        private View view1, view2, view3, view4;//各个页卡
        //view1 components
        private ButtonFlat btnnext;
        private ButtonRectangle btnenter;
        private ButtonRectangle btnreset;
        private com.rengwuxian.materialedittext.MaterialEditText etnum;
        private EditText etdata;
        private TextView tvdis;
        private double[] data_ave = new double[30];
        private int data_num = -1;
        private int i = 0;
        // view2
        private ButtonRectangle btngo;
        private double ave_exp;
        private TextView tvdis_v2;
        private boolean v1equipped;
        // view3
        private double ave_exps;
        private double ave_n;
        private ButtonRectangle btnokv3;
        private EditText etdmv3;
        private TextView tvdisv3;

        //view4
        private ButtonRectangle btnnextv4;
        private ButtonRectangle btnresetv4;
        private ButtonRectangle btncalv4;
        private EditText etx;
        private EditText ety;
        private TextView tvdisv4;
        private TextView tvbv4;
        int nv4 = 0;
        double[][] data4 = new double[60][2];
        double b, a;

        //private EditText test;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            update();


            // System.out.println( getDeviceInfo(MainActivity.this) );

            setContentView(R.layout.paper);
            //InitImageView();
           // InitTextView();
            InitViewPager();

            Initview1();
            Initview2();
            Initview3();
            Initview4();

        }
    /*
     * 统计,update使用
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
   //  /   // end !


        // update
        private void update() {
            // TODO Auto-generated method stub



            //	 MobclickAgent.setDebugMode( true ); //Debug 模式
            MobclickAgent.enableEncrypt(true);

            UpdateHelper.getInstance().setDebugMode(false);
            UpdateHelper.getInstance().init(getApplicationContext(), Color.parseColor("#0A93DB"));
          //  UpdateHelper.getInstance().manualUpdate(getPackageName());
            UpdateHelper.getInstance().autoUpdate(getPackageName());

        }

    //end !

        private void Initview4() {

            // TODO Auto-generated method stub
            btnnextv4 = (ButtonRectangle) view4.findViewById(R.id.btnnextv4);
            btncalv4 = (ButtonRectangle) view4.findViewById(R.id.btncalv4);
            btnresetv4 = (ButtonRectangle) view4.findViewById(R.id.btnresetv4);
            etx = (com.rengwuxian.materialedittext.MaterialEditText) view4.findViewById(R.id.etxv4);
            etx.setInputType(8194);  //inputType=“numberDecimal”
            ety = (com.rengwuxian.materialedittext.MaterialEditText) view4.findViewById(R.id.etyv4);
            ety.setInputType(8194);
            tvdisv4 = (TextView) view4.findViewById(R.id.tvdisv4);
            tvbv4 = (TextView) view4.findViewById(R.id.tvbv4);

            btnnextv4.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    double x = 0, y = 0;

                    //double [][]data=new double[60][2];
                    try {
                        x = Double.valueOf(etx.getText().toString().trim());
                        y = Double.valueOf(ety.getText().toString().trim());
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "数据输入错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tvdisv4.setText(tvdisv4.getText().toString().trim() + " (" + x + "," + y + ") ,");
                    data4[nv4][0] = x;
                    data4[nv4][1] = y;
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
                    double avex = 0, avey = 0, x2 = 0, y2 = 0, xy = 0;

                    if (nv4<1) {
                        Toast.makeText(MainActivity.this, "请输入数据", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (i = 0; i < nv4; i++) {
                        avex = data4[i][0] + avex;
                        avey = data4[i][1] + avey;
                        x2 = data4[i][0] * data4[i][0] + x2;
                        y2 = data4[i][1] * data4[i][1] + y2;
                        xy = data4[i][0] * data4[i][1] + xy;
                    }
                    avey = avey / nv4;
                    avex = avex / nv4;
                    x2 = x2 / nv4;
                    y2 = y2 / nv4;
                    xy = xy / nv4;

                    b = (avex * avey - xy) / (avex * avex - x2);
                    a = avey - b * avex;

                    tvbv4.setText("b=" + new Num(b).show() + "\na=" + new Num(a).show());
                }
            });
            btnresetv4.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    tvdisv4.setText("坐标(输入完整坐标后点Next):");
                    tvbv4.setText("结果");
                    etx.setText("");
                    ety.setText("");
                    nv4 = 0;
                }
            });
        }

        private void Initview3() {
            // TODO Auto-generated method stub

            btnokv3 = (ButtonRectangle) view3.findViewById(R.id.btnokv3);
            btnokv3.setEnabled(false);
            etdmv3 = (com.rengwuxian.materialedittext.MaterialEditText) view3.findViewById(R.id.etdmv3);
            etdmv3.setInputType(8194);//inputType=“numberDecimal”
            tvdisv3 = (TextView) view3.findViewById(R.id.tvdisv3);
            btnokv3.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    double t = 0;
                    double ua = ave_exps, ub, ux, ex;
                    try {
                        t = Double.valueOf(etdmv3.getText().toString().trim());
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "数据输入错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ub = t / Math.sqrt(3);
                    ux = Math.sqrt(ua * ua + ub * ub);
                    ex = ux / ave_n;
                    tvdisv3.setText("u(x)=" + new Num(ux).show() + "\nE(x)=" + new Num(ex).show() + "\n请自行保留适当位数，推荐不确定度保留1位,且与平均值的最后一位对齐.\n");

                }
            });


        }

        private void Initview2() {
            // TODO Auto-generated method stub
            btngo = (ButtonRectangle) view2.findViewById(R.id.btngo);
            // test=(EditText) view2.findViewById(R.id.editText1);
            tvdis_v2 = (TextView) view2.findViewById(R.id.tvdis_v2);


            btngo.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    boolean flag = true;
                    double[] data_temp = new double[30];
                    int k = 0, j;

                    if (!v1equipped)
                        return;
                    //第一次
                    for (j = 0; j < data_num; j++) {
                        if (Math.abs(data_ave[j] - ave_n) > 3 * ave_exp) {
                            tvdis_v2.setText(tvdis_v2.getText().toString().trim() + data_ave[j] + ", ");
                            flag = false;
                        } else {
                            data_temp[k] = data_ave[j];
                            k++;
                        }
                    }
                    if (k == data_num && flag == true) {
                        tvdis_v2.setText("剔除:\n第一次 无坏值");
                        return;
                    }

                    //第二次
                    flag = true;
                    double result;
                    int jc = 2;

                    while (jc<4) {
                        result = aveex(data_temp, k);

                        int t = 0;
                        for (j = 0; j < k; j++) {
                            if (Math.abs(data_temp[j] - ave_n) > 3 * result) {
                                tvdis_v2.setText(tvdis_v2.getText().toString().trim() + "\n第" + jc + "次：" + data_ave[j] + ", ");
                                flag = false;
                            } else {
                                data_temp[t] = data_temp[j];
                                t++;
                            }

                            if (t == k && flag) {
                                tvdis_v2.setText(tvdis_v2.getText().toString().trim() + "\n第" + jc + "次检查无坏值");
                                ave_exps = result;
                                v1equipped = false;
                                return;
                            }
                            k = t;
                            j++;
                        }
                        flag=true;
                        jc=jc+1;
                    }


                }

                private double aveex(double[] data, int cou) {
                    // TODO Auto-generated method stub
                    double result = 0;
                    double ave_t = 0;
                    int j;
                    for (j = 0; j < cou; j++)
                        ave_t = ave_t + data[j];
                    ave_t = ave_t / (double) cou;
                    ave_n = ave_t;
                    for (j = 0; j < cou; j++) {
                        result = result + (data[j] - ave_t) * (data[j] - ave_t);
                    }
                    result = result / (double) (cou - 1);
                    result = Math.sqrt(result);
                    return result;
                }
            });
        }

        private void Initview1() {
            // TODO Auto-generated method stub
            btnnext = (ButtonFlat) view1.findViewById(R.id.btnnext);
            btnreset = (ButtonRectangle) view1.findViewById(R.id.btnreset);
            btnenter = (ButtonRectangle) view1.findViewById(R.id.btnenter);
            btnenter.setEnabled(false);
            etnum = (com.rengwuxian.materialedittext.MaterialEditText) view1.findViewById(R.id.etnum);
            etnum.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
            etdata = (EditText) view1.findViewById(R.id.etdata);
            etdata.setInputType(8194);//inputType=“numberDecimal”
            tvdis = (TextView) view1.findViewById(R.id.tvdis);
            btnnext.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    // TODO Auto-generated method stub
                    if (etnum.getText() == null || etnum.getText().toString().trim().equals("")) {
                        Toast.makeText(MainActivity.this, "未输入数据个数", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (data_num == -1) {
                        int t;
                        try {
                            t = Integer.parseInt(etnum.getText().toString().trim());
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "数据个数输入错误", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (t <= 0) {
                            Toast.makeText(MainActivity.this, "数据个数输入错误", Toast.LENGTH_SHORT).show();
                            return;
                        } else
                            data_num = t;
                        //etnum.setEnabled(false);

                    }
                    if (etdata.getText().toString().trim().equals("")) {
                        Toast.makeText(MainActivity.this, "数据不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        double data_t = Double.valueOf(etdata.getText().toString().trim());
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "数据输入错误", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (i < data_num) {
                        tvdis.setText(tvdis.getText() + etdata.getText().toString().trim() + ", ");
                        data_ave[i] = Double.valueOf(etdata.getText().toString().trim());
                        etdata.setText("");
                        i++;
                    } else
                        Toast.makeText(MainActivity.this, "超出数据个数", Toast.LENGTH_SHORT).show();
                    if (i == data_num) {
                        v1equipped = true;
                        btnenter.setEnabled(true);
                    }
                }
            });

            btnenter.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    double ave, aveexp, aveexp_s;
                    ave = cal_ave();
                    //StringBuffer str=new StringBuffer();
                    aveexp = cal_exp_ave();
                    aveexp_s = aveexp / Math.sqrt(data_num);
                    //str.append(aveexp);
                    tvdis.setText(tvdis.getText().toString().trim() + "\n"
                            + "平均数x_ave=" + new Num(ave).show() + "\n"
                            + "实验标准差S(x)=" + new Num(aveexp).show() + "\n"
                            + "平均值的实验标准差S(x_ave)=" + new Num(aveexp_s).show());
                    ave_exp = aveexp;
                    ave_exps = aveexp_s;
                    //3
                    ave_n = ave;

                    btnenter.setEnabled(false);
                    btnokv3.setEnabled(true);

                }

                private double cal_exp_ave() {
                    // TODO Auto-generated method stub
                    double result = 0, ave = cal_ave();
                    int j;
                    for (j = 0; j < data_num; j++) {
                        result = result + (data_ave[j] - ave) * (data_ave[j] - ave);
                    }
                    result = result / (double) (data_num - 1);
                    result = Math.sqrt(result);
                    return result;
                }

                private double cal_ave() {
                    // TODO Auto-generated method stub
                    double result = 0;
                    int j;
                    for (j = 0; j < data_num; j++)
                        result = result + data_ave[j];
                    result = result / data_num;
                    return result;
                }
            });

            btnreset.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    i = 0;
                    data_num = -1;
                    etnum.setText("");
                    etdata.setText("");
                    btnenter.setEnabled(false);
                    tvdis.setText("数据:");
                }
            });
        }


        private void InitViewPager() {
            // TODO Auto-generated method stub
           // viewPager = (ViewPager) findViewById(R.id.vPager);
            mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
            views = new ArrayList<View>();
            LayoutInflater lf = getLayoutInflater().from(this);
            viewPager = mViewPager.getViewPager();
            //infalater view

            view1 = lf.inflate(R.layout.view1, null);
            view2 = lf.inflate(R.layout.view2, null);
            view3 = lf.inflate(R.layout.view3, null);
            view4 = lf.inflate(R.layout.view4, null);

            views.add(view1);
            views.add(view2);
            views.add(view3);
            views.add(view4);

            viewPager.setAdapter(new MyViewPagerAdapter(views));
            mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

            mViewPager.getToolbar().setVisibility(viewPager.INVISIBLE);
            mViewPager.getPagerTitleStrip().setTextColor(Color.WHITE);
            mViewPager.getPagerTitleStrip().setBackgroundColor(0xff326cd1);//1e88e5
         //   mViewPager.getHeaderBackgroundContainer().setBackgroundColor(0xff326cd1);
            viewPager.setCurrentItem(0);
          //  viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
            mViewPager.getPagerTitleStrip().setOnPageChangeListener(new MyOnPageChangeListener());
        }


        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Object mHelperUtils;
                    Toast.makeText(this, "再按一次退出物理实燕", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();

                } else {
                    finish();
                }
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }

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
                Intent intent = new Intent(MainActivity.this, About_menu.class);
                startActivity(intent);
                // intent.
            }
            return super.onOptionsItemSelected(item);
        }
/*
        private class MyOnClickListener implements OnClickListener {
            private int index = 0;

            public MyOnClickListener(int i) {
                index = i;
            }

            public void onClick(View v) {
                viewPager.setCurrentItem(index);
            }

        }
*/

/*
        /**
         * 初始化底标
         *

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
*/
    /**
     * 2      * 初始化动画，页卡滑动时，下面的横线也滑动效果
     * 3
     */
    /*

        private void InitImageView() {
            // TODO Auto-generated method stub
            imageView = (ImageView) findViewById(R.id.cursor);
            bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();// 获取图片宽度
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int screenW = dm.widthPixels;// 获取分辨率宽度
            offset = (screenW / 4 - (int) (bmpW * 0.75)) / 3;// 计算偏移量
            Matrix matrix = new Matrix();
            matrix.postTranslate(offset, 0);
            imageView.setImageMatrix(matrix);// 设置动画初始位置
        }
*/


// //设备ID获取
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


