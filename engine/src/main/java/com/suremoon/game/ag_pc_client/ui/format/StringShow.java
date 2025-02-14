package com.suremoon.game.ag_pc_client.ui.format;

import com.suremoon.game.ag_pc_client.show.showable_rect.string_show.LineSpacing;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class StringShow {
    Rectangle showArea;
    int pos;
    Font font;
    Color color = Color.BLACK;
    final Queue<String> ready = new LinkedList<>();
    protected LinkedList<String> ssls = new LinkedList<>();
    LineSpacing ls = new FixedLineSpacing();
    int charHeight = 1;
    int startIndex = 0;

    public StringShow(String str, Rectangle showArea) {
        this(str, showArea, new Font("宋体", Font.BOLD, 24));
    }

    public StringShow(String str, Rectangle showArea, Font font) {
        this(str, showArea, font, 0);
    }

    public StringShow(String str, Rectangle showArea, Font font, int pos) {
        this.ready.add(str);
        this.showArea = showArea;
        this.pos = pos;
        this.font = font;
    }

    protected void drawDeal(FontMetrics fm, int height) {
        String copy;
        synchronized (this.ready) {
            if (this.ready.isEmpty()) return;
            copy = this.ready.poll();
        }
        if (copy.isEmpty()) return;
        StringBuilder tmp = new StringBuilder();
        for (int i = pos; i < copy.length(); i++) {
            char c = copy.charAt(i);
            int nextLns = fm.stringWidth(tmp.toString() + c);

            if (nextLns > showArea.width || c == '\n') {
                ssls.add(tmp.toString());
                tmp = new StringBuilder("" + c);
            } else if (nextLns == showArea.width) {
                tmp.append(c);
                ssls.add(tmp.toString());
                tmp = new StringBuilder();
            } else {
                tmp.append(c);
            }
        }
        if (!tmp.isEmpty()) {
            ssls.add(tmp.toString());
        }
    }

    public boolean drawOn(Graphics gp) {
        return drawOn(gp, 0);
    }

    /**
     * @param gp
     * @param yChange Y方向上的偏移
     * @return 是否仍有可绘制的块（为false的话表示这个StringShow已经绘制完了）
     */
    public boolean drawOn(Graphics gp, int yChange) {
        gp.setFont(this.font);
        gp.setColor(this.color);

        FontMetrics fm = gp.getFontMetrics(font);
        charHeight = fm.getHeight() + ls.getLineSpacing(fm.getHeight());

        this.drawDeal(fm, charHeight);
        boolean drawed = false;
        int index = startIndex;

        int showBottom = showArea.y + showArea.height;

        for (Iterator<String> iter = ssls.iterator(); iter.hasNext(); ) {
            String line = iter.next();
            int lineY = showArea.y + (index + 1) * charHeight + yChange;
            if (lineY < showArea.y) {
                iter.remove();
                ++startIndex;
            } else if (lineY < showBottom) {
                drawed = true;
                gp.drawString(line, showArea.x, lineY);
            }
            ++index;
        }

        return drawed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void insertString(String str) {
        synchronized (this.ready) {
            if (this.ready.size() > 50) {
                this.ready.poll();
            }

            this.ready.add(str);
        }
    }

    public int MaxLines() {
        return this.showArea.height / charHeight;
    }

    public int size() {
        return this.ssls.size();
    }

    private static class FixedLineSpacing implements LineSpacing {

        @Override
        public int getLineSpacing(int height) {
            return height * 10 / 100;
        }
    }
}
