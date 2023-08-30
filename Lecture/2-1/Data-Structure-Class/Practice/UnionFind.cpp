#include <iostream>
#include <cstdio>

using namespace std;

int n; // 그룹의 크기를 나타내는 변수

int A[1000]; // 그룹의 구성원을 나타내는 배열

// 구성원 a가 속한 그룹을 찾는 함수
int Find(int a) {
	int r, q;
	int p = a;

	// 그룹의 부모 노드를 찾을 때까지 반복
	while (A[p] != 0) {
		p = A[p];
	}

	r = p;
	p = a;

	// 그룹의 부모 노드를 찾아서 모든 구성원들의 부모 노드를 업데이트
	while (A[p] != 0) {
		q = p;
		p = A[p];
		A[q] = r;
	}

	return p;
}

// 두 그룹을 합치는 함수
void Union(int a, int b) {
	int p, q;
	p = Find(a); // 그룹 a의 부모 노드를 찾음
	q = Find(b); // 그룹 b의 부모 노드를 찾음

	if (p != q)
		A[q] = p; // 두 그룹을 합치기 위해 b의 부모를 a로 변경

	return;
}

// 그룹 구성원을 출력하는 함수
void Print() {
	int i;
	for (i = 1; i <= n; i++)
		printf("%4d", A[i]);
	printf("\n");
	return;
}

int main() {
	char c;
	int a, b;

	printf("Size? "); // 그룹의 크기를 입력받음
	scanf("%d", &n);

	while (true) {
		Print(); // 현재 그룹 구성원 출력

		scanf(" %c", &c); // 사용자로부터 명령 입력 받음

		if (c == 'u') { // 합치기 명령인 경우
			scanf("%d %d", &a, &b); // 합칠 두 그룹의 번호를 입력받음
			Union(a, b); // 두 그룹을 합침
		}
		else if (c == 'f') { // 찾기 명령인 경우
			scanf("%d", &a); // 그룹에 속한 구성원의 번호를 입력받음
			printf("%d belongs to group %d\n", a, Find(a)); // 구성원이 속한 그룹 출력
		}
		else if (c == 'q') { // 종료 명령인 경우
			break; // 반복문 종료
		}
		else {
			printf("???\n"); // 잘못된 명령인 경우
		}
	}

	return 0;
}
