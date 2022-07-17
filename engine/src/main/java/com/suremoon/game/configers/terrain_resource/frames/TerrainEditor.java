package com.suremoon.game.configers.terrain_resource.frames;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.configers.JPreview;
import com.suremoon.game.ag_pc_client.resource.image.xml_init.XmlAnalysisFactory;
import com.suremoon.game.door.code_tools.Pair;
import net.sf.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Water Moon on 2018/2/17.
 */
public class TerrainEditor extends JFrame {
    JPreview preViewP;
    JButton openPicResB, openTConfB, addBuffB, deleteChoiceB, saveB;
    JLabel  /*choiceResTypeL,*///no-need, it config in res xml;
            resNameL, preViewTypeL, passableOL, passableIL;
    JComboBox /*resTypeListCB,*/
            preViewTypeCB, passableOCB, passableICB, buffListCB;
    JTextField resNameTF, extraPTF;
    JTable buffListT;
    DefaultTableModel buffListDTM = new DefaultTableModel();
    DefaultComboBoxModel /*resTypeListDCBM = new DefaultComboBoxModel(),*/ buffListDCBM = new DefaultComboBoxModel();

    final String tableTitle[] = new String[]{"Buff名称", "附加参数"};
    LinkedList<Pair<String, String>> buffListD = new LinkedList<>();//pair is buff, extra_p
    public String resPath;
    AGSAdapter adapter;

    /**
     *  用于确定可以保存临时文件。临时文件的保存目录为下述目录。
     */
    static {
        File f = new File("temp/terrain_config/");
        if(!f.exists()){
            f.mkdirs();
        }
    }
    public TerrainEditor() {
        super("Terrain Editor");
        setBounds(100, 100, 624, 434);
        setLayout(null);
        setResizable(false);
        initComponent();
        initAction();
    }
    protected void initComponent(){
        //----------------------------------------preViewP--------------------------------------------
        preViewP = new JPreview();
        preViewP.setBounds(18, 12, 232, 146);
        add(preViewP);
        //----------------------------------------openTConfB--------------------------------------------
        openTConfB = new JButton("打开地形配置文件");
        openTConfB.setBounds(464, 13, 133, 24);
        add(openTConfB);
        //------------------------------------------openPicResB------------------------------------------
        openPicResB = new JButton("打开资源配置文件");
        openPicResB.setBounds(282, 12, 133, 25);;
        add(openPicResB);
        //----------------------------------------addBuffB--------------------------------------------
        addBuffB = new JButton("添加");
        addBuffB.setBounds(377, 365, 60, 22);
        add(addBuffB);
        //------------------------------------deleteChoiceB------------------------------------------------
        deleteChoiceB = new JButton("删除所选");
        deleteChoiceB.setBounds(442, 365, 90, 22);
        add(deleteChoiceB);
        //-------------------------------------saveB-----------------------------------------------
        saveB = new JButton("保存");
        saveB.setBounds(537, 365, 60, 22);
        add(saveB);
        /*
        //----------------------------------choiceResTypeL--------------------------------------------------
        choiceResTypeL = new JLabel("选择资源类型:");
        choiceResTypeL.setBounds(282, 54, 133, 25);
        add(choiceResTypeL);
        */
        //----------------------------------resNameL--------------------------------------------------
        resNameL = new JLabel("资源名");
        resNameL.setBounds(282, 54, 133, 25);
        add(resNameL);
        //-----------------------------------preViewTypeL-------------------------------------------------
        preViewTypeL = new JLabel("资源预览方式:");
        preViewTypeL.setBounds(282, 95, 133,24);
        add(preViewTypeL);
        //-----------------------------------passableIL-------------------------------------------------
        passableIL = new JLabel("是否可通行(内)");
        passableIL.setBounds(308, 176, 141, 22);
        add(passableIL);
        //----------------------------------passableOL--------------------------------------------------
        passableOL = new JLabel("是否可通行(外)");
        passableOL.setBounds(18, 176, 141, 22);
        add(passableOL);
        /*
        //--------------------------------resTypeListCB----------------------------------------------------
        resTypeListCB = new JComboBox(resTypeListDCBM);
        resTypeListCB.setBounds(464, 54, 133, 24);
        add(resTypeListCB);
        */
        //-----------------------------preViewTypeCB-------------------------------------------------------
        preViewTypeCB = new JComboBox();
        for(int i = 0; i < 4; ++i){
            for(int j = 0; j < 3; ++j){
                preViewTypeCB.addItem("" + i + j);
            }
        }
        preViewTypeCB.setBounds(464, 95, 133, 24);
        add(preViewTypeCB);
        //------------------------------passableOCB------------------------------------------------------
        passableOCB = new JComboBox();
        passableOCB.addItem("true");
        passableOCB.addItem("false");
        passableOCB.setBounds(177, 176, 125, 22);
        add(passableOCB);
        //-----------------------------passableICB-------------------------------------------------------
        passableICB = new JComboBox();
        passableICB.addItem("true");
        passableICB.addItem("false");
        passableICB.setBounds(472, 176, 125, 22);
        add(passableICB);
        //-------------------------------------buffListCB-----------------------------------------------
        buffListCB = new JComboBox(buffListDCBM);
        buffListCB.setBounds(18, 365, 119, 22);
        add(buffListCB);
        //---------------------------------resNameTF---------------------------------------------------
        resNameTF = new JTextField();
        resNameTF.setBounds(464, 54, 133, 23);
        add(resNameTF);
        //-------------------------------------extraPTF-----------------------------------------------
        extraPTF = new JTextField();
        extraPTF.setBounds(143, 365, 226, 22);
        add(extraPTF);
        //-----------------------------------buffListT-------------------------------------------------
        buffListT = new JTable(buffListDTM);
        buffListT.setBounds(18, 211, 579, 148);
        JScrollPane jsp = new JScrollPane(buffListT);
        jsp.setBounds(18, 211, 579, 148);
        add(jsp);
        tableRefresh();
    }

