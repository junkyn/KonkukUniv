#include <iostream>
#include <cstdio>
#include <vector>
#include <time.h>
//O(n) , Use Stack,Vector DFS
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
void SetLink(int x, int y) { // ����~
	MAP[x].push_back(y); // ���� x�� y����
	return;
}
int LastForward[1000];
int NextForward(int x) { // x���� ����� ��� ã�� x���Ϳ���~
	LastForward[x]++; // LastForward�� -1���� �����ϹǷ� 0����~
	if (LastForward[x] < MAP[x].size()) // size�� ����� ���ϱ�
		return MAP[x][LastForward[x]]; // ���Ͷ� �迭 ���ϻ����� ����. 
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
		if (Visited[LastStart] == 0) // 1���� �ö󰡼� �湮���� ��� Ž��
			return LastStart;
		else
			LastStart++;
	}
	return -1;
}
int main() {

	int i, x, y, cur, s;
	scanf("%d %d", &n, &m);
	for (int i = 0; i < 1000; i++)LastForward[i] = -1; //������ ������ 0�̹Ƿ� -1�� �������
	for (i = 0; i < m; i++) {
		scanf("%d %d", &x, &y);
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