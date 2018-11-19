package mobile.fpts.com.ezmibile.view.splash_screen.dialog;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import mobile.fpts.com.ezmibile.R;
import mobile.fpts.com.ezmibile.databinding.DialogWatchlistNewCategoryBinding;

public class WatchlistDialogFragment extends DialogFragment implements View.OnClickListener {
    DialogWatchlistNewCategoryBinding binding;

    public static WatchlistDialogFragment newInstance() {
        WatchlistDialogFragment fragment = new WatchlistDialogFragment();
        Log.w("WatchlistDialogFragment", "newInstance: ");
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("WatchlistDialogFragment", "onCreate: ");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_watchlist_new_category, container,
                false);
        getDialog().setTitle(Html.fromHtml(getString(R.string.menu_folows_category)));

        Log.w("WatchlistDialogFragment", "onCreateView: ");
        binding.txtDone.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);//SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.txtDone.getId()){
            dismiss();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
