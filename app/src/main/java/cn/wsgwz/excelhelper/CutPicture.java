import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
public class CutPicture {

 
 
 private static final int SPLITE_HEIGHT = 960;
 
 private static void cut0(File file) throws Exception{
	 String fileName=file.getName();  
	 String s=fileName.substring(fileName.lastIndexOf("."));
	 File resultDerectory = new File(file.getParentFile().getAbsolutePath().replace("d:\\", "F:\\"),fileName.replace(s, ""));
	 if(!resultDerectory.exists()){
		 //System.out.println("-------------->"+resultDerectory);
		 
		 if(resultDerectory.mkdirs()){
			cut(file,resultDerectory);
		 }else{
			//System.out.println("异常创建结果储存文件夹失败，请检查文件名=====>"+resultDerectory);
		 }
		 
	 }else{
		 cut(file,resultDerectory);
	 }
	 
 }
 
 private static void  cut(File file,File resultDerectory) throws IOException{
	 
	 
	 
	 BufferedImage srcImage = null;
	 srcImage = ImageIO.read(file);
	 int srcImageHeight = srcImage.getHeight();
	 int srcImageWidth = srcImage.getWidth();
	 
	 int len = (int)Math.floor(srcImageHeight/SPLITE_HEIGHT);
	 
	 
	 int y = 0;
	// int y1 = 0;
	 for(int i=0;i<len;i++){
		 final File resultFile = new File(resultDerectory,i+"_"+file.getName());
		 if(resultFile.exists()){
			 resultFile.delete();
		 }
		 y = i*SPLITE_HEIGHT;
		 //y1=y+SPLITE_HEIGHT;
		 cut2(file,resultFile,y,srcImageWidth,SPLITE_HEIGHT);
		 
		 //System.out.println(y+"  ");
	 }
	 
	 
	 int lastY0 = srcImageHeight%SPLITE_HEIGHT;
	 if(lastY0!=0){
		 
		 final File resultFile = new File(resultDerectory,(int)(len+1)+"_"+file.getName());
		 if(resultFile.exists()){
			 resultFile.delete();
		 }
		 //y1=y+SPLITE_HEIGHT;
		 cut2(file,resultFile,(int)len*SPLITE_HEIGHT,srcImageWidth,lastY0);
		 
		 //System.out.println(y+"  "+lastY0);
	 }
	
	 
	 
	 
	 System.out.println();
 }
 
 private static void  cut2(File file,File resultFile,int y,int x1,int y1) throws IOException{
	 FileInputStream is = null;
	  ImageInputStream iis = null;
	  try {
	   is = new FileInputStream(file);
	   Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
	   ImageReader reader = it.next();
	   
	 
	   
	 
	   iis = ImageIO.createImageInputStream(is);
	   reader.setInput(iis, true);
	   ImageReadParam param = reader.getDefaultReadParam();
	   
	   
	   Rectangle rect = new Rectangle(0, y, x1, y1);
	   param.setSourceRegion(rect);
	   BufferedImage bi = reader.read(0, param);
	   int srcImageHeight =  bi.getHeight();
	   int srcImageWidth =  bi.getWidth();
	   //System.out.println(srcImageHeight+" "+srcImageWidth);
	   ImageIO.write(bi, "jpg", resultFile);
	  } finally {
	   if (is != null)
	    is.close();
	   if (iis != null)
	    iis.close();
	   
	   System.out.println("-->"+file.getAbsolutePath().toString()+"      ===>"+resultFile.getAbsolutePath().toString());
	  }
 }
 
 
  
 
 private static boolean recursion(File file) throws Exception{
	 if(file==null){System.out.println("不存在该目录");return false;}
	 
	 boolean isFile = file.isFile();
	 if(isFile){
		 cut0(file);
	 }else{
		 
		 File[] files = file.listFiles();
		 if(files!=null){
			 for(int i=0;i<files.length;i++){
				 if(file.isDirectory()){
					 if(recursion(files[i])){
						 continue;
					 }
				 }else{
					 cut0(files[i]);
				 }
			 }
				
		 }
		 
	 }

     return isFile;
 }
 
 public static void main(String[] args) throws Exception{	 
	 recursion(new File("d:/需要裁剪的图片/"));
	 System.out.println("处理完成!");
 }
}
