package com.suremoon.game.kernel.data.units.time_tools;

/** Created by Water Moon on 2017/9/21. */
public interface SMPlayable {
  void Play(long time);

  void RePlay(long time);

  void Stop();

  void Pause();

  void Continue(long time);

  void Update(long time);
}
