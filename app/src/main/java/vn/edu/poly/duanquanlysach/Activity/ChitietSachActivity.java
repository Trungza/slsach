package vn.edu.poly.duanquanlysach.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import vn.edu.poly.duanquanlysach.R;
import vn.edu.poly.duanquanlysach.Model.Sachclass;
import vn.edu.poly.duanquanlysach.Sqlite.Sachsql;
import vn.edu.poly.duanquanlysach.Sqlite.Theloaisql;

public class ChitietSachActivity extends AppCompatActivity {
TextView tvName,tvMa,tvGia,tvTheloai,tvNXB,tvSoluong;
Sachsql sachsql;
Theloaisql theloaisql;
List<Sachclass> sachList;
List<Theloaisql> theloaiList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sach);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    Anhxa();

   Bundle bundle= getIntent().getExtras();// lấy giá trị bundle

        if(bundle!=null){
            tvName.setText(bundle.getString("name"));
            tvMa.setText(bundle.getString("ma"));
            tvGia.setText(bundle.getString("gia"));
            tvTheloai.setText(bundle.getString("matheloai"));
            tvNXB.setText(bundle.getString("nxb"));
            tvSoluong.setText(bundle.getString("soluong"));
        }




    }

    private void Anhxa(){
        tvName=findViewById(R.id.chitiet_name);
        tvMa=findViewById(R.id.chitiet_ma);
        tvGia=findViewById(R.id.chitet_gia);
        tvTheloai=findViewById(R.id.chitiet_theloai);
        tvNXB=findViewById(R.id.chitiet_nxb);
        tvSoluong=findViewById(R.id.chitiet_soluong);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    }

