package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IVillagerData {
	Optional<Boolean> doesVillagerKnowsPlayerIsPregnantFromThem(UUID playerId);
	Optional<Boolean> doesVillagerKnowPlayerGivenBirthToChildFromThem(UUID playerId);
	Set<UUID> getPlayersThatHadSexWithVillager();
	Set<UUID> getPlayersThatArePregnantFromVillager();

	boolean addPlayerThatHadSexWithVillager(UUID playerId);
	boolean addPlayerThatIsPregnantFromVillager(UUID playerId);
	boolean villagerKnowsPlayerIsPregnantFromThem(UUID playerId);
	boolean villagerKnowsPlayerGivenBirthToChildFromThem(UUID playerId);
}
