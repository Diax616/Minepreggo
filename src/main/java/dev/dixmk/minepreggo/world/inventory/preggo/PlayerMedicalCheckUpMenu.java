package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.Optional;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.joml.Vector3i;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemImpl;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.network.NetworkHooks;

public abstract class PlayerMedicalCheckUpMenu<T extends Mob> extends AbstractMedicalCheckUpMenu<Player, T> {	
	protected Optional<PlayerPregnancySystemImpl.ScreenData> data;
	protected int totalNumOfBabies = -1;
	protected int daysToPassedNextPhase = -1;
	protected boolean valid;
	
	protected PlayerMedicalCheckUpMenu(MenuType<?> p_38851_, int id, Inventory inv, FriendlyByteBuf extraData) {
		super(p_38851_, id, inv);
		Vector3i p = null;
		if (extraData != null) {
			var pos = extraData.readBlockPos();			
			p = new Vector3i(pos.getX(), pos.getY(), pos.getZ());
		}
		this.pos = Optional.ofNullable(p);
	}
	
	private boolean readScreenData(FriendlyByteBuf extraData) {
		if (extraData != null) {
			data = Optional.ofNullable(PlayerPregnancySystemImpl.ScreenData.decode(extraData));		
			if (data.isPresent()) {
				totalNumOfBabies = data.get().babiesInsideWomb().values().stream().mapToInt(Integer::intValue).sum();
				daysToPassedNextPhase = data.get().daysByCurrentPhase() - data.get().daysPassed();
				return true;
			}
		}
		else {
			data = Optional.empty();
		}
		return false;
	}

	@Override																																																																																																						
	public String getName() {
		return this.isValid() ? this.source.get().getDisplayName().getString() : "null";
	}

	@Override
	public PregnancyPhase getCurrentPregnancyPhase() {
		return this.isValid() ? this.data.get().currentPregnancyPhase() : PregnancyPhase.P0;
	}
	
	@Override
	public PregnancyPhase getLastPregnancyPhase() {
		return this.isValid() ? this.data.get().lastPregnancyPhase() : PregnancyPhase.P0;
	}
	
	@Override
	public int getPregnancyHealth() {
		return this.isValid() ? this.data.get().pregnancyHealth() : -1;
	}

	@Override
	public int getTotalNumOfBabies() {
		return this.totalNumOfBabies;
	}

	@Override
	public Baby getTypeOfBaby() {
		return Baby.HUMAN;
	}

	@Override
	public int getDaysToGiveBirth() {
		return this.isValid() ? this.data.get().daysToGiveBirth() : -1;
	}

	@Override
	public int getDaysPassed() {
		return this.isValid() ? this.data.get().daysPassed() : -1;
	}

	@Override
	public int getDaysToAdvanceNextPhase() {
		return daysToPassedNextPhase;
	}
	
	@Override
	public boolean isValid() {
		return this.valid;
	}

	
	
	
	public static class DoctorVillager extends PlayerMedicalCheckUpMenu<Villager> {	
		public DoctorVillager(int id, Inventory inv, FriendlyByteBuf extraData) {
			super(MinepreggoModMenus.PLAYER_MEDICAL_CHECKUP_BY_VILLAGER_MENU.get(), id, inv, extraData);

			final boolean result = super.readScreenData(extraData);
			
			Player s = null;	
			Villager t = null;
			
			if (extraData != null) {
				if (level.getEntity(extraData.readVarInt()) instanceof Player pregnantPlayer)  {
					s = pregnantPlayer;					
				}	
			
				if (level.getEntity(extraData.readVarInt()) instanceof Villager villager) {
					t = villager;
				}									
			}
	
			this.target = Optional.ofNullable(t);
			this.source = Optional.ofNullable(s);
			this.numOfEmerald = 30;
			
			this.valid = this.source.isPresent() && this.target.isPresent() && result;
		
			if (!isValid()) {
				MinepreggoMod.LOGGER.error("Target={} or Source={} or Capability={} was null",
						this.source.isPresent(), this.target.isPresent(), this.data.isPresent());
			}
		}

		@Override
		protected void onSuccessful() {
			if (this.level.isClientSide && this.pos.isPresent()) {
				this.level.playLocalSound(this.pos.get().x, this.pos.get().y, this.pos.get().z, SoundEvents.VILLAGER_CELEBRATE, SoundSource.AMBIENT, 1, 1, false);
			}
		}

