package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.Optional;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.joml.Vector3i;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.network.capability.PregnancySystemImpl;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
	protected Optional<PregnancySystemImpl.DataGUI> dataGUI;
	protected boolean valid;
	
	protected PlayerMedicalCheckUpMenu(MenuType<?> p_38851_, int id, Inventory inv) {
		super(p_38851_, id, inv);
	}

	@Override
	public String getName() {
		return this.isValid() ? this.source.get().getDisplayName().getString() : null;
	}

	@Override
	public PregnancyStage getCurrentStage() {
		return this.isValid() ? this.dataGUI.get().currentPregnancyStage : null;
	}

	@Override
	public int getPregnancyHealth() {
		return this.isValid() ? this.dataGUI.get().pregnancyHealth : -1;
	}

	@Override
	public int getNumberOfChildren() {
		return this.isValid() ? this.dataGUI.get().babies.values().stream().mapToInt(Integer::intValue).sum() : -1;
	}

	@Override
	public Baby getBabyType() {
		return Baby.HUMAN;
	}

	@Override
	public int getDaysToGiveBirth() {
		return this.isValid() ? this.dataGUI.get().daysToGiveBirth : -1;
	}

	@Override
	public int getDaysPassed() {
		return this.isValid() ? this.dataGUI.get().daysPassed : -1;
	}

	@Override
	public int getDaysToNextStage() {
		return this.isValid() ? this.dataGUI.get().daysByStage - this.dataGUI.get().daysPassed : -1;
	}
	
	@Override
	public boolean isValid() {
		return this.valid;
	}

	public static class DoctorVillager extends PlayerMedicalCheckUpMenu<Villager> {	
		public DoctorVillager(int id, Inventory inv, FriendlyByteBuf extraData) {
			super(MinepreggoModMenus.PLAYER_MEDICAL_CHECKUP_BY_VILLAGER_MENU.get(), id, inv);
		
			Player s = null;	
			Villager t = null;
			PregnancySystemImpl.DataGUI d = null;
			Vector3i p = null;
			
			if (extraData != null) {
				var pos = extraData.readBlockPos();			
				p = new Vector3i(pos.getX(), pos.getY(), pos.getZ());
				
				if (level.getEntity(extraData.readVarInt()) instanceof Player pregnantPlayer)  {
					s = pregnantPlayer;					
				}	
			
				if (level.getEntity(extraData.readVarInt()) instanceof Villager villager) {
					t = villager;
				}			
								
				d = new PregnancySystemImpl.DataGUI(extraData.readNbt());		
			}
			

			
			this.target = Optional.ofNullable(t);
			this.source = Optional.ofNullable(s);
			this.dataGUI = Optional.ofNullable(d);
			this.pos = Optional.ofNullable(p);
			this.numOfEmerald = 30;
			
			this.valid = this.source.isPresent() && this.target.isPresent() && this.dataGUI.isPresent();
		
			if (!isValid()) {
				MinepreggoMod.LOGGER.error("Target={} or Source={} or Capability={} was null",
						this.source.isPresent(), this.target.isPresent(), this.dataGUI.isPresent());
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
		
		public static void showMedicalCheckUpMenu(@NonNull ServerPlayer serverPlayer, @NonNull Villager villager, PregnancySystemImpl.DataGUI data) {					
			final var bpos = BlockPos.containing(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());
			final var playerId = serverPlayer.getId();
			final var villagerId = villager.getId();
			final var nbt = (CompoundTag) data.serializeNBT();
			
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
	                packetBuffer.writeNbt(nbt);
	                return new DoctorVillager(id, inventory, packetBuffer);
	            }
	        }, buf -> {
				buf.writeBlockPos(bpos);
				buf.writeVarInt(playerId);
				buf.writeVarInt(villagerId);
				buf.writeNbt(nbt);
			});	  				
		}
	}
	
	public static class Illager extends PlayerMedicalCheckUpMenu<ScientificIllager> {	
		public Illager(int id, Inventory inv, FriendlyByteBuf extraData) {
			super(MinepreggoModMenus.PLAYER_MEDICAL_CHECKUP_BY_ILLAGER_MENU.get(), id, inv);
		
			Player s = null;	
			ScientificIllager t = null;
			PregnancySystemImpl.DataGUI d = null;
			Vector3i p = null;
			
			if (extraData != null) {
				var pos = extraData.readBlockPos();			
				p = new Vector3i(pos.getX(), pos.getY(), pos.getZ());		
				
				if (level.getEntity(extraData.readVarInt()) instanceof Player pregnantPlayer)  {
					s = pregnantPlayer;					
				}	
			
				if (level.getEntity(extraData.readVarInt()) instanceof ScientificIllager scientificIllager) {
					t = scientificIllager;
				}			
								
				d = new PregnancySystemImpl.DataGUI(extraData.readNbt());		
			}
			
			this.target = Optional.ofNullable(t);
			this.source = Optional.ofNullable(s);
			this.dataGUI = Optional.ofNullable(d);
			this.pos = Optional.ofNullable(p);
			this.numOfEmerald = 30;
			
			this.valid = this.source.isPresent() && this.target.isPresent() && this.dataGUI.isPresent();
		
			if (!isValid()) {
				MinepreggoMod.LOGGER.error("Target={} or Source={} or Capability={} was null",
						this.source.isPresent(), this.target.isPresent(), this.dataGUI.isPresent());
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
		
		public static void showMedicalCheckUpMenu(@NonNull ServerPlayer serverPlayer, @NonNull ScientificIllager scientificIllager, PregnancySystemImpl.DataGUI data) {						
			final var bpos = BlockPos.containing(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());
			final var playerId = serverPlayer.getId();
			final var scientificIllagerId = scientificIllager.getId();
			final var nbt = (CompoundTag) data.serializeNBT();
			
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
	                packetBuffer.writeNbt(nbt);
	                return new Illager(id, inventory, packetBuffer);
	            }
	        }, buf -> {
				buf.writeBlockPos(bpos);
				buf.writeVarInt(playerId);
				buf.writeVarInt(scientificIllagerId);
				buf.writeNbt(nbt);
			});	  				
		}
	}
}
