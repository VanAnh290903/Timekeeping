package com.vanh.timekeeping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vanh.timekeeping.R;
import com.vanh.timekeeping.database.StaffDatabase;
import com.vanh.timekeeping.databinding.ItemifRvTimeskeepingBinding;
import com.vanh.timekeeping.entity.Staff;
import com.vanh.timekeeping.entity.Timekeeping;
import com.vanh.timekeeping.listeners.StaffListener;
import com.vanh.timekeeping.listeners.TimekeepingListener;
import com.vanh.timekeeping.ulitilies.Constants;

import java.io.File;
import java.util.List;

public class ReviewTimekeepingAdapter extends RecyclerView.Adapter<ReviewTimekeepingAdapter.ReviewTimekeepingAdapterholder>{
    public List<Timekeeping> timekeepingList;
    public StaffListener staffListener;


    public ReviewTimekeepingAdapter(List<Timekeeping> timekeepingList, StaffListener staffListener) {
        this.timekeepingList = timekeepingList;
        this.staffListener = staffListener;

        notifyDataSetChanged();

    }

    // hiển thị ra rcv
    public ReviewTimekeepingAdapterholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType )
    {
        ItemifRvTimeskeepingBinding itemifRvTimeskeepingBinding= ItemifRvTimeskeepingBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ReviewTimekeepingAdapterholder(itemifRvTimeskeepingBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewTimekeepingAdapterholder holder, int position) {
        holder.setRVTimekeepingData(timekeepingList.get(position));
    }

    @Override
    public int getItemCount() {
        return timekeepingList.size();
    }


    class ReviewTimekeepingAdapterholder extends RecyclerView.ViewHolder{
        ItemifRvTimeskeepingBinding binding;

        ReviewTimekeepingAdapterholder(ItemifRvTimeskeepingBinding itemifRvTimeskeepingBinding)
        {
            super(itemifRvTimeskeepingBinding.getRoot());
            binding= itemifRvTimeskeepingBinding;
        }

        void setRVTimekeepingData(Timekeeping timekeeping){
            Staff staff = StaffDatabase.getInstance(itemView.getContext()).staffDAO().getStaffById(timekeeping.getIdStaff());
            binding.staffId.setText(timekeeping.getIdStaff());
            binding.staffName.setText(staff.getNameStaff());
            if (timekeeping.getIdStatus() == Constants.STATUS_ACCEPT) {
                binding.resultBtn.setImageResource(R.drawable.ic_accept);
            } else if (timekeeping.getIdStatus() == Constants.STATUS_LATE) {
                binding.resultBtn.setImageResource(R.drawable.ic_alarmtime);
            } else if (timekeeping.getIdStatus() == Constants.STATUS_OFF) {
                binding.resultBtn.setImageResource(R.drawable.ic_stop);
            } else

            if (!staff.getAvatar().isEmpty()) {
                Picasso.get()
                        .load(new File(staff.getAvatar()))
                        .into(binding.imgAvtStaff);  // imageView là ImageView để hiển thị ảnh
            }

            binding.getRoot().setOnClickListener(v -> {
                staffListener.onStaffClicked(staff);
            });
        }
    }
}
