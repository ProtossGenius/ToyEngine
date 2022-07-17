package com.suremoon.game.door.client;

/** Input Event ActionData. */
public class ActionData {
  public static final int DATA_TYPE_KEY_BOARD = 0,
      DATA_TYPE_MOUSE = 1,
      DATA_TYPE_ANOTHER = 2,
      VALUE_TYPE_PRESS = 0,
      VALUE_TYPE_REPLASE = 1;
  int dataType, // 0:key, 1:Mouse, 2:自定义
      dataValue, // 如key值，鼠标哪个键等等
      /** valueType 0:按下 1:松开 */
      valueType; // 如鼠标按下，鼠标抬起，键盘按下等等事件，具体的定义会进行补充。

  public ActionData() {}

  public ActionData(int dataType, int dataValue, int valueType) {
    this.dataType = dataType;
    this.dataValue = dataValue;
    this.valueType = valueType;
  }

  public int getValueType() {
    return valueType;
  }

  public void setValueType(int valueType) {
    this.valueType = valueType;
  }

  public int getDataType() {
    return dataType;
  }

  public void setDataType(int dataType) {
    this.dataType = dataType;
  }

  public int getDataValue() {
    return dataValue;
  }

  public void setDataValue(int dataValue) {
    this.dataValue = dataValue;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj instanceof ActionData) {
      return dataType == ((ActionData) obj).dataType
          && dataValue == ((ActionData) obj).dataValue
          && valueType == ((ActionData) obj).valueType;
    }
    return super.equals(obj);
  }

  @Override
  public String toString() {
    String s_dataType = "";
    switch (dataType) {
      case 0:
        s_dataType = "键盘";
        break;
      case 1:
        s_dataType = "鼠标";
        break;
      case 2:
        s_dataType = "自定义";
        break;
      default:
        s_dataType = "未定义";
    }
    return "动作为" + s_dataType + ", 值为：" + dataValue + "， 状态为： " + valueType;
  }
}
