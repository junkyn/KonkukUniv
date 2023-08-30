#include <iostream>
#include <cstdio>
using namespace std;

int* A; // 배열
int N; // 최대 인덱스
int Search(int x, int* A) { // 
	int m, n,r; 
	m = 0; n = N-1; //m = 시작인덱스, n=끝 인덱스 
	r = (m + n) / 2; // r은 중간값 
	while (true) { 
		r = (m + n) / 2;
		if (m == r)break; // r이 m과 같으면 (m+n/2 = m, 즉 마지막까지 탐색) break
		if (A[r] == x)return r; // 검색 
		else if (A[r] > x) { // x가 작으면 A[r]왼쪽에 있는거니까 n=r로, 즉 절반기준 왼쪽에서 검색
			n = r;
		}
		else { // 반대
			m = r;
		}
	}
	if (A[m] == x)return m; // 마지막 탐색 후 m과 n위치 값 검사
	else if (A[n] == x)return n;
	return -1; // 검사에 안걸리면 없는거니까 검색 실패
}
int SearchRecur(int x, int m, int n) {
	int r = (m + n) / 2;
	if (r == m && A[r] != x) { // 위에서 했던걸 여기선 한번에 
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