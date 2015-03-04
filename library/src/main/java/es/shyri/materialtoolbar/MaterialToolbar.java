package es.shyri.materialtoolbar;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import es.shyri.materialtoolbar.interfaces.MaterialToolbarMessageReceiver;


/**
 * Created by Shyri on 27/02/2015.
 */
public class MaterialToolbar extends Toolbar implements MaterialToolbarContent.UIToolbarContentListener {
    MaterialToolbarContent mSmartToolbarComponent;
    MaterialToolbarMessageReceiver mMessageReceiver;

    public MaterialToolbar(Context context)
    {
        super(context);
    }

    public MaterialToolbar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MaterialToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setContent (MaterialToolbarContent smartToolbarComponent) {
        if (this.mSmartToolbarComponent != null) {
            removeView(this.mSmartToolbarComponent.getView());
        }
        this.mSmartToolbarComponent = smartToolbarComponent;
        this.mSmartToolbarComponent.setSmartToolbarComponentListener(this);

        addView(this.mSmartToolbarComponent.getView());

        this.mSmartToolbarComponent.configureActionBar((ActionBarActivity) getContext());

        getMenu().clear();
        if (this.mSmartToolbarComponent.getMenuId() != 0) {
            inflateMenu(this.mSmartToolbarComponent.getMenuId());
        } else {
            getMenu().clear();
        }
    }

    protected void sendMessage(Object message){
        this.mMessageReceiver.onReceiveToolbarMessage(message);
    }

    public void setmMessageReceiver(MaterialToolbarMessageReceiver messageReceiver){
        this.mMessageReceiver = messageReceiver;
    }

    public void onToolbarComponentMessage(Object message)
    {
        sendMessage(message);
    }
}
