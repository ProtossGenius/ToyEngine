#!python3
from datetime import datetime
print(f"""package com.suremoon.game.door.units_itf;
import com.suremoon.game.door.utils.Assert;
import com.suremoon.game.door.kernel.WorldItf;
// This interface is product by scripts/generate_bag.py, You should change scripts to fix this file.
// @Author: ProtossGenius @Time: {datetime.now().strftime("%Y-%m-%d %H:%M:%S")}

public interface BagMgrItf {{
    GoodsItf get(int pos);

    void pick(GoodsItf goods, WorldItf worldItf);

    void swap(int oldIndex, int newIndex, WorldItf worldItf);

    // ################# default goods type. ########################
""")
ATTRIB_LIST = {
    "LEFT_HAND": "LeftHand",
    "RIGHT_HAND":"RightHand",
    "HEAD":     "Head",
    "BODY":     "Body",
    "LEFT_LEG":  "LeftLeg",
    "RIGHT_LEG": "RightLeg",
    "LEFT_FOOT": "LeftFoot",
    "RIGHT_FOOT":"RightFoot",
    }

ATTRIB_DESC = {
    "LEFT_HAND": "LeftHand",
    "RIGHT_HAND":"RightHand",
    "HEAD":     "Head",
    "BODY":     "Body",
    "LEFT_LEG":  "LeftLeg",
    "RIGHT_LEG": "RightLeg",
    "LEFT_FOOT": "LeftFoot",
    "RIGHT_FOOT":"RightFoot",
    }
index = 0
for key, value in ATTRIB_DESC.items():
    print(f"""
     /**
      * {key} {value}.
      */
     int {key}= {index};
""")
    index = index + 1

for key, value in ATTRIB_LIST.items():
    print(f"""
    /**
     * 设置 {ATTRIB_DESC[key]}.
     */
    default void load{value}(int newIndex, WorldItf worldItf) {{
        var goods = get(newIndex);
        Assert.True(goods == null || goods.getGoodsType() == {key}, "Need Goods Type is {ATTRIB_DESC[key]}");
        swap({key}, newIndex, worldItf);
    }}

    /**
     * 获得 {ATTRIB_DESC[key]}.
     */
    default GoodsItf get{value}() {{
        return get({key});
    }}
""")

print("}")