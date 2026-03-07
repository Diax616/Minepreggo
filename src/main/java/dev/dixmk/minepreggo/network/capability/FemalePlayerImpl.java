package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.s2c.ResetPregnancyS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncFemalePlayerDataS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPlayerLactationS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyType;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class FemalePlayerImpl extends FemaleEntityImpl implements IFemalePlayer {

	private PlayerPregnancyDataHolder pregnancySystemHolder = new PlayerPregnancyDataHolder();
	
	private final Object2IntMap<Species> attacks = new Object2IntOpenHashMap<>(3);
	
	public boolean isPregnancyDataInitialized() {
		return pregnancySystemHolder.isInitialized();
	}
	
	@Override
	public PlayerPregnancyDataImpl getPregnancyData() {
		return pregnancySystemHolder.getValue();
	}
	
	@Override
	public boolean tryImpregnate(PregnancyType pregnancyType, @Nonnegative int fertilizedEggs, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father) {
		if (super.tryImpregnate(pregnancyType, fertilizedEggs, father)) {
			attacks.clear();
			pregnancySystemHolder.reset();
			return true;
		}
		return false;
	}
	
	public boolean tryImpregnateByHurting(Species species) {
		if (species == Species.HUMAN || species == Species.VILLAGER) {
			return false;
		}
		if (!isPregnant()) {
			final var count = attacks.merge(species, 1, Integer::sum);
			switch (species) {
			case ZOMBIE: {	
				return count >= 8; 
			}
			case CREEPER: {	
				return count >= 2; 
			}
			case ENDER: {	
				return count >= 10; 
			}
			default:
				return false;
			}
		}
		return false;
	}
	
	public void clearMobAttacks() {
		attacks.clear();
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = super.serializeNBT();	
		if (pregnancySystemHolder.isInitialized()) {
			nbt.put("PlayerPregnancySystem", pregnancySystemHolder.serializeNBT());
		}		
		if (!attacks.isEmpty()) {
			ListTag list = new ListTag();
			attacks.forEach((species, count) -> {
				CompoundTag wrapper = new CompoundTag();
				wrapper.putString("species", species.name());
				wrapper.putInt("count", count);
				list.add(wrapper);
			});
			nbt.put("attacksCount", list);
		}

		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		super.deserializeNBT(nbt);		
		if (nbt.contains("PlayerPregnancySystem", Tag.TAG_COMPOUND)) {
			pregnancySystemHolder.deserializeNBT(nbt.getCompound("PlayerPregnancySystem"));
		}	
		if (nbt.contains("attacksCount", Tag.TAG_LIST)) {
			attacks.clear();
			ListTag list = nbt.getList("attacksCount", Tag.TAG_COMPOUND);
			for (final var wrapper : list) {
				CompoundTag data = (CompoundTag) wrapper;
				attacks.put(Species.valueOf(data.getString("species")), data.getInt("count"));
			}
		}
	}
	
	public void resetPregnancy() {
		pregnancySystemHolder = new PlayerPregnancyDataHolder();
	}
	
	public void resetPregnancyOnClient(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer),
				new ResetPregnancyS2CPacket(serverPlayer.getUUID()));
	}
	
	public ClientData createClientData() {
		return new ClientData(this.pregnant, this.postPregnancyData.orElse(null), this.fertility);
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer),
				new SyncFemalePlayerDataS2CPacket(serverPlayer.getUUID(), createClientData()));
	}
	
	public void syncLactation(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer),
				new SyncPlayerLactationS2CPacket(serverPlayer.getUUID(), this.postPregnancyData.map(PostPregnancyData::getPostPartumLactation).orElse(0)));
	}

	// TODO: It does not validate if the data is valid for player's current state, it could cause inconsistencies.
	public void update(ClientData data) {		   
		if (data.fertility() >= 0.0f && data.fertility() <= 1.0f) {
			this.fertility = data.fertility;
	    }

		this.pregnant = data.pregnant;
		this.postPregnancyData = Optional.ofNullable(data.postPregnancy);
	}
	
	public static record ClientData(boolean pregnant, @Nullable PostPregnancyData postPregnancy, float fertility) {}
}
