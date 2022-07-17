package com.suremoon.game.door.save_about;

import com.suremoon.game.door.tools.ByteStream;

/** 可序列化，不同于AGMessage，这个是完全序列化，期待可以无损的初始化所属类。 */
public interface SerializeAble {
  void parseFromBytes(ByteStream byteStream);

  byte[] encodeToBytes();
}
