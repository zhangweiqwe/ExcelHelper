package cn.wsgwz.excelhelper;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.write.WriteException;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1023; // 请求码

    public boolean isWrite = true;


    public List<ShoesSimple> shoesSimpleList;
    public MainAdapter mainAdapter;
    private  FileHelper fileHelper;

    private RecyclerView recyclerView;

    public long id = 1;

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        if(Build.VERSION.SDK_INT>22){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE);
            }else {
                initData();
            }
        }else {
            initData();
        }




    }


    private void initData(){
        fileHelper =  FileHelper.getInstance();

        shoesSimpleList = new ArrayList<>();

        try {
            List<ShoesSimple> listTemp = fileHelper.getData();

            if(listTemp!=null){
                shoesSimpleList.addAll(listTemp);
                id = Long.parseLong(shoesSimpleList.get(shoesSimpleList.size()-1).getId());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        mainAdapter = new MainAdapter(shoesSimpleList,MainActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(mainAdapter);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initData();
            }else {
                Toast.makeText(this,getString(R.string.lack_permission),Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },3000);
            }
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                new CustomDialog(MainActivity.this).show();
                break;
            case R.id.write:
                try {
                    if(shoesSimpleList==null){
                       break;
                    }
                    fileHelper.writeData(shoesSimpleList);
                    isWrite = true;


                    ExcelHelper.createExcel(shoesSimpleList);
                    toastSuccess();
                } catch (IOException e) {
                    e.printStackTrace();
                    toastError(e.getMessage());
                } catch (WriteException e) {
                    e.printStackTrace();
                    toastError(e.getMessage());
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {


            if((!isWrite)){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("尚未将内容写入Sd卡");
                builder.setMessage("确定要退出吗?");

                builder.setNegativeButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });
                builder.create().show();
            }else {
                return super.onKeyDown(keyCode,event);
            }


        }

        return false;

    }

    public void toastSuccess(){
        Toast.makeText(MainActivity.this,getString(R.string.success),Toast.LENGTH_SHORT).show();
    }

    public void toastError(String msg){
        Toast.makeText(MainActivity.this,getString(R.string.error)+"\n"+msg,Toast.LENGTH_SHORT).show();
    }


}
