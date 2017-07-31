package cn.wsgwz.excelhelper;


import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 
 * 艾佳鞋业样品(鞋子样品)
 * @author admin
 *
 */

public class ShoesSimple {

	private String title = "艾佳鞋业样品单";//
	
	private String modelNo = "款号";//款号
	private String color;//颜色
	private String inTheMiddle;//内里
	private String buttom;//大底

	private String id = "0";
	
	private String creatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//创建时间

	public ShoesSimple( String modelNo, String color, String inTheMiddle, String buttom) {
		this.modelNo = modelNo;
		this.color = color;
		this.inTheMiddle = inTheMiddle;
		this.buttom = buttom;
	}

	public ShoesSimple( String modelNo, String color, String inTheMiddle, String buttom,String id) {
		this.modelNo = modelNo;
		this.color = color;
		this.inTheMiddle = inTheMiddle;
		this.buttom = buttom;
		this.id = id;
	}



	public ShoesSimple(String title, String modelNo, String color, String inTheMiddle, String buttom, String creatTime,String id) {
		this.title = title;
		this.modelNo = modelNo;
		this.color = color;
		this.inTheMiddle = inTheMiddle;
		this.buttom = buttom;
		this.creatTime = creatTime;
		this.id = id;
	}

	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getInTheMiddle() {
		return inTheMiddle;
	}
	public void setInTheMiddle(String inTheMiddle) {
		this.inTheMiddle = inTheMiddle;
	}
	public String getButtom() {
		return buttom;
	}
	public void setButtom(String buttom) {
		this.buttom = buttom;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
