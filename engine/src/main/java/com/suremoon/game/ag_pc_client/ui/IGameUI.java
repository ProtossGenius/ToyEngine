package com.suremoon.game.ag_pc_client.ui;


import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class IGameUI {
    protected final Rectangle _bundle;
    protected final List<IGameUI> children;
    public int _zIndex = 0;
    protected AGForm agForm;
    private boolean _visible = true;
    private BufferedImage cache;
    private boolean _need_redraw = true;
    private boolean _always_redraw = false;
    private boolean _inDrag = false;

    public IGameUI(AGForm agForm, Rectangle _bundle) {
        this.agForm = agForm;
        this._bundle = _bundle;
        cache = new BufferedImage(_bundle.width, _bundle.height, BufferedImage.TYPE_INT_ARGB);
        children = new ArrayList<>();
    }

    public int getZIndex() {
        return _zIndex;
    }

    public void addChildren(IGameUI ui) {
        children.add(ui);
        for (int i = children.size() - 2; i >= 0; --i) {
            var son = children.get(i);
            if (ui.getZIndex() <= son.getZIndex()) {
                break;
            }
            children.set(i + 1, son);
            children.set(i, ui);
        }
    }

    protected abstract void _draw(Graphics cache);

    public void setAlwaysRedraw(boolean al) {
        _always_redraw = al;
    }

    public void setNeedRedraw(boolean _need_redraw) {
        this._need_redraw = _need_redraw;
    }

    public void draw(Graphics graphics) {
        if (!_visible) return;
        var g = cache.getGraphics();
        if (_need_redraw) {
            g.clearRect(0, 0, _bundle.width, _bundle.height);
            _draw(g);
            if (!_always_redraw) {
                _need_redraw = false;
            }
        }
        for (var son : children) {
            son.draw(g);
        }
        graphics.drawImage(cache, _bundle.x, _bundle.y, null);
    }

    public void setVisible(boolean visible) {
        this._visible = visible;
    }

    public boolean getVisible() {
        return this._visible;
    }

    public void setLocation(Point p) {
        _bundle.setLocation(p);
    }

    public void setSize(Dimension s) {
        _bundle.setSize(s);
        cache = new BufferedImage(s.width, s.height, BufferedImage.TYPE_INT_ARGB);
        _need_redraw = true;
    }

    protected boolean _mousePressed(MouseEvent e) {
        return false;
    }

    public final boolean mousePressed(MouseEvent e) {
        if (!_bundle.contains(e.getPoint())) return false;
        for (var son : children) {
            if (son.mousePressed(e)) {
                return true;
            }
        }
        _inDrag = true;
        return _mousePressed(e);
    }

    protected boolean _mouseMoved(MouseEvent e) {
        return false;
    }

    public final boolean mouseMoved(MouseEvent e) {
        if (!_bundle.contains(e.getPoint())) return false;
        for (var son : children) {
            if (son.mouseMoved(e)) {
                return true;
            }
        }
        return _mouseMoved(e);
    }

    protected boolean _mouseDragged(MouseEvent e) {
        return false;
    }

    public final boolean mouseDragged(MouseEvent e) {
        if (!_inDrag && !_bundle.contains(e.getPoint())) return false;
        for (var son : children) {
            if (son.mouseDragged(e)) {
                return true;
            }
        }
        return _mouseDragged(e);
    }

    protected boolean _mouseReleased(MouseEvent e) {
        return false;
    }

    public final boolean mouseReleased(MouseEvent e) {
        if (!_bundle.contains(e.getPoint())) return false;
        for (var son : children) {
            if (son.mouseReleased(e)) {
                return true;
            }
        }
        _inDrag = false;
        return _mouseReleased(e);
    }

    protected boolean _keyPressed(KeyEvent e) {
        return false;
    }

    public final boolean keyPressed(KeyEvent e) {
        for (var son : children) {
            if (son.keyPressed(e)) {
                return true;
            }
        }

        return _keyPressed(e);
    }
}
