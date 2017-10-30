package sky.customtabview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * description:
 * created by sky
 * 17/10/30 上午10:43
 */
public abstract class TabBaseItem extends FrameLayout {

    public TabBaseItem(Context context) {
        super(context);
    }

    public TabBaseItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabBaseItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置当前Item被选中
     */
    public abstract void setChecked(boolean checked);

    /**
     * 设置是否显示新消息小红点
     * @param hasMessage 是否显示消息，true显示，false不显示
     */
    public abstract void setHasMessage(boolean hasMessage);

}
