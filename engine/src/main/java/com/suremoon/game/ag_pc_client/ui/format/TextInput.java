package com.suremoon.game.ag_pc_client.ui.format;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.ag_pc_client.ui.IGameUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TextInput extends IGameUI {
    String content;

    public TextInput(AGForm agForm, Rectangle _bundle) {
        super(agForm, _bundle);
        this.content = "";
    }

    @Override
    protected void _draw(Graphics cache) {
        cache.setColor(Color.YELLOW);
        cache.fillRect(0, 0, getWidth(), getHeight());
        new StringShow(content, new Rectangle(0, 0, getWidth(), getHeight())).drawOn(cache, 0);
    }

    @Override
    protected boolean _mousePressed(MouseEvent e) {
        if (getCalcBundle().contains(e.getPoint())) {
            setFocus(this);
        } else {
            setFocus(null);
            return false;
        }
        return true;
    }

    public String getContent() {
        return content;
    }

    @Override
    protected boolean _keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
            this.content = this.content.substring(0, this.content.length() - 1);
        } else {
            this.content += e.getKeyChar();
        }
        this.setNeedRedraw();
        return true;
    }
}
