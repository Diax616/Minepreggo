package dev.dixmk.minepreggo.world.pregnancy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMobData;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class PrenatalCheckupHelper {
	
	private PrenatalCheckupHelper() {}

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	public static PrenatalCheckupHelper.PrenatalRegularCheckUpData createRegularPrenatalCheckUpData(ITamablePregnantPreggoMobData pregnancyData) {
		return new PrenatalCheckupHelper.PrenatalRegularCheckUpData(
				pregnancyData.getCurrentPregnancyPhase(),
				pregnancyData.getLastPregnancyStage(),
				pregnancyData.getPregnancyHealth(),
				pregnancyData.getWomb().getNumOfBabies(),
				pregnancyData.getDaysPassed(),
				PregnancySystemHelper.calculateDaysToNextPhase(pregnancyData),
				pregnancyData.getDaysToGiveBirth()
				);
	}
	
	public static PrenatalCheckupHelper.PrenatalUltrasoundScanData createUltrasoundScanPrenatalCheckUpData(Species motherSpecies, ITamablePregnantPreggoMobData pregnancyData) {
		return new PrenatalCheckupHelper.PrenatalUltrasoundScanData(
				motherSpecies,
				pregnancyData.getWomb()	
				);
	}
	
	
	public static ItemStack createPrenatalCheckUpResult(PrenatalCheckUpInfo info, PrenatalRegularCheckUpData data, String autor) {
		ListTag pages = new ListTag();
		List<Component> components = new ArrayList<>();

		components.add(Component.literal("")
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.1.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.mother", info.motherName))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.price", Integer.toString(info.price)))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.date", info.date.format(DATE_FORMAT)))
	    		);		
		components.add((Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.2.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.2.current_phase", data.currentPregnancyPhase.toString()))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.2.last_phase", data.lastPregnancyPhase.toString()))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.2.health", Integer.toString(data.pregnancyHealth)))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.2.num_of_babies", Integer.toString(data.numOfBabies)))
		    	);
		components.add((Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.3.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.3.days_elapsed", Integer.toString(data.daysPassed)))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.3.days_next_phase", Integer.toString(data.daysToNextPhase)))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.3.days_birth", Integer.toString(data.daysToGiveBirth)))
		    	);	
		
		
		components.forEach(c -> pages.add(StringTag.valueOf(Component.Serializer.toJson(c))));				
	    ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
	    CompoundTag tag = book.getOrCreateTag();
	    tag.put("pages", pages);
	    tag.putString("title", Component.translatable("book.minepreggo.prenatal_checkup.regular.template.title").getString());
	    tag.putString("author", autor); 
		return book;
	}
	
	public static ItemStack createPrenatalCheckUpResult(PrenatalCheckUpInfo info, PrenatalUltrasoundScanData data, String autor) {
		ListTag pages = new ListTag();
		List<Component> components = new ArrayList<>();
		
		components.add(Component.literal("")
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.1.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.mother", info.motherName))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.price", Integer.toString(info.price)))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.date", info.date.format(DATE_FORMAT)))
				.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.1.babies_inside_belly", Integer.toString(data.babiesInsideWomb.getNumOfBabies())))
				.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.1.womb_size_state", data.babiesInsideWomb.isWombOverloaded()
						? Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.1.womb_size_state.overloaded").getString()
						: Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.1.womb_size_state.normal").getString()))
	    		);

		data.babiesInsideWomb().forEach(babyData -> 
			components.add((Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.default.title").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
			    	.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.gender", babyData.gender.toString()))
			    	.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.type_of_species", babyData.typeOfSpecies.toString()))
			    	.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.type_of_creature", babyData.typeOfCreature.toString())))
		);
		

		components.forEach(c -> pages.add(StringTag.valueOf(Component.Serializer.toJson(c))));
		
	    ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
	    CompoundTag tag = book.getOrCreateTag();
	    tag.put("pages", pages);
	    tag.putString("title", Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.title").getString());
	    tag.putString("author", autor); 
		return book;
	}
	
	public static ItemStack createPrenatalCheckUpResult(PrenatalCheckUpInfo info, List<PrenatalPaternityTestData> data, String autor) {
		ListTag pages = new ListTag();
		List<Component> components = new ArrayList<>();
		
		long result = data.stream()
			.filter(d -> d.result)
			.count();
			
		components.add(Component.literal("")
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.1.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.mother", info.motherName))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.price", Integer.toString(info.price)))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.date", info.date.format(DATE_FORMAT)))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.1.number_of_target", Integer.toString(data.size())))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.1.result",
	    				result != 0 ? Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.1.result.known").getString()
	    						: Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.1.result.unknown").getString())));
			
		data.forEach(d ->
			components.add((Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
		    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.id", Integer.toString(d.id)))
		    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.name", d.name))
		    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.result",
		    				d.result ? Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.result.positive").getString()
		    						: Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.result.negative").getString())))
		);

		components.forEach(c -> pages.add(StringTag.valueOf(Component.Serializer.toJson(c))));	
	    ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
	    CompoundTag tag = book.getOrCreateTag();
	    tag.put("pages", pages);
	    tag.putString("title", Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.title").getString());
	    tag.putString("author", autor); 
		return book;
	}

    public static record PrenatalCheckUpInfo(
    		String motherName,
    		int price,
    		LocalDateTime date) {}
    
    public static record PrenatalRegularCheckUpData(
			PregnancyPhase currentPregnancyPhase,
			PregnancyPhase lastPregnancyPhase,
			int pregnancyHealth,
			int numOfBabies,
			int daysPassed,
			int daysToNextPhase,
			int daysToGiveBirth) {}
    
	public static record PrenatalUltrasoundScanData(
			Species motherSpecies,
			Womb babiesInsideWomb) {}
    
	public static record PrenatalPaternityTestData(
			int id,
			String name,
			boolean result) {}
}
