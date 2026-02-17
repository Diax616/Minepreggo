package dev.dixmk.minepreggo.client.renderer.entity.layer.player;

import java.util.List;

import javax.annotation.CheckForNull;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientPlayerHelper {

	private ClientPlayerHelper() {}
	
	private static final ImmutableMap<String, ImmutablePair<ResourceLocation, ResourceLocation>> PREDEFINED_SKINS = ImmutableMap.of(
			"player1", ImmutablePair.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1.png"), MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_extra.png"))		
			);
	
	private static final ImmutableMap<String, ImmutableMap<PregnancyPhase, ImmutablePair<ResourceLocation, ResourceLocation>>> MATERNITY_PREDEFINED_SKINS = ImmutableMap.of(
			"player1", ImmutableMap.of(
					PregnancyPhase.P0, ImmutablePair.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p0.png"), MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p0_extra.png")),
					PregnancyPhase.P1, ImmutablePair.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p1.png"), MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p1_extra.png")),
					PregnancyPhase.P2, ImmutablePair.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p2.png"), MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p2_extra.png")),
					PregnancyPhase.P3, ImmutablePair.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p3.png"), MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p3_extra.png")),
					PregnancyPhase.P4, ImmutablePair.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p4.png"), MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p4_extra.png")),
					PregnancyPhase.P5, ImmutablePair.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p5.png"), MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p5_extra.png")),
					PregnancyPhase.P6, ImmutablePair.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p6.png"), MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p6_extra.png")),
					PregnancyPhase.P7, ImmutablePair.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p7.png"), MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p7_extra.png")),
					PregnancyPhase.P8, ImmutablePair.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p8.png"), MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/predefined/player1/player_1_p8_extra.png"))
			));

	private static final ImmutableMap<Craving, List<ResourceLocation>> CRAVING_ICONS = ImmutableMap.of(
			Craving.SALTY, List.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/salty_pickle.png"),
					MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/french_fries.png")), 
			Craving.SWEET, List.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/chocolate_bar.png"),
					MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/candy_apple.png")), 
			Craving.SOUR, List.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_popsicles.png"),
					MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_cream.png"),
					MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_drop.png")),
			Craving.SPICY, List.of(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/spicy_chicken.png"),
					MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/chili_poppers.png"))
			);
	
	@CheckForNull
	public static ImmutablePair<ResourceLocation, ResourceLocation> getPredefinedPlayerTextures(String name, PregnancyPhase phase) {
		var textures = MATERNITY_PREDEFINED_SKINS.get(name);
		if (textures != null) {
			return textures.get(phase);			
		}	
		return null;
	}

	@CheckForNull
	public static ImmutablePair<ResourceLocation, ResourceLocation> getPredefinedPlayerTextures(String name) {
		return PREDEFINED_SKINS.get(name);
	}
	
	@CheckForNull
	public static List<ResourceLocation> getCravingIcon(Craving craving) {
		return craving != null ? CRAVING_ICONS.get(craving) : null;	
	}
	
    public static boolean isPlayingBirthAnimation(Player player) {
    	var anim = PlayerAnimationManager.getInstance().get(player).getCurrentAnimationName();
        return anim != null && anim.equals("birth");
    }	
}
