#include <iostream>
using namespace std;

/*
void swapArray(int(&arr1) [5], int(&arr2)[5]) {
	int tmp;
	for (int i = 0; i < 5; i++) {
		tmp = arr1[i]; arr1[i] = arr2[i]; arr2[i] = tmp;
	}
}
*/
void swapArray(int a[], int b[]) {
	int tmp;
	for (int i = 0; i < 5; i++) {
		tmp = a[i]; a[i] = b[i]; b[i] = tmp;
	}
}
int main() {
	int arr1[5], arr2[5];

	cout << "ù��° �迭�� ������ �Է��ϼ���: ";
	for (int i = 0; i < 5; i++) {
		cin >> arr1[i];
	}
	cout << "�ι�° �迭�� ������ �Է��ϼ���: ";
	for (int i = 0; i < 5; i++) {
		cin >> arr2[i];
	}
	swapArray(arr1, arr2);
	cout << "\nAfter - Swapping"<<endl;
	cout << "\nù��° �迭: ";
	for(int i = 0; i <5;i++)
		cout << arr1[i]<<" ";
	cout << "\n�ι�° �迭: ";
	
	for (int i = 0; i < 5; i++)
		cout << arr2[i]<<" ";
	


}