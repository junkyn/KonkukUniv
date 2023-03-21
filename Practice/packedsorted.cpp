#include <iostream>
#include <cstdio>
using namespace std;

int A[100];
int S, lef,rig;

int srch(int x) {
	int s, e, m;
	s = 0; e = S - 1;
	while(s<=e){
		m = (s + e) / 2;
		if (A[m] == x) {
			lef = rig = m;
			return 1;
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
	for (int i = lef;i < S - 1; i++)
		A[i] = A[i+1];
	A[rig] = x;
	S--;
}

void insrt(int x) {
	int i;

	for (i = S - 1; i >= rig; i--)
		A[i + 1] = A[i];
	A[rig] = x;
	S++;
}
int main() {
	char c;
	int x;
	S = 0;
	while (true) {
		for (int i = 0; i < S; i++)
			printf("%3d   ", i);
		cout << endl;
		for (int i = 0; i < S; i++)
			printf("%3d   ", A[i]);
		printf("\n");
		cin >> c;
		if (c == 's') {
			cin >> x;
			if (srch(x) == -1)
				printf("%d Not Found Should be between\n", x,lef,rig);
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
			cout << "Delete\n" << endl;
		}
		else {
			cout << "???\n";
		}
	}
	return 0;
}