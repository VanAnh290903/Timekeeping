package com.vanh.timekeeping.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vanh.timekeeping.databinding.ItemTimekeepingBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.listeners.TimekeepingListener;
import com.vanh.timekeeping.ulitilies.Constants;

import java.io.File;
import java.util.List;

public class TimekeepingAdapter extends RecyclerView.Adapter<TimekeepingAdapter.TimekeepingViewHolder> {

    public List<Staff> staffList;
    public TimekeepingListener timekeepingListener;

    public TimekeepingAdapter(List<Staff> staffList, TimekeepingListener timekeepingListener) {
        this.staffList = staffList;
        this.timekeepingListener = timekeepingListener;
        notifyDataSetChanged();
    }

// hiển thị dữ liệu ra rcv
    public TimekeepingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTimekeepingBinding itemTimekeepingBinding= ItemTimekeepingBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TimekeepingViewHolder(itemTimekeepingBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TimekeepingViewHolder holder, int position) {
        holder.setTimekeepingData(staffList.get(position));
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }


    class TimekeepingViewHolder extends RecyclerView.ViewHolder{
        ItemTimekeepingBinding binding;

        TimekeepingViewHolder(ItemTimekeepingBinding itemTimekeepingBinding)
        {
            super(itemTimekeepingBinding.getRoot());
            binding=itemTimekeepingBinding;
        }
        void onVis(int status) {
            switch (status) {
                case Constants.STATUS_ACCEPT:
                    binding.btnLate.setAlpha(0.5f);
                    binding.btnOff.setAlpha(0.5f);
                    binding.btnAccept.setAlpha(1f);
                    break;
                case Constants.STATUS_LATE:
                    binding.btnAccept.setAlpha(0.5f);
                    binding.btnOff.setAlpha(0.5f);
                    binding.btnLate.setAlpha(1f);
                    break;
                case Constants.STATUS_OFF:
                    binding.btnAccept.setAlpha(0.5f);
                    binding.btnLate.setAlpha(0.5f);
                    binding.btnOff.setAlpha(1f);
                    break;
            }
        }
        void setTimekeepingData(Staff staff)
        {
            binding.staffName.setText(staff.getNameStaff());
            binding.staffId.setText(staff.getIdStaff());
            if (!staff.getAvatar().isEmpty()) {
                Picasso.get()
                        .load(new File(staff.getAvatar()))
                        .into(binding.imgAvtStaff);  // imageView là ImageView để hiển thị ảnh
            }
            binding.btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onVis(Constants.STATUS_ACCEPT);
                    timekeepingListener.onTimekeeingClick(staff, Constants.STATUS_ACCEPT);
                }
            });
            binding.btnLate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onVis(Constants.STATUS_LATE);
                     timekeepingListener.onTimekeeingClick(staff, Constants.STATUS_LATE);

                }
            });
            binding.btnOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onVis(Constants.STATUS_OFF);
                    timekeepingListener.onTimekeeingClick(staff, Constants.STATUS_OFF);

                }
            });
        }

    }

}