    public void initAction(){
        openPicResB.addActionListener((ActionEvent e)->{
            try {
                JFileChooser jfc = new JFileChooser(new File("./resource/maps"));
                int resoult = jfc.showOpenDialog(TerrainEditor.this);
                if(resoult == jfc.APPROVE_OPTION){
                    File configFile = jfc.getSelectedFile();
                    openRes(configFile.toString());
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        openTConfB.addActionListener((ActionEvent e)->{
            try {
                JFileChooser jfc = new JFileChooser(new File("./configs/terrain_config"));
                int resoult = jfc.showOpenDialog(TerrainEditor.this);
                if(resoult == jfc.APPROVE_OPTION){
                    File configFile = jfc.getSelectedFile();
                    openTConf(configFile.toString());
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        preViewTypeCB.addActionListener((ActionEvent e)->{
            new Thread(()->{
                PreviewShow();
            }).start();
        });

        addBuffB.addActionListener((ActionEvent e)->{
            addBuff();
        });
        deleteChoiceB.addActionListener((ActionEvent e)->{
            deleteChoice();
        });
        saveB.addActionListener((ActionEvent e)->{
            boolean isExist = new File(getSavePath()).exists();
            if(isExist){
                int res = JOptionPane.showConfirmDialog(null, "文件已经存在，是否替换？.", "警告", JOptionPane.YES_NO_OPTION);// 显示一个confirm的窗口
                if(res == JOptionPane.NO_OPTION){
                    JOptionPane.showConfirmDialog(null, "放弃保存当前设置.", "信息", JOptionPane.YES_OPTION);// 显示一个confirm的窗口
                    return;
                }
            }
            try{
                save(getSavePath());
            }catch (Exception e1){
                e1.printStackTrace();
                JOptionPane.showConfirmDialog(null, "保存过程中发生了异常，异常信息为：" + e1.toString(), "错误", JOptionPane.YES_OPTION);// 显示一个confirm的窗口
            }
            JOptionPane.showConfirmDialog(null, "当前单位的配置信息保存成功.", "成功", JOptionPane.YES_OPTION);// 显示一个confirm的窗口
        });
    }


    /**
     * this method want read a xml (PicRes's config) about terrain.
     * @param path
     * @throws IOException
     */
    public void openRes(String path) throws Exception {
        File f = new File(".");
        String basePath = new File("").getAbsolutePath().toString();
        if(path.startsWith(basePath)){
            resPath = path.substring(basePath.length()+1);
        }else {
            resPath = path;
        }
        resNameTF.setText(getResName(new File(resPath).getName()));
        save(getTempPath());
        openTConf(getTempPath());
    }

    public void initBuffs() throws Exception{
        ConfigInf ci = new EasyConfig("configs/same_pairs.xml");
        ci.open("Buffs");
        ConfigInf[] cis = ci.listConfigs();
        for(int i = 0; i < cis.length; ++i){
            String name = ci.getValue("name");
            buffListDCBM.addElement(name);
        }
    }

    public void openTConf(String path) throws Exception {
        resNameTF.setText(getResName(new File(path).getName()));
        ConfigInf ci =  new EasyConfig(path);
        resPath = ci.getConfig("ResFile").getValue();
        String walkable = ci.getConfig("Walkable").getValue();
        JSONObject jo = JSONObject.fromObject(walkable);
        passableICB.setSelectedIndex(jo.getString("WalkableI").toLowerCase().equals("true") ? 0 : 1);
        passableOCB.setSelectedIndex(jo.getString("WalkableO").toLowerCase().equals("true") ? 0 : 1);
        ci.open("Buffs");
        ConfigInf cis[] = ci.listConfigs();
        buffListD.clear();
        if(cis != null) {
            for (int i = 0; i < cis.length; ++i) {
                String buff[] = cis[i].getValue("BuffName").split("\\?");
                buffListD.add(new Pair<>(buff[0], buff[1]));
                buffListDCBM.removeElement(buff[0]);
            }
        }
        adapter = new AGSAdapter();
        XmlAnalysisFactory.xaf.Init(adapter.getResList(), resPath);
        new Thread(()-> {PreviewShow();}).start();
    }

    protected void PreviewShow(){
        preViewP.close();
        preViewP.setAgsAdapter(adapter, "Terrain" + preViewTypeCB.getSelectedItem());
        preViewP.setShowRect(new Rectangle(0, 0, preViewP.getWidth(), preViewP.getHeight()));
        preViewP.showAgsAdapter();
    }

    protected String getResName(String inp){
        return inp.split("\\.")[0];
    }


    public void save(String path) throws IOException {
        if(resPath == null || resPath.equals(""))return;
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Root>\n");
            fileWriter.write("\t<ResFile>" + resPath + "</ResFile>\n");
            fileWriter.write("\t<Buffs>\n ");
            for (int i = 0; i < buffListD.size(); ++i) {
                Pair<String, String> p = buffListD.get(i);
                fileWriter.write("\t\t<Buff>" + p.getKey() + "?" + p.getValue() + "</Buff>\n");
            }
            fileWriter.write("\t</Buffs>\n");
            JSONObject jo = new JSONObject();
            jo.put("Type", "WalkableOI");
            jo.put("WalkableO", passableOCB.getSelectedItem());
            jo.put("WalkableI", passableICB.getSelectedItem());
            fileWriter.write("\t<Walkable>" + jo.toString() + "</Walkable>\n");
            fileWriter.write("</Root>");
            fileWriter.flush();
            fileWriter.close();
    }

    protected String getSavePath(){
        return "configs/terrain_config/" + resNameTF.getText() + "T.xml";
    }

    protected String getTempPath(){
        return "temp/terrain_config/" + resNameTF.getText() + "T.xml";
    }

    public void deleteChoice(){
        int row = buffListT.getSelectedRow();
        if(row == -1)return;
        String buff = (String) buffListDTM.getValueAt(row, 0);
        buffListDCBM.addElement(buff);
        buffListDTM.removeRow(row);
    }

    protected void addBuff(){
        int idx = buffListCB.getSelectedIndex();
        if(idx == -1)return;
        String buff = (String) buffListCB.getItemAt(idx);
        buffListD.add(new Pair<>(buff, extraPTF.getText()));
        buffListDCBM.removeElementAt(idx);
        tableRefresh();
    }

    protected void tableRefresh(){
        synchronized (buffListD){
            String[][] res = new String[buffListD.size()][2];
            for(int i = 0; i < buffListD.size(); ++i){
                res[i][0] = buffListD.get(i).getKey();
                res[i][0] = buffListD.get(i).getValue();
            }
            buffListDTM.setDataVector(res, tableTitle);
        }
    }


    public static void main(String[] args) {
        TerrainEditor te = new TerrainEditor();
        te.setVisible(true);
    }

}
