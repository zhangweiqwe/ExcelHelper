package cn.wsgwz.excelhelper;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/28 0028.
 */

public class FileHelper {
    private static final String TAG = FileHelper.class.getSimpleName();

    private static final FileHelper fileHelper = new FileHelper();
    private String spliteFlag = "-----";
    private String endLine = "\\r?\\n";
    private String defaultFileName = "备份.txt";
    private FileHelper(){}
    public static final FileHelper getInstance(){
        return fileHelper;
    }



    private static File initBackupsFolder(){
        File backupsFolder = new File(Environment.getExternalStorageDirectory()+"/"+Const.BACKUPS_FOLDER);
        if(!backupsFolder.exists()){
            backupsFolder.mkdirs();
        }
        return backupsFolder;
    }


    public File writeData(List<ShoesSimple> shoesSimpleList) throws IOException {
        File derectory = initBackupsFolder();
        File file = new File(derectory+"/"+defaultFileName);
        writeData(file,shoesSimpleList);
        return file;
    }


    private void writeData(File file,List<ShoesSimple> shoesSimpleList) throws IOException {

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<shoesSimpleList.size();i++){
            ShoesSimple shoesSimple = shoesSimpleList.get(i);
            String line = shoesSimple.getTitle()+spliteFlag+
                    shoesSimple.getModelNo()+spliteFlag+
                    shoesSimple.getColor()+spliteFlag+
                    shoesSimple.getInTheMiddle()+spliteFlag+
                    shoesSimple.getButtom()+spliteFlag+
                    shoesSimple.getCreatTime()+spliteFlag+
                    shoesSimple.getId();

            sb.append(line+"\r\n");
        }

        ByteArrayInputStream in = new ByteArrayInputStream(sb.toString().getBytes());
        FileOutputStream out = new FileOutputStream(file);

        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) != -1){
            out.write(buffer,0,len);
        }
        out.flush();
        out.close();
        in.close();
    }

    public List<ShoesSimple> getData() throws IOException {
        return getData(defaultFileName);
    }

    private List<ShoesSimple> getData(String  fileName) throws IOException {

        File derectory = initBackupsFolder();
        File file = new File(derectory+"/"+fileName);
        List<ShoesSimple> shoesSimples = null;



        InputStream in = new FileInputStream(file);


        byte[] buffer = new byte[1024];
        int len = 0;
        StringBuilder sb = new StringBuilder();

        while((len = in.read(buffer)) != -1){
            sb.append(new String(buffer,0,len));
        }
        in.close();




        String contentStr = sb.toString();
        if(contentStr.contains("\r\n")){
            shoesSimples = new ArrayList<>();
            String[] content = contentStr.split(endLine);
            if(content!=null){

                for (int i=0;i<content.length;i++){
                    String[] args = content[i].split(spliteFlag);
                    ShoesSimple shoesSimple = new ShoesSimple(args[0],args[1],args[2],args[3],args[4],args[5],args[6]);
                    shoesSimples.add(shoesSimple);
                }

            }
        }


        return shoesSimples;
    }




}
