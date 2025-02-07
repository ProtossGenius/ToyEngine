#!python3
from datetime import datetime
print(f"""package com.suremoon.game.door.units_itf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.utils.Assert;
// This interface is product by scripts/generate_bag.py, You should change scripts to fix this file.
// @Author: ProtossGenius @Time: {datetime.now().strftime("%Y-%m-%d %H:%M:%S")}
""")
print("""public class BagManager {
    PlayerItf playerItf;

    public BagManager(PlayerItf playerItf) {
        this.playerItf = playerItf;
    }

    public PlayerItf getPlayer() {
        return playerItf;
    }

    // todo: set private.
    GoodsItf getDirect(int pos) { // get from user's bag direct.
        return getPlayer().getBag().get(pos);
    }

    /**
     * pick goods from world.
     *
     * @param goods
     * @param worldItf
     */
    public void pick(GoodsItf goods, WorldItf worldItf) {
        var goodsOnHand = getBagOnHand();
        var bag = getUserBag().getBag();
        // put the goods on Hand to bag.
        goodsOnHand = addToBag(goodsOnHand);
        if (goodsOnHand != null) {
            dropGoods(goods, worldItf);
        }

        getPlayer().getBag().set(BAG_ON_HAND, goods);
    }

    private void dropGoods(GoodsItf goods, WorldItf worldItf) {
        goods.setPos(getPlayer().getPos());
        worldItf.addCalcUnit(goods);
    }

    /**
     * add goods to bag.
     *
     * @param goods
     * @return if can't add to bag, return goods.
     */
    private GoodsItf addToBag(GoodsItf goods) {
        if (goods == null) {
            return null;
        }
        var userBag = getPlayer().getBag();
        // try add to hand first.
        for (int i = 0; i < 10 && i < userBag.size(); ++i) {
            if (userBag.get(i) == null) {
                userBag.set(i, goods);
                return null;
            }
        }
        var bag = getUserBag().getBag();
        for (int i = 0; i < bag.size(); ++i) {
            if (bag.get(i) == null) {
                bag.set(i, goods);
                return null;
            }
        }
        return goods;
    }

    public void swap(int oldIndex, int newIndex, WorldItf worldItf) {

    }

    public GoodsItf get(int pos) {
        if (pos < FIRST_BAG_VALUE)
            return getDirect(pos);
        int specialBag = pos / 10000;
        if (FIRST_BAG_VALUE + specialBag >= MAX_BAG_VALUE) {
            return null;
        }

        var bag = getDirect(FIRST_BAG_VALUE + specialBag);
        if (bag == null) {
            return null;
        }
        int index = pos % 10000;
        if (index > bag.getBag().size()) {
            return null;
        }
        return bag.getBag().get(index);
    }""")

index = 10
Name = 0
Desc = 1
EQUIP_LIST = {
    # CONST_VALUE: [CameCaseName, Desc]
    "LEFT_HAND":["LeftHand","LeftHand"],
    "RIGHT_HAND":["RightHand","RightHand"],
    "HEAD":["Head","Head"],
    "BODY":["Body","Body"],
    "LEFT_LEG":["LeftLeg","LeftLeg"],
    "RIGHT_LEG":["RightLeg","RightLeg"],
    "LEFT_FOOT":["LeftFoot","LeftFoot"],
    "RIGHT_FOOT":["RightFoot","RightFoot"],
    }

BAG_LIST = {
        "USER_BAG": ["UserBag", "user's bag"],
        "BAG_ON_HAND": ["BagOnHand", "bag on user's hand"],
        }
print("    // ################# 0 to 10 is goods in handle(bottom).  ########################")
print("    // ################# const default equip type. ########################")
for key, value in EQUIP_LIST.items():
    print(f"""  /**
    * {key} {value[Desc]}.
    */
    public static final int {key}= {index};
""")
    index = index + 1
print(" // ################### const goods for special bag. #######################")
print(" // index of First bag type.")
print(f" int FIRST_BAG_VALUE = {index};")
for key, value in BAG_LIST.items():
    print(f"""  /**
  * {key} {value[Desc]}.
  */
  public static final int {key}= {index};
""")
    index = index + 1

print(f"    public static final int MAX_BAG_VALUE = {index};")
for key, value in EQUIP_LIST.items():
    print(f"""
  /**
   * 设置 {value[Desc]}.
   */
  public void load{value[Name]}(int newIndex, WorldItf worldItf) {{
    var goods = get(newIndex);
    Assert.True(goods == null || goods.getGoodsType() == {key}, "Need Goods Type is {value[Desc]}");
    swap({key}, newIndex, worldItf);
  }}

  /**
   * 获得 {value[Desc]}.
   */
  public GoodsItf get{value[Name]}() {{
    return getDirect({key});
  }}
""")

for key, value in BAG_LIST.items():
    print(f"""
    /**
     * 获得 {value[Desc]}.
     */
    public GoodsItf get{value[Name]}() {{
        return getDirect({key});
    }}
""")
print("}")