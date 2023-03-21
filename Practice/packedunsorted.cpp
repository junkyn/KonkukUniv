#include <iostream>
#include <cstdio>
using namespace std;

int A[100];
int S, loc;

int srch(int x) {
	int i;
	for (i = 0; i < S; i++) {
		if (A[i] == x)
			break;
	}
	if (i == S)
		return -1;
	else {
		loc = i;
		return 1;
	}
}
void dlte(int x) {
	A[loc] = A[S - 1];
	S--;
	return;
}

void insrt(int x) {
	A[S++] = x;
}
int main() {
	char c;
	int x;
	while (true) {
		for (int i = 0; i < S; i++)
			printf("%3d   ", i);
		cout << endl;
		for (int i = 0; i < S; i++)
			printf("%3d   ", A[i]);
		printf("\n");
		cin >>c;
		if (c == 's') {
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
			cout << "Delete\n" << endl;
		}
		else {
			cout << "???\n";
		}
	}
	return 0;
}