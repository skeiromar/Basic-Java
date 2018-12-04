import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class MySorts {
	
	public static void swap(List<Integer> arr, int elem1, int elem2) {
		int temp = arr.get(elem1);
		arr.set(elem1, arr.get(elem2));
		arr.set(elem2, temp);
	}
	public static void bubbleSort(List<Integer> arr) {
		boolean sorted = false;
		for (int pass = 0; pass < arr.size()-1 && !sorted; pass++) {
			sorted = true;
			for ( int i = 0; i < arr.size() - pass - 1; i++) {
				if (arr.get(i) > arr.get(i + 1)) {
					swap(arr, i, i+1);
					sorted = false;
				}
			}
		}
	}
	public static void insertionSort(List<Integer> arr) {
		for (int i = 1; i < arr.size(); i++) {
			int location = i - 1;
			int key = arr.get(i);
			while(location >= 0 && arr.get(location) > key) {
				swap(arr, location, location-- + 1);				
			}
		}
	}
	public static int partition(List<Integer> arr, int first, int last) {
		// random pivoting
		Random r = new Random();
		int r1 = r.nextInt(last - first) + first;
		swap(arr, r1, last);
		
		int pivot = arr.get(last);
		// partitionIndex = start, i = 0 -> last - 1 (check), 
		int partitionIndex = first;
		for (int i = first; i < last; i++) {
			//System.out.println(arr + " 1 partitionIndex: " + partitionIndex + " i: " + i); 
			if (arr.get(i) < pivot) {
				//System.out.println(arr + " partitionIndex: " + partitionIndex + " i: " + i); 
				swap(arr, i, partitionIndex++);
				//System.out.println(arr + " 1 partitionIndex: " + partitionIndex + " i: " + i); 
			}
			//System.out.println(arr + " 2 partitionIndex: " + partitionIndex + " i: " + i); 
		}
		//System.out.println(pivot);
		swap(arr, partitionIndex, last);
		return partitionIndex;
		
	}
	
	public static void quickSort(List<Integer> arr, int first, int last) {
		// get a partition index for the array after partitioning -> recursively call the function on both sides of partition index
		if (first < last) {
			int partitionIndex = partition(arr, first, last);
			System.out.println(partitionIndex);
			quickSort(arr, first, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, last);
		}
	}
	public static void merge(List<Integer> bigArr, List<Integer> leftArr, List<Integer> rightArr) {
		int indB = 0, indL = 0, indR = 0;

		int sizeL = leftArr.size();
		int sizeR = rightArr.size();
		
		while (indL < sizeL && indR < sizeR) {
			if (leftArr.get(indL) < rightArr.get(indR)) {
				bigArr.set(indB++, leftArr.get(indL++));
			} else {
				bigArr.set(indB++, rightArr.get(indR++));
			}
		}
		while (indL < sizeL) {
			bigArr.set(indB++, leftArr.get(indL++));
		}
		while (indR < sizeR) {
			bigArr.set(indB++, rightArr.get(indR++));
		}
	}
	public static void mergeSort(List<Integer> bigArr) {
		if (bigArr.size() <= 1)
			return;
		
		int mid = bigArr.size() / 2;
		List<Integer> leftArr = new ArrayList<>();
		List<Integer> rightArr = new ArrayList<>();
		for (int i = 0; i < bigArr.size(); i++)
			if (i < mid)
				leftArr.add(bigArr.get(i));
			else rightArr.add(bigArr.get(i));
		
		mergeSort(leftArr);
		mergeSort(rightArr);
		merge(bigArr, leftArr, rightArr);
	}
	public static void main(String[] args) {
		Random r = new Random();
		List<Integer> arr = new ArrayList<Integer>();
		for (int i = 10; i > 0; i--) {
			arr.add(i);
		}

		//System.out.println(partition(arr, 0, arr.size() - 1));
		System.out.println(arr);
		quickSort(arr, 0, arr.size() - 1);
		//mergeSort(arr);
		System.out.println(arr);
	}

}
