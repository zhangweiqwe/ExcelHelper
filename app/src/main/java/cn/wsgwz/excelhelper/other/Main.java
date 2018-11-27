package poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import poi.Sort.OnSortListener;
/**
 * 
 * @author Administrator
 * 复制docx文件内容，并写入指定文件
 * 
 * remark:仅支持纯文本docx文件 
 *
 */

public class Main {

	public static void main(String[] args) throws Exception {
		doxc();
	}

	public static void doxc() throws InvalidFormatException, IOException {
		File targetFile = new File("F:/z.docx");
		File resultFile = new File("F:/result.docx");

		FileInputStream inputStream = new FileInputStream(targetFile);
		XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
		List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
		// List<XWPFPictureData> pictures = xDocument.getAllPictures();
		/*
		 * Map<String, String> map = new HashMap<String, String>(); for(XWPFPictureData
		 * picture : pictures){
		 * 
		 * String id = picture.getParent().getRelationId(picture); File folder = new
		 * File(absolutePath); if (!folder.exists()) { folder.mkdirs(); } String rawName
		 * = picture.getFileName(); String fileExt =
		 * rawName.substring(rawName.lastIndexOf(".")); String newName =
		 * System.currentTimeMillis() + UUID.randomUUID().toString() + fileExt; File
		 * saveFile = new File(absolutePath + File.separator + newName);
		 * 
		 * @SuppressWarnings("resource") FileOutputStream fos = new
		 * FileOutputStream(saveFile); fos.write(picture.getData());
		 * System.out.println(id); System.out.println(saveFile.getAbsolutePath());
		 * map.put(id, saveFile.getAbsolutePath()); }
		 */

		XWPFDocument mXwpfDocument = new XWPFDocument();
		int currentIndex = 0;
		
		int start = 326;
		int end = 338;
		for (int i = start; i < end; i++) {
			System.out.println(i);
			for (int u = 0; u < paragraphs.size(); u++) {
				XWPFParagraph paragraph = paragraphs.get(u);
				XWPFParagraph mWParagraph = mXwpfDocument.createParagraph();
				copyParagraph(paragraph, mWParagraph);
				mXwpfDocument.setParagraph(mWParagraph, currentIndex);

				if (u == 0) {
					prepareHandler(mWParagraph, i);
				}
				currentIndex++;
			}
			if(i!=end-1) {
				XWPFParagraph mWParagraph = mXwpfDocument.createParagraph();
				mWParagraph.setPageBreak(true);
				currentIndex++;
			}
			
		}

		OutputStream os = new FileOutputStream(resultFile);
		mXwpfDocument.write(os);

		os.close();
		inputStream.close();

	}

	private static void prepareHandler(XWPFParagraph wParagraph, int num) {
		String s0 = "CGJ-201807" + String.format("%0" + 3 + "d", num);
		List<XWPFRun> runs = wParagraph.getRuns();
		for (XWPFRun run : runs) {
			/* System.out.println(run.getCTR().xmlText()); */
			if (run.getCTR().xmlText().indexOf("<w:drawing>") != -1) {
				/*
				 * String runXmlText = run.getCTR().xmlText(); int rIdIndex =
				 * runXmlText.indexOf("r:embed"); int rIdEndIndex = runXmlText.indexOf("/>",
				 * rIdIndex); String rIdText = runXmlText.substring(rIdIndex, rIdEndIndex);
				 * System.out.println(rIdText.split("\"")[1].substring("rId".length())); String
				 * id = rIdText.split("\"")[1]; System.out.println(map.get(id));
				 * 
				 * text = text +"<img src = '"+map.get(id)+"'/>";
				 */
			} else {
				run.setText(s0, 0);
				break;
			}
		}
	}


	private static void copyParagraph(XWPFParagraph source, XWPFParagraph target) {
		target.getCTP().setPPr(source.getCTP().getPPr());
		for (int i = 0; i < source.getRuns().size(); i++) {
			XWPFRun run = source.getRuns().get(i);
			XWPFRun targetRun = target.createRun();
			// copy formatting
			targetRun.getCTR().setRPr(run.getCTR().getRPr());
			// no images just copy text
			targetRun.setText(run.getText(0));

			
			
		}
	}

}
