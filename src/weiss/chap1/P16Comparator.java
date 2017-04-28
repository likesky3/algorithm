package weiss.chap1;

import java.util.Arrays;
import java.util.Comparator;

public class P16Comparator {
	public static <AnyType> AnyType findMax(AnyType[] array, Comparator<? super AnyType> cmp) {
		int maxIndex = 0;
		for (int i = 1; i < array.length; i++) {
			if (cmp.compare(array[i], array[maxIndex]) > 0) {
				maxIndex = i;
			}
		}
		return array[maxIndex];
	}
	
	public static void main(String[] args) {
		Rectangle[] rectangles = new Rectangle[10];
		for (int i = 0; i < rectangles.length; i++) {
			rectangles[i] = new Rectangle((int)(Math.random() * 100) + 1, (int)(Math.random() * 100) + 1);
		}
		Rectangle maxAreaRect = findMax(rectangles, new AreaComparator());
		Rectangle maxPerimeterRect = findMax(rectangles, new PerimeterComparator());
		Arrays.sort(rectangles, new AreaComparator());
		System.out.println(maxAreaRect == rectangles[rectangles.length - 1]);
		Arrays.sort(rectangles, new PerimeterComparator());
		System.out.println(maxPerimeterRect == rectangles[rectangles.length - 1]);
	}
}

class Rectangle {
	int length;
	int width;
	public Rectangle(int x, int y) {
		length = x;
		width = y;
	}
	
	@Override
	public String toString() {
		return "width=" + width + ", length=" + length;
 	}
}
class AreaComparator implements Comparator<Rectangle> {

	@Override
	public int compare(Rectangle o1, Rectangle o2) {
		int area1 = o1.length * o1.width;
		int area2 = o2.length * o2.width;
		if (area1 == area2) {
			return 0;
		}
		return area1 < area2 ? -1 : 1;
	} 
}

class PerimeterComparator implements Comparator<Rectangle> {

	@Override
	public int compare(Rectangle o1, Rectangle o2) {
		int halfPerimeter1 = o1.length + o1.width;
		int halfPerimeter2 = o2.length + o2.width;
		if (halfPerimeter1 == halfPerimeter2) {
			return 0;
		}
		return halfPerimeter1 < halfPerimeter2 ? -1 : 1;
	} 
}
