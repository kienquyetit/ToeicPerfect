package com.app.learningtoeic.ui.fragment.test;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.entity.HighScore;
import com.app.learningtoeic.utils.Config;
import com.app.learningtoeic.utils.DbHelper;

import java.io.IOException;

/**
 * Created by dell on 5/9/2017.
 */

public class SaveScoreDialogFragment extends DialogFragment {
    TextView dialogLabelTV;
    TextView dialogDescriptionTV;
    ImageView dialogImg;
    TextView closeBtn;
    EditText etUserName;
    String score;
    String time;
    int countQues = 0;
    public SaveScoreDialogFragment(String score, String time,int countQuestion) {
        this.score = score;
        this.time = time;
        this.countQues = countQuestion;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.save_score_fragment, container, false);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        BindView(v);
        return v;
    }

    public void BindView(View v) {
        dialogLabelTV = (TextView) v.findViewById(R.id.common_dialog_label);
        dialogDescriptionTV = (TextView) v.findViewById(R.id.common_description);
        dialogImg = (ImageView) v.findViewById(R.id.common_dialog_img);
        etUserName = (EditText) v.findViewById(R.id.user_name);
        dialogDescriptionTV.setText(score);
        closeBtn = (TextView) v.findViewById(R.id.common_dialog_btn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HighScore highScore = new HighScore();
                highScore.setName(etUserName.getText().toString());
                highScore.setScore(Integer.parseInt(dialogDescriptionTV.getText().toString()));
                highScore.setTime(time);
                highScore.setNumberQuestion(countQues);
                try {
                    new DbHelper(getContext()).saveScore(highScore);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getDialog().dismiss();
            }
        });
    }
}
