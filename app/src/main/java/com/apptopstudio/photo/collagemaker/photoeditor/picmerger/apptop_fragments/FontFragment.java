package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.FontCache;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.GridViewAdapter;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.TextData;
import com.flask.colorpicker.ColorPickerView.WHEEL_TYPE;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import java.io.IOException;
import java.util.ArrayList;

public class FontFragment extends Fragment implements OnClickListener {
    private static final String TAG = "FontFragment";
    Activity activity;
    GridViewAdapter customGridAdapter;
    EditText editText;
    FontChoosedListener fontChoosedListener;
    private ArrayList fontList;
    private String[] fontPathList;
    TextData textData;
    TextView textView;

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.fragments.FontFragment$1 */
    class C03361 implements TextWatcher {
        C03361() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence message, int start, int before, int count) {
            if (message.toString().compareToIgnoreCase("") != 0) {
                FontFragment.this.textView.setText(message.toString());
            } else {
                FontFragment.this.textView.setText(TextData.defaultMessage);
            }
            FontFragment.this.editText.setSelection(FontFragment.this.editText.getText().length());
        }

        public void afterTextChanged(Editable s) {
            FontFragment.this.editText.setSelection(FontFragment.this.editText.getText().length());
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.fragments.FontFragment$2 */
    class C03372 implements OnItemClickListener {
        C03372() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Typeface typeFace = FontCache.get(FontFragment.this.activity, FontFragment.this.fontPathList[position]);
            if (typeFace != null) {
                FontFragment.this.textView.setTypeface(typeFace);
            }
            FontFragment.this.textData.setTextFont(FontFragment.this.fontPathList[position], FontFragment.this.activity);
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.fragments.FontFragment$3 */
    class C03383 implements Runnable {
        C03383() {
        }

        public void run() {
            FontFragment.this.editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0f, 0.0f, 0));
            FontFragment.this.editText.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0f, 0.0f, 0));
            FontFragment.this.editText.setSelection(FontFragment.this.editText.getText().length());
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.fragments.FontFragment$4 */
    class C03394 implements DialogInterface.OnClickListener {
        C03394() {
        }

        public void onClick(DialogInterface dialog, int which) {
        }
    }

    public interface FontChoosedListener {
        void onOk(TextData textData);
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.fragments.FontFragment$5 */
    class C08895 implements ColorPickerClickListener {
        C08895() {
        }

        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
            FontFragment.this.textView.setTextColor(selectedColor);
            FontFragment.this.textData.textPaint.setColor(selectedColor);
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.fragments.FontFragment$6 */
    class C08906 implements OnColorSelectedListener {
        C08906() {
        }

        public void onColorSelected(int selectedColor) {
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_font, container, false);
        this.activity = getActivity();
        Bundle extras = getArguments();
        if (extras != null) {
            this.textData = (TextData) extras.getSerializable("text_data");
        }
        listFiles6("fonts");
        this.fontPathList = new String[this.fontList.size()];
        ArrayList arrayList = this.fontList;
        this.fontPathList = (String[]) arrayList.toArray(new String[arrayList.size()]);
        this.textView = (TextView) fragmentView.findViewById(R.id.textview_font);
        TextView textView = this.textView;
        textView.setPaintFlags(textView.getPaintFlags() | 128);
        this.textView.setOnClickListener(this);
        this.editText = (EditText) fragmentView.findViewById(R.id.edittext_font);
        EditText editText = this.editText;
        editText.setInputType((editText.getInputType() | 524288) | 176);
        this.editText.addTextChangedListener(new C03361());
        this.editText.setFocusableInTouchMode(true);
        TextData textData = this.textData;
        String str = TextData.defaultMessage;
        String str2 = TAG;
        if (textData == null) {
            this.textData = new TextData(this.activity.getResources().getDimension(R.dimen.myFontSize));
            float screenWidth = (float) getResources().getDisplayMetrics().widthPixels;
            float screenHeight = (float) getResources().getDisplayMetrics().heightPixels;
            Rect r = new Rect();
            this.textData.textPaint.getTextBounds(str, 0, str.length(), r);
            this.textData.xPos = (screenWidth / 2.0f) - ((float) (r.width() / 2));
            this.textData.yPos = (screenHeight / 2.0f) - ((float) (r.height() / 2));
            Log.e(str2, "textData==null");
            this.editText.setText("");
            this.textView.setText(getString(R.string.preview_text));
        } else {
            if (!textData.message.equals(str)) {
                this.editText.setText(this.textData.message, BufferType.EDITABLE);
            }
            Log.e(str2, this.textData.message);
            this.textView.setTextColor(this.textData.textPaint.getColor());
            this.textView.setText(this.textData.message);
            if (this.textData.getFontPath() != null) {
                Typeface typeFace = FontCache.get(this.activity, this.textData.getFontPath());
                if (typeFace != null) {
                    this.textView.setTypeface(typeFace);
                }
            }
        }
        Log.e(str2, this.textView.getText().toString());
        Log.e(str2, this.textData.message);
        Log.e(str2, this.editText.getText().toString());
        GridView gridView = (GridView) fragmentView.findViewById(R.id.gridview_font);
        this.customGridAdapter = new GridViewAdapter(this.activity, R.layout.row_grid, this.fontPathList);
        gridView.setAdapter(this.customGridAdapter);
        gridView.setOnItemClickListener(new C03372());
        fragmentView.findViewById(R.id.button_text_color).setOnClickListener(this);
        fragmentView.findViewById(R.id.button_font_ok).setOnClickListener(this);
        return fragmentView;
    }

