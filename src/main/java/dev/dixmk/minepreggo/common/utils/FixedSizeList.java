package dev.dixmk.minepreggo.common.utils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.index.qual.NonNegative;

public class FixedSizeList<T> extends AbstractList<T> {
    private final List<T> internalList;
    private final int maxSize;

    public FixedSizeList(@NonNegative int maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException("Maximum size must be non-negative");
        }
        this.maxSize = maxSize;
        this.internalList = new ArrayList<>(maxSize);
    }

    public FixedSizeList(@NonNegative int maxSize, List<T> initialElements) {
		this(maxSize);
		if (initialElements.size() > maxSize) {
			throw new IllegalArgumentException("Initial elements exceed maximum size");
		}
		this.internalList.addAll(initialElements);
	}
    
    @Override
    public boolean add(T element) {
        if (internalList.size() >= maxSize) {
            return false;
        }
        return internalList.add(element);
    }

    @Override
    public boolean remove(Object o) {
        return internalList.remove(o);
    }

    @Override
    public T get(int index) {
        return internalList.get(index);
    }

    @Override
    public int size() {
        return internalList.size();
    }
    
    public int maxSize() {
        return maxSize;
    }
    
    public boolean isFull() {
		return internalList.size() >= maxSize;
	}
}