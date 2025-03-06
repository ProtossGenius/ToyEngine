package com.suremoon.suremoon.mods.world.index_0;

/** This file product by auto-code-tools
 * It's path is $(ProjectPath)/cmd/prebuild.go
 * @ToolAuthor: ProtossGenius
 */

import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.mods.WorldModItf;

//@ModName 魔法之门

public class WorldMod implements WorldModItf {
    @Override
    public void Do(WorldItf world) {
		Monsters.Join(world);
    }

    @Override
    public String ModName() {
        return "魔法之门";
    }
}