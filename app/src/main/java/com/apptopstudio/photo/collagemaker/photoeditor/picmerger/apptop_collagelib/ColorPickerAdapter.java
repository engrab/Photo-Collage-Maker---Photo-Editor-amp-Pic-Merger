package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib.MyAdapter.CurrentCollageIndexChangedListener;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"InflateParams"})
public class ColorPickerAdapter extends MyRecylceAdapterBase<ColorPickerAdapter.ViewHolder> implements OnClickListener {
    private static final String TAG = "Adapter";
    int colorDefault;
    private List<Integer> colorList = new ArrayList();
    int colorSelected;
    String[] colors;
    CurrentCollageIndexChangedListener listener;
    RecyclerView recylceView;
    View selectedListItem;
    int selectedPosition;

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private static final String TAG = "ViewHolder";
        public View colorPickerView;
        private int item;
        CurrentCollageIndexChangedListener viewHolderListener;

        public void setCurrentCollageIndexChangedListener(CurrentCollageIndexChangedListener l) {
            this.viewHolderListener = l;
        }

        public void setItem(int items) {
            this.item = items;
            this.colorPickerView.setBackgroundColor(this.item);
        }

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            this.colorPickerView = itemLayoutView.findViewById(R.id.color_picker_view);
        }
    }

    public ColorPickerAdapter(CurrentCollageIndexChangedListener l, int cDefault, int cSelected) {
        colors = new String[122];
        colors[0] = "#FFFF99";
        colors[1] = "#C5E384";
        colors[2] = "#B2EC5D";
        colors[3] = "#87A96B";
        colors[4] = "#A8E4A0";
        colors[5] = "#1DF914";
        colors[6] = "#76FF7A";
        colors[7] = "#71BC78";
        colors[8] = "#6DAE81";
        colors[9] = "#9FE2BF";
        colors[10] = "#1CAC78";
        colors[11] = "#30BA8F";
        colors[12] = "#45CEA2";
        colors[13] = "#3BB08F";
        colors[14] = "#1CD3A2";
        colors[15] = "#17806D";
        colors[16] = "#158078";
        colors[17] = "#1FCECB";
        colors[18] = "#78DBE2";
        colors[19] = "#77DDE7";
        colors[20] = "#80DAEB";
        colors[21] = "#414A4C";
        colors[22] = "#199EBD";
        colors[23] = "#1CA9C9";
        colors[24] = "#1DACD6";
        colors[25] = "#9ACEEB";
        colors[26] = "#1A4876";
        colors[27] = "#1974D2";
        colors[28] = "#2B6CC4";
        colors[29] = "#1F75FE";
        colors[30] = "#C5D0E6";
        colors[31] = "#B0B7C6";
        colors[32] = "#5D76CB";
        colors[33] = "#A2ADD0";
        colors[34] = "#979AAA";
        colors[35] = "#ADADD6";
        colors[36] = "#7366BD";
        colors[37] = "#7442C8";
        colors[38] = "#7851A9";
        colors[39] = "#7851A9";
        colors[40] = "#7851A9";
        colors[41] = "#FDFC74";
        colors[42] = "#FDFC74";
        colors[43] = "#FFFF99";
        colors[44] = "#C5E384";
        colors[45] = "#B2EC5D";
        colors[46] = "#87A96B";
        colors[47] = "#A8E4A0";
        colors[48] = "#1DF914";
        colors[49] = "#76FF7A";
        colors[50] = "#71BC78";
        colors[51] = "#6DAE81";
        colors[52] = "#9FE2BF";
        colors[53] = "#1CAC78";
        colors[54] = "#30BA8F";
        colors[55] = "#45CEA2";
        colors[56] = "#3BB08F";
        colors[57] = "#1CD3A2";
        colors[58] = "#17806D";
        colors[59] = "#158078";
        colors[60] = "#1FCECB";
        colors[61] = "#78DBE2";
        colors[62] = "#77DDE7";
        colors[63] = "#80DAEB";
        colors[64] = "#414A4C";
        colors[65] = "#199EBD";
        colors[66] = "#1CA9C9";
        colors[67] = "#1DACD6";
        colors[68] = "#9ACEEB";
        colors[69] = "#1A4876";
        colors[70] = "#1974D2";
        colors[71] = "#2B6CC4";
        colors[72] = "#1F75FE";
        colors[73] = "#C5D0E6";
        colors[74] = "#B0B7C6";
        colors[75] = "#5D76CB";
        colors[76] = "#A2ADD0";
        colors[77] = "#979AAA";
        colors[78] = "#ADADD6";
        colors[79] = "#7366BD";
        colors[80] = "#7442C8";
        colors[81] = "#7851A9";
        colors[82] = "#9D81BA";
        colors[83] = "#926EAE";
        colors[84] = "#CDA4DE";
        colors[85] = "#8F509D";
        colors[86] = "#C364C5";
        colors[87] = "#FB7EFD";
        colors[88] = "#FC74FD";
        colors[89] = "#8E4585";
        colors[90] = "#FF1DCE";
        colors[91] = "#FF1DCE";
        colors[92] = "#FF48D0";
        colors[93] = "#E6A8D7";
        colors[94] = "#C0448F";
        colors[95] = "#6E5160";
        colors[96] = "#DD4492";
        colors[97] = "#FF43A4";
        colors[98] = "#F664AF";
        colors[99] = "#FCB4D5";
        colors[100] = "#FFBCD9";
        colors[101] = "#F75394";
        colors[102] = "#FFAACC";
        colors[103] = "#E3256B";
        colors[104] = "#FDD7E4";
        colors[105] = "#CA3767";
        colors[106] = "#DE5D83";
        colors[107] = "#FC89AC";
        colors[108] = "#F780A1";
        colors[109] = "#C8385A";
        colors[110] = "#EE204D";
        colors[111] = "#FF496C";
        colors[112] = "#EF98AA";
        colors[113] = "#FC6C85";
        colors[114] = "#FC2847";
        colors[115] = "#FF9BAA";
        colors[116] = "#CB4154";
        colors[117] = "#EDEDED";
        colors[118] = "#DBD7D2";
        colors[119] = "#CDC5C2";
        colors[120] = "#95918C";
        colors[121] = "#232323";
//        colors = r0;
        listener = l;
        colorDefault = cDefault;
        colorSelected = cSelected;
        createColorList();
    }

    private void createColorList() {
        for (String parseColor : this.colors) {
            this.colorList.add(Integer.valueOf(Color.parseColor(parseColor)));
        }
    }

    public void setSelectedPositinVoid() {
        this.selectedListItem = null;
        this.selectedPosition = -1;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_recycler_view_item, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        viewHolder.setCurrentCollageIndexChangedListener(this.listener);
        itemLayoutView.setOnClickListener(this);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setItem(((Integer) this.colorList.get(position)).intValue());
        if (this.selectedPosition == position) {
            viewHolder.itemView.setBackgroundColor(this.colorSelected);
        } else {
            viewHolder.itemView.setBackgroundColor(this.colorDefault);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recylceView) {
        this.recylceView = recylceView;
    }

    public void onClick(View view) {
        View oldView;
        StringBuilder stringBuilder;
        int position = this.recylceView.getChildPosition(view);
        android.support.v7.widget.RecyclerView.ViewHolder oldViewHolder = this.recylceView.findViewHolderForPosition(this.selectedPosition);
        if (oldViewHolder != null) {
            oldView = oldViewHolder.itemView;
            if (oldView != null) {
                oldView.setBackgroundColor(this.colorDefault);
            }
        }
        oldView = this.selectedListItem;
        String str = TAG;
        if (oldView != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("selectedListItem ");
            stringBuilder.append(position);
            Log.d(str, stringBuilder.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("onClick ");
        stringBuilder.append(position);
        Log.d(str, stringBuilder.toString());
        this.listener.onIndexChanged(((Integer) this.colorList.get(position)).intValue());
        this.selectedPosition = position;
        view.setBackgroundColor(this.colorSelected);
        this.selectedListItem = view;
    }

    public int getItemCount() {
        return this.colorList.size();
    }
}
