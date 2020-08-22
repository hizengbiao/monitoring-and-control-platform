package com.plantapp1_0_0;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.refen.Shed;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

//import com.example.hellocharttest.R;

import android.app.DatePickerDialog;
import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityHis extends Activity implements View.OnClickListener {
//	MyConnect con;
	private long exitTime = 0;//用于计算用户连续按两次返回键的时间间隔，决定是否退出程序
	private int mYear1 = 2016, mMonth1 = 12, mDay1 = 1;// 起始日期
	private int mYear2 = 2016, mMonth2 = 12, mDay2 = 10;// 结束日期
	private int countDay = 0;// 两个日期的间隔时间
	DatePickerDialog datePickerDialog1 = null;
	DatePickerDialog datePickerDialog2 = null;
	private Button date1;// 显示开始日期的控件
	private Button date2;// 显示结束日期的控件
	// final private int DATE_DIALOG = 1;
	// private int dataSign = 1;
	private Spinner spinner_greenhouses2;// 选择温室的下拉列表
	private Spinner spinner_type;// 选择查询类型的下拉列表
	private int spinner_sel1 = 0;// 当前选择的温室在下拉列表的序号
	private int spinner_sel2 = 0;// 当前选择的查询类型在下拉列表的序号
	private Button line_query;// 查询按钮

	private LineChartView lineChart;
	String[] date = null;// X轴的标注
	float[] score = null;// 图表的数据
	// float[] score2 = null;// 图表的数据2（室外数据）

	/*
	 * String[] date = { "5-23", "5-22", "6-22", "5-23", "5-22", "2-22", "5-22",
	 * "4-22", "9-22", "10-22", "11-22", "12-22", "1-22", "6-22", "5-23",
	 * "5-22", "2-22", "5-22", "4-22", "9-22", "10-22", "11-22", "12-22",
	 * "4-22", "9-22", "10-22" };// X轴的标注 int[] score = { 74, 22, 18, 79, 20,
	 * 74, 20, 74, 42, 90, 74, 42, 90, 50, 42, 90, 33, 10, 74, 22, 18, 79, 20,
	 * 74, 22, 18, 79, 20, 0 };// 图表的数据
	 */

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
			// private int mYear1 , mMonth1, mDay1 ;// 起始日期
			// private int mYear2 , mMonth2 , mDay2;// 结束日期
			// 大棚ID：shedId[spinner_sel]
			// spinner_type 温度、湿度、光照等类型的序号
			
			String ip =url_que + "";//此处改
			String data = Myconnect(ip);
			ret = his(data);
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

	public int his(String data) {
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
		String msg = (String) map.get("msg");
		if (msg.equals("suc")) {
			
			List<Long> date1=new ArrayList<Long>();
			 List<Float> score1=new ArrayList<Float>();
			 JSONArray arr=(JSONArray) map.get("sheds");//此处改
			 int n=0;
			 for(Object object:arr){
				 JSONObject shedjson=(JSONObject) object;
				 Shed shed=JSON.toJavaObject(shedjson, Shed.class);//此处改
			/*	 date1.add(shed.getCode());//此处改
				 score1.add(shed.getId());//此处改
*/				 n++;				 
			 }	
			
			
			date = new String[n];			
			score = new float[n];
			
			for (int i = 0; i < date.length; i++) {				
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(date1.get(i));
				cal.set(mYear1, mMonth1 - 1, mDay1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				date[i] = sdf.format(cal.getTime());
				score[i]=score1.get(i);				
			}
			return 1;
		} else
			return -1;
	}

	private List<PointValue> mPointValues = null;
	// private List<PointValue> mPointValues2 = null;
	private List<AxisValue> mAxisXValues = null;

	private DatePickerDialog.OnDateSetListener mdateListener1 = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear1 = year;
			mMonth1 = monthOfYear + 1;
			mDay1 = dayOfMonth;
			display(1);
		}
	};
	private DatePickerDialog.OnDateSetListener mdateListener2 = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear2 = year;
			mMonth2 = monthOfYear + 1;
			mDay2 = dayOfMonth;
			display(2);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_his);
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

		final Calendar ca = Calendar.getInstance();
		mYear2 = ca.get(Calendar.YEAR);
		mMonth2 = ca.get(Calendar.MONTH) + 1;
		mDay2 = ca.get(Calendar.DAY_OF_MONTH);

		ca.add(Calendar.DATE, -7);
		mYear1 = ca.get(Calendar.YEAR);
		mMonth1 = ca.get(Calendar.MONTH) + 1;
		mDay1 = ca.get(Calendar.DAY_OF_MONTH);

		date1 = (Button) findViewById(R.id.dateChoose1);
		date2 = (Button) findViewById(R.id.dateChoose2);

		date1.setOnClickListener(this);
		date2.setOnClickListener(this);

		/*
		 * date1.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // dataSign = 1; 555
		 * 
		 * } });
		 * 
		 * date2.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // dataSign = 2;
		 * showDialog(DATE_DIALOG + 1); // onCreateDialog(DATE_DIALOG+1); } });
		 */

		spinner_greenhouses2 = (Spinner) findViewById(R.id.spinner_greenhouse2);
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
		spinner_greenhouses2.setAdapter(adapter);
		spinner_greenhouses2.setPrompt("请选择温室：");
		spinner_greenhouses2.setSelection(0, true);

		spinner_greenhouses2
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View v,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						spinner_sel1 = arg2;
						// line_query.setText(spinner_sel1 + "" + spinner_sel2);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		spinner_type = (Spinner) findViewById(R.id.spinner_type);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
				this, R.array.typess, R.layout.my_simple_spinner_item);
		adapter2.setDropDownViewResource(R.layout.my_simple_spinner_dropdown_item);
		spinner_type.setAdapter(adapter2);
		spinner_type.setPrompt("请选择查询类型：");
		spinner_type.setSelection(0, true);
		spinner_type
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View v,
							int arg2, long arg3) {
						// TODO Auto-generated method stub

						spinner_sel2 = arg2;
						// line_query.setText(spinner_sel1 + "" + spinner_sel2);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

		line_query = (Button) findViewById(R.id.line_query);
		line_query.setOnClickListener(this);

		// 拆线图：
		lineChart = (LineChartView) findViewById(R.id.line_chart);

		freshView();

	}

	/*
	 * @Override protected Dialog onCreateDialog(int id) { switch (id) { case
	 * DATE_DIALOG: // removeDialog(DATE_DIALOG); return new
	 * DatePickerDialog(this, mdateListener1, mYear1, mMonth1 - 1, mDay1); case
	 * DATE_DIALOG + 1: // removeDialog(DATE_DIALOG+1); return new
	 * DatePickerDialog(this, mdateListener2, mYear2, mMonth2 - 1, mDay2); }
	 * return null; }
	 */

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
			Intent intent = new Intent(MainActivityHis.this,
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
		case R.id.dateChoose1: {
			// 点击日期1后的事件
			if (datePickerDialog1 != null) {

				// 非首次启动就用最近一次修改的日期更新datePickerDialog
				datePickerDialog1.updateDate(mYear1, mMonth1 - 1, mDay1);
			}
			// showDialog(DATE_DIALOG);
			else {

				// 首次启动时，用context、OnDateSetListener对象和当前得到系统日期初始化datePickerDialog
				// datePickerDialog1=new DatePickerDialog();
				datePickerDialog1 = new DatePickerDialog(this, mdateListener1,
						mYear1, mMonth1 - 1, mDay1);

			}
			datePickerDialog1.show();

		}
			break;
		case R.id.dateChoose2: {
			// 点击日期2后的事件

			if (datePickerDialog2 != null) {

				// 非首次启动就用最近一次修改的日期更新datePickerDialog
				datePickerDialog2.updateDate(mYear2, mMonth2 - 1, mDay2);
			}
			// showDialog(DATE_DIALOG);
			else {

				// 首次启动时，用context、OnDateSetListener对象和当前得到系统日期初始化datePickerDialog
				// datePickerDialog1=new DatePickerDialog();
				datePickerDialog2 = new DatePickerDialog(this, mdateListener2,
						mYear2, mMonth2 - 1, mDay2);

			}
			datePickerDialog2.show();

		}
			break;
		case R.id.btnQue: {
			// 点击查询后的事件
			Intent intent = new Intent(MainActivityHis.this,
					MainActivityQue.class);
			startActivity(intent);
			finish();
		}
			break;
		case R.id.btnCtr: {
			// 点击控制后的事件
			Intent intent = new Intent(MainActivityHis.this,
					MainActivityCtr.class);
			startActivity(intent);
			finish();
		}
			break;
		case R.id.btnSet: {
			// 点击配置后的事件
			Intent intent = new Intent(MainActivityHis.this,
					MainActivitySet.class);
			startActivity(intent);
			finish();
		}
			break;
		case R.id.btnHis: {
			// 点击历史后的事件
			// Intent intent = new Intent(MainActivityHis.this,
			// MainActivityHis.class);
			// startActivity(intent);
		}
			break;
		case R.id.line_query: {
			// 点击查询后的事件
			checkDate();
			date1.setText(new StringBuffer().append(mYear1).append("-")
					.append(mMonth1).append("-").append(mDay1).append(" "));
			date2.setText(new StringBuffer().append(mYear2).append("-")
					.append(mMonth2).append("-").append(mDay2).append(" "));
			// line_query.setText(spinner_sel1+""+spinner_sel2);

			freshView();
		}
			break;
		}
	}

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

	/**
	 * 设置日期 利用StringBuffer追加
	 */
	public void display(int dataSign) {
		if (dataSign == 1)
			date1.setText(new StringBuffer().append(mYear1).append("-")
					.append(mMonth1).append("-").append(mDay1).append(" "));
		else
			date2.setText(new StringBuffer().append(mYear2).append("-")
					.append(mMonth2).append("-").append(mDay2).append(" "));
	}

	void checkDate() {// 调整日期
		if (mYear1 < 2010)
			mYear1 = 2010;
		if (mYear2 < 2010)
			mYear2 = 2010;
		Calendar ca = Calendar.getInstance();
		int tYear1 = ca.get(Calendar.YEAR);
		int tMonth1 = ca.get(Calendar.MONTH) + 1;
		int tDay1 = ca.get(Calendar.DAY_OF_MONTH);

		if (!IfDate1Smaller(mYear1, mMonth1, mDay1, tYear1, tMonth1, tDay1)) {
			mYear1 = tYear1;
			mMonth1 = tMonth1;
			mDay1 = tDay1;
		}
		if (!IfDate1Smaller(mYear2, mMonth2, mDay2, tYear1, tMonth1, tDay1)) {
			mYear2 = tYear1;
			mMonth2 = tMonth1;
			mDay2 = tDay1;
		}
		if (!IfDate1Smaller(mYear1, mMonth1, mDay1, mYear2, mMonth2, mDay2)) {
			int tem = mYear1;
			mYear1 = mYear2;
			mYear2 = tem;
			tem = mMonth1;
			mMonth1 = mMonth2;
			mMonth2 = tem;
			tem = mDay1;
			mDay1 = mDay2;
			mDay2 = tem;
		}

		if (mYear1 == mYear2 && mMonth1 == mMonth2 && mDay1 == mDay2) {
			ca.set(mYear2, mMonth2 - 1, mDay2);
			ca.add(Calendar.DATE, -7);
			mYear1 = ca.get(Calendar.YEAR);
			mMonth1 = ca.get(Calendar.MONTH) + 1;
			mDay1 = ca.get(Calendar.DAY_OF_MONTH);
		}

	}

	boolean IfDate1Smaller(int y1, int m1, int d1, int y2, int m2, int d2) {// 日期1是否小于日期2
		if (y1 < y2)
			return true;
		else if (y1 > y2)
			return false;
		else {
			if (m1 < m2)
				return true;
			else if (m1 > m2)
				return false;
			else {
				if (d1 < d2)
					return true;
				else
					// if(d1>d2)and if(d1==d2)
					return false;
			}
		}

	}

	public static int daysBetween(int y1, int m1, int d1, int y2, int m2, int d2) {
		m1--;
		m2--;
		Calendar cal = Calendar.getInstance();
		cal.set(y1, m1, d1);
		long time1 = cal.getTimeInMillis();
		// Calendar cal2 = Calendar.getInstance();
		cal.set(y2, m2, d2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	

	/**
	 * 初始化LineChart的一些设置
	 */
	private void initLineChart() {
		Line line = new Line(mPointValues)
				.setColor(Color.parseColor("#33CD41")); // 折线的颜色
		List<Line> lines = new ArrayList<Line>();
		line.setShape(ValueShape.CIRCLE);// 折线图上每个数据点的形状 这里是圆形 （有三种
											// ：ValueShape.SQUARE
											// ValueShape.CIRCLE
											// ValueShape.SQUARE）
		line.setCubic(false);// 曲线是否平滑
		// line.setStrokeWidth(3);//线条的粗细，默认是3
		line.setFilled(false);// 是否填充曲线的面积
		line.setHasLabels(true);// 曲线的数据坐标是否加上备注
		// line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
		line.setHasLines(true);// 是否用直线显示。如果为false 则没有曲线只有点显示
		line.setHasPoints(true);// 是否显示圆点 如果为false 则没有原点只有点显示
		lines.add(line);

		/*
		 * line = new Line(mPointValues2).setColor(Color.parseColor("#FFCD41"));
		 * // 折线的颜色 line.setShape(ValueShape.SQUARE);// 折线图上每个数据点的形状 这里是圆形 （有三种
		 * // ：ValueShape.SQUARE // ValueShape.CIRCLE // ValueShape.SQUARE）
		 * line.setCubic(false);// 曲线是否平滑 // line.setStrokeWidth(3);//线条的粗细，默认是3
		 * line.setFilled(false);// 是否填充曲线的面积 line.setHasLabels(true);//
		 * 曲线的数据坐标是否加上备注 //
		 * line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line
		 * .setHasLabels(true);就无效） line.setHasLines(true);// 是否用直线显示。如果为false
		 * 则没有曲线只有点显示 line.setHasPoints(true);// 是否显示圆点 如果为false 则没有原点只有点显示
		 * 
		 * lines.add(line);
		 */
		LineChartData data = new LineChartData();
		data.setLines(lines);

		// 坐标轴
		Axis axisX = new Axis(); // X轴
		axisX.setHasTiltedLabels(false); // X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示
		// axisX.setTextColor(Color.WHITE); //设置字体颜色
		axisX.setTextColor(Color.parseColor("#D6D6D9"));// 灰色

		String name = spinner_greenhouses2.getSelectedItem().toString()
				+ spinner_type.getSelectedItem().toString() + "折线图";//

		axisX.setName(name); // 表格名称
		axisX.setTextSize(18);// 设置字体大小
		axisX.setMaxLabelChars(7); // 最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
		axisX.setValues(mAxisXValues); // 填充X轴的坐标名称
		data.setAxisXBottom(axisX); // x 轴在底部
		// data.setAxisXTop(axisX); //x 轴在顶部
		axisX.setHasLines(true); // x 轴分割线

		Axis axisY = new Axis(); // Y轴
		String name2 = spinner_type.getSelectedItem().toString();
		if (name2.equals("室内温度") || name2.equals("室外温度")) {
			name2 = name2 + "，单位：℃";
		} else if (spinner_sel2 == 3) {
			// name2.equals("CO2浓度")
			name2 = "CO2浓度，单位：％";
		} else if (name2.equals("湿度")) {
			name2 = "湿度，单位：％";
		} else {// 光照
			name2 = "光照，单位：lux";
			// name2 = spinner_sel1 + "";
		}
		axisY.setName(name2);// y轴标注
		axisY.setTextSize(14);// 设置字体大小
		data.setAxisYLeft(axisY); // Y轴设置在左边
		// data.setAxisYRight(axisY); //y轴设置在右边
		// 设置行为属性，支持缩放、滑动以及平移
		lineChart.setInteractive(true);
		lineChart.setZoomType(ZoomType.HORIZONTAL); // 缩放类型，水平
		lineChart.setMaxZoom((float) 300);// 缩放比例
		lineChart.setLineChartData(data);
		lineChart.setVisibility(View.VISIBLE);

		Viewport v = new Viewport(lineChart.getMaximumViewport());
		v.left = 0;
		v.right = 7;
		lineChart.setCurrentViewport(v);
	}

	
	/**
	 * X 轴的显示
	 */
	private void getAxisXLables() {
		countDay = daysBetween(mYear1, mMonth1, mDay1, mYear2, mMonth2, mDay2) + 1;
		date = new String[countDay];

		Calendar cal = Calendar.getInstance();
		cal.set(mYear1, mMonth1 - 1, mDay1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String dateStr = sdf.format(cal.getTime());
		mAxisXValues = new ArrayList<AxisValue>();

		for (int i = 0; i < date.length; i++) {
			date[i] = sdf.format(cal.getTime());
			mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
			cal.add(Calendar.DATE, 1);// 日
		}
	}

	/**
	 * 图表的每个点的显示
	 */
	private void getAxisPoints() {
		countDay = daysBetween(mYear1, mMonth1, mDay1, mYear2, mMonth2, mDay2) + 1;
		score = new float[countDay];
		// score2 = new float[countDay];
		getScore();
		mPointValues = new ArrayList<PointValue>();
		// mPointValues2 = new ArrayList<PointValue>();
		for (int i = 0; i < score.length; i++) {
			mPointValues.add(new PointValue(i, score[i]));
			// mPointValues2.add(new PointValue(i, score2[i]));
		}
	}
	
	void getScore() {

		for (int i = 0; i < score.length; i++) {

			// 未写：从服务器下载score数据

			// score：室内数据，score2室外数据

			// 起始日期 int mYear1, mMonth1, mDay1 ;
			// 结束日期 int mYear2, mMonth2 , mDay2 ;
			// 当前选择的温室Id int spinner_sel1;
			// 当前选择的查询类型Id int spinner_sel2;

			int max = 1;
			int min = 0;
			Random random = new Random();

			score[i] = (float) ((random.nextInt(max) % (max - min + 1) + min) / 10.0);
			// score2[i] = (float) ((random.nextInt(max) % (max - min + 1) +
			// min) / 10.0);
		}

	}

	void freshView() {
		
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
			Toast toast = Toast.makeText(getApplicationContext(), "查询成功！",
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			
			
			//初始化x轴:
			
			mAxisXValues = new ArrayList<AxisValue>();

			for (int i = 0; i < date.length; i++) {
				//date[i] = sdf.format(cal.getTime());
				mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
			}
			
			//初始化y轴数据：
			mPointValues = new ArrayList<PointValue>();
			
			for (int i = 0; i < score.length; i++) {
				mPointValues.add(new PointValue(i, score[i]));
				// mPointValues2.add(new PointValue(i, score2[i]));
			}
			
			
		} else {
			String err_msg = "连接服务器超时！";
			if (ret == -1)
				err_msg = "查询失败,没有相应的数据！";
			Toast toast = Toast.makeText(getApplicationContext(), err_msg,
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			
			getAxisXLables();// 获取x轴的标注
			getAxisPoints();// 获取坐标点
			initLineChart();// 初始化

			
		}

		

	}

	/*
	 * @Override public void onDateSet(DatePicker view, int year, int
	 * monthOfYear, int dayOfMonth) { // TODO Auto-generated method stub if
	 * ((datePickerDialog1 != null) && (view.getId() ==
	 * datePickerDialog1.getDatePicker().getId())) {
	 * 
	 * // 更新TextView,并把修改后的值保存； mYear1 = year; mMonth1 = monthOfYear + 1; mDay1
	 * = dayOfMonth; display(1); } if ((datePickerDialog2 != null) &&
	 * (view.getId() == datePickerDialog2.getDatePicker().getId())) {
	 * 
	 * // 更新TextView,并把修改后的值保存； // year=0; mYear2 = year; mMonth2 = monthOfYear
	 * + 1; mDay2 = dayOfMonth; display(2); } if ((datePickerDialog1 != null) &&
	 * (datePickerDialog2 != null)) { if
	 * (datePickerDialog1.getDatePicker().getId() == datePickerDialog2
	 * .getDatePicker().getId()) { mYear2 = 1; display(2); } } }
	 */

}
