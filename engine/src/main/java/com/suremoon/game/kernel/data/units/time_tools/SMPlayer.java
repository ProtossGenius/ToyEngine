package com.suremoon.game.kernel.data.units.time_tools;

/** Created by Water Moon on 2017/9/21. */
public class SMPlayer implements SMPlayable {
  public long startTime, currentTime, passedTime;
  int state;
  public static final int PLAYING = 1, STOPING = 0, PAUSING = 2;

  public SMPlayer(long time) {
    currentTime = startTime = time;
    state = PLAYING;
  }

  public SMPlayer() {
    startTime = System.currentTimeMillis();
    state = STOPING;
  }

  @Override
  public void Play(long time) {
    if (state == STOPING) {
      currentTime = startTime = time;
      state = PLAYING;
    } else if (state == PAUSING) {
      Continue(time);
    }
  }

  @Override
  public void RePlay(long time) {
    Stop();
    Play(time);
  }

  @Override
  public void Stop() {
    state = STOPING;
  }

  @Override
  public void Pause() {
    if (state != STOPING) state = PAUSING;
  }

  @Override
  public void Continue(long time) {
    startTime += (time - currentTime);
    currentTime = time;
    state = PLAYING;
  }

  @Override
  public void Update(long time) {
    passedTime = time - currentTime;
    currentTime = time;
  }

  public long getPassedTime() {
    return passedTime = currentTime - startTime;
  }

  public long getPassedTime(long time) {
    Update(time);
    return getPassedTime();
  }

  public long getCurrentTime() {
    return currentTime;
  }

  public long getStartTime() {
    return startTime;
  }
}
