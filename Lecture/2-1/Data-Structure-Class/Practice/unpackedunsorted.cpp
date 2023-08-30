#include <iostream>
#include <cstdio>

using namespace std;

int A[100];
int B[100];
int S, loc, flh;

int srch(int x) {
	for (int i = 0; i < S; i++) {
		if (A[i] == x && B[i]==1) {
			loc = i;

			return 1;
		}
	}
	return -1;
}
void dlte(int x) {
	B[loc] = 0;
	A[loc] = flh;
	flh = loc;
}
void insrt(int x) {
	if (flh == -1) {
		A[S] = x;
		B[S++] = 1;
	}
	else {
		loc = flh;
		flh = A[flh];
		A[loc] = x; B[loc] = 1;
	}
}
int main() {
	S = 0; loc = 0;
	char c;
	int x;
	flh = -1;
	while (true) {
		for (int i = 0; i < S; i++)
			printf("%3d   ", i);
		cout << endl;
		for (int i = 0; i < S; i++)
			printf("%3d   ",A[i]);
		cout << endl;
		for (int i = 0; i < S; i++)
			printf("%3d   ", B[i]);
		cout << endl;
		cin >> c;
		if (c == 's'){
			cin>>x;
			if (srch(x) == -1)
				printf("%d Not Found\n", x);
			else
				printf("%d Found at Index %d\n", x, loc);
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
}
