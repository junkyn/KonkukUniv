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
void SetLink(int x, int y) { // ?���?~
	MAP[x][y] = 1;
	return;
}
int LastForward[1000];
int NextForward(int x) { // x���� ����� ��� ã�µ� 1���� ������ ã��
	LastForward[x]++; // �ʱⰪ 0�ε� 1���ʹϱ� �ϳ� �����ϸ鼭 ����
	while (LastForward[x] <= n) { // �ִ񰪺��� ������~
		if (MAP[x][LastForward[x]] == 1) // x�� �̰� �����ֳ���?
			return LastForward[x]; //�� �� �ֳ�
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
	while ((cur = NextStart()) != -1) { // �湮���� ���� ��� Ž��, ó�� laststart�� 0
		printf("%d", cur); // �湮���� ��尡 ������ϱ� ���
		Mark(cur); // �湮�ߴٰ� ��ũ
		while (1) {
			if ((s = NextForward(cur)) != -1) { // ?��?�� 기�???���? ?��결된 ?��?�� ?��?��
				if (isMarked(s) == 0) { // �? ?��결된 ?��?���? 방문?��?���??
					printf(" %d", s); Mark(s); // 방문?��?��?���? 출력, 방문?��?���? 마크
					Push(cur); // ?��?��?�� ?���?
					cur = s; // 방문?�� ?��?���? ?��?�� ?��?��?��?���?
				}
				else {

				}
			}
			else { // ?��결된 ?��?���? ?��?��
				if (isEmpty() == 1) // ?��?��?�� 비었?��?
					break; // 비었?���? break;
				else
					cur = Pop(); // ?��?��?��?�� ?�� (?��?�� ?��?���? ?��?��)
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