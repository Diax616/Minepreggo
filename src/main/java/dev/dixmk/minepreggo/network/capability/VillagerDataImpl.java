package dev.dixmk.minepreggo.network.capability;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.dixmk.minepreggo.world.pregnancy.IObstetrician;
import dev.dixmk.minepreggo.world.pregnancy.PrenatalCheckupCostHolder;
import dev.dixmk.minepreggo.world.pregnancy.PrenatalCheckupCostHolder.PrenatalCheckupCost;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class VillagerDataImpl implements IVillagerData, IObstetrician, INBTSerializable<Tag> {
    private PrenatalCheckupCostHolder prenatalCheckUpCosts = new PrenatalCheckupCostHolder(3, 8);    
    private Set<PlayerInfo> players = new HashSet<>();
        
    @Override
    public PrenatalCheckupCost getPrenatalCheckupCosts() {
    	return this.prenatalCheckUpCosts.getValue();
    }

    public boolean isPrenatalCheckupCostsInitialized() {
		return this.prenatalCheckUpCosts.isInitialized();
	} 
    
    /**
     * Checks if the villager knows the player is pregnant from them. This will only return a value if the player has had sex with the villager before, otherwise it will return an empty Optional.
     * 
     * @param playerId The UUID of the player to check.
     * @return An Optional containing whether the villager knows the player is pregnant from them, or an empty Optional if the player has never had sex with the villager.
     **/
	@Override
	public Optional<Boolean> doesVillagerKnowsPlayerIsPregnantFromThem(UUID playerId) {
		return players.stream()
				.filter(info -> info.playerId.equals(playerId))
				.map(info -> info.villagerKnowsThatPlayerIsPregnantFromThem)
				.findFirst();	
	}

	@Override
	public Set<UUID> getPlayersThatHadSexWithVillager() {
		return players.stream()
				.filter(info -> info.hadSexWithVillager)
				.map(info -> info.playerId)
				.collect(Collectors.toUnmodifiableSet());
	}

	@Override
	public Set<UUID> getPlayersThatArePregnantFromVillager() {
		return players.stream()
				.filter(info -> info.isPregnantFromVillager)
				.map(info -> info.playerId)
				.collect(Collectors.toUnmodifiableSet());
	}

	/**
	 * Adds a player to the set of players
	 * @param playerId The UUID of the player to add.
	 * @return true if the player was added, false if the player was already in the set and their information was updated to reflect that they had sex with the villager.
	 */
	@Override
	public boolean addPlayerThatHadSexWithVillager(UUID playerId) {
		Optional<PlayerInfo> existingInfo = players.stream()
				.filter(info -> info.playerId.equals(playerId))
				.findFirst();
		
		if (existingInfo.isPresent()) {
			PlayerInfo info = existingInfo.get();
			info.hadSexWithVillager = true;
			return false;
		} else {
			PlayerInfo newInfo = new PlayerInfo(playerId);
			newInfo.hadSexWithVillager = true;
			players.add(newInfo);
			return true;
		}
	}

	/**
	 * Adds a player to the set of players that are pregnant from the villager. This will also mark the player as having had sex with the villager if they are not already marked as such.
	 * @param playerId The UUID of the player to add.
	 * @return true if the player was added, false if the player was already in the set and their information was updated to reflect that they are pregnant from the villager.
	 */
	@Override
	public boolean addPlayerThatIsPregnantFromVillager(UUID playerId) {
		Optional<PlayerInfo> existingInfo = players.stream()
				.filter(info -> info.playerId.equals(playerId))
				.findFirst();
		
		if (existingInfo.isPresent()) {
			PlayerInfo info = existingInfo.get();
			info.isPregnantFromVillager = true;
			return false;
		} else {
			PlayerInfo newInfo = new PlayerInfo(playerId);
			newInfo.isPregnantFromVillager = true;
			players.add(newInfo);
			return true;
		}
	}
	
	@Override
	public Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();	
		if (!players.isEmpty()) {
			ListTag playerList = new ListTag();
			for (PlayerInfo info : players) {
				playerList.add(info.serializeNBT());
			}
			nbt.put("players", playerList);
		}
		if (prenatalCheckUpCosts.isInitialized()) {		
			nbt.put("prenatalCheckUpCosts", prenatalCheckUpCosts.serializeNBT());
		}
		return nbt;
	}
	
	@Override
	public Optional<Boolean> doesVillagerKnowPlayerGivenBirthToChildFromThem(UUID playerId) {
		return players.stream()
				.filter(info -> info.playerId.equals(playerId))
				.map(info -> info.villagerKnowsThatPlayerGivenBirthToChildFromThem)
				.findFirst();
	}
	
	@Override
	public boolean villagerKnowsPlayerIsPregnantFromThem(UUID playerId) {
		Optional<PlayerInfo> existingInfo = players.stream()
				.filter(info -> info.playerId.equals(playerId))
				.findFirst();	
		if (existingInfo.isPresent()) {
			PlayerInfo info = existingInfo.get();
			info.villagerKnowsThatPlayerIsPregnantFromThem = true;
			return true;
		} 	
		return false;
	}
	
	@Override
	public boolean villagerKnowsPlayerGivenBirthToChildFromThem(UUID playerId) {
		Optional<PlayerInfo> existingInfo = players.stream()
				.filter(info -> info.playerId.equals(playerId))
				.findFirst();	
		if (existingInfo.isPresent()) {
			PlayerInfo info = existingInfo.get();
			info.villagerKnowsThatPlayerGivenBirthToChildFromThem = true;
			return true;
		} 	
		return false;
	}
	
	@Override
	public void deserializeNBT(Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;	
		if (nbt.contains("players", Tag.TAG_LIST)) {
			ListTag playerList = nbt.getList("players", Tag.TAG_COMPOUND);
			for (int i = 0; i < playerList.size(); i++) {
				CompoundTag playerNbt = playerList.getCompound(i);
				PlayerInfo info = new PlayerInfo(playerNbt.getUUID("playerId"));
				info.deserializeNBT(playerNbt);
				players.add(info);
			}
		}
		if (nbt.contains("prenatalCheckUpCosts", Tag.TAG_COMPOUND)) {
			prenatalCheckUpCosts.deserializeNBT(nbt.getCompound("prenatalCheckUpCosts"));
		}
	}
	
	private class PlayerInfo implements INBTSerializable<CompoundTag> {
		private UUID playerId;
		private boolean hadSexWithVillager = false;
		private boolean isPregnantFromVillager = false;
		private boolean villagerKnowsThatPlayerIsPregnantFromThem = false;
		private boolean villagerKnowsThatPlayerGivenBirthToChildFromThem = false;
		
		private PlayerInfo(UUID playerId) {
			this.playerId = playerId;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			PlayerInfo other = (PlayerInfo) obj;
			return playerId.equals(other.playerId);
		}
			
		@Override
		public int hashCode() {
			return playerId.hashCode();
		}

		@Override
		public CompoundTag serializeNBT() {
			CompoundTag nbt = new CompoundTag();
			if (isPrenatalCheckupCostsInitialized()) {
				nbt.put("prenatalCheckUpCosts", prenatalCheckUpCosts.serializeNBT());
			}			
			nbt.putUUID("playerId", playerId);
			nbt.putBoolean("hadSexWithVillager", hadSexWithVillager);
			nbt.putBoolean("isPregnantFromVillager", isPregnantFromVillager);
			nbt.putBoolean("villagerKnowsThatPlayerIsPregnantFromThem", villagerKnowsThatPlayerIsPregnantFromThem);
			nbt.putBoolean("villagerKnowsThatPlayerGivenBirthToChildFromThem", villagerKnowsThatPlayerGivenBirthToChildFromThem);
			return nbt;
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
			if (nbt.contains("prenatalCheckUpCosts", Tag.TAG_COMPOUND)) {
				prenatalCheckUpCosts.deserializeNBT(nbt.getCompound("prenatalCheckUpCosts"));
			}	
			this.playerId = nbt.getUUID("playerId");					
			this.hadSexWithVillager = nbt.getBoolean("hadSexWithVillager");
			this.isPregnantFromVillager = nbt.getBoolean("isPregnantFromVillager");
			this.villagerKnowsThatPlayerIsPregnantFromThem = nbt.getBoolean("villagerKnowsThatPlayerIsPregnantFromThem");
			this.villagerKnowsThatPlayerGivenBirthToChildFromThem = nbt.getBoolean("villagerKnowsThatPlayerGivenBirthToChildFromThem");
		}
	}
}
