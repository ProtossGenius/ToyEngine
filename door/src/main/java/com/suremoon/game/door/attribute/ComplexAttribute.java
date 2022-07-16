package com.suremoon.game.door.attribute;

import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;

/**
 * @Note:    this file product by tools, you should change nothing in here,
 *             or your changes will be cover.
 * @Author:  ProtossGenius
 *
 * @Time:    2021-02-09 16:00:58
 */
public class ComplexAttribute extends AttributeAdapter{
    AttributeAdapter extra;

    public ComplexAttribute(){
        super();
        this.extra = new AttributeAdapter();
    }

    public ComplexAttribute(AttributeAdapter itself) {
        super(itself);
        this.extra = new AttributeAdapter();
    }

    public ComplexAttribute(AttributeAdapter itself, AttributeAdapter extra) {
        super(itself);
        this.extra = extra;
    }
    
    public AttributeAdapter getExtra() {
        return extra;
    }


	/** 获得 血量. */
	public double getHp (){ return super.getHp () + extra.getHp (); }
	/** 获得 蓝量. */
	public double getMp (){ return super.getMp () + extra.getMp (); }
	/** 获得 最大血量. */
	public double getMaxHp (){ return super.getMaxHp () + extra.getMaxHp (); }
	/** 获得 最大蓝量. */
	public double getMaxMp (){ return super.getMaxMp () + extra.getMaxMp (); }
	/** 获得 速度. */
	public double getSpd (){ return super.getSpd () + extra.getSpd (); }
	/** 获得 敏捷（命中和闪避）. */
	public double getAgi (){ return super.getAgi () + extra.getAgi (); }
	/** 获得 金. */
	public double getMetal (){ return super.getMetal () + extra.getMetal (); }
	/** 获得 木. */
	public double getWood (){ return super.getWood () + extra.getWood (); }
	/** 获得 水. */
	public double getWater (){ return super.getWater () + extra.getWater (); }
	/** 获得 火. */
	public double getFire (){ return super.getFire () + extra.getFire (); }
	/** 获得 土. */
	public double getEarth (){ return super.getEarth () + extra.getEarth (); }
	/** 获得 雷. */
	public double getThunder (){ return super.getThunder () + extra.getThunder (); }
	/** 获得 风. */
	public double getWind (){ return super.getWind () + extra.getWind (); }
	/** 获得 冰. */
	public double getIce (){ return super.getIce () + extra.getIce (); }

	@Override
	public void parseFromBytes(ByteStream byteStream) {
		super.parseFromBytes(byteStream);
		this.extra.parseFromBytes(byteStream);
	}

	@Override
	public byte[] encodeToBytes() {
		return CJDeal.ByteArrayConnect(super.encodeToBytes(), this.extra.encodeToBytes());
	}
}

