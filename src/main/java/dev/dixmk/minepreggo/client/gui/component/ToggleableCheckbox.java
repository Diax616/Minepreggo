package dev.dixmk.minepreggo.client.gui.component;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ToggleableCheckbox extends Checkbox {
	
	private final Optional<List<ToggleableCheckbox>> group;
    private final Optional<Runnable> onSelect;
       
    public ToggleableCheckbox(ToggleableCheckbox.Builder builder) {
    	super(builder.x, builder.y, builder.width, builder.height, builder.label, builder.checked);
    	this.group = Optional.ofNullable(builder.group);
    	this.onSelect = Optional.ofNullable(builder.onSelect);
    }
       
	@Override
    public void onPress() {
		this.group.ifPresent(list -> {
	        for (ToggleableCheckbox cb : list) {
	            if (cb.selected()) {
	            	cb.selected = false;
	            }
	        }
		});
               
        this.selected = true;               
        onSelect.ifPresent(r -> r.run());     
    }

	public static ToggleableCheckbox.Builder builder(int x, int y, int width, int height, Component label, boolean checked) {
		return new ToggleableCheckbox.Builder(x, y, width, height, label, checked);
	}
    
	@OnlyIn(Dist.CLIENT)
    public static class Builder {
    	private @Nullable List<ToggleableCheckbox> group;
    	private @Nullable Runnable onSelect;
    	private int x;
    	private int y; 
    	private int width; 
    	private int height; 
    	private Component label;
    	private boolean checked;
    	 		
    	protected Builder(int x, int y, int width, int height, Component label, boolean checked) {
    		this.x = x;
    		this.y = y;
    		this.width = width;
    		this.height = height;
    		this.label = label;
    		this.checked = checked;
    	}
    	
    	public Builder group(@Nullable List<ToggleableCheckbox> group) {
    		this.group = group;
    		return this;
    	}
    		
    	public Builder onSelect(@Nullable Runnable onSelect) {
    		this.onSelect = onSelect;
    		return this;
    	}
    	
    	public ToggleableCheckbox build() {
    		return new ToggleableCheckbox(this);
    	} 	
    }
}