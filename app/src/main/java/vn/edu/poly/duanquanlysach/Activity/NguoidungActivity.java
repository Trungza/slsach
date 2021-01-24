package vn.edu.poly.duanquanlysach.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duanquanlysach.R;
import vn.edu.poly.duanquanlysach.adapter.NguoiDungAdapter;
import vn.edu.poly.duanquanlysach.Model.ItemClickSupport;
import vn.edu.poly.duanquanlysach.Model.Nguoidungclass;
import vn.edu.poly.duanquanlysach.Sqlite.Nguoidungsql;

public class NguoidungActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public List<Nguoidungclass> list = new ArrayList<>();
    public NguoiDungAdapter adapter;
    public AlertDialog alertDialog;
    public MenuItem item;
    public Nguoidungsql nguoidungsql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoidung);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quản lý người dùng");

        recyclerView = findViewById(R.id.rcv_nguoidung);

        nguoidungsql = new Nguoidungsql(NguoidungActivity.this);
        list = nguoidungsql.getallnguoidung();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NguoiDungAdapter(list, NguoidungActivity.this);
        recyclerView.setAdapter(adapter);


        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(final RecyclerView recyclerView, final int position, final View v) {

                final Nguoidungclass nguoidungclass = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(recyclerView.getContext());
                final View alert = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.dialog_update_user, null);
                final TextInputEditText edttenUpdate = alert.findViewById(R.id.edtten_update);
                final TextInputEditText edtmkUpdate = alert.findViewById(R.id.edtmk_update);
                final TextInputEditText edtsdtUpdate = alert.findViewById(R.id.edtsdt_update);

                final TextInputEditText edtmk2update = alert.findViewById(R.id.edtmk2_update);
                final TextInputLayout tip1 = alert.findViewById(R.id.tip_1);
                final Button btnsua = alert.findViewById(R.id.btnsua_update);
                final Button btnhuy = alert.findViewById(R.id.btnhuy_update);
                final TextInputEditText edttentk = alert.findViewById(R.id.edttentk_update);
                tip1.setEnabled(false);
                edttenUpdate.setEnabled(false);
                edtmkUpdate.setEnabled(false);
                edtmk2update.setEnabled(false);
                edtsdtUpdate.setEnabled(false);
                edttentk.setEnabled(false);

                btnsua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnsua.setText("Sửa hoàn tất");
                        tip1.setEnabled(true);
                        edtmkUpdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                        edtmk2update.setEnabled(true);
                        edttenUpdate.setEnabled(true);
                        edtmkUpdate.setEnabled(true);
                        edtsdtUpdate.setEnabled(true);


                        btnsua.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (edttenUpdate.getText().length() == 0 || edtmkUpdate.getText().length() == 0 || edtmk2update.getText().length() == 0 || edtsdtUpdate.getText().length() == 0) {
                                    Toast.makeText(NguoidungActivity.this, "không để rỗng", Toast.LENGTH_SHORT).show();
                                } else if (edtmkUpdate.getText().length() < 6) {
                                    Toast.makeText(NguoidungActivity.this, "yêu cầu mật khẩu lớn hơn 6 kí tự", Toast.LENGTH_SHORT).show();
                                } else if (edtsdtUpdate.getText().toString().length() < 10) {
                                    Toast.makeText(NguoidungActivity.this, "yêu cầu nhập sdt đủ 10 số", Toast.LENGTH_SHORT).show();
                                } else if (!edtmkUpdate.getText().toString().equals(edtmk2update.getText().toString())) {
                                    Toast.makeText(NguoidungActivity.this, "yêu cầu nhập mật khẩu khớp nhau", Toast.LENGTH_SHORT).show();
                                } else {
                                    String ten = edttenUpdate.getText().toString().trim();
                                    String tentk = edttentk.getText().toString().trim();
                                    String mk = edtmkUpdate.getText().toString().trim();
                                    String sdt = edtsdtUpdate.getText().toString().trim();
                                    nguoidungclass.name = ten;
                                    nguoidungclass.tentk = tentk;
                                    nguoidungclass.matkhau = mk;
                                    nguoidungclass.sdt = sdt;

                                    long result = nguoidungsql.updatenguoidung(nguoidungclass);
                                    if (result > 0) {
                                        Toast.makeText(NguoidungActivity.this, "sửa thanh cong", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(NguoidungActivity.this, "Không thanh cong", Toast.LENGTH_SHORT).show();
                                    }

                                    LinearLayoutManager layoutManager = new LinearLayoutManager(NguoidungActivity.this);
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setAdapter(adapter);

                                    adapter = new NguoiDungAdapter(list, NguoidungActivity.this);
                                    recyclerView.setAdapter(adapter);
                                    alertDialog.dismiss();

                                }
                                //

                            }
                        });


                    }
                });


                //lấy lại dữ liệu tại vị trí index để sửa
                edttentk.setText(nguoidungclass.getTentk());
                edttenUpdate.setText(nguoidungclass.getName());
                edtsdtUpdate.setText(nguoidungclass.getSdt());
                edtmkUpdate.setText(nguoidungclass.getMatkhau());
                edtmk2update.setText(nguoidungclass.getMatkhau());

                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                builder.setView(alert);
                builder.create();
                alertDialog = builder.show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuuse, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void open_add_user(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View alert = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null);

        //tham chiếu
        Button btnthem;
        Button btnhuy;
        final TextInputEditText edtten;
        final TextInputEditText edttentkThem;
        final TextInputEditText edtmk1;
        final TextInputEditText edtmk2;
        final TextInputEditText edtsdt;


        edtten = (TextInputEditText) alert.findViewById(R.id.edtten);
        edttentkThem = (TextInputEditText) alert.findViewById(R.id.edttentk_them);
        edtmk1 = (TextInputEditText) alert.findViewById(R.id.edtmk1);
        edtmk2 = (TextInputEditText) alert.findViewById(R.id.edtmk2);
        edtsdt = (TextInputEditText) alert.findViewById(R.id.edtsdt);

        btnthem = (Button) alert.findViewById(R.id.btnthem);
        btnhuy = (Button) alert.findViewById(R.id.btnhuy);

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( edtten.getText().length() == 0 || edttentkThem.getText().length() == 0 || edtmk1.getText().length() == 0 || edtmk2.getText().length() == 0 || edtsdt.getText().length() == 0) {
                    Toast.makeText(NguoidungActivity.this, "không để rỗng", Toast.LENGTH_SHORT).show();
                } else if (edtmk1.getText().length() < 6) {
                    Toast.makeText(NguoidungActivity.this, "yêu cầu mật khẩu lớn hơn 6 kí tự", Toast.LENGTH_SHORT).show();
                } else if (edtsdt.getText().toString().length() < 10) {
                    Toast.makeText(NguoidungActivity.this, "yêu cầu nhập sdt đủ 10 số", Toast.LENGTH_SHORT).show();
                } else if (!edtmk1.getText().toString().equals(edtmk2.getText().toString())) {
                    Toast.makeText(NguoidungActivity.this, "yêu cầu nhập mật khẩu khớp nhau", Toast.LENGTH_SHORT).show();
                } else{
                    Nguoidungclass nguoidungclass = new Nguoidungclass();
                    nguoidungclass.name = edtten.getText().toString();
                    nguoidungclass.tentk = edttentkThem.getText().toString();
                    nguoidungclass.matkhau = edtmk1.getText().toString();
                    nguoidungclass.sdt = edtsdt.getText().toString();

                    long result = nguoidungsql.insertnguoidung(nguoidungclass);

                    list = nguoidungsql.getallnguoidung();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(NguoidungActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter = new NguoiDungAdapter(list, NguoidungActivity.this);
                    recyclerView.setAdapter(adapter);

                    if (result > 0) {
                        Toast.makeText(NguoidungActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();

                    } else {
                        Toast.makeText(NguoidungActivity.this, "Them khong thanh cong", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                }

            }
        });


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        builder.setView(alert);
        builder.create();
        alertDialog = builder.show();
    }



}
