package com.vanh.timekeeping.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.vanh.timekeeping.R;
import com.vanh.timekeeping.databinding.FragmentHomepageBinding;
import com.vanh.timekeeping.databinding.FragmentSettingsBinding;

import java.util.Locale;

public class MenuSettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);


        init();
        setListeners();
        return binding.getRoot();
    }
    private void init() {

    }
    private void setListeners() {
        binding.btnLanguage.setOnClickListener(v -> {
//            final String[] languages = {"Tiếng anh", "Tiếng việt"};
//            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//            builder.setTitle("Chọn ngôn ngữ");
//            int checkedItem = 0;
//            if(preferenceManager.getString(Constants.KEY_STATUS_LANGUAGE) == "vi")
//                checkedItem = 1;
//            builder.setSingleChoiceItems(languages, checkedItem, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    selectLanguage = i;
//                }
//            });
//            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    if(selectLanguage == 0) {
//                        language = "en";
//                    }
//                    if(selectLanguage == 1) {
//                        language = "vi";
//                    }
//                    if(language == "" || language == null) {
//                        language = "en";
//                    }
//                    // Cập nhật trạng thái ngôn ngữ
//                    preferenceManager.putString(Constants.KEY_STATUS_LANGUAGE, language);
//                    // Thay đổi ngôn ngữ
//                    setLocale(language);
//                    Toast.makeText(getContext(), "Thay đổi ngôn ngữ thành công", Toast.LENGTH_SHORT).show();
//                    dialogInterface.dismiss();
//                }
//            });
//            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            });
//            builder.show();
        });
    }
    private void setLocale(String language) {
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        resources.updateConfiguration(configuration, metrics);
        onConfigurationChanged(configuration);
    }
}
