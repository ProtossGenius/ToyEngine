package com.suremoon.game.door.kernel;

import com.suremoon.game.door.gometry.Connectivity;
import com.suremoon.game.door.infos.MapInformation;
import com.suremoon.game.door.kernel.manager.EffectMgrItf;
import com.suremoon.game.door.kernel.manager.UnitMgrItf;

import java.awt.*;

/**
 *  ÓÎÏ·µØÍ¼½Ó¿Ú
 */
public interface GameMapItf {

    /** ¸ü»»µØÍ¼ÐÅÏ¢£¬Õâ¸ö·½·¨Ö÷ÒªÊÇÔÚµØÍ¼±à¼­Æ÷ÀïÓÃµ½
     * @param mapInfo µØÍ¼ÐÅÏ¢
     */
    void changeMap(MapInformation mapInfo);

    /**
     * @param value
     * µØÍ¼ÊÇÓÉËÄÐÐÈýÁÐµÄÍ¼Æ¬ËùÃèÊöµÄ£¬ÔÚ´æ´¢µÄÊ±ºò»á°ÑËü´æ´¢ÎªÒ»¸öÊý×Ö
     * @return
     * ½«´æ´¢µÄÊý×Ö±ä¸ü³ÉµØÐÎµÄ×´Ì¬£¨ÓÃÓÚÏÔÊ¾£©
     */
    static String getType(Integer value) {
        int first = value / 10, second = value % 10;
        return "" + first + second;
    }

    /**
     * @return µØÍ¼µÄÁÐÊý
     */
    int getCols();

    /**
     * @return µØÍ¼µÄÐÐÊý
     */
    int getRows();

    default boolean isLegalPos(int x, int y){return x >= 0 && x < getCols() && y >= 0 && y < getRows();}

    /**
     * @return »ñÈ¡Åö×²¼ì²âÆ÷
     */
    ImpactCheckerItf getImpactChecker();

    /**
     * @param ic ÉèÖÃÅö×²¼ì²âÆ÷
     */
    void setImpactChecker(ImpactCheckerItf ic);

    /**
     * @return »ñµÃµØÍ¼ÄÚµØÐÎ¾ØÐÎµÄ¿í¶È
     */
    int getTerrainWidth();

    /**
     * @return »ñµÃµØÍ¼ÄÚµØÐÎ¾ØÐÎµÄ¸ß¶È
     */
    int getTerrainHeight();

    /**
     * @return µØÍ¼µÄ×Ü¿í¶È
     */
    int getMapWidth();

    /**
     * @return µØÍ¼µÄ×Ü¸ß¶È
     */
    int getMapHeight();

    /**
     * »ñÈ¡µØÐÎ
     * @param x µØÐÎË÷ÒýÔÚx·½ÏòµÄÖµ
     * @param y µØÐÎË÷ÒýÔÚy·½ÏòµÄÖµ
     * @return ´¦ÓÚ(x,y)Î»ÖÃµÄµØÐÎ
     */
    TerrainItf getTerrain(int x, int y);
    default TerrainItf getTerrain(Point p){return getTerrain(p.x, p.y);}

    void setTerrain(int x, int y, TerrainItf terrain);

    /**
     * @param x µØÐÎË÷ÒýÔÚx·½ÏòµÄÖµ
     * @param y µØÐÎË÷ÒýÔÚy·½ÏòµÄÖµ
     * @return ´¦ÓÚ(x,y)Î»ÖÃµÄµØÐÎÁ¬Í¨ÐÔ
     */
    Connectivity getConnectivity(int x, int y);
    default Connectivity getConnectivity(Point p){return getConnectivity(p.x, p.y);}
    GameScreenItf createGameScreen();
    UnitMgrItf getUnitMgr();
    void setUnitMgr(UnitMgrItf um);
    EffectMgrItf getEffectMgr();
    void setEffectMgr(EffectMgrItf em);
    /**
     * @param worldMgr ÉèÖÃÊÀ½ç¹ÜÀíÆ÷
     */
    void setWorldMgr(WorldMgrItf worldMgr);

    /**
     * @param world ÉèÖÃµ±Ç°µØÍ¼ËùÊôµÄÊÀ½ç
     */
    void setWorld(WorldItf world);
    /**
     * @return µ±Ç°µØÍ¼ËùÊôµÄÊÀ½ç
     */
    WorldItf getWorld();

}

