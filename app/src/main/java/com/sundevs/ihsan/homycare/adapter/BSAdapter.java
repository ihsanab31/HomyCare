package com.sundevs.ihsan.homycare.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sundevs.ihsan.homycare.HomyCare;
import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.adapter.listener.ItemClickListener;
import com.sundevs.ihsan.homycare.adapter.model.SusterModel;
import com.sundevs.ihsan.homycare.util.param.BaseUrl;
import com.sundevs.ihsan.homycare.view.activities.DetailActivity;

import java.util.List;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 087825382796
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for  adapter
 */

public class BSAdapter extends RecyclerView.Adapter<BSAdapter.ViewHolder> {
    private List<SusterModel> susterModels;
    private List<SusterModel> susterModel;
    private Context mContext;
    ImageLoader imageLoader;

    public BSAdapter(List<SusterModel> susterModels, Context mContext) {
        this.susterModels = susterModels;
        this.mContext = mContext;
    }

    @Override
    public BSAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list_baby, parent, false));
    }

    @Override
    public void onBindViewHolder(BSAdapter.ViewHolder holder, int position) {
        final SusterModel susterModel = susterModels.get(position);
        holder.bindTo(susterModel);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("id_suster", susterModel.getId_suster());
                intent.putExtra("nama", susterModel.getNama());
                intent.putExtra("alamat", susterModel.getAlamat());
                intent.putExtra("harga", susterModel.getHarga());
                intent.putExtra("pendidikan", susterModel.getPedidikan());
                intent.putExtra("umur", susterModel.getUmur());
                intent.putExtra("status", susterModel.getStatus());
                intent.putExtra("lng", susterModel.getLng() );
                intent.putExtra("lat", susterModel.getLat());
                intent.putExtra("jarak", susterModel.getJarak());
                intent.putExtra("gambar", susterModel.getGambar());
                intent.putExtra("nohp", susterModel.getNo_hp());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return susterModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemClickListener itemClickListener;
        //Member Variables for the TextViews
        private TextView nama;
        private TextView alamat;
        private TextView bidang;
        private TextView jarak;
        private NetworkImageView imageView;
        private SusterModel susterModel;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file
         */
        ViewHolder(View itemView) {
            super(itemView);
            //Initialize the views
            imageView = (NetworkImageView) itemView.findViewById(R.id.img_foto);
            nama = (TextView) itemView.findViewById(R.id.txt_nama);
            alamat = (TextView) itemView.findViewById(R.id.txt_alamat);
            bidang = (TextView) itemView.findViewById(R.id.txt_umur);
            jarak = (TextView) itemView.findViewById(R.id.txt_jarak);
            itemView.setOnClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @SuppressLint("SetTextI18n")
        void bindTo(SusterModel susterModel) {
            if (imageLoader == null)
                imageLoader = HomyCare.getInstance().getImageLoader();
            //Populate the textviews with data
            imageView.setImageUrl(BaseUrl.URL_BASE+"/" +susterModel.getGambar(), imageLoader);
            nama.setText(susterModel.getNama());
            alamat.setText(susterModel.getAlamat());
            bidang.setText(susterModel.getUmur()+" Tahun");
            jarak.setText("Jakar "+susterModel.getJarak() +" KM\n"+"Status "+susterModel.getStatus());
            //Get the current sport
            this.susterModel = susterModel;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v);
        }
    }

}
