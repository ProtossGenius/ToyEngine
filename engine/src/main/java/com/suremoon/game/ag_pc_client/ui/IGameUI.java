package com.suremoon.game.ag_pc_client.ui;


import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class IGameUI {
    // Location base parent UI.
    private final Rectangle _bundle;
    protected Rectangle _baseBundle;
    protected final List<IGameUI> children;
    public int _zIndex = 0;
    protected AGForm agForm;
    private boolean _visible = true;
    private BufferedImage cache;
    private boolean _need_redraw = true;
    private boolean _always_redraw = false;
    private boolean _inDrag = false;
    private IGameUI parent;
    private IGameUI focusUi;

    public IGameUI(AGForm agForm, Rectangle _bundle) {
        this.agForm = agForm;
        this._bundle = _bundle;
        this._baseBundle = new Rectangle();
        cache = new BufferedImage(_bundle.width, _bundle.height, BufferedImage.TYPE_INT_ARGB);
        children = new ArrayList<>();
    }

    public IGameUI(AGForm agForm, Rectangle _bundle, boolean _need_draw_self) {
        this.agForm = agForm;
        this._bundle = _bundle;
        this._baseBundle = new Rectangle();
        if (_need_draw_self) {
            cache = new BufferedImage(_bundle.width, _bundle.height, BufferedImage.TYPE_INT_ARGB);
        }
        children = new ArrayList<>();
    }

    protected void onMove(int xOffset, int yOffset) {
        _bundle.x += xOffset;
        _bundle.y += yOffset;
    }

    public void setPosition(int x, int y) {
        _bundle.x = x;
        _bundle.y = y;
    }

    protected int getWidth() {
        return _bundle.width;
    }

    protected int getHeight() {
        return _bundle.height;
    }

    private void setBasePosition(Rectangle p) {
        this._baseBundle = p;
    }

    public int getZIndex() {
        return _zIndex;
    }

    public void addChildren(IGameUI ui) {
        children.add(ui);
        ui.parent = this;
        ui.setBasePosition(this._bundle);
        for (int i = children.size() - 2; i >= 0; --i) {
            var son = children.get(i);
            if (ui.getZIndex() <= son.getZIndex()) {
                break;
            }
            children.set(i + 1, son);
            children.set(i, ui);
        }
    }

    public void clearCacheToZero() {
        for (int i = 0; i < cache.getWidth(); ++i) {
            for (int j = 0; j < cache.getHeight(); ++j) {
                int rgb = cache.getRGB(i, j);
                if ((rgb | 0x00ffffff) == 0x00ffffff) continue;
                cache.setRGB(i, j, 0);
                // Mark the alpha bits as zero - transparent
            }
        }
    }

    protected abstract void _draw(Graphics cache);

    public void setAlwaysRedraw(boolean al) {
        _always_redraw = al;
    }

    public void setNeedRedraw() {
        this._need_redraw = true;
        if (parent != null) {
            parent.setNeedRedraw();
        }
    }

    public final void draw(Graphics graphics) {
        if (!_visible) return;
        var g = cache == null ? graphics : cache.getGraphics();
        if (_always_redraw || _need_redraw) {
            if (this.cache != null) {
                g.clearRect(0, 0, _bundle.width, _bundle.height);
            }
            _draw(g);
            _need_redraw = false;
        }
        for (int i = children.size() - 1; i >= 0; --i) {
            var son = children.get(i);
            son.draw(g);
        }
        if (this.cache != null) {
            graphics.drawImage(cache, _bundle.x, _bundle.y, null);
        }
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

    protected Rectangle getCalcBundle() {
        var bundle = new Rectangle(this._bundle);
        var baseLocation = this._baseBundle.getLocation();
        bundle.x = bundle.x + baseLocation.x;
        bundle.y = bundle.y + baseLocation.y;
        return bundle;
    }

    public final boolean mousePressed(MouseEvent e) {
        if (this.focusUi != null) {
            if (this.focusUi._mousePressed(e)) {
                return true;
            }
        }
        if (!getCalcBundle().contains(e.getPoint())) return false;
        for (var son : children) {
            if (son.getVisible() && son.mousePressed(e)) {
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
        if (this.focusUi != null) {
            if (this.focusUi._mouseMoved(e)) {
                return true;
            }
        }
        if (!getCalcBundle().contains(e.getPoint())) return false;
        for (var son : children) {
            if (son.getVisible() && son.mouseMoved(e)) {
                return true;
            }
        }
        return _mouseMoved(e);
    }

    protected boolean _mouseDragged(MouseEvent e) {
        return false;
    }

    public final boolean mouseDragged(MouseEvent e) {
        if (this.focusUi != null) {
            if (this.focusUi._mouseDragged(e)) {
                return true;
            }
        }
        this.mouseMoved(e);
        if (!_inDrag) return false;
        for (var son : children) {
            if (son.getVisible() && son.mouseDragged(e)) {
                return true;
            }
        }
        return _mouseDragged(e);
    }

    protected boolean _mouseReleased(MouseEvent e) {
        return false;
    }

    public final boolean mouseReleased(MouseEvent e) {
        if (this.focusUi != null) {
            if (this.focusUi._mouseReleased(e)) {
                return true;
            }
        }
        _inDrag = false;
        if (!getCalcBundle().contains(e.getPoint())) return false;
        for (var son : children) {
            if (son.getVisible() && son.mouseReleased(e)) {
                return true;
            }
        }
        return _mouseReleased(e);
    }

    protected boolean _keyPressed(KeyEvent e) {
        return false;
    }

    public final boolean keyPressed(KeyEvent e) {
        if (this.focusUi != null) {
            if (this.focusUi._keyPressed(e)) {
                return true;
            }
        }
        for (var son : children) {
            if (son.getVisible() && son.keyPressed(e)) {
                return true;
            }
        }

        return _keyPressed(e);
    }

    protected boolean _keyReleased(KeyEvent e) {
        return false;
    }

    public final boolean keyReleased(KeyEvent e) {
        if (this.focusUi != null) {
            if (this.focusUi._keyReleased(e)) {
                return true;
            }
        }
        for (var son : children) {
            if (son.getVisible() && son.keyReleased(e)) {
                return true;
            }
        }

        return _keyReleased(e);
    }

    protected boolean _keyTyped(KeyEvent e) {
        return false;
    }

    public final boolean keyTyped(KeyEvent e) {
        if (this.focusUi != null) {
            if (this.focusUi._keyTyped(e)) {
                return true;
            }
        }
        for (var son : children) {
            if (son.getVisible() && son.keyTyped(e)) {
                return true;
            }
        }

        return _keyTyped(e);
    }

    protected void setFocus(IGameUI ui) {
        this.focusUi = ui;
        if (parent != null) {
            parent.setFocus(ui);
        }
    }
}
