package com.vanh.timekeeping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanh.timekeeping.databinding.ItemifRvTimeskeepingBinding;
import com.vanh.timekeeping.entity.Timekeeping;
import com.vanh.timekeeping.listeners.TimekeepingListener;

import java.util.List;

public class ReviewTimekeepingAdapter extends RecyclerView.Adapter<ReviewTimekeepingAdapter.ReviewTimekeepingAdapterholder>{
    public List<Timekeeping> timekeepingList;
    public TimekeepingListener timekeepingListener;


    public ReviewTimekeepingAdapter(List<Timekeeping> timekeepingList, TimekeepingListener timekeepingListener) {
        this.timekeepingList = timekeepingList;
        this.timekeepingListener = timekeepingListener;

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class ReviewTimekeepingAdapterholder extends RecyclerView.ViewHolder{
        ItemifRvTimeskeepingBinding binding;

        ReviewTimekeepingAdapterholder(ItemifRvTimeskeepingBinding itemifRvTimeskeepingBinding)
        {
            super(itemifRvTimeskeepingBinding.getRoot());
            binding= itemifRvTimeskeepingBinding;
        }

        void setRVTimekeepingData(Timekeeping timekeeping){
                binding.staffId.setText(timekeeping.getIdStaff());

        }
    }
}
