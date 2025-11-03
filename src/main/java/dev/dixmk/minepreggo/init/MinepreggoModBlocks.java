package dev.dixmk.minepreggo.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.block.ChilipepperBlock;
import dev.dixmk.minepreggo.block.CucumberBlock;
import dev.dixmk.minepreggo.block.MedicalTableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;


public class MinepreggoModBlocks {
	
	private MinepreggoModBlocks() {}
	
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MinepreggoMod.MODID);
	
	public static final RegistryObject<Block> MEDICAL_TABLE = REGISTRY.register("medical_table", MedicalTableBlock::new);
	public static final RegistryObject<Block> CHILIPEPPERS = REGISTRY.register("chili_peppers", () -> new ChilipepperBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
	public static final RegistryObject<Block> CUCUMBERS = REGISTRY.register("cucumbers", () -> new CucumberBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));


}
