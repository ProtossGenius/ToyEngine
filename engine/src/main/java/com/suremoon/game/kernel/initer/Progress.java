package com.suremoon.game.kernel.initer;

public class Progress {
  public static final Progress NIL = new Progress();
  private OnUpdate onUpdate = (a, b) -> {};
  private Runnable onFinish = () -> {};
  private int total = 100;
  private int current = 0;

  public void setCallback(OnUpdate onUpdate, Runnable onFinish) {
    this.onUpdate = onUpdate;
    this.onFinish = onFinish;
  }

  public void done() {
    this.current++;
    onUpdate.update(this.total, this.current);
  }

  public void allDone() {
    onFinish.run();
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
    onUpdate.update(this.total, this.current);
  }

  public int getCurrent() {
    return current;
  }

  public void reset() {
    total = 100;
    current = 0;
  }

  public interface OnUpdate {
    void update(int total, int current);
  }
}
