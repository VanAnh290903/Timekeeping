package com.vanh.timekeeping.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanh.timekeeping.databinding.ItemTimekeepingBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.listeners.TimekeepingListener;

import java.util.List;

public class TimekeepingAdapter extends RecyclerView.Adapter<TimekeepingAdapter.TimekeepingViewHolder> {

    public List<Staff> staffList;
    public TimekeepingListener timekeepingListener;

    public TimekeepingAdapter(List<Staff> staffList, TimekeepingListener timekeepingListener) {
        this.staffList = staffList;
        this.timekeepingListener = timekeepingListener;
        notifyDataSetChanged();
    }


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
        void setTimekeepingData(Staff timekeeping)
        {
            binding.staffName.setText(timekeeping.getNameStaff());
            binding.staffId.setText(timekeeping.getIdStaff());
            binding.btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timekeepingListener.onAcceptClick();
                }
            });
        }
    }
}