package com.plantapp1_0_0;

public class MyConnect {
	static String ip="http://115.159.89.24:8080/";
	static String[] greenhouses=null;//大棚下拉列表数据源
	static int[] shedId=new int[1];//大棚下拉列表每个数据项在服务器的id
	static int lastOkValue[];// 控制界面每个设备上一次设置成功的值，当网络连接失败或控制失败时便于重置
	
/*	String address;
	static String data;
	
	String url_que = "http://112.24.25.130:8080/environment/socket/login?username=";
	static int connect_sign = 1;// 网络连接成功否
	Thread readURL;
	URL url;
	URLConnection myConn;
	InputStream in;
	
	public MyConnect() {
		address = null;
		url = null;
		data = "";
	}

	public void setAd(String a) {
		address = a;
		try {
			// url=new URL(address);
			url = new URL(
					"http://112.24.25.130:8080/environment/socket/login?username=troy&password=1314520");
			try {
				myConn = url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int login(String id, String psw) {
		String a = url_que + id + "&password=" + psw;
		setAd(a);
		send();
		
		//Myconnect();

		JSONObject json = JSON.parseObject(data);
		Map map = (Map) JSON.toJSON(json);
		System.out.println(data);
		// String msg=(String) map.get("msg");
		String msg = "1001";
		if (msg.equals("1001"))// 成功
		{
			return 1;
		} else if (msg.equals("1000"))// 无此用户
			return 0;
		else if (msg.equals("1002"))// 密码错误
			return -1;
		else
			// 其他
			return -2;

	}

	public void ctrSend(int spinner_sel, int i, int value) {
		// 发送控制信号
		// spinner_sel：下拉列表号码，从0开始，,
		// int i 第i个设备，从0开始，,
		// int value 设备档次，-2~2

	}

	public void ctrRecv(int spinner_sel, int value[]) {
		// spinner_sel：下拉列表号码，从0开始，,
		// value[0~3]:接收四个设备的档次
		// example: value[2]=2;

	}

	public void Myconnect() {
		try {
			url = new URL(
					"http://112.24.25.130:8080/environment/socket/login?username=troy&password=1314520");
			in = url.openStream();

			byte[] b = new byte[1024];
			int n = -1;

			while ((n = in.read(b)) != -1) {
				String str = new String(b, 0, n);
				System.out.println(str);
				data=str;
				// data+=str;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send() {

		try {
			look = new Look();
			look.setURL(url);
			readURL = new Thread(look);
			readURL.start();
			data = look.data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data = "err";
		}
		
		 * try {
		 * 
		 * in=myConn.getInputStream(); BufferedInputStream bis = new
		 * BufferedInputStream(in);//获取BufferedInputStream对象 ByteArrayBuffer baf
		 * = new ByteArrayBuffer(bis.available()); int ttt = 0; while((ttt =
		 * bis.read())!= -1){ //读取BufferedInputStream中数据 baf.append((byte)ttt);
		 * //将数据读取到ByteArrayBuffer中 } data =
		 * EncodingUtils.getString(baf.toByteArray(), "UTF-8"); //转换为字符串
		 * 
		 * byte []b=new byte[1024];
		 * 
		 * int n = in.read(b) ;
		 * 
		 * data = new String(b,0,n) ; // int n=-1; // while((n=in.read(b))!=-1){
		 * // String str=new String(b,0,n); // data+=str; // }
		 * 
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); connect_sign=0; }
		 
	}*/

}
