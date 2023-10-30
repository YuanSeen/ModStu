package io.github.YuanSeen.ling2li4;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerLing2li4Provider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerLing2li4> PLAYER_LING2LI4 = CapabilityManager.get(new CapabilityToken<PlayerLing2li4>() {
    });

    private PlayerLing2li4 ling2li4 = null;
    private LazyOptional<PlayerLing2li4> ling2li4Handle = LazyOptional.of(this::createPlayerLing2li4);

    private PlayerLing2li4 createPlayerLing2li4(){
        if (this.ling2li4 == null){
            this.ling2li4 = new PlayerLing2li4();
        }
        return  ling2li4;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_LING2LI4){
            return ling2li4Handle.cast();
        }else {
            return LazyOptional.empty();
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerLing2li4().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerLing2li4().loadNBTData(nbt);

    }
}
