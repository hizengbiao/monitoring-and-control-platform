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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivityCtr extends Activity implements View.OnClickListener,
		OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {
	// MyConnect con;
	private long exitTime = 0;//用于计算用户连续按两次返回键的时间间隔，决定是否退出程序
	private Spinner spinner_greenhouses;// 选择温室的下拉列表
	private int spinner_sel = 0;// 当前选择的温室在下拉列表的序号
	private int eqpId;// 当前选择的设备ID
	/*
	 * private ToggleButton togglebutton1; private ToggleButton togglebutton2;
	 * private ToggleButton togglebutton3; private ToggleButton togglebutton4;
	 */
	private SeekBar[] bar;//四个设备的档次控制开关
	int value[];// 每个设备的当前档次
	
	private TextView tip_values[];//当用户拖动开关时的实时档次提示信息

	/*
	 * private SeekBar[] bar1; private SeekBar bar2; private SeekBar bar3;
	 * private SeekBar bar4;
	 */

	// 设备的档次-2~2
	private final int MAX = 2;
	private final int SEC_MAX = 1;
	private final int MIDDLE = 0;
	private final int SEC_MIN = -1;
	private final int MIN = -2;
	// private int value_temp;

	String url_que = MyConnect.ip + "/environment/socket/login?username=";//此处改
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
			
			//需要的参数：
			// 大棚ID：shedId[spinner_sel]
			// 设备ieqpId(0~3),值为value[ieqpId];
			
			 String ip =url_que + "";//此处改
			
			String data = Myconnect(ip);
			ret = ctr(data);
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

	public int ctr(String data) {
		//根据服务器返回的数据，提取出控制是否成功的信息
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
		String msg = (String) map.get("msg");

		if (msg.equals("suc")) {

			// 如果发送成功：
			MyConnect.lastOkValue[eqpId] = value[eqpId];

			return 1;
		}
		else
			return -1;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_ctr);
		SysApplication.getInstance().addActivity(this);

		// con = new MyConnect();

		Button btnQue = (Button) findViewById(R.id.btnQue);
		btnQue.setOnClickListener(this);

		Button btnCtr = (Button) findViewById(R.id.btnCtr);
		btnCtr.setOnClickListener(this);

		Button btnSet = (Button) findViewById(R.id.btnSet);
		btnSet.setOnClickListener(this);

		Button btnHis = (Button) findViewById(R.id.btnHis);
		btnHis.setOnClickListener(this);

		Button refresh_greenhouse = (Button) findViewById(R.id.refresh_greenhouse);
		refresh_greenhouse.setOnClickListener(this);
		spinner_greenhouses = (Spinner) findViewById(R.id.spinner_greenhouse);
		/*
		 * ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		 * this, R.array.greenhouses, R.layout.my_simple_spinner_item);
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

		/*
		 * togglebutton1 = (ToggleButton) findViewById(R.id.togglebutton1);
		 * togglebutton2 = (ToggleButton) findViewById(R.id.togglebutton2);
		 * togglebutton3 = (ToggleButton) findViewById(R.id.togglebutton3);
		 * togglebutton4 = (ToggleButton) findViewById(R.id.togglebutton4);
		 * togglebutton1.setOnClickListener(this);
		 * togglebutton2.setOnClickListener(this);
		 * togglebutton3.setOnClickListener(this);
		 * togglebutton4.setOnClickListener(this);
		 */

		bar = new SeekBar[4];
		bar[0] = (SeekBar) findViewById(R.id.SeekBar1);
		bar[1] = (SeekBar) findViewById(R.id.SeekBar2);
		bar[2] = (SeekBar) findViewById(R.id.SeekBar3);
		bar[3] = (SeekBar) findViewById(R.id.SeekBar4);

		value = new int[4];
		MyConnect.lastOkValue = new int[4];

		tip_values = new TextView[4];
		tip_values[0] = (TextView) findViewById(R.id.vaule1);
		tip_values[1] = (TextView) findViewById(R.id.vaule2);
		tip_values[2] = (TextView) findViewById(R.id.vaule3);
		tip_values[3] = (TextView) findViewById(R.id.vaule4);

		for (int i = 0; i < bar.length; i++) {
			value[i] = 0;
			MyConnect.lastOkValue[i] = 0;
			bar[i].setOnSeekBarChangeListener(this);
		}

		/*
		 * bar1 = (SeekBar) findViewById(R.id.SeekBar1); bar2 = (SeekBar)
		 * findViewById(R.id.SeekBar2); bar3 = (SeekBar)
		 * findViewById(R.id.SeekBar3); bar4 = (SeekBar)
		 * findViewById(R.id.SeekBar4);
		 * 
		 * bar1.setOnSeekBarChangeListener(this);
		 * bar2.setOnSeekBarChangeListener(this);
		 * bar3.setOnSeekBarChangeListener(this);
		 * bar4.setOnSeekBarChangeListener(this);
		 */

		freshData();

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
			Intent intent = new Intent(MainActivityCtr.this,
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
		case R.id.refresh_greenhouse: {
			// freshData();

		}
			break;
		case R.id.btnQue: {
			// 点击查询后的事件
			Intent intent = new Intent(MainActivityCtr.this,
					MainActivityQue.class);
			startActivity(intent);
			finish();
		}
			break;
		case R.id.btnCtr: {
			// 点击控制后的事件
			// Intent intent = new Intent(MainActivityCtr.this,
			// MainActivityCtr.class);
			// startActivity(intent);
		}
			break;
		case R.id.btnSet: {
			// 点击配置后的事件
			Intent intent = new Intent(MainActivityCtr.this,
					MainActivitySet.class);
			startActivity(intent);
			finish();
		}
			break;
		case R.id.btnHis: {
			// 点击历史后的事件
			Intent intent = new Intent(MainActivityCtr.this,
					MainActivityHis.class);
			startActivity(intent);
			finish();
		}
			break;
		/*
		 * case R.id.togglebutton1: { // 点击开关1后的事件
		 * 
		 * send(1, togglebutton1.isChecked());
		 * 
		 * if (togglebutton1.isChecked()) {
		 * 
		 * } // 当按钮再次被点击时候响应的事件 else {
		 * 
		 * }
		 * 
		 * 
		 * } break; case R.id.togglebutton2: { // 点击开关2后的事件
		 * 
		 * send(2, togglebutton2.isChecked());
		 * 
		 * if (togglebutton2.isChecked()) {
		 * 
		 * } // 当按钮再次被点击时候响应的事件 else {
		 * 
		 * }
		 * 
		 * 
		 * } break;
		 * 
		 * case R.id.togglebutton3: { // 点击开关3后的事件
		 * 
		 * send(3, togglebutton3.isChecked());
		 * 
		 * 
		 * if (togglebutton3.isChecked()) {
		 * 
		 * } // 当按钮再次被点击时候响应的事件 else {
		 * 
		 * }
		 * 
		 * 
		 * } break;
		 * 
		 * case R.id.togglebutton4: { // 点击开关4后的事件
		 * 
		 * send(4, togglebutton4.isChecked());
		 * 
		 * 
		 * if (togglebutton4.isChecked()) {
		 * 
		 * } // 当按钮再次被点击时候响应的事件 else {
		 * 
		 * }
		 * 
		 * 
		 * } break;
		 */

		}
	}

	/*
	 * // 发送控制信号： void send(int btNo, boolean b) { // btNo是第几个控制信号，b是发送开启还是关闭
	 * 
	 * // 当前选择的温室Id ： spinner_sel
	 * 
	 * // 未写：通过温室ID spinner_sel 给服务器发送控制信号
	 * 
	 * }
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

	void setButton(ToggleButton b) {
		if (b.isChecked()) {
			b.setChecked(false);
		}
		// 当按钮再次被点击时候响应的事件
		else {
			b.setChecked(true);
		}
	}

	@Override
	public void onProgressChanged(SeekBar v, int arg1, boolean arg2) {
		//当拖动条状态发生变化时触发，参数arg1:当前seekbar的值，0~100
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.SeekBar1: {
			setSeekBar(0, arg1, 1);

		}
			break;
		case R.id.SeekBar2: {
			setSeekBar(1, arg1, 1);

		}
			break;
		case R.id.SeekBar3: {
			setSeekBar(2, arg1, 1);
		}
			break;
		case R.id.SeekBar4: {
			setSeekBar(3, arg1, 1);
		}
			break;
		}
	}

	public void setSeekBar(int which, int progress, int sys) {
		//设置seekbar的值
		//参数which:表明是哪个设备的档次开关；progress设置开关的值；sys:如果sys为1，progress的值为0-100，如果sys为0，表明progress的值为-2~2
		int temp;
		if (sys == 1) {
			temp = (progress - 50) / 25;
		} else {
			temp = progress;
		}

		value[which] = temp;
		tip_values[which].setText("当前档次：" + value[which]);
		// int temp = Integer.valueOf(progress);
		switch (temp) {
		case MAX:
			bar[which].setProgress(100);
			break;
		case SEC_MAX:
			bar[which].setProgress(75);
			break;
		case MIDDLE:
			bar[which].setProgress(50);
			break;
		case SEC_MIN:
			bar[which].setProgress(25);
			break;
		case MIN:
			bar[which].setProgress(0);
			break;
		default:
			bar[which].setProgress(0);
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar v) {
		//当开关停止拖动时触发，向服务器发送相应设备的控制档次
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.SeekBar1: {
			send(0);

		}
			break;
		case R.id.SeekBar2: {
			send(1);

		}
			break;
		case R.id.SeekBar3: {
			send(2);
		}
			break;
		case R.id.SeekBar4: {
			send(3);
		}
			break;
		}

	}

	void send(int i) {// 发送数据
		// 设备i(0~3),值为value[i];
		/*
		 * Button refresh_greenhouse = (Button)
		 * findViewById(R.id.refresh_greenhouse);
		 * refresh_greenhouse.setText(i+" "+value[i]);
		 */
		eqpId = i;
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
			Toast toast = Toast.makeText(getApplicationContext(), "控制信号发送成功！",
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else {
			String err_msg="连接服务器超时！";
			if(ret==-1)
				err_msg="保存失败！";
			Toast toast = Toast.makeText(getApplicationContext(),err_msg ,
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			freshData();
		}

		/*
		 * 
		 * con.ctrSend(spinner_sel, i, value[i]);
		 */
	}

	void freshData() {//当网络连接失败、服务器控制失败等情况发生时调用，将档次开关重置为上一次配置成功的状态

		for (int i = 0; i < 4; i++) {
			setSeekBar(i, MyConnect.lastOkValue[i], 0);
			tip_values[i].setText("当前档次：" + MyConnect.lastOkValue[i]);
		}

		// 当前选择的温室Id ： spinner_sel
		/*
		 * boolean checked1, checked2, checked3, checked4; checked1 = checked2 =
		 * checked3 = checked4 = false;
		 * 
		 * // 未写：通过温室ID spinner_sel 从服务器下载checked1,checked2,checked3,checked4 ,
		 * // 改变控件的状态
		 * 
		 * // 从服务器接收value[0~3] // String val[]=new String[4];
		 * con.ctrRecv(spinner_sel, value);
		 * 
		 * for (int i = 0; i < 4; i++) { setSeekBar(i, value[i], 0);
		 * tip_values[i].setText("当前档次：" + value[i]); }
		 * 
		 * Toast toast = Toast.makeText(getApplicationContext(), "刷新成功！",
		 * Toast.LENGTH_SHORT); toast.setGravity(Gravity.CENTER, 0, 0);
		 * toast.show();
		 * 
		 * 
		 * 
		 * togglebutton1.setChecked(checked1);
		 * togglebutton2.setChecked(checked2);
		 * togglebutton3.setChecked(checked3);
		 * togglebutton4.setChecked(checked4);
		 * 
		 * 
		 * // setButton(togglebutton1); // setButton(togglebutton2); //
		 * setButton(togglebutton3); // setButton(togglebutton4);
		 */
	}

}
