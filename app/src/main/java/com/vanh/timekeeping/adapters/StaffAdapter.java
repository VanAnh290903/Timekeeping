package com.vanh.timekeeping.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanh.timekeeping.activities.DetailStaffActivity;
import com.vanh.timekeeping.databinding.ItemStaffBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.listeners.StaffListener;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewholder> {

    public List<Staff> staffs;
    public StaffListener staffListener;

    public StaffAdapter(List<Staff> staffs, StaffListener staffListener) {
        this.staffs = staffs;
        this.staffListener = staffListener;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    // dùng để hiển thị dữ liệu ra RCV
    public StaffViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStaffBinding itemStaffBinding = ItemStaffBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new StaffViewholder(itemStaffBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewholder holder, int position) {
        holder.setStaffdata(staffs.get(position));
    }

    @Override
    public int getItemCount() {
        return staffs.size();
    }

    class StaffViewholder extends RecyclerView.ViewHolder {

        ItemStaffBinding binding;

        StaffViewholder(ItemStaffBinding itemStaffBinding) {
            super(itemStaffBinding.getRoot());
            binding = itemStaffBinding;

        }

        void setStaffdata(Staff staff) {
            binding.staffName.setText(staff.getNameStaff());
            binding.staffId.setText(staff.getIdStaff());
            binding.itemStaff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    staffListener.onStaffClicked(staff);
                }
            });
        }
    }

}
