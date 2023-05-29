#include <iostream>
#include <cstdio>
#include <time.h>

//O(n^2) , Use Stack, DFS
using namespace std;

int n,m; 
int MAP[1000][1000]; // �������� 1 ������ 0
int Visited[1000]; // �湮������ 1 �������� 0
int Stack[1000]; // �湮�Ѱ� ����� ����
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
<<<<<<< Updated upstream
int NextForward(int x) { // x���� ����� ��� ã�µ� 1���� ������ ã��
	LastForward[x]++; // �ʱⰪ 0�ε� 1���ʹϱ� �ϳ� �����ϸ鼭 ����
	while (LastForward[x] <= n) { // �ִ񰪺��� ������~
		if (MAP[x][LastForward[x]] == 1) // x�� �̰� �����ֳ���?
			return LastForward[x]; //�� �� �ֳ�
=======
int NextForward(int x) { // x노드와 연결된 노드 찾는데 1부터 찬찬히 찾음
	LastForward[x]++;
	while (LastForward[x] <= n) {
		if (MAP[x][LastForward[x]] == 1) 
			return LastForward[x];
>>>>>>> Stashed changes
		else
			LastForward[x]++; // ������ ������ �˻� -> �ᱹ 1���� n���� ���ٴ°�
		
	}
	return -1; // ���� �����


}
int isMarked(int x) {
	return Visited[x]; 
}
void Mark(int x) {
	Visited[x] = 1;
}
int NextStart() {
	LastStart++;
<<<<<<< Updated upstream
	while (LastStart <= n) { 
		if (Visited[LastStart] == 0) // 1���� �ö󰡼� �湮���� ��� Ž��
=======
	while (LastStart <= n) {
		if (Visited[LastStart] == 0) // 1부터 올라가서 방문안한 노드 탐색
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	while ((cur = NextStart()) != -1) { // �湮���� ���� ��� Ž��, ó�� laststart�� 0
		printf("%d", cur); // �湮���� ��尡 ������ϱ� ���
		Mark(cur); // �湮�ߴٰ� ��ũ
=======

	while ((cur = NextStart()) != -1) { // 방문하지 않은 노드 탐색
		printf("%d", cur); // 방문안한 노드가 현재노드니까 출력
		Mark(cur); // 방문했다고 마크
>>>>>>> Stashed changes
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