#include <iostream>
#include <cstdio>

//O(n^2) , Use Stack, DFS
using namespace std;

int n,m; 
int MAP[1000][1000];
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
void SetLink(int x, int y) { // ����~
	MAP[x][y] = 1;
	return;
}
int LastForward[1000];
int NextForward(int x) { // x���� ����� ��� ã�µ� 1���� ������ ã��
	LastForward[x]++;
	while (LastForward[x] <= n) {
		if (MAP[x][LastForward[x]] == 1) 
			return LastForward[x];
		else
			LastForward[x]++;
		
	}
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
		if (Visited[LastStart] == 0) // 1���� �ö󰡼� �湮���� ��� Ž��
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
	while ((cur = NextStart()) != -1) { // �湮���� ���� ��� Ž��
		printf("%d", cur); // �湮���� ��尡 ������ϱ� ���
		Mark(cur); // �湮�ߴٰ� ��ũ
		while (1) {
			if ((s = NextForward(cur)) != -1) { // ��� �������� ����� ��� Ž��
				if (isMarked(s) == 0) { // �� ����� ��带 �湮�ߴ°�?
					printf(" %d", s); Mark(s); // �湮�������� ���, �湮�ߴٰ� ��ũ
					Push(cur); // ���ÿ� �ֱ�
					cur = s; // �湮�� ��带 �ٽ� �������
				}
				else {

				}
			}
			else { // ����� ��尡 ����
				if (isEmpty() == 1) // ���õ� �����?
					break; // ������� break;
				else
					cur = Pop(); // ���ÿ��� �� (���� ���� �̵�)
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