package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderGirl;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllEnderGirlModel extends AbstractMonsterEnderGirlModel<IllEnderGirl>{
	public IllEnderGirlModel(ModelPart root) {
		super(root);
	}
}
