#include <stdio.h>
#include <stdlib.h>

int sort(int a[], int n) {
	int m;
	int* b = (int*)malloc(n * sizeof(int));

	//클론 배열 생성
	if (n <= 1) return 0;
	for (int i = 0; i < n; i++) {
		b[i] = a[i];
	}
	m = n / 2;
	//배열을 반으로 쪼갠 후 재귀를 통해 정렬
	sort(b, m); sort(b + m, n - m);

	int count, ac, bc;
	count = ac =0;
	bc = m;

	//합병
	while (count < n) {
		if (ac == m) a[count++] = b[bc++];
		else if (bc == n)a[count++] = b[ac++];
		else {
			if (b[ac] < b[bc]) {
				a[count++] = b[ac++];
			}
			else {
				a[count++] = b[bc++];
			}
		}

	}
	return 0;

}
int main() {
	int array[] = { 2,8,10,1,3,12,7,4,6,5 };
	sort(array, 10);
	for (int i = 0; i < 10; i++) {
		printf("%d ", array[i]);
	}
}