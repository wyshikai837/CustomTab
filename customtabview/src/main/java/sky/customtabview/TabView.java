package sky.customtabview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * created by sky
 * 17/10/30 上午10:40
 */
public class TabView extends FrameLayout {

    private TabLayout mTabLayout;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TabBuilder init() {
        return new TabBuilder();
    }

    public class TabBuilder {
        private List<TabBaseItem> mTabItems;
        private int mDefaultIndex;
        private OnTabItemSelectedListener mOnTabItemSelectedListener;

        public TabBuilder() {
            mTabItems = new ArrayList<>();
        }

        public TabBuilder addTabItem(TabBaseItem tabItem) {
            mTabItems.add(tabItem);
            return TabBuilder.this;
        }

        public TabBuilder setOnTabItemSelectedListener(OnTabItemSelectedListener onTabItemSelectedListener) {
            mOnTabItemSelectedListener = onTabItemSelectedListener;
            return TabBuilder.this;
        }

        public TabBuilder setDefaultIndex(int defaultIndex) {
            mDefaultIndex = defaultIndex;
            return TabBuilder.this;
        }

        public void build() {
            removeAllViews();

            mTabLayout = new TabLayout(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mTabLayout.setLayoutParams(layoutParams);
            addView(mTabLayout);
            mTabLayout.initTabItems(mTabItems, mDefaultIndex);

            if (null != mOnTabItemSelectedListener) {
                mTabLayout.setOnTabItemSelectedListener(mOnTabItemSelectedListener);
            }
        }
    }

    /**
     * 设置选中项
     * @param index 顺序索引
     */
    public void setSelectedItem(int index) {
        if (null != mTabLayout) {
            mTabLayout.setSelectedItem(index);
        }
    }

    /**
     * 获取选中项顺序索引
     * @return 选中项顺序索引
     */
    public int getSelected() {
        if (null != mTabLayout) {
            return mTabLayout.getSelected();
        }
        return 0;
    }

    /**
     * 设置小红点
     * @param index 顺序索引
     * @param hasMessage 是否显示消息，true显示，false不显示
     */
    public void setHasMessage(int index, boolean hasMessage) {
        if (null != mTabLayout) {
            mTabLayout.setHasMessage(index, hasMessage);
        }
    }
}
