package sky.customtabview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * description:
 * created by sky
 * 17/10/30 上午10:44
 */
public class TabNormalItem extends TabBaseItem {

    private int mDrawableRes;
    private int mCheckedDrawableRes;
    private int mTextColor;
    private int mCheckedTextColor;

    private ImageView mIcon;
    private TextView mTitle;
    private View mRedOval;

    public TabNormalItem(Context context) {
        this(context, null);
    }

    public TabNormalItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabNormalItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.tab_normal_item, this, true);

        mIcon = (ImageView) findViewById(R.id.iv);
        mTitle = (TextView) findViewById(R.id.tv);
        mRedOval = findViewById(R.id.red_oval);
    }

    public void setDrawableRes(int drawableRes, int checkedDrawableRes) {
        mDrawableRes = drawableRes;
        mCheckedDrawableRes = checkedDrawableRes;

        mIcon.setImageResource(mDrawableRes);
    }

    public void setText(String text) {
        mTitle.setText(text);
    }

    public void setTextColor(int defaultColor, int checkedColor) {
        mTextColor = defaultColor;
        mCheckedTextColor = checkedColor;
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked) {
            mIcon.setImageResource(mCheckedDrawableRes);
            mTitle.setTextColor(mCheckedTextColor);
        } else {
            mIcon.setImageResource(mDrawableRes);
            mTitle.setTextColor(mTextColor);
        }
    }

    @Override
    public void setHasMessage(boolean hasMessage) {
        if (hasMessage) {
            mRedOval.setVisibility(View.VISIBLE);
        } else {
            mRedOval.setVisibility(View.GONE);
        }
    }
}
