#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
using namespace std;

class edge {
public:
	int a, b, w;
	bool operator < (const edge& k) const {
		return w < k.w;
	}
};

vector<pair<int, int>> AD[1000]; // 인접 리스트를 저장하기 위한 배열 AD
edge ED[1000]; // 간선들을 저장하기 위한 배열 ED
int Used[1000]; // 사용된 간선을 표시하기 위한 배열 Used

int n, m;

int A[1000]; // 서로소 집합 연산을 위한 배열 A

// a의 루트를 찾는 함수
int Find(int a) {
	int r, q;
	int p = a;
	while (A[p] != 0) {
		p = A[p];
	}
	r = p; p = a;
	while (A[p] != 0) {
		q = p;
		p = A[p];
		A[q] = r;
	}
	return p;
}

// a와 b를 합치는 함수
void Union(int a, int b) {
	int p, q;
	p = Find(a); q = Find(b);
	if (p != q)
		A[q] = p;
	return;
}

int Visited[1000]; // 방문 여부를 체크하기 위한 배열 Visited

// 최소 신장 트리를 출력하는 함수
void Print(int nd, int w, int d, int LR) {
	int i, first;
	Visited[nd] = 1;
	if (LR == 1)
		for (i = 0; i < d; i++)
			printf("          "); // 깊이에 따라 들여쓰기 출력

	printf("[%3d, %3d]", w, nd); // 현재 정점과 가중치 출력
	first = 1;
	for (i = 0; i < AD[nd].size(); i++) {
		if (Visited[AD[nd][i].first] == 0) { // 방문하지 않은 인접 정점에 대해
			if (first == 1) {
				Print(AD[nd][i].first, AD[nd][i].second, d + 1, 0); // 왼쪽 자식인 경우
			}
			else {
				Print(AD[nd][i].first, AD[nd][i].second, d + 1, 1); // 오른쪽 자식인 경우
			}
			first = 0;
		}
	}
	if (first == 1) {
		printf("\n"); // 리프 노드인 경우 개행
	}
}

int main() {
	int i, j, a, b, w, sum;
	edge E;
	scanf("%d %d", &n, &m); // 정점의 개수와 간선의 개수 입력받기
	for (i = 1; i <= m; i++) {
		scanf(" %d %d %d", &a, &b, &w); // 간선 정보 입력받기
		E.a = a;
		E.b = b;
		E.w = w;
		ED[i] = E;
	}
	sort(ED + 1, ED + m + 1); // 가중치를 기준으로 간선을 오름차순으로 정렬하기
	for (i = 1; i <= m; i++) {
		if (Find(ED[i].a) != Find(ED[i].b)) { // 두 정점이 서로 다른 집합에 속해있을 경우
			printf("Using Edge #%d, between %d and %d with weight %d\n", i, ED[i].a, ED[i].b, ED[i].w); // 해당 간선 사용
			Used[i] = 1; // 해당 간선 사용 표시
			Union(ED[i].a, ED[i].b); // 두 정점을 합치기
		}
	}
	sum = 0;
	for (i = 1; i <= m; i++) {
		if (Used[i] == 1) {
			sum += ED[i].w; // 사용된 간선의 가중치를 더해 최소 신장 트리의 가중치 합 구하기
		}
	}
	printf("Weight Sum = %d\n", sum); // 최소 신장 트리의 가중치 합 출력
	for (i = 1; i < m; i++) {
		if (Used[i]) {
			AD[ED[i].a].push_back(make_pair(ED[i].b, ED[i].w)); // 최소 신장 트리를 인접 리스트로 표현하기 위해 AD 배열에 정점과 가중치 추가
			AD[ED[i].b].push_back(make_pair(ED[i].a, ED[i].w));
		}

	}
	Print(1, 0, 0, 0); // 최소 신장 트리 출력
	return 0;
}

/*

5 10
1 2 10
1 4 5
1 5 6
2 3 1
2 4 3
2 4 4
3 4 9
3 5 4
3 5 6
4 5 2
*/