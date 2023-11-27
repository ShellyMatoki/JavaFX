package model_project;

import java.io.Serializable;
import java.util.Arrays;

public class Set<T> implements Serializable {

	private final int ENLARGE_FACTOR = 2;
	private T[] arr;
	private int currentSize;

	public Set() {
		arr = (T[]) new Object[ENLARGE_FACTOR];
		currentSize = 0;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public T get(int index) {
		if (index < arr.length)
			return arr[index];
		return null;
	}

	public int capacity() {
		return arr.length;
	}

	public boolean add(T newValue)throws Exception {
		if (!(equals(newValue))) {
			if (currentSize >= arr.length)
				enlargeArray();
			arr[currentSize] = newValue;
			currentSize++;
			return true;
		} else {
			throw new Exception("cant add object, it already exists");
		}
	}

	public boolean setElementAt(T text, int index) {
		arr[index] = text;
		return true;
	}

	public void removeElementAt(int index) {
		if (index == arr.length - 1) {
			arr = Arrays.copyOf(arr, arr.length - 1);
		} else {
			for (int i = 0; i < arr.length; i++) {
				if (i > index) {
					arr[i - 1] = arr[i];
				}
			}
			arr = Arrays.copyOf(arr, arr.length - 1);
			currentSize--;
		}
	}

	@Override
	public boolean equals(Object other) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				if (arr[i].equals(other))
					return true;
			}
		}
		return false;
	}

	public void enlargeArray() {
		T[] temp = (T[]) new Object[arr.length * ENLARGE_FACTOR];
		for (int i = 0; i < arr.length; i++)
			temp[i] = arr[i];
		arr = temp;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}

	@Override
	public String toString() {
		return "Set [ENLARGE_FACTOR=" + ENLARGE_FACTOR + ", arr=" + Arrays.toString(arr) + ", currentSize="
				+ currentSize + "]";
	}

}
