package com.suremoon.game.configers.map_resource.frames;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.ag_pc_client.show.showable_rect.string_show.StringShow;
import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.configers.JPicture;
import com.suremoon.game.configers.map_resource.file_deal.MapFileReader;
import com.suremoon.game.configers.map_resource.file_deal.MapFileWriter;
import com.suremoon.game.door.infos.MapInformation;
import com.suremoon.game.configers.map_resource.show_tools.ChoicedTerrainSign;
import com.suremoon.game.configers.map_resource.show_tools.GridLines;
import com.suremoon.game.configers.map_resource.show_tools.TerrainFollowMouse;
import com.suremoon.game.door.code_tools.Pair;
import com.suremoon.game.door.kernel.TerrainItf;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import com.suremoon.game.kernel.data.map.GameScreen;
import com.suremoon.game.kernel.data.map.Terrain;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.gometry.GRect;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.kernel.initer.InitManager;
import com.suremoon.game.kernel.initer.terrain_init.TerrainInfManager;
import com.suremoon.game.door.infos.TerrainInformation;
import com.suremoon.game.kernel.data.map.GameMap;


import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Water Moon on 2018/4/10.
 */
public class MapEditor extends JFrame implements MouseListener, MouseMotionListener {
    public GameMap gm;
    GameScreen gs;
    GameConfiger gc = GameConfiger.getGC();
    JPicture littleMap, mapPv, terrainPv, msg;
    TreeMap<String, Integer> cIdMap = new TreeMap<>();
    MapInformation mapIfm;
    BufferedImage tfm, lmap;
    Point lastPos = new Point(0, 0);
    TerrainChoice tc;
    private boolean updateMsg = true;
    boolean leftButtonDown;
    AGSAdapter adapter;
    Map<Integer, Map<Integer, Boolean>> findSet = new TreeMap<>();
    private void initComponent() {
        MenuTest mt = new MenuTest();
        this.setJMenuBar(mt);
        littleMap = new JPicture();
        littleMap.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX() * mapIfm.getCols() / littleMap.getWidth(), y = e.getY() * mapIfm.getCols() / littleMap.getHeight();
                gs.setFocusPoint(x * Terrain.width - gc.getScreenWidth() / 2, y * Terrain.height - gc.getScreenHeight() / 2);
            }
        });
        terrainPv = new JPicture();
        terrainPv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                tc.setVisible(true);
            }
        });
        msg = new JPicture();
        msg = new JPicture();
        MSplitPane mts = new MSplitPane(JSplitPane.VERTICAL_SPLIT, msg, terrainPv);
        MSplitPane m2 = new MSplitPane(JSplitPane.VERTICAL_SPLIT, mts, littleMap);
        mapPv = new JPicture();
        mapPv.setCacheResizeable(false);
        mapPv.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                gc.setScreenSize(mapPv.getWidth(), mapPv.getHeight());
                super.componentResized(e);
            }
        });
        gc.setScreenSize(mapPv.getWidth(), mapPv.getHeight());
        mapPv.enableInputMethods(false);
        mapPv.addMouseMotionListener(this);
        mapPv.addMouseListener(this);
        MSplitPane sp = new MSplitPane(JSplitPane.HORIZONTAL_SPLIT, m2, mapPv);
        sp.setSize(getSize());
        sp.setDividerLocation(0.25);

        m2.setDividerLocation(0.66);
        mts.setDividerLocation(0.5);
        sp.enableInputMethods(false);
        add(sp);
    }

    public MapEditor(int cols, int rows)throws Exception{
        super();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InitManager.init();
        initMapIfm(cols, rows);
        initFindSet();
        tc = new TerrainChoice(this);
        tfm = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        gm = new GameMap();
        updateGameMap();
        tc.setTerrainType(gm.getTerrain(0, 0).getAGType());
        gs = gm.createGameScreen();
        gs.setFocusPoint(0, 0);
        setBounds(50, 10, 1200, 800);
        initComponent();
        previewShow();
        enableInputMethods(false);
        addMouseListener(this);
        setResizable(false);
    }

    int toInt(Integer i){
        if(i == null)return 0;
        return i;
    }

    void initFindSet() throws Exception {
        ConfigInf cfg = new EasyConfig("./configs/map_editor_config/same_pairs.xml");
        cfg.open("SamePairs");
        ConfigInf[] samePairs = cfg.listConfigs();
        for(ConfigInf samePair : samePairs){
            int father = toInt(cIdMap.get(samePair.getValue("father")));
            int son = toInt(cIdMap.get(samePair.getValue("son")));
            Map<Integer, Boolean> fatherMap = findSet.get(son);
            if(fatherMap == null){
                fatherMap = new TreeMap<>();
                findSet.put(son, fatherMap);
            }
            fatherMap.put(father, true);
        }
    }

    public MapEditor() throws Exception {
        this(500, 500);
    }

    private static String pointToString(Point p) {
        if (p.equals(new Point(-7758, -521))) return "范围之外";
        return "(" + p.x + ", " + p.y + ")";
    }

    public void initMapIfm(int cols, int rows) {
        mapIfm = new MapInformation();
        mapIfm.setVersion("1.0.0.0");
        HashMap<Integer, TerrainInformation> map = TerrainInfManager.getTIM().getTihm();
        Map<Integer, String> list = mapIfm.gettConfs();
        ArrayList<Integer> idNotDe = new ArrayList<>();
        int id = 0;
        for (Map.Entry<Integer, TerrainInformation> it : map.entrySet()) {
            if (!it.getValue().isDecorate()) {
                idNotDe.add(id);
            }
            list.put(id, IDManager.getName(it.getKey()));
            cIdMap.put(IDManager.getName(it.getKey()), id);
            ++id;
        }
        mapIfm.setCols(cols);
        mapIfm.setRows(rows);
        mapIfm.setTheight(100);
        mapIfm.setTwidth(100);
        Pair<Integer, Integer> mimap[][] = new Pair[cols][rows];
        for (int i = 0; i < cols; ++i) {
            for (int j = 0; j < rows; j++) {
                mimap[i][j] = new Pair<>(idNotDe.get(1), 21);
//                mimap[i][j] = new Pair<>(idNotDe.get((i * i + j * j) % idNotDe.size()), (i%4) * 10 + j % 3);
            }
        }
        mapIfm.setTmap(mimap);
    }

    public void updateGameMap() {
        gm.changeMap(mapIfm);
        lmap = new BufferedImage(mapIfm.getCols(), mapIfm.getRows(), BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < mapIfm.getCols(); i++) {
            for (int j = 0; j < mapIfm.getRows(); j++) {
                TerrainInformation ti = TerrainInfManager.TIM.getTerrainInf(gm.getTerrain(i, j).getAGType());
                lmap.setRGB(i, j, getColor(ti.getAverageColor()));
            }
        }
    }

    static int getColor(String num) {
        return 0xff000000 | HexToInt(num);
    }

    @Deprecated
    static int HexToInt(String num) {
        return Integer.parseInt(num.replaceAll("^0[x|X]", ""), 16);
    }

    private void showMsg(){
        if(!updateMsg){
            return;
        }
        msg.getGraphics().setColor(Color.WHITE);
        msg.getGraphics().fillRect(0, 0, msg.getWidth(), msg.getHeight());
        msg.getGraphics().setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 20);
        new StringShow("地形名称: " + tc.getTerrainName(), new Rectangle(0, 30, msg.getWidth(), 50), font).drawOn(msg.getGraphics());
        new StringShow("鼠标真实位置: " + pointToString(new Point(lastPos.x + gs.getFocusPoint().x, lastPos.y + gs.getFocusPoint().y)), new Rectangle(0, 130, msg.getWidth(), 50), font).drawOn(msg.getGraphics());
        new StringShow("视窗位置: " + pointToString(gs.getFocusPoint()), new Rectangle(0, 230, msg.getWidth(), 50), font).drawOn(msg.getGraphics());
        msg.refresh();
    }

    public void showLoop() {
        gs.setMoveLength(35);
        while (true) {
            try {
                if (!lastPos.equals(new Point(-7758, -521))) {
                    gs.setLastPoint(lastPos);
                    gs.screenMove();
                }
                showMsg();
                gs.showMap(mapPv.getGraphics());
//                gm.drawOn(mapPv.getGraphics(), gs.getFocusePoint());
                GridLines.showNetLine(mapPv.getGraphics(), gs, new Point(Terrain.width, Terrain.height));
                if (!lastPos.equals(new Point(-7758, -521))) {
                    ChoicedTerrainSign.showSign(mapPv.getGraphics(), gs, new Point(Terrain.width, Terrain.height), lastPos);
                }
                adapter.show(terrainPv.getGraphics(), "Terrain00", new GRect(terrainPv.getWidth(), terrainPv.getHeight()), PointF.DIRECTION_ZERO, System.currentTimeMillis(), 60, true, new Point(0, 0));
                adapter.show(tfm.getGraphics(), "Terrain00", new GRect(100, 100), PointF.DIRECTION_ZERO, System.currentTimeMillis(), 60, true, new Point(0, 0));
                if (!lastPos.equals(new Point(-7758, -521))) {
                    TerrainFollowMouse.showSign(mapPv.getGraphics(), tfm, new Point(Terrain.width, Terrain.height), lastPos);
                }
                littleMap.getGraphics().drawImage(lmap, 0, 0, littleMap.getWidth(), littleMap.getHeight(), null);
                drawRect(littleMap.getGraphics(), getLittleMapRect());
                mapPv.refresh();
                terrainPv.refresh();
                littleMap.refresh();
                Thread.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void drawRect(Graphics graphics, Rectangle rect) {
        graphics.drawLine(rect.x, rect.y, rect.x, rect.y + rect.height);
        graphics.drawLine(rect.x, rect.y, rect.x + rect.width, rect.y);
        graphics.drawLine(rect.x + rect.width, rect.y, rect.x + rect.width, rect.y + rect.height);
        graphics.drawLine(rect.x, rect.y + rect.height, rect.x + rect.width, rect.y + rect.height);
    }

    Rectangle getLittleMapRect() {
        Rectangle rect = gs.getScreenRect();
        double sx = (double)rect.x / Terrain.width,
                sy = (double)rect.y / Terrain.height,
                ex = (double)rect.width / Terrain.width,
                ey = (double)rect.height / Terrain.height;
        return new Rectangle((int)(sx * littleMap.getWidth() / mapIfm.getCols()),
                (int)(sy * littleMap.getHeight() / mapIfm.getRows()),
                (int)(ex * littleMap.getWidth() / mapIfm.getCols()),
                (int)(ey * littleMap.getHeight() / mapIfm.getRows()));
    }


    public void previewShow() {
        TerrainInformation ti = TerrainInfManager.getTIM().getTerrainInf(tc.getTerrainName());
        adapter = ti.getShower();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        updateMap();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftButtonDown = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftButtonDown = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        lastPos = new Point(-7758, -521);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        lastPos = gc.getDesignPos(e.getPoint());
        if (leftButtonDown) {
            updateMap();
        }
    }

    public void updateMap() {
        Point tp = lastPos;
        int ssx = tp.x + gs.getFocusPoint().x, ssy = tp.y + gs.getFocusPoint().y;
        if(ssx < 0 || ssy < 0){
            return;
        }
        ssx /= Terrain.width;
        ssy /= Terrain.height;
        ssx = (ssx + mapIfm.getCols()) % mapIfm.getCols();
        ssy = (ssy + mapIfm.getRows()) % mapIfm.getRows();
        if(setTerrain(ssx, ssy, tc.getTerrainName())){
            terrainChange(ssx, ssy);
        }
    }
    public boolean setTerrain(int ssx, int ssy, String terrainName){
        return setTerrain(ssx, ssy, terrainName, "00");
    }
    public boolean setTerrain(int ssx, int ssy, String terrainName, String showTypeId) {
        TerrainInfManager tim = TerrainInfManager.TIM;
        if (gm.getTerrain(ssx, ssy).getAGType() == IDManager.getID(tc.getTerrainName()) && gm.getTerrain(ssx, ssy).getTerrainType().equals("Terrain" + showTypeId))
            return false;
        gm.setTerrain(ssx, ssy, TerrainInfManager.getTIM().productTerrain(IDManager.getID(terrainName)));
        TerrainItf t = gm.getTerrain(ssx, ssy);
        t.setTerrainType(showTypeId);
        t.setPos(ssx * Terrain.width, ssy * Terrain.height);
        lmap.setRGB(ssx, ssy, getColor(tim.getTerrainInf(t.getAGType()).getAverageColor()));
        mapIfm.setTerrain(ssx, ssy, new Pair<>(cIdMap.get(terrainName), Integer.parseInt(showTypeId)));
        littleMap.getGraphics().drawImage(lmap, 0, 0, littleMap.getWidth(), littleMap.getHeight(), null);
        drawRect(littleMap.getGraphics(), getLittleMapRect());
        littleMap.refresh();
        return true;
    }

    private boolean terrainPosAble(int posx, int posy) {
        if (posx < 0 || posy < 0 || posx >= this.mapIfm.getCols() || posy >= this.mapIfm.getRows()) {
            return false;
        }
        return true;
    }

    private boolean isTerrainEqual(int posx, int posy, String terrainName) {
        int tMapId = cIdMap.get(terrainName);
        if (!terrainPosAble(posx, posy)) {
            return true;
        }

        int targetId = this.mapIfm.getTmap()[posx][posy].getKey();

        if (targetId == tMapId) {
            return true;
        }
        Map<Integer, Boolean> fatherMap = findSet.get(targetId);
        if(fatherMap == null){
            return false;
        }

        Boolean check = fatherMap.get(tMapId);
        if(check == null){
            return false;
        }
        return check;
    }


    public String calcTerrainShowTypeId(int x, int y, String terrainName) {
        final int LEFT = 0, RIGHT = 2, UP = 0, DOWN = 2, MID = 1;
        boolean[][] eqaMap = new boolean[3][3];
        int eqaCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(eqaMap[i+1][j+1] = isTerrainEqual(x+i, y+j, terrainName)) {
                    eqaCount++;
                }
            }
        }
        if (eqaCount == 9){
            return "21";
        }
        if(eqaMap[LEFT][MID] && eqaMap[RIGHT][MID] && eqaMap[MID][UP] && eqaMap[MID][DOWN]){
            return "21";
        }
        if(eqaMap[LEFT][MID] && eqaMap[RIGHT][MID] && eqaMap[MID][DOWN]){
            return "11";
        }
        if(eqaMap[MID][UP] && eqaMap[MID][DOWN] && eqaMap[RIGHT][MID]){
            return "20";
        }
        if(eqaMap[MID][UP] && eqaMap[MID][DOWN] && eqaMap[LEFT][MID]){
            return "22";
        }
        if(eqaMap[LEFT][MID] && eqaMap[RIGHT][MID] && eqaMap[MID][UP]){
            return "31";
        }
        if(eqaMap[RIGHT][MID] && eqaMap[MID][DOWN]){
            return "10";
        }
        if(eqaMap[LEFT][MID] && eqaMap[MID][DOWN]){
            return "12";
        }
        if(eqaMap[RIGHT][MID] && eqaMap[MID][UP]){
            return "30";
        }
        if(eqaMap[LEFT][MID] && eqaMap[MID][UP]){
            return "32";
        }
        if(eqaCount != 1){
            return "02";
        }
        return "00";
    }

    public void updateTerrain(int posx, int posy){
        String terrainName = IDManager.getName(gm.getTerrain(posx, posy ).getAGType());
        String showType = calcTerrainShowTypeId(posx, posy , terrainName);
        setTerrain(posx , posy, terrainName, showType);
    }

    public boolean terrainChange(int posx, int posy) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!terrainPosAble(posx + i, posy + j)) {
                    continue;
                }
                String terrainName = IDManager.getName(gm.getTerrain(posx + i, posy + j).getAGType());
                String showType = calcTerrainShowTypeId(posx + i, posy + j, terrainName);
                setTerrain(posx + i, posy + j, terrainName, showType);
            }
        }
        return false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastPos = gc.getDesignPos(e.getPoint());
    }

    class MenuTest extends JMenuBar {// 继承菜单Bar
        private JDialog aboutDialog;

        public MenuTest() {
            JMenu fileMenu = new JMenu("文件");// 实例化一个“文件”的菜单
            fileMenu.addMenuListener(new MenuListener() {
                @Override
                public void menuSelected(MenuEvent e) {
                    updateMsg = false;
                }

                @Override
                public void menuDeselected(MenuEvent e) {
                    updateMsg = true;
                }

                @Override
                public void menuCanceled(MenuEvent e) {
                    updateMsg = true;
                }
            });
            JMenuItem exitMenuItem = new JMenuItem("退出", KeyEvent.VK_E); // 退出菜单项
            JMenuItem openMenuItem = new JMenuItem("打开", KeyEvent.VK_O);
            JMenuItem saveMenuItem = new JMenuItem("保存", KeyEvent.VK_S);
            JMenuItem updateMenuItem = new JMenuItem("刷新", KeyEvent.VK_F5);
            JMenuItem aboutMenuItem = new JMenuItem("关于..", KeyEvent.VK_A); // 关于菜单项
            fileMenu.add(openMenuItem);
            fileMenu.add(saveMenuItem);
            fileMenu.add(updateMenuItem);
            fileMenu.add(exitMenuItem); // 把这些菜单项放到‘文件’菜单项中
            fileMenu.add(aboutMenuItem);
            this.add(fileMenu);// 添加到这个jframe里面
            aboutDialog = new JDialog(); // 再实例化一个Dialog
            initAboutDialog();// 初始化这个dialog
            openMenuItem.addActionListener(e -> {
                try {
                    JFileChooser jfc = new JFileChooser(new File("./resource/maps"));
                    int result = jfc.showOpenDialog(MapEditor.this);
                    if (result == jfc.APPROVE_OPTION) {
                        File configFile = jfc.getSelectedFile();
                        MapFileReader mfr = new MapFileReader(configFile.toString());
                        mapIfm = mfr.getMi();
                        updateGameMap();
                        JOptionPane.showConfirmDialog(null, "打开地图成功", "提示框", JOptionPane.YES_OPTION);// 显示一个confirm的窗口
                    }
                } catch (IOException e1) {
                    JOptionPane.showConfirmDialog(null, "打开地图失败,错误信息为：" + e1.getMessage(), "提示框", JOptionPane.YES_OPTION);// 显示一个confirm的窗口
                }
            });
            saveMenuItem.addActionListener(e -> {
                synchronized (mapIfm) {
                    try {
                        JFileChooser jfc = new JFileChooser(new File("./resource/maps"));
                        int result = jfc.showSaveDialog(MapEditor.this);
                        if (result == jfc.APPROVE_OPTION) {
                            File configFile = jfc.getSelectedFile();
                            new MapFileWriter(configFile.toString(), mapIfm);
                            JOptionPane.showConfirmDialog(null, "保存成功", "提示框", JOptionPane.YES_OPTION);// 显示一个confirm的窗口
                        }
                    } catch (IOException e1) {
                        JOptionPane.showConfirmDialog(null, "保存地图失败，错误信息为：" + e1.getMessage(), "提示框", JOptionPane.YES_OPTION);// 显示一个confirm的窗口
                    }
                }
            });
            updateMenuItem.addActionListener((ActionEvent e)->{
                for(int i = 0; i < mapIfm.getRows(); i++){
                    for(int j = 0; j < mapIfm.getCols(); j++){
                        updateTerrain(i, j);
                    }
                }
                JOptionPane.showConfirmDialog(null, "刷新地图成功", "提示框", JOptionPane.YES_OPTION);// 显示一个confirm的窗口
            });
            // 给退出菜单项添加一个退出时间
            exitMenuItem.addActionListener(e -> {// 退出添加监听事件
                dispose();
                System.exit(0);
            });
            // 关于添加监听事件
            aboutMenuItem.addActionListener(e -> aboutDialog.setVisible(true));
        }

        public void initAboutDialog() { // 初始化dialog
            aboutDialog.setTitle("关于");// 设置dialog的标题
            Container con = aboutDialog.getContentPane();// 获得dialog整个的容器
            Icon icon = new ImageIcon("images/LxGLory.gif");
            JLabel aboutLabel = new JLabel("<html><b><font size=5>"
                    + "<center>TA地图编辑器 by moon_sui" + "<br>", icon, JLabel.CENTER);
            con.add(aboutLabel, BorderLayout.CENTER);// 设置边界布局 为居中
            aboutDialog.setSize(450, 225);// 设置dialog的大小
            aboutDialog.setLocationRelativeTo(null);// 默认屏幕居中
        }
    }

}
