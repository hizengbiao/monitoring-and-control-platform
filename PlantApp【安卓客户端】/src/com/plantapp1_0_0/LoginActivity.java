package com.plantapp1_0_0;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.refen.Shed;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {

	public static final String SP_INFOS = "SPDATA_Files";
	public static final String USERID = "UserID";
	public static final String PASSWORD = "PassWord";
//	MyConnect con;
	// String url="http://112.24.27.74:8080/environment/socket/login?username=";
	

	private static String uidstr; // 用户帐号,记住密码用
	private static String pwdstr; // 用户密码
	
	String idd;//登录用
	String psww;

	private EditText id;//输入用户名的控件
	private EditText psw;//输入密码的控件
	private TextView loginSucTip;//登录失败的各种原因提示
	private long exitTime = 0;//用于计算用户连续按两次返回键的时间间隔，决定是否退出程序
	private static CheckBox cb; // "记住我"复选框组件

	private static boolean key_correct;//是否保存密码的标记，只有当用户名、密码正确才保存

	/*
	 * String data1; int t;
	 */
	
	
	String url_que = MyConnect.ip + "/environment/socket/login?username=";
	int t;//判断和服务器是否通讯完毕
	int ret;//返回信号

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle data = msg.getData();
			String val = data.getString("value");
			Log.i("mylog", "请求结果为-->" + val);
			// TODO
			// UI界面的更新等相关操作
		}
	};

	Runnable networkTask = new Runnable() {

		@Override
		public void run() {
			// TODO
			// 在这里进行 http request.网络请求相关操作

			String ip =url_que + idd + "&password=" + psww;
			String data=Myconnect(ip);
			ret=login(data);
			t=1;

			Message msg = new Message();
			Bundle data2 = new Bundle();
			data2.putString("value", "请求结果");
			msg.setData(data2);
			handler.sendMessage(msg);
		}
	};

	public String Myconnect(String ip) {//ip:通信的url地址
		try {
			String data="";
			URL url = new URL(ip);
			InputStream in = url.openStream();
			byte[] b = new byte[1024];
			int n = -1;
			while ((n = in.read(b)) != -1) {
				String str = new String(b, 0, n);
				data += str;
			}
			return data;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}



	public int login(String data) {	
		//根据服务器返回的数据，提取出登录是否成功的信息，如果登录成功，则再取出用户的大棚信息
		//参数data:从服务器获取到的数据	
		if(data==null||data.equals(""))
			return -2;
		JSONObject json ;
		try {
			json = JSON.parseObject(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return -2;
		}
		Map map = (Map) JSON.toJSON(json);
		System.out.println(data);
		 String msg=(String) map.get("msg");
		// int n=(int)map.get("number");//大棚数量
		 int n=0; 
		if (msg.equals("1001"))// 成功
		{
			List<String> dapeng=new ArrayList<String>();
			 List<Integer> dapengId=new ArrayList<Integer>();
			 JSONArray arr=(JSONArray) map.get("sheds");
			 for(Object object:arr){
				 JSONObject shedjson=(JSONObject) object;
				 Shed shed=JSON.toJavaObject(shedjson, Shed.class);
				 dapeng.add(shed.getCode());
				 dapengId.add(shed.getId());
				 /*shedIds[i]=shed.getId();*/
				 n++;
				 
			 }
			 String codes[]=new String[n];
			 int shedIds[]=new int[n];
			 for(int i=0;i<n;i++){
				 codes[i]=dapeng.get(i);
				 shedIds[i]=dapengId.get(i);
			 }
			MyConnect.greenhouses=codes;
			MyConnect.shedId=shedIds;
			return 1;
		} else if (msg.equals("1000"))// 无此用户
			return 0;
		else if (msg.equals("1002"))// 密码错误
			return -1;
		else
			// 其他
			return -2;

	}
	
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {//创建Activity前的一些准备工作，取得页面布局中的控件，初始化相关变量
		//
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		SysApplication.getInstance().addActivity(this);

		id = (EditText) findViewById(R.id.loginid);
		psw = (EditText) findViewById(R.id.loginpsw);
		loginSucTip = (TextView) findViewById(R.id.LoginSucTip);

//		con = new MyConnect();

		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);

		Button btnReg = (Button) findViewById(R.id.btnReg);
		btnReg.setOnClickListener(this);

		key_correct = false;

		cb = (CheckBox) findViewById(R.id.cbRemember); // 获得CheckBox对象
		checkIfRemember(); // 从SharedPreferences中读取用户的帐号和密码

	}

	@Override
	protected void onStop() {//如果用户点击了记住密码并且登录成功，则将用户信息保存
		super.onStop();
		if (cb.isChecked() && (key_correct)) {
			uidstr = id.getText().toString().trim(); // 获得输入的帐号
			pwdstr = psw.getText().toString().trim(); // 获得输入的密码
			rememberMe(uidstr, pwdstr); // 将用户的帐号与密码存入SharedPreferences
		} else {
			rememberMe(null, null);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	// 方法：从SharedPreferences中读取用户的帐号和密码
	public void checkIfRemember() {
		SharedPreferences sp = getSharedPreferences(SP_INFOS, MODE_PRIVATE);
		// 获得Preferences
		uidstr = sp.getString(USERID, null); // 取Preferences中的帐号
		pwdstr = sp.getString(PASSWORD, null); // 取Preferences中的密码
		if (uidstr != null && pwdstr != null) {
			id.setText(uidstr); // 给EditText控件赋帐号
			psw.setText(pwdstr); // 给EditText控件赋密码
			cb.setChecked(true);
		}
	}

	// 方法：将用户的id和密码存入SharedPreferences
	public void rememberMe(String uid, String pwd) {
		SharedPreferences sp = getSharedPreferences(SP_INFOS, MODE_PRIVATE);
		// 获得Preferences
		SharedPreferences.Editor editor = sp.edit(); // 获得Editor
		editor.putString(USERID, uid); // 将用户的帐号存入Preferences
		editor.putString(PASSWORD, pwd); // 将密码存入Preferences
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {//添加退出程序的选项菜单
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.login, menu);
		menu.add(0, 0, 0, R.string.exit).setIcon(R.drawable.exitt);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) { // 判断按下的菜单选项
		case 0: // 按下了“退出”菜单
			SysApplication.getInstance().exit();// 退出程序
			// System.exit(0);//退出程序
			// pos =
			// DiaryListActivity.this.lv.getSelectedItemPosition();//取ListView当前项的ID
			// showDialog(DIALOG_DELETE); //显示确认删除对话框，并实施相应操作
			break;
		}
		return true;
		// return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {//点击各个按钮的监听事件
		//参数v：当前点击的按钮
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnLogin: {
			// 点击登录后的事件
			idd = id.getEditableText().toString().trim();
			psww  = psw.getEditableText().toString().trim();

			if (idd.equals("")) {
				loginSucTip.setText("请输入用户名！");
			} else if (psww.equals("")) {
				loginSucTip.setText("请输入密码！");
			} else {				
				t = 0;
				new Thread(networkTask).start();				
				long begin=System.currentTimeMillis();
				while (t == 0) {
					long end=System.currentTimeMillis();
					if((end-begin)>3000){
						ret=-2;
						break;
					}						
				}

				if (ret == 1) {// 登录成功

					// loginSucTip.setText("登录成功");

					key_correct = true;

					TextView tv = new TextView(this);
					tv.setText("登录成功！"); // 内容
					//tv.setText(data1);
					tv.setTextSize(30);// 字体大小
					// tv.setPadding(30, 20, 10, 10);//位置
					tv.setTextColor(Color.parseColor("#FF0000"));// 颜色
					tv.setGravity(Gravity.CENTER);
					tv.setWidth(200);
					tv.setHeight(100);
					// AlertDialog.Builder builder = new
					// AlertDialog.Builder(this).setTitle("登录成功");
					AlertDialog.Builder builder = new AlertDialog.Builder(this)
							.setCustomTitle(tv);
					builder.setPositiveButton("确定", new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(LoginActivity.this,
									MainActivityQue.class);
							startActivity(intent);
							finish();
						}
					}).show();

					/*
					 * new
					 * AlertDialog.Builder(this).setTitle("登录成功").setPositiveButton
					 * ("确定",new OnClicklistener() { public void
					 * onClick(DialogInterface dialog, int which) {
					 * show.setText("单击了【确定】按钮！"); } }).show();
					 */
					// Intent intent = new Intent(LoginActivity.this,
					// MainActivityQue.class);
					// startActivity(intent);

				} else if (ret == -1)// 密码错误
				{
					loginSucTip.setText("密码错误！");
				}

				else if (ret == 0) {// 无此用户
					loginSucTip.setText("无此用户！");
				} /*else if (MyConnect.connect_sign == 0) {// 网络连接失败
					loginSucTip.setText("网络连接失败！");
				}*/ else// 服务器出错
				{
					loginSucTip.setText("连接服务器超时！");
				}

			}

		}
			break;
		case R.id.btnReg: {
			// 点击后门后的事件
			//该部分为方便调试设置，程序测试完成后应移除

			Intent intent = new Intent(LoginActivity.this,
					MainActivityQue.class);
			startActivity(intent);
			finish();

		}
			break;
		}
	}

	/*
	 * boolean IsLoginOk(String sid, String spsw) { // 判断是否登录成功 // 未写：和服务器通讯
	 * 
	 * int c=con.login(sid,spsw); if (c==1) return true; else if(c==-1) return
	 * false; else if(c==0) return false; else return false; }
	 */

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//如果用户连续快速点击了两次返回键，则退出程序
		//参数：keycode，按键码；event，事件
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