    private void listFiles6(String assestsFolderName) {
        this.fontList = new ArrayList();
        this.fontList.clear();
        int i = 0;
        String[] strArr = new String[0];
        try {
            strArr = getResources().getAssets().list(assestsFolderName);
            if (strArr != null) {
                int length = strArr.length;
                while (i < length) {
                    String str = strArr[i];
                    ArrayList arrayList = this.fontList;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(assestsFolderName);
                    stringBuilder.append("/");
                    stringBuilder.append(str);
                    arrayList.add(stringBuilder.toString());
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFontChoosedListener(FontChoosedListener l) {
        this.fontChoosedListener = l;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activity = getActivity();
    }

    public void onClick(View v) {
        int id = v.getId();
        String str = "input_method";
        CharSequence charSequence = "";
        String str2 = TextData.defaultMessage;
        if (id == R.id.textview_font) {
            this.editText.requestFocusFromTouch();
            ((InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(this.editText, 0);
            str = this.textView.getText().toString();
            if (str.compareToIgnoreCase(str2) != 0) {
                this.editText.setText(str);
                EditText editText = this.editText;
                editText.setSelection(editText.getText().length());
            } else {
                this.editText.setText(charSequence);
            }
            new Handler().postDelayed(new C03383(), 200);
        } else if (id == R.id.button_font_ok) {
            String newMessage = textView.getText().toString();
            if (newMessage.compareToIgnoreCase(str2) != 0) {
                if (newMessage.length() != 0) {
                    if (newMessage.length() == 0) {
                        this.textData.message = str2;
                    } else {
                        this.textData.message = newMessage;
                    }
                    this.editText.setText(charSequence);
                    this.textView.setText(charSequence);
                    ((InputMethodManager) this.activity.getSystemService(str)).hideSoftInputFromWindow(this.editText.getWindowToken(), 0);
                    FontChoosedListener fontChoosedListener = this.fontChoosedListener;
                    if (fontChoosedListener != null) {
                        try {
                            fontChoosedListener.onOk(this.textData);
                        } catch (Exception e) {
                            Toast.makeText(this.activity, "Somthing Went Wrong.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Null", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            if (this.activity == null) {
                this.activity = getActivity();
            }
            Toast msg = Toast.makeText(this.activity, getString(R.string.canvas_text_enter_text), Toast.LENGTH_SHORT);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();

        } else if (id == R.id.button_text_color) {
            try {
                ColorPickerDialogBuilder.with(getActivity()).setTitle("Choose color").initialColor(this.textView.getCurrentTextColor()).wheelType(WHEEL_TYPE.FLOWER).density(12).setOnColorSelectedListener(new C08906()).setPositiveButton((CharSequence) "ok", new C08895()).setNegativeButton((CharSequence) "cancel", new C03394()).build().show();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onDestroy() {
        GridViewAdapter gridViewAdapter = this.customGridAdapter;
        if (gridViewAdapter != null) {
            if (gridViewAdapter.typeFaceArray != null) {
                int length = this.customGridAdapter.typeFaceArray.length;
                for (int i = 0; i < length; i++) {
                    this.customGridAdapter.typeFaceArray[i] = null;
                }
            }
            this.customGridAdapter.typeFaceArray = null;
        }
        super.onDestroy();
    }
}
