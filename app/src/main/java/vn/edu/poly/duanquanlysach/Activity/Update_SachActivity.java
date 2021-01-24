package vn.edu.poly.duanquanlysach.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import vn.edu.poly.duanquanlysach.R;
import vn.edu.poly.duanquanlysach.Model.Sachclass;
import vn.edu.poly.duanquanlysach.Model.Theloaiclass;
import vn.edu.poly.duanquanlysach.Sqlite.Sachsql;
import vn.edu.poly.duanquanlysach.Sqlite.Theloaisql;

public class Update_SachActivity extends AppCompatActivity {

    String name,matl,masach,soluong,gia,nxb;
    TextView tvTitle;
    TextInputEditText tvupMasach;
    TextInputEditText tvupTensach;
    TextInputEditText tvupNxb;
     TextInputEditText tvupSoluong;
    Button btnhuy, btnsua;
    TextInputEditText tvupGiabia;
    Sachsql sachsql;
    Sachclass sachclass;
    List<Theloaiclass> theloai_List;
    List<Sachclass> list;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__sach);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AnhXa();
        Bundle bundle= getIntent().getExtras();
        if (bundle != null) {
           name=  bundle.getString("name");
           Log.e("name",name);
           masach=  bundle.getString("ma");
            Log.e("ma",masach);
           soluong=  bundle.getString("soluong");
            Log.e("soluong",soluong);
         gia=  bundle.getString("gia");
           nxb=  bundle.getString("nxb");
           matl=  bundle.getString("matheloai");
            Log.e("matl",matl);

           sachclass= new Sachclass(masach,matl,name,nxb,soluong,gia);

        }
        spinner.setSelection(getIndexSpinner(spinner, sachclass.getMatheloai()));
        gettl(spinner);
        tvupMasach.setText(sachclass.getMasach());
        tvupTensach.setText(sachclass.getTensach());
        tvupNxb.setText(sachclass.getNxb());
        tvupSoluong.setText(sachclass.getSoluong());
        tvupGiabia.setText(sachclass.getGiabia());



        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsua.setText("Sửa xong");
                tvupTensach.setEnabled(true);
                tvupNxb.setEnabled(true);
                tvupSoluong.setEnabled(true);
                tvupGiabia.setEnabled(true);


                btnsua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tvupMasach.getText().toString().equals("") || tvupTensach.getText().toString().equals("") || tvupNxb.getText().toString().equals("") || tvupSoluong.getText().toString().equals("") || tvupGiabia.getText().toString().equals("")) {
                            Toast.makeText(Update_SachActivity.this, "không để rỗng", Toast.LENGTH_SHORT).show();
                            tvupMasach.requestFocus();
                        } else {


                            Sachclass sachclass = new Sachclass();
                            String masach = tvupMasach.getText().toString();
                            String tensach = tvupTensach.getText().toString();
                            String matheloai = spinner.getSelectedItem().toString();
                            String nxb = tvupNxb.getText().toString();
                            String soluong = tvupSoluong.getText().toString();
                            String giabia = tvupGiabia.getText().toString();
                            sachclass.setMasach(masach);
                            sachclass.setTensach(tensach);
                            sachclass.setMatheloai(matheloai);
                            sachclass.setNxb(nxb);
                            sachclass.setSoluong(soluong);
                            sachclass.setGiabia(giabia);



                            long result = sachsql.updatesach(sachclass);
                            if (result > 0) {
                                Toast.makeText(Update_SachActivity.this, "sửa thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Update_SachActivity.this, "sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                            btnhuy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        }


                    }
                });

            }
        });

    }

    public void AnhXa(){
        spinner = findViewById(R.id.spinner);
        btnhuy = findViewById(R.id.btnhuy_update_sach);
        btnsua = findViewById(R.id.btnsua_update_sach);
        tvTitle =  findViewById(R.id.tv_title);
        tvupMasach =  findViewById(R.id.tv_add_masach);
        tvupTensach = findViewById(R.id.tv_add_tensach);
        tvupNxb =  findViewById(R.id.tv_add_nxb);
        tvupSoluong = findViewById(R.id.tv_add_soluong);
        tvupGiabia = findViewById(R.id.tv_add_giabia);

    }
    public void gettl(Spinner spinner) {
        Theloaisql theloaisql = new Theloaisql(Update_SachActivity.this);
        theloai_List = theloaisql.getalltheloai();

        ArrayAdapter<Theloaiclass> adapter = new ArrayAdapter<>(Update_SachActivity.this, android.R.layout.simple_spinner_item, theloai_List);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
    private int getIndexSpinner(Spinner spinner, String s) {
        for (int j = 0; j < spinner.getCount(); j++) {
            if (spinner.getItemAtPosition(j).toString().equalsIgnoreCase(s)) {
                return j;
            }
        }
        return 0;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
                default:
                    break;
        }

        return super.onOptionsItemSelected(item);
    }
}