		@Override
		protected void onFailure() {
			if (this.level.isClientSide && this.pos.isPresent()) {
				this.level.playLocalSound(this.pos.get().x, this.pos.get().y, this.pos.get().z, SoundEvents.VILLAGER_NO, SoundSource.AMBIENT, 1, 1, false);
			}
		}
		
		public static void showMedicalCheckUpMenu(@NonNull ServerPlayer serverPlayer, @NonNull Villager villager, PlayerPregnancySystemImpl.ScreenData data) {					
			final var bpos = BlockPos.containing(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());
			final var playerId = serverPlayer.getId();
			final var villagerId = villager.getId();
			
			NetworkHooks.openScreen(serverPlayer,new MenuProvider() {
	            @Override
	            public Component getDisplayName() {
	                return Component.literal("CreeperGirlMainGUI");
	            }

	            @Override
	            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
	                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());    
	                packetBuffer.writeBlockPos(bpos);
	                packetBuffer.writeVarInt(playerId);
	                packetBuffer.writeVarInt(villagerId);
	                data.encode(packetBuffer);
	                return new DoctorVillager(id, inventory, packetBuffer);
	            }
	        }, buf -> {
				buf.writeBlockPos(bpos);
				buf.writeVarInt(playerId);
				buf.writeVarInt(villagerId);
                data.encode(buf);
			});	  				
		}
	}
	
	public static class Illager extends PlayerMedicalCheckUpMenu<ScientificIllager> {	
		public Illager(int id, Inventory inv, FriendlyByteBuf extraData) {
			super(MinepreggoModMenus.PLAYER_MEDICAL_CHECKUP_BY_ILLAGER_MENU.get(), id, inv, extraData);
		
			final var result = super.readScreenData(extraData);
			
			Player s = null;	
			ScientificIllager t = null;
			
			if (extraData != null) {
				if (level.getEntity(extraData.readVarInt()) instanceof Player pregnantPlayer)  {
					s = pregnantPlayer;					
				}	
			
				if (level.getEntity(extraData.readVarInt()) instanceof ScientificIllager scientificIllager) {
					t = scientificIllager;
				}										
			}
			
			this.target = Optional.ofNullable(t);
			this.source = Optional.ofNullable(s);
			this.numOfEmerald = 30;
			
			this.valid = this.source.isPresent() && this.target.isPresent() && result;
		
			if (!isValid()) {
				MinepreggoMod.LOGGER.error("Target={} or Source={} or Capability={} was null",
						this.source.isPresent(), this.target.isPresent(), this.data.isPresent());
			}
		}

		@Override
		protected void onSuccessful() {
			if (this.level.isClientSide && this.pos.isPresent()) {
				this.level.playLocalSound(this.pos.get().x, this.pos.get().y, this.pos.get().z, SoundEvents.PILLAGER_CELEBRATE, SoundSource.AMBIENT, 1, 1, false);
			}
		}

		@Override
		protected void onFailure() {
			if (this.level.isClientSide && this.pos.isPresent()) {
				this.level.playLocalSound(this.pos.get().x, this.pos.get().y, this.pos.get().z, SoundEvents.PILLAGER_AMBIENT, SoundSource.AMBIENT, 1, 1, false);
			}
		}
		
		public static void showMedicalCheckUpMenu(@NonNull ServerPlayer serverPlayer, @NonNull ScientificIllager scientificIllager, PlayerPregnancySystemImpl.ScreenData data) {						
			final var bpos = BlockPos.containing(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());
			final var playerId = serverPlayer.getId();
			final var scientificIllagerId = scientificIllager.getId();
			
			NetworkHooks.openScreen(serverPlayer,new MenuProvider() {
	            @Override
	            public Component getDisplayName() {
	                return Component.literal("CreeperGirlMainGUI");
	            }

	            @Override
	            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
	                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
	                packetBuffer.writeBlockPos(bpos);
	                packetBuffer.writeVarInt(playerId);
	                packetBuffer.writeVarInt(scientificIllagerId);
	                data.encode(packetBuffer);
	                return new Illager(id, inventory, packetBuffer);
	            }
	        }, buf -> {
				buf.writeBlockPos(bpos);
				buf.writeVarInt(playerId);
				buf.writeVarInt(scientificIllagerId);
				data.encode(buf);
			});	  				
		}
	}
}
