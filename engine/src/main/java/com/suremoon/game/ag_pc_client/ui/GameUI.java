package com.suremoon.game.ag_pc_client.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GameUI {
    private final Rectangle _bundle;
    public int _zIndex = 0;
    // 是否可拖拽
    private boolean _draggable = true;

    private Point _mousePressPoint;

    private Point _currentPoint = new Point(0, 0);


    public GameUI(Rectangle _bundle) {
        this._bundle = _bundle;
    }

    public void draw(Graphics graphics) {
        var color = graphics.getColor();
        graphics.setColor(Color.RED);
        Point offset = new Point();
        if (_draggable && _mousePressPoint != null) {
            offset.x = _currentPoint.x - _mousePressPoint.x;
            offset.y = _currentPoint.y - _mousePressPoint.y;
//            System.out.println(_currentPoint);

        }
        graphics.fillRect(_bundle.x + offset.x, _bundle.y + offset.y, _bundle.width, _bundle.height);

        graphics.setColor(color);
    }

    public void setLocation(Point p) {
        _bundle.setLocation(p);
    }

    public void setSize(Dimension s) {
        _bundle.setSize(s);
    }


    public boolean mousePressed(MouseEvent e) {
        this._mousePressPoint = e.getPoint();
        return _draggable;
    }

    public boolean mouseMoved(MouseEvent e) {
        this._currentPoint = e.getPoint();
        return _draggable && _mousePressPoint != null;
    }

    public boolean mouseDragged(MouseEvent e) {
        System.out.println(e.getPoint());
        this._currentPoint = e.getPoint();
        return _draggable && _mousePressPoint != null;
    }

    public boolean mouseReleased(MouseEvent e) {
        this._mousePressPoint = null;
        return false;
    }


    public void set_draggable(boolean _draggable) {
        this._draggable = _draggable;
    }
}
