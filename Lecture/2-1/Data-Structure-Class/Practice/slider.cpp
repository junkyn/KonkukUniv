#include <iostream>
using namespace std;
int main() {
	int size;
	cin >> size;
	int *A, *B;
	A = new int[size];
	B = new int[size];
	for (int i = 0; i < size; i++) {
		cin >> A[i];
	}
	for (int i = 0; i < size; i++) {
		cin >> B[i];
	}
	int answer,temp;
	temp = 0;
	int least = 0;
	int leastb = 0;
	cin >> answer;
	for (int i = 0; i < size-1; i++) {
		least = i;
		leastb = i;
		for (int j = i+1; j < size; j++) {
			if (A[j] < A[least])
				least = j;
			if (B[j] < B[leastb])
				leastb = j;
		}
		if (i != least) {
			temp = A[i];
			A[i] = A[least];
			A[least] = temp;
		}
		if (i != leastb) {
			temp = B[i];
			B[i] = B[leastb];
			B[leastb] = temp;
		}
	}
	for (int i = 0; i < size; i++) {
		cout << A[i] << " ";
	}cout << endl;

	for (int i = 0; i < size; i++) {
		cout << B[i] << " ";
	}cout << endl;
	int idxA, idxB;
	idxA = 0; idxB = size - 1;
	int count = 0;
	while (count < size) {
		if (A[idxA] + B[idxB] > answer) {
			idxB--;
			count++;
		}
		else if (A[idxA] + B[idxB] < answer) {
			idxA++;
			count++;
		}
		else if (A[idxA] + B[idxB] == answer) {
			cout << endl << idxA << " " << idxB << endl;
			cout << count + 1 << "È¸";
			break;
		}
	}
	if (count == size) {
		cout << "¾È³ª¿È";
	}

}