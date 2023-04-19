#include <iostream>
#include <cstdio>
using namespace std;

int* A;
int N;
int Search(int x, int* A) {
	int m, n,r;
	m = 0; n = N-1;
	r = (m + n) / 2;
	while (true) {
		r = (m + n) / 2;
		if (m == r)break;
		if (A[r] == x)return r;
		else if (A[r] > x) {
			n = r;
		}
		else {
			m = r;
		}
	}
	if (A[m] == x)return m;
	else if (A[n] == x)return n;
	return -1;
}
int SearchRecur(int x, int m, int n) {
	int r = (m + n) / 2;
	if (r == m && A[r] != x) {
		if (A[n] == x)return n;
		return -1;
	}
	if (A[r] == x)return r;
	else if (A[r] > x) {
		SearchRecur(x, m, r);
	}
	else {

		SearchRecur(x, r, n);
	}

}

int main() {
	cin >> N;
	A = new int[N];
	for (int i = 0; i < N; i++) {
		cin >> A[i]; //단, 올림차순해서
	}
	int c;
	int result;
	while (true) {
		cin >> c;
		if (c == -1)
			break;
		else {
			if ((result = SearchRecur(c, 0,N-1)) == -1)
				cout << "can't find" << endl;
			else
				cout << c << " fount at " << result << endl;
		}
	}

}