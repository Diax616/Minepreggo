package dev.dixmk.minepreggo.client.renderer.entity.layer.player;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.client.model.entity.player.AbstractBoobsModel;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedBoobsModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PredefinedBoobsLayer extends AbstractBoobsLayer {

	private final PredefinedBoobsModel boobsModel;
	    
    public PredefinedBoobsLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent, EntityModelSet modelSet) {
        super(parent);
        this.boobsModel = new PredefinedBoobsModel(modelSet.bakeLayer(PredefinedBoobsModel.LAYER_LOCATION));
    }

	@Override
	protected @NonNull AbstractBoobsModel getBoobsModel() {
		return boobsModel;
	}
}
