package com.suremoon.suremoon.coding_tools;

import com.suremoon.game.kernel.initer.InitListItf;
import com.suremoon.game.kernel.initer.Progress;

import java.io.FileWriter;

public class ProductConst {
    public static void main(String[] args) throws Exception {
        new Creator("unit").product();
        new Creator("effect").product();
    }

    static class Creator {
        String name;

        public Creator(String name) {
            this.name = name;
        }

        public void product() throws Exception {
            FileWriter writer = new FileWriter("./src/com/suremoon/suremoon/consts/" + name + "s.java");
            writer.write("package com.suremoon.suremoon.consts;\n" +
                    "import com.suremoon.game.door.tools.IDManager;\n" +
                    "\n" +
                    "public class " + name + "s {\n");
            new InitListItf() {
                @Override
                public void init(Progress progress) throws Exception {
                    loadList("./game_root/configs/" + name + "_config", ".xml", name -> {
                        writer.write("\tpublic static final int " + name + " = IDManager.getID(\"" + name + "\");\n"
                        );
                    }, progress);
                }
            }.init(new Progress());
            writer.write("\n}");
            writer.flush();
            writer.close();
        }
    }
}
