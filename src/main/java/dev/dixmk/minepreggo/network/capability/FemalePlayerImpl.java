package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.ResetPregnancyS2CPacket;
import dev.dixmk.minepreggo.network.packet.SyncFemalePlayerDataS2CPacket;
import dev.dixmk.minepreggo.network.packet.SyncPlayerLactationS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancyData;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;

public class FemalePlayerImpl extends FemaleEntityImpl implements IFemalePlayer {

	private PlayerPregnancySystemHolder pregnancySystemHolder = new PlayerPregnancySystemHolder();
	private PlayerPregnancyEffectsHolder pregnancyEffectsHolder = new PlayerPregnancyEffectsHolder();
	
	private final Object2IntMap<Species> attacks = new Object2IntOpenHashMap<>(3);
	
	public boolean isPregnancySystemInitialized() {
		return pregnancySystemHolder.isInitialized();
	}
	
	public boolean isPregnancyEfectsInitialized() {
		return pregnancyEffectsHolder.isInitialized();
	}
	
	@Override
	public PlayerPregnancySystemImpl getPregnancySystem() {
		return pregnancySystemHolder.getValue();
	}
	
	@Override
	public PlayerPregnancyEffectsImpl getPregnancyEffects() {
		return pregnancyEffectsHolder.getValue();
	}
	
	@Override
	public boolean tryImpregnate(@Nonnegative int fertilizedEggs, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father) {
		if (super.tryImpregnate(fertilizedEggs, father)) {
			attacks.clear();
			return true;
		}
		return false;
	}
	
	public boolean tryImpregnateByHurting(Species species) {
		if (species == Species.HUMAN || species == Species.VILLAGER) {
			return false;
		}
		if (!isPregnant()) {
			final var count = attacks.computeInt(species, (key, newCount) -> ++newCount);		
			switch (species) {
			case ZOMBIE: {	
				return count > 30; 
			}
			case CREEPER: {	
				return count > 35; 
			}
			case ENDER: {	
				return count > 40; 
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
		if (pregnancyEffectsHolder.isInitialized()) {
			nbt.put("PlayerPregnancyEffects", pregnancyEffectsHolder.serializeNBT());
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
		if (nbt.contains("PlayerPregnancyEffects", Tag.TAG_COMPOUND)) {
			pregnancyEffectsHolder.deserializeNBT(nbt.getCompound("PlayerPregnancyEffects"));
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
		pregnancySystemHolder = new PlayerPregnancySystemHolder();
		pregnancyEffectsHolder = new PlayerPregnancyEffectsHolder();
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

	@OnlyIn(Dist.CLIENT)
	public void updateFromServer(ClientData data) {
		this.pregnant = data.pregnant;
		this.postPregnancyData = Optional.ofNullable(data.postPregnancy);
		this.fertility = data.fertility;
	}
	
	public static record ClientData(boolean pregnant, @Nullable PostPregnancyData postPregnancy, float fertility) {}
}
