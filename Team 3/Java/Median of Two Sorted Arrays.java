public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));  // 2.0
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));  // 2.5
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // nums1 debe ser el arreglo más pequeño
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLen = m + n;
        int halfLen = totalLen / 2;

        int left = 0, right = m;

        while (left <= right) {
            int partition1 = (left + right) / 2;
            int partition2 = halfLen - partition1;

            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // Si la longitud total es impar
                if (totalLen % 2 == 1) {
                    return Math.min(minRight1, minRight2);
                } else {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
            } else if (maxLeft1 > minRight2) {
                right = partition1 - 1;  // mucho a la derecha en nums1, mover hacia la izquierda
            } else {
                left = partition1 + 1;  // mucho a la izquierda en nums1, mover hacia la derecha
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }
}