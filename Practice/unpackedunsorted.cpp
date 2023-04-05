#include <iostream>
#include <cstdio>
using namespace std;

int A[100], B[100];
int S, lef, rig;

int srch(int x) {
	int s, e, m;
	s = 0; e = S - 1;
	while (s <= e) {
		m = (s + e) / 2;
		if (A[m] == x) {
			lef = rig = m;
			if (B[m] == 1)
				return 1;
			else
				return -1;
		}
		else if (A[m] > x) {
			e = m - 1;
		}
		else {
			s = m + 1;
		}
	}
	lef = e; rig = s;
	return -1;
}
void dlte(int x) {
	B[lef] = 0;
}

void insrt(int x) {
	int i,j;
	if (lef == rig) {
		B[lef] = 1;
	}
	else if (lef == -1) {
		i = rig;
		while (B[i] == 1 && i < S)
			i++;
		if (i == S) {
			for (j = S - 1; j >= rig; j--) {
				A[j + 1] = A[j]; B[j + 1] = B[j];
			}
			A[rig] = x; B[rig] = 1;
			S++;
		}
		else {
			for (j = i - 1; j >= rig; j--) {
				A[j + 1] = A[j]; B[j + 1] = B[j];
			}
			A[rig] = x; B[rig] = 1;
		}
	}
	else if (rig == S) {
		i = lef;
		while (B[i] == 1 && i >= 0)
			i--;
		if (i == -1) {
			A[S] = x; B[S] = 1; S++;
		}
		else {
			for (j = i; j <rig; j++) {
				A[j] = A[j+1]; B[j] = B[j+1];
			}
			A[rig-1] = x; B[rig-1] = 1;
		}
	}
	else {
		i = lef;
		while (B[i] == 1 && i >= 0)
			i--;
		if (i != -1) {
			for (j = i; j < lef; j++) {
				A[j] = A[j + 1]; B[j] = B[j + 1];
			}
			A[lef] = x; B[lef] = 1;
		}
		else {
			i = rig;
			while (B[i] == 1 && i < S)
				i++;
			if (i != S) {
				for (j = i - 1; j >= rig; j--) {
					A[j + 1] = A[j]; B[j + 1] = B[j];
				}
				A[rig] = x; B[rig] = 1;
			}
			else {
				for (j = i - 1; j >= rig; j--) {
					A[j + 1] = A[j]; B[j + 1] = B[j];
				}
				A[rig] = x; B[rig] = 1;
				S++;
			}
		}
	}
}
int main() {
	char c;
	int x;
	S = 0	;
	while (true) {
		for (int i = 0; i < S; i++)
			printf("%3d   ", i);
		cout << endl;
		for (int i = 0; i < S; i++)
			printf("%3d   ", A[i]);
		printf("\n");
		for (int i = 0; i < S; i++)
			printf("%3d   ", B[i]);
		printf("\n");
		cin >> c;
		if (c == 's') {
			cin >> x;
			if (srch(x) == -1 && lef !=rig)
				printf("%d Not Found Should be between %d and %d\n", x, lef, rig);
			else if (srch(x) == -1 && lef == rig) {
				printf("%d Not Found, Stored as deleted value at %d\n", x, lef);
			}
			else
				printf("%d Found at Index %d\n", x, lef);
		}
		else if (c == 'i') {
			cin >> x;
			if (srch(x) == -1) {
				insrt(x);
			}
		}
		else if (c == 'd') {
			cin >> x;
			if (srch(x) == 1) {
				dlte(x);
			}
		}
		else if (c == 'q') {
			break;
		}
		else {
			cout << "???\n";
		}
	}
	return 0;
}