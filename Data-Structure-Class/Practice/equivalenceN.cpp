#include <iostream>
#include <cstdio>
#include <vector>
#include <time.h>
//O(n+m) , Use Stack,Vector DFS
using namespace std;

int n, m;
vector<int> MAP[1000];
int Visited[1000];
int Stack[1000];
int LastStart;
int SP;

void Push(int x) {
	Stack[SP++] = x;
}
int Pop() {
	return Stack[--SP];
}
int isEmpty() {
	return (SP == 0);
}
void SetLink(int x, int y) { // 연결~
	MAP[x].push_back(y); // 벡터 x에 y넣음
	return;
}
int LastForward[1000];
int NextForward(int x) { // x노드와 연결된 노드 찾음 x벡터에서~
	LastForward[x]++; // LastForward는 -1부터 시작하므로 0부터~
	if (LastForward[x] < MAP[x].size()) // size는 연결된 수니까
		return MAP[x][LastForward[x]]; // 벡터랑 배열 리턴사용법은 같음. 
	else
		return -1;


}
int isMarked(int x) {
	return Visited[x];
}
void Mark(int x) {
	Visited[x] = 1;
}
int NextStart() {
	LastStart++;
	while (LastStart <= n) {
		if (Visited[LastStart] == 0) // 1부터 올라가서 방문안한 노드 탐색
			return LastStart;
		else
			LastStart++;
	}
	return -1;
}
int main() {

	int i, x, y, cur, s;
	scanf("%d %d", &n, &m);
	for (int i = 0; i < 1000; i++)LastForward[i] = -1; //벡터의 시작은 0이므로 -1로 만들어줌
	for (i = 0; i < m; i++) {
		scanf("%d %d", &x, &y);
		SetLink(x, y);
		SetLink(y, x);
	}

	while ((cur = NextStart()) != -1) { // 방문하지 않은 노드 탐색
		printf("%d", cur); // 방문안한 노드가 현재노드니까 출력
		Mark(cur); // 방문했다고 마크
		while (1) {
			if ((s = NextForward(cur)) != -1) { // 노드 기준으로 연결된 노드 탐색
				if (isMarked(s) == 0) { // 그 연결된 노드를 방문했는가?
					printf(" %d", s); Mark(s); // 방문안했으면 출력, 방문했다고 마크
					Push(cur); // 스택에 넣기
					cur = s; // 방문한 노드를 다시 현재노드로
				}
				else {

				}
			}
			else { // 연결된 노드가 없다
				if (isEmpty() == 1) // 스택도 비었나?
					break; // 비었으면 break;
				else
					cur = Pop(); // 스택에서 팝 (이전 노드로 이동)
			}
		}
		printf("\n");
	}

	return 0;
} 

/*
10 7
1 3
3 9
6 2
5 10
7 3
4 9
8 10





*/
