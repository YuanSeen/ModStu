package io.github.YuanSeen.ling2li4;

import net.minecraft.nbt.CompoundTag;

public class PlayerLing2li4 {
    private int ling2li4;

    private final int MIN_LING2LI4 = 0;
    private final int MAX_LING2LI4 = 20;

    public int getLing2li4(){
        return this.ling2li4;
    }

    public void addling2li4(int add){
        this.ling2li4 = Math.min(ling2li4+add,MAX_LING2LI4);
    }

    public void subling2li4(int sub){
        this.ling2li4 = Math.max(ling2li4-sub,MIN_LING2LI4);
    }

    public void copyFrom(PlayerLing2li4 sourse){
        this.ling2li4 = sourse.ling2li4;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("ling2li4",this.ling2li4);
    }

    public void loadNBTData(CompoundTag nbt){
        this.ling2li4 = nbt.getInt("ling2li4");
    }


}
