package com.plantapp1_0_0;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.refen.Shed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivitySet extends Activity implements View.OnClickListener,
		OnItemSelectedListener, TextWatcher {
//	MyConnect con;
	private long exitTime = 0;//用于计算用户连续按两次返回键的时间间隔，决定是否退出程序
	private Spinner spinner_greenhouses;// 选择温室的下拉列表
	private int spinner_sel = 0;// 当前选择的温室在下拉列表的序号

	private EditText tem;//页面上的四个输入控件EditText
	private EditText wet;
	private EditText sun;
	private EditText wind;
	
	String msgSetTemp ;//服务器收到保存的信号后返回的提示信息
	String msgSetWet ;
	String msgSetSun ;
	String msgSetCO2 ;
	

	int change = 0;//当用户点击保存时判断数据和上一次是否有改变，如果数据没有发生变化则只向用户提示，不向服务器发送
	String s1, s2, s3, s4;//输入的预设值

	String url_que = MyConnect.ip + "/environment/socket/shed/set?shedId=";
	int t;// 判断和服务器是否通讯完毕
	int ret;// 返回信号

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
			// 大棚ID：shedId[spinner_sel]
			// 保存数据参数：s1,s2,s3,s4
			 String ip =url_que + MyConnect.shedId[spinner_sel]+"&temp="+s1+"&light="+s3+"&humi="+s2+"&gas="+s4;
			//String ip = "http://www.baidu.com";
			String data = Myconnect(ip);
			ret = set(data);
			t = 1;

			Message msg = new Message();
			Bundle data2 = new Bundle();
			data2.putString("value", "请求结果");
			msg.setData(data2);
			handler.sendMessage(msg);
		}
	};

	public String Myconnect(String ip) {
		try {
			String data = "";
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

	public int set(String data) {
		//根据服务器返回的数据，提取出是否保存成功的信息，并将服务器返回的信息显示出来
		//参数data:从服务器获取到的数据
		if (data == null || data.equals(""))
			return -2;
		JSONObject json;
		try {
			json = JSON.parseObject(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return -2;
		}
		Map map = (Map) JSON.toJSON(json);
		// System.out.println(data);
		msgSetTemp = (String) map.get("temp");
		msgSetWet = (String) map.get("light");
		msgSetSun = (String) map.get("humi");
		msgSetCO2 = (String) map.get("gas");
		
		// int n=(int)map.get("number");//大棚数量
		/*
		 * int n=0; String codes[]=new String[n]; int shedIds[]=new int[n];
		 */
		/*int i = 0;
		JSONArray arr = (JSONArray) map.get("sheds");
		for (Object object : arr) {
			JSONObject shedjson = (JSONObject) object;
			Shed shed = JSON.toJavaObject(shedjson, Shed.class);
			
			 * codes[i]=shed.getCode(); shedIds[i]=shed.getId();
			 
			i++;

		}*/

		return 1;
		/*
		 * if (msg.equals("1001"))// 成功 { return 1; } else if
		 * (msg.equals("1000"))// 无此用户 return 0; else if (msg.equals("1002"))//
		 * 密码错误 return -1; else // 其他 return -2;
		 */

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_set);
		SysApplication.getInstance().addActivity(this);
//		con = new MyConnect();

		Button btnQue = (Button) findViewById(R.id.btnQue);
		btnQue.setOnClickListener(this);

		Button btnCtr = (Button) findViewById(R.id.btnCtr);
		btnCtr.setOnClickListener(this);

		Button btnSet = (Button) findViewById(R.id.btnSet);
		btnSet.setOnClickListener(this);

		Button btnHis = (Button) findViewById(R.id.btnHis);
		btnHis.setOnClickListener(this);

		spinner_greenhouses = (Spinner) findViewById(R.id.spinner_greenhouse);
		/*
		 * ArrayAdapter<CharSequence> adapter = ArrayAdapter
		 * .createFromResource(this, R.array.greenhouses,
		 * R.layout.my_simple_spinner_item);
		 */
		if (MyConnect.greenhouses == null) {
			MyConnect.greenhouses = new String[1];
			MyConnect.greenhouses[0] = "您没有温室";
		}
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				this, R.layout.my_simple_spinner_item, MyConnect.greenhouses);
		adapter.setDropDownViewResource(R.layout.my_simple_spinner_dropdown_item);
		spinner_greenhouses.setAdapter(adapter);
		spinner_greenhouses.setPrompt("请选择温室：");
		spinner_greenhouses.setSelection(0, true);
		spinner_greenhouses.setOnItemSelectedListener(this);

		Button refresh_greenhouse = (Button) findViewById(R.id.refresh_greenhouse);
		refresh_greenhouse.setOnClickListener(this);

		tem = (EditText) findViewById(R.id.set_tem);
		wet = (EditText) findViewById(R.id.set_wet);
		sun = (EditText) findViewById(R.id.set_sun);
		wind = (EditText) findViewById(R.id.set_wind);

		tem.addTextChangedListener(this);
		wet.addTextChangedListener(this);
		sun.addTextChangedListener(this);
		wind.addTextChangedListener(this);

		Button save = (Button) findViewById(R.id.save);
		save.setOnClickListener(this);

		freshData();

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		/*
		 * change=1; Button t = (Button) findViewById(R.id.refresh_greenhouse);
		 * t.setText(change+"");
		 */
	}

	@Override
	public void afterTextChanged(Editable v) {
		// TODO Auto-generated method stub
		change = 1;
		// Button t = (Button) findViewById(R.id.refresh_greenhouse);
		// t.setText(change+"");

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.login, menu);
		menu.add(0, 0, 0, R.string.logout).setIcon(R.drawable.logoutt);
		menu.add(0, 1, 0, R.string.exit).setIcon(R.drawable.exitt);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) { // 判断按下的菜单选项
		case 0:// 注销
			Intent intent = new Intent(MainActivitySet.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
		case 1: // 按下了“退出”菜单
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnQue: {
			// 点击查询后的事件
			Intent intent = new Intent(MainActivitySet.this,
					MainActivityQue.class);
			startActivity(intent);
			finish();
		}
			break;
		case R.id.btnCtr: {
			// 点击控制后的事件
			Intent intent = new Intent(MainActivitySet.this,
					MainActivityCtr.class);
			startActivity(intent);
			finish();
		}
			break;
		case R.id.btnSet: {
			// 点击配置后的事件
			/*
			 * Intent intent = new Intent(MainActivityHis.this,
			 * MainActivitySet.class); startActivity(intent); finish();
			 */
		}
			break;
		case R.id.btnHis: {
			// 点击历史后的事件
			Intent intent = new Intent(MainActivitySet.this,
					MainActivityHis.class);
			startActivity(intent);
			finish();
		}
			break;
		case R.id.refresh_greenhouse: {
			// 点击刷新后的事件

			freshData();

		}
			break;
		case R.id.save: {

			// String sid = id.getEditableText().toString().trim();
			if (change == 0) {
				// 数据没有更改过

				Toast toast = Toast.makeText(getApplicationContext(),
						"数据没有发生变化哦！", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();

			} else {

				s1 = tem.getEditableText().toString().trim();
				s2 = wet.getEditableText().toString().trim();
				s3 = sun.getEditableText().toString().trim();
				s4 = wind.getEditableText().toString().trim();

				// 如果发送成功：
				send();
				/*
				 * if (send(s1, s2, s3, s4)) { change = 0; Toast toast =
				 * Toast.makeText(getApplicationContext(), "保存成功！",
				 * Toast.LENGTH_SHORT); toast.setGravity(Gravity.CENTER, 0, 0);
				 * toast.show();
				 * 
				 * }
				 */

			}
		}
			break;
		}
	}

	void send() {
		//发送预设环境值到服务器的相关操作
		change = 0;
		t = 0;
		ret = 0;

		new Thread(networkTask).start();

		long begin = System.currentTimeMillis();

		while (t == 0) {
			long end = System.currentTimeMillis();
			if ((end - begin) > 3000) {
				ret = -2;
				break;
			}

		}
		


		if (ret == 1) {
			Toast toast = Toast.makeText(getApplicationContext(), msgSetTemp+"\n"+msgSetWet+"\n"+msgSetSun+"\n"+msgSetCO2,
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else {
			Toast toast = Toast.makeText(getApplicationContext(), "连接服务器超时！",
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}

	}

	/*
	 * boolean send(String s1, String s2, String s3, String s4) { boolean a, b,
	 * c, d; a = b = c = d = true; if (!s1.equals("")) a = send(s1, 1); if
	 * (!s1.equals("")) b = send(s2, 2); if (!s1.equals("")) c = send(s3, 3); if
	 * (!s1.equals("")) d = send(s4, 4); return (a && b && c && d); }
	 * 
	 * boolean send(String s, int i) { // i：第i个EditText // s：第i个EditText的值
	 * 
	 * // 未写：点击保存后的事件，把EditView的值发送到服务器 return true;
	 * 
	 * // if发送失败，return false; }
	 */

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
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

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		spinner_sel = arg2;
		/*
		 * Button t = (Button) findViewById(R.id.refresh_greenhouse);
		 * t.setText(spinner_sel+"");
		 */
		freshData();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	void freshData() {
		// 从服务器下载数据s1~s4，改变EditView的值

		// 当前选择的温室Id ： spinner_sel

		// con.setRecv(spinner_sel,);

		String s1, s2, s3, s4;
		s1 = "";
		s2 = "";
		s3 = "";
		s4 = "";

		tem.setText(s1);
		wet.setText(s2);
		sun.setText(s3);
		wind.setText(s4);
		/*
		 * Toast toast = Toast.makeText(getApplicationContext(), "刷新成功！",
		 * Toast.LENGTH_SHORT); toast.setGravity(Gravity.CENTER, 0, 0);
		 * toast.show();
		 */
		change = 0;

		// 111
		/*
		 * Button t = (Button) findViewById(R.id.refresh_greenhouse);
		 * t.setText(change+"");
		 */

	}

}
