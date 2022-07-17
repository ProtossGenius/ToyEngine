package com.suremoon.game.ag_pc_client.show;

import java.util.LinkedList;
import java.util.Queue;

public class FrequencyCollector {
  Queue<Long> queue = new LinkedList<>();

  public FrequencyCollector(long timeLength) {
    this.timeLength = timeLength;
  }

  long timeLength;

  private void dropTimeOuts() {
    long targetStart = System.currentTimeMillis() - timeLength;
    synchronized (this.queue) {
      while (!this.queue.isEmpty() && this.queue.peek() < targetStart) {
        this.queue.poll();
      }
    }
  }

  public void Tick() {
    dropTimeOuts();
    synchronized (this.queue) {
      queue.add(System.currentTimeMillis());
    }
  }

  public int See() {
    dropTimeOuts();
    synchronized (this.queue) {
      return queue.size();
    }
  }
}
