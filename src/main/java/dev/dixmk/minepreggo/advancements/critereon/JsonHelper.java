package dev.dixmk.minepreggo.advancements.critereon;

import java.util.EnumSet;
import java.util.Set;

import javax.annotation.CheckForNull;

import com.google.gson.JsonObject;

import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckup;

class JsonHelper {

	private JsonHelper() {}

	@CheckForNull
	static Set<Species> parseSpeciesSet(JsonObject json, String key) {
		if (json.has(key) && json.get(key).isJsonArray()) {
			Set<Species> speciesSet = EnumSet.noneOf(Species.class);
			json.get(key).getAsJsonArray().forEach(speciesElem -> {
				try {					
					speciesSet.add(Species.valueOf(speciesElem.getAsString()));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			});
			return speciesSet;
		}
		return null;
	}
	
	@CheckForNull
	static Set<PrenatalCheckup> parsePrenatalCheckupSet(JsonObject json, String key) {
		if (json.has(key) && json.get(key).isJsonArray()) {
			Set<PrenatalCheckup> checkupSet = EnumSet.noneOf(PrenatalCheckup.class);
			json.get(key).getAsJsonArray().forEach(checkupElem -> {
				try {					
					checkupSet.add(PrenatalCheckup.valueOf(checkupElem.getAsString()));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			});
			return checkupSet;
		}
		return null;
	}
}
