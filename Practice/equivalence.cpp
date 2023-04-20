#include <iostream>
#include <cstdio>

//O(n^2) , Use Stack, DFS
using namespace std;

int n,m; 
int MAP[1000][1000]; // 길있으면 1 없으면 0
int Visited[1000]; // 방문했으면 1 안햇으면 0
int Stack[1000]; // 방문한거 여기다 넣음
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
	MAP[x][y] = 1;
	return;
}
int LastForward[1000];
int NextForward(int x) { // x노드와 연결된 노드 찾는데 1부터 찬찬히 찾음
	LastForward[x]++; // 초기값 0인데 1부터니까 하나 증가하면서 시작
	while (LastForward[x] <= n) { // 최댓값보다 작으면~
		if (MAP[x][LastForward[x]] == 1) // x가 이걸 갈수있나요?
			return LastForward[x]; //갈 수 있네
		else
			LastForward[x]++; // 못가네 다음거 검색 -> 결국 1부터 n까지 본다는거
		
	}
	return -1; // 길이 없어요


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
	int i,x,y,cur,s;
	scanf("%d %d", &n, &m);
	for (i = 0; i < m; i++) {
		scanf("%d %d" ,&x,&y);
		SetLink(x, y);
		SetLink(y, x);
	}
	while ((cur = NextStart()) != -1) { // 방문하지 않은 노드 탐색, 처음 laststart는 0
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