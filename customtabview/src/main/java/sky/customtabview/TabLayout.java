package sky.customtabview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * description:
 * created by sky
 * 17/10/30 上午10:41
 */
public class TabLayout extends ViewGroup {

    private List<TabBaseItem> mTabItems;
    private int mSelectedIndex = 0;
    private OnTabItemSelectedListener mOnTabItemSelectedListener;

    public TabLayout(Context context) {
        super(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initTabItems(List<TabBaseItem> items, int defaultIndex) {
        mTabItems = items;

        removeAllViews();

        for (int i = 0; i < mTabItems.size(); i++) {
            final TabBaseItem tabItem = mTabItems.get(i);
            addView(tabItem);

            final int index = i;
            tabItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectedItem(index);
                }
            });
        }

        if (defaultIndex >= 0 && defaultIndex < mTabItems.size()) {
            mSelectedIndex = defaultIndex;
        }

        mTabItems.get(mSelectedIndex).setChecked(true);
    }

    public void setOnTabItemSelectedListener(OnTabItemSelectedListener onTabItemSelectedListener) {
        mOnTabItemSelectedListener = onTabItemSelectedListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();

        if (count == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        int childWidth = MeasureSpec.getSize(widthMeasureSpec) / count;
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY);

        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        if (count == 0) {
            return;
        }

        int height = b - t;
        int childLeft = 0;

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            childView.layout(childLeft, 0, childView.getMeasuredWidth() + childLeft, height);
            childLeft += childView.getMeasuredWidth();
        }
    }

    public int getSelected() {
        return mSelectedIndex;
    }

    public void setSelectedItem(int index) {
        if (index < 0 || index >= mTabItems.size()) {
            return;
        }

        mTabItems.get(mSelectedIndex).setChecked(false);
        mSelectedIndex = index;
        mTabItems.get(mSelectedIndex).setChecked(true);

        if (null != mOnTabItemSelectedListener) {
            mOnTabItemSelectedListener.onSelected(mSelectedIndex);
        }
    }

    public void setHasMessage(int index, boolean hasMessage) {
        if (index < 0 || index >= mTabItems.size()) {
            return;
        }

        mTabItems.get(index).setHasMessage(hasMessage);
    }
}
