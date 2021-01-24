package vn.edu.poly.duanquanlysach.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.poly.duanquanlysach.R;
import vn.edu.poly.duanquanlysach.Activity.Update_SachActivity;
import vn.edu.poly.duanquanlysach.Model.Sachclass;
import vn.edu.poly.duanquanlysach.Model.Theloaiclass;
import vn.edu.poly.duanquanlysach.Sqlite.Sachsql;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    List<Sachclass> list;
    List<Theloaiclass> list_theloai;
    Context context;
    Sachclass sachclass;
    Sachsql sachsql;
    Intent intent;
    int i;
    public androidx.appcompat.app.AlertDialog alertDialog;

    public SachAdapter(List<Sachclass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       sachclass= list.get(position);
        holder.tvTensach1.setText(sachclass.getTensach());
        holder.tvMasach1.setText(sachclass.getMasach());
        holder.tvSoluong1.setText(sachclass.getSoluong());
        holder.tvGiabia1.setText(sachclass.getGiabia());
        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(view.getContext(),Update_SachActivity.class);
                Bundle bundle= new Bundle();
                bundle.putString("ma",sachclass.getMasach());
                bundle.putString("name",sachclass.getTensach());
                bundle.putString("soluong",sachclass.getSoluong());
                bundle.putString("gia",sachclass.getGiabia());
                bundle.putString("nxb",sachclass.getNxb());
                bundle.putString("matheloai",sachclass.getMatheloai());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Bạn có muốn xóa?");
                final Sachclass sachclass = list.get(position);
                final Sachsql sachsql = new Sachsql(view.getContext());
                builder.setPositiveButton("xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "bạn đã xóa" + sachclass.getTensach(), Toast.LENGTH_SHORT).show();
                       sachsql.xoasach(sachclass);
                        list.remove(position);
                        notifyDataSetChanged();


                    }
                });
                builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "bạn đã hủy xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create();
                builder.show();
            }
        });


    };

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgBook1;
        public TextView tvTensach1;
        public TextView tvTitle;
        public TextView tvMasach1;
        public TextView tvTitleSl;
        public TextView tvSoluong1;
        public TextView tvGiabia1;
        public ImageView imgdelete;
        public ImageView imgedit;
        public ViewHolder(View item) {
            super(item);
            imgBook1 =  item.findViewById(R.id.img_book1);
            tvTensach1 = item.findViewById(R.id.tv_tensach1);
            tvTitle = item.findViewById(R.id.tv_title);
            tvMasach1 = (TextView) item.findViewById(R.id.tv_masach1);
            tvTitleSl = (TextView) item.findViewById(R.id.tv_title_sl);
            tvSoluong1 = (TextView) item.findViewById(R.id.tv_soluong1);
            tvGiabia1 = (TextView) item.findViewById(R.id.tv_giabiasach);
            imgdelete=item.findViewById(R.id.img_delete_book);
            imgedit=item.findViewById(R.id.img_edit_book);
        }
    }

    }
