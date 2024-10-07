function findMedianSortedArrays(nums1: number[], nums2: number[]): number {
    // nums1 debe ser el arreglo más pequeño
    if (nums1.length > nums2.length) {
        [nums1, nums2] = [nums2, nums1];
    }

    let m = nums1.length;
    let n = nums2.length;
    let totalLen = m + n;
    let halfLen = Math.floor(totalLen / 2);

    let left = 0;
    let right = m;

    while (left <= right) {
        let partition1 = Math.floor((left + right) / 2);
        let partition2 = halfLen - partition1;

        let maxLeft1 = (partition1 === 0) ? -Infinity : nums1[partition1 - 1];
        let minRight1 = (partition1 === m) ? Infinity : nums1[partition1];

        let maxLeft2 = (partition2 === 0) ? -Infinity : nums2[partition2 - 1];
        let minRight2 = (partition2 === n) ? Infinity : nums2[partition2];

        if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
            // Si la longitud total es impar
            if (totalLen % 2 === 1) {
                return Math.min(minRight1, minRight2);
            } else {
                return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2;
            }
        } else if (maxLeft1 > minRight2) {
            right = partition1 - 1;  // mucho a la derecha en nums1, mover hacia la izquierda
        } else {
            left = partition1 + 1;  // mucho a la izquierda en nums1, mover hacia la derecha
        }
    }

    throw new Error("Input arrays are not sorted properly.");
}

console.log(findMedianSortedArrays([1, 3], [2]));  // 2.0
console.log(findMedianSortedArrays([1, 2], [3, 4]));  // 2.5