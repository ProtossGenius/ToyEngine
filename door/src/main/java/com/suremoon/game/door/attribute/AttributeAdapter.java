package com.suremoon.game.door.attribute;

/**
 * @Note:    this file product by tools, you should only change the comment about ATTRIB_LIST,
 * 	           your another change will be cover.
 * @Author:  ProtossGenius
 *
 * @Time:    2021-02-09 16:00:58
 */
public class AttributeAdapter extends SMAttribute{ 

/*#ATTRIB_LIST
    Hp = 血量, Mp = 蓝量, MaxHp = 最大血量, MaxMp = 最大蓝量,
    Spd = 速度, Agi = 敏捷（命中和闪避）,
    Metal = 金, Wood = 木, Water = 水, Fire = 火, Earth = 土,
    Thunder = 雷, Wind = 风, Ice = 冰
    */
	/** Hp   血量.*/
	public static final int Hp  = 0;
	/** Mp   蓝量.*/
	public static final int Mp  = 1;
	/** MaxHp   最大血量.*/
	public static final int MaxHp  = 2;
	/** MaxMp   最大蓝量.*/
	public static final int MaxMp  = 3;
	/** Spd   速度.*/
	public static final int Spd  = 4;
	/** Agi   敏捷（命中和闪避）.*/
	public static final int Agi  = 5;
	/** Metal   金.*/
	public static final int Metal  = 6;
	/** Wood   木.*/
	public static final int Wood  = 7;
	/** Water   水.*/
	public static final int Water  = 8;
	/** Fire   火.*/
	public static final int Fire  = 9;
	/** Earth   土.*/
	public static final int Earth  = 10;
	/** Thunder   雷.*/
	public static final int Thunder  = 11;
	/** Wind   风.*/
	public static final int Wind  = 12;
	/** Ice   冰.*/
	public static final int Ice  = 13;
	public AttributeAdapter(){ super(14); }
	public AttributeAdapter(AttributeAdapter another){super(another);}
	/** 设置 血量. */
	public void setHp (double _hp ){setAttribute(Hp , _hp );}
	/** 获得 血量. */
	public double getHp (){ return getAttribute(Hp ); }
	/** 设置 蓝量. */
	public void setMp (double _mp ){setAttribute(Mp , _mp );}
	/** 获得 蓝量. */
	public double getMp (){ return getAttribute(Mp ); }
	/** 设置 最大血量. */
	public void setMaxHp (double _maxhp ){setAttribute(MaxHp , _maxhp );}
	/** 获得 最大血量. */
	public double getMaxHp (){ return getAttribute(MaxHp ); }
	/** 设置 最大蓝量. */
	public void setMaxMp (double _maxmp ){setAttribute(MaxMp , _maxmp );}
	/** 获得 最大蓝量. */
	public double getMaxMp (){ return getAttribute(MaxMp ); }
	/** 设置 速度. */
	public void setSpd (double _spd ){setAttribute(Spd , _spd );}
	/** 获得 速度. */
	public double getSpd (){ return getAttribute(Spd ); }
	/** 设置 敏捷（命中和闪避）. */
	public void setAgi (double _agi ){setAttribute(Agi , _agi );}
	/** 获得 敏捷（命中和闪避）. */
	public double getAgi (){ return getAttribute(Agi ); }
	/** 设置 金. */
	public void setMetal (double _metal ){setAttribute(Metal , _metal );}
	/** 获得 金. */
	public double getMetal (){ return getAttribute(Metal ); }
	/** 设置 木. */
	public void setWood (double _wood ){setAttribute(Wood , _wood );}
	/** 获得 木. */
	public double getWood (){ return getAttribute(Wood ); }
	/** 设置 水. */
	public void setWater (double _water ){setAttribute(Water , _water );}
	/** 获得 水. */
	public double getWater (){ return getAttribute(Water ); }
	/** 设置 火. */
	public void setFire (double _fire ){setAttribute(Fire , _fire );}
	/** 获得 火. */
	public double getFire (){ return getAttribute(Fire ); }
	/** 设置 土. */
	public void setEarth (double _earth ){setAttribute(Earth , _earth );}
	/** 获得 土. */
	public double getEarth (){ return getAttribute(Earth ); }
	/** 设置 雷. */
	public void setThunder (double _thunder ){setAttribute(Thunder , _thunder );}
	/** 获得 雷. */
	public double getThunder (){ return getAttribute(Thunder ); }
	/** 设置 风. */
	public void setWind (double _wind ){setAttribute(Wind , _wind );}
	/** 获得 风. */
	public double getWind (){ return getAttribute(Wind ); }
	/** 设置 冰. */
	public void setIce (double _ice ){setAttribute(Ice , _ice );}
	/** 获得 冰. */
	public double getIce (){ return getAttribute(Ice ); }
}